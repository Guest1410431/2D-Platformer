package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import Menu.Popup;
import Utilities.Assets;
import World.Block;
import World.Map;

public class Player
{
	private final float MAX_SPEED = 3f;
	private final float ACCEL_SPEED = 0.3f;
	private final float DECEL_SPEED = 0.2f;
	private final float MAX_FALL_SPEED = 8f;
	private final float JUMP_SPEED = 7f;
	private final float JUMP_DECEL = 0.4f;
	private final float IDLE_SPEED = 1.2f;	
	
	private final int SCREEN_MID_WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
	private final int SCREEN_MID_HEIGHT= (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
	private final int RENDER_WINDOW_WIDTH = 60;
	private final int RENDER_WINDOW_HEIGHT = 35;
	
	private int IMG_RATIO = 5;
	private int playerWidth;
	private int playerHeight;
	
	private Map map;
	private Block[][] world;	
	
	private float xPos;
	private float yPos;
	private float speedRight;
	private float speedLeft;
	private float speedUp;

	private boolean mapMove;

	private boolean jump;
	private boolean right;
	private boolean left;
	private boolean dir = true;// true if right, false if left - holds last state

	private ArrayList<BufferedImage> anim_right;
	private ArrayList<BufferedImage> anim_left;
	private int anim_frame;
	private int anim_speed;
	private int anim_space;
	private int idle_frame = 1;

	private CopyOnWriteArrayList<Particle> particles;

	private Color playerColor = Popup.getPlayerColor();
	private Color bikeColor = Popup.getBikeColor();

	public Player(int x, int y)
	{
		mapMove = true;

		map = new Map();
		world = map.getWorld();

		xPos = x;
		yPos = y;

		speedRight = 0;
		speedLeft = 0;

		Assets.changeColors(playerColor, bikeColor);

		anim_right = Assets.getRight();
		anim_left = Assets.getLeft();

		anim_frame = 0;
		anim_speed = 0;

		playerWidth = anim_right.get(0).getWidth();
		playerHeight = anim_right.get(0).getHeight();

		particles = new CopyOnWriteArrayList<Particle>();
	}

	public void update()
	{
		map.update();
		
		for(int i=(int) ((xPos/Map.getBlockSize())-(RENDER_WINDOW_WIDTH/2)); i<((xPos/Map.getBlockSize()) + (RENDER_WINDOW_WIDTH/2)); i++)
		{
			for(int h =(int) ((yPos/Map.getBlockSize())-(RENDER_WINDOW_HEIGHT/2)); h<((yPos/Map.getBlockSize()) + (RENDER_WINDOW_HEIGHT/2)); h++)
			{
				if(i >= 0 && i <world.length && h >= 0 && h < world[0].length)
				{
					world[i][h].setOnScreen(true);
				}
			}
		}
		// If the player is close to a wall, set map move to false;
		for (Particle p : particles)
		{
			if (p.opacity <= 0)
			{
				particles.remove(p);
			}
			p.update();
		}

		if (mapMove)
		{
			if (right)
			{
				if (onGround())
				{
					particles.add(new Particle(xPos - ((playerWidth * IMG_RATIO) / 2) + 20, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));

					if (Math.random() > 0.5)
					{
						particles.add(new Particle(xPos + ((playerWidth * IMG_RATIO) / 2) - 60, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));
					}
				}
				dir = true;

				if (!collide())
				{
					if (speedRight < MAX_SPEED)
					{
						speedRight += ACCEL_SPEED;
					}
					else
					{
						speedRight = MAX_SPEED;
					}
					xPos += speedRight;
					map.moveRight(speedRight);
				}
				else
				{
					speedRight = 0;
				}
			}
			else
			{
				if (!collide())
				{
					if (speedRight != 0)
					{
						speedRight -= DECEL_SPEED;

						if (speedRight < 0)
						{
							speedRight = 0;
						}
					}
					xPos += speedRight;
					map.moveRight(speedRight);
				}
				else
				{
					speedRight = 0;
				}
			}
			if (left)
			{
				if (onGround())
				{
					particles.add(new Particle(xPos + ((playerWidth * IMG_RATIO) / 2) - 20, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));

					if (Math.random() > 0.5)
					{
						particles.add(new Particle(xPos - ((playerWidth * IMG_RATIO) / 2) + 60, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));
					}
				}
				dir = false;

				if (!collide())
				{
					if (speedLeft < MAX_SPEED)
					{
						speedLeft += ACCEL_SPEED;
					}
					else
					{
						speedLeft = MAX_SPEED;
					}
					xPos -= speedLeft;
					map.moveLeft(speedLeft);
				}
				else
				{
					speedLeft = 0;
				}
			}
			else
			{
				if (!collide())
				{
					if (speedLeft != 0)
					{
						speedLeft -= DECEL_SPEED;

						if (speedLeft < 0)
						{
							speedLeft = 0;
						}
					}
					xPos -= speedLeft;
					map.moveLeft(speedLeft);
				}
				else
				{
					speedLeft = 0;
				}
			}
			if (!onGround())
			{
				if (speedUp > -MAX_FALL_SPEED)
				{
					speedUp -= JUMP_DECEL;
				}
				if (collisionAbove())
				{
					speedUp = -1;
				}
				map.moveUp(speedUp);
				yPos -= speedUp;
			}
			else if (jump)
			{
				if (onGround())
				{
					speedUp = JUMP_SPEED;
				}
				if (speedUp > -MAX_FALL_SPEED)
				{
					speedUp -= JUMP_DECEL;
				}
				if (speedUp <= -MAX_FALL_SPEED)
				{
					speedUp = -MAX_FALL_SPEED;
				}
				if (collisionAbove())
				{
					speedUp = -1;
				}
				yPos -= speedUp;
				map.moveUp(speedUp);
			}
		}
		else
		{
			if (right)
			{
				if (onGround())
				{
					particles.add(new Particle(xPos - ((playerWidth * IMG_RATIO) / 2) + 20, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));

					if (Math.random() > 0.5)
					{
						particles.add(new Particle(xPos + ((playerWidth * IMG_RATIO) / 2) - 60, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));
					}
				}
				dir = true;

				if (!collide())
				{
					if (speedRight < MAX_SPEED)
					{
						speedRight += ACCEL_SPEED;
					}
					else
					{
						speedRight = MAX_SPEED;
					}
					xPos += speedRight;
				}
				else
				{
					speedRight = 0;
				}
			}
			else
			{
				if (!collide())
				{
					if (speedRight != 0)
					{
						speedRight -= DECEL_SPEED;

						if (speedRight < 0)
						{
							speedRight = 0;
						}
					}
					xPos += speedRight;
				}
				else
				{
					speedRight = 0;
				}
			}
			if (left)
			{
				if (onGround())
				{
					particles.add(new Particle(xPos + ((playerWidth * IMG_RATIO) / 2) - 20, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));

					if (Math.random() > 0.5)
					{
						particles.add(new Particle(xPos - ((playerWidth * IMG_RATIO) / 2) + 60, yPos + ((playerHeight * IMG_RATIO) / 2) - 10));
					}
				}
				dir = false;

				if (!collide())
				{
					if (speedLeft < MAX_SPEED)
					{
						speedLeft += ACCEL_SPEED;
					}
					else
					{
						speedLeft = MAX_SPEED;
					}
					xPos -= speedLeft;
				}
				else
				{
					speedLeft = 0;
				}
			}
			else
			{
				if (!collide())
				{
					if (speedLeft != 0)
					{
						speedLeft -= DECEL_SPEED;

						if (speedLeft < 0)
						{
							speedLeft = 0;
						}
					}
					xPos -= speedLeft;
				}
				else
				{
					speedLeft = 0;
				}
			}
			if (!onGround())
			{
				if (speedUp > -MAX_FALL_SPEED)
				{
					speedUp -= JUMP_DECEL;
				}
				if (collisionAbove())
				{
					speedUp = -1;
				}
				yPos -= speedUp;
			}
			else if (jump)
			{
				if (onGround())
				{
					speedUp = JUMP_SPEED;
				}
				if (speedUp > -MAX_FALL_SPEED)
				{
					speedUp -= JUMP_DECEL;
				}
				if (speedUp <= -MAX_FALL_SPEED)
				{
					speedUp = -MAX_FALL_SPEED;
				}
				if (collisionAbove())
				{
					speedUp = -1;
				}
				yPos -= speedUp;
			}
		}
	}

	public boolean collisionAbove()
	{
		if (right)
		{
			
		}
		if (left)
		{
			
		}
		else
		{
			
		}
		return false;
	}

	public boolean onGround()
	{
		if (right)
		{
			
		}
		if (left)
		{
			
		}
		else
		{
			
		}
		return false;
	}

	public boolean collide()
	{
		if (right)
		{
			
		}
		if (left)
		{
			
		}
		return false;
	}

	public void render(Graphics g)
	{
		if (mapMove)
		{
			if(map != null)
			{
				map.render(g);
			}		
			if (right)
			{
				anim_space = 15;

				g.drawImage(anim_right.get(anim_speed), (int) (SCREEN_MID_WIDTH-((playerWidth*IMG_RATIO)/2)), (int) (SCREEN_MID_HEIGHT)-((playerHeight*IMG_RATIO)/2), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
				anim_frame++;

				if (anim_frame >= anim_space)
				{
					anim_frame = 0;
					anim_speed++;
				}
				if (anim_speed >= anim_right.size() || anim_speed >= anim_left.size())
				{
					anim_speed = 0;
				}
			}
			else if (left)
			{
				anim_space = 15;

				g.drawImage(anim_left.get(anim_speed), (int) (SCREEN_MID_WIDTH-((playerWidth*IMG_RATIO)/2)), (int) (SCREEN_MID_HEIGHT)-((playerHeight*IMG_RATIO)/2), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
				anim_frame++;

				if (anim_frame >= anim_space)
				{
					anim_frame = 0;
					anim_speed++;
				}
				if (anim_speed >= anim_right.size() || anim_speed >= anim_left.size())
				{
					anim_speed = 0;
				}
			}
			else
			{
				anim_space = 30;

				if (dir)
				{
					g.drawImage(anim_right.get(anim_speed), (int) (SCREEN_MID_WIDTH-((playerWidth*IMG_RATIO)/2)), (int) (SCREEN_MID_HEIGHT)-((playerHeight*IMG_RATIO)/2), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
					anim_frame++;

					if (anim_speed > anim_right.size() / 2 || anim_speed > anim_left.size() / 2)
					{
						idle_frame = -1;
					}
					if (anim_speed <= 0)
					{
						idle_frame = 1;
					}
					if (anim_frame >= anim_space)
					{
						anim_frame = 0;
						anim_speed += idle_frame;

						if (!collide())
						{
							xPos += (idle_frame * IDLE_SPEED);
						}
					}
				}
				else
				{
					g.drawImage(anim_left.get(anim_speed), (int) (SCREEN_MID_WIDTH-((playerWidth*IMG_RATIO)/2)), (int) (SCREEN_MID_HEIGHT)-((playerHeight*IMG_RATIO)/2), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
					anim_frame++;

					if (anim_speed > anim_right.size() / 2 || anim_speed > anim_left.size() / 2)
					{
						idle_frame = -1;
					}
					if (anim_speed <= 0)
					{
						idle_frame = 1;
					}
					if (anim_frame >= anim_space)
					{
						anim_frame = 0;
						anim_speed += idle_frame;

						if (!collide())
						{
							xPos += (idle_frame * IDLE_SPEED);
						}
					}
				}
			}
		}
		else
		{
			if (right)
			{
				anim_space = 15;

				g.drawImage(anim_right.get(anim_speed), (int) (xPos - ((playerWidth * IMG_RATIO) / 2)), (int) (yPos - ((playerHeight * IMG_RATIO) / 2)), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
				anim_frame++;

				if (anim_frame >= anim_space)
				{
					anim_frame = 0;
					anim_speed++;
				}
				if (anim_speed >= anim_right.size() || anim_speed >= anim_left.size())
				{
					anim_speed = 0;
				}
			}
			else if (left)
			{
				anim_space = 15;

				g.drawImage(anim_left.get(anim_speed), (int) (xPos - ((playerWidth * IMG_RATIO) / 2)), (int) (yPos - ((playerHeight * IMG_RATIO) / 2)), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
				anim_frame++;

				if (anim_frame >= anim_space)
				{
					anim_frame = 0;
					anim_speed++;
				}
				if (anim_speed >= anim_right.size() || anim_speed >= anim_left.size())
				{
					anim_speed = 0;
				}
			}
			else
			{
				anim_space = 30;

				if (dir)
				{
					g.drawImage(anim_right.get(anim_speed), (int) (xPos - ((playerWidth * IMG_RATIO) / 2)), (int) (yPos - ((playerHeight * IMG_RATIO) / 2)), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
					anim_frame++;

					if (anim_speed > anim_right.size() / 2 || anim_speed > anim_left.size() / 2)
					{
						idle_frame = -1;
					}
					if (anim_speed <= 0)
					{
						idle_frame = 1;
					}
					if (anim_frame >= anim_space)
					{
						anim_frame = 0;
						anim_speed += idle_frame;

						if (!collide())
						{
							xPos += (idle_frame * IDLE_SPEED);
						}
					}
				}
				else
				{
					g.drawImage(anim_left.get(anim_speed), (int) (xPos - ((playerWidth * IMG_RATIO) / 2)), (int) (yPos - ((playerHeight * IMG_RATIO) / 2)), playerWidth * IMG_RATIO, playerHeight * IMG_RATIO, null);
					anim_frame++;

					if (anim_speed > anim_right.size() / 2 || anim_speed > anim_left.size() / 2)
					{
						idle_frame = -1;
					}
					if (anim_speed <= 0)
					{
						idle_frame = 1;
					}
					if (anim_frame >= anim_space)
					{
						anim_frame = 0;
						anim_speed += idle_frame;

						if (!collide())
						{
							xPos += (idle_frame * IDLE_SPEED);
						}
					}
				}
			}
		}
		for (Particle p : particles)
		{
			p.render(g);
		}
	}
	public void setRight(boolean right)
	{
		this.right = right;
	}
	public void setLeft(boolean left)
	{
		this.left = left;
	}
	public void setJump(boolean jump)
	{
		this.jump = jump;
	}
}
