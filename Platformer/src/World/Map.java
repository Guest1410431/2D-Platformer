package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Main;
import Utilities.LoadImageFrom;
import World.Block.BlockType;

public class Map
{
	private static float xOffset;
	private static float yOffset;
	
	private final static int BLOCK_SIZE = 32;
	private static BufferedImage map = null;
	
	private Block[][]world;
	
	public Map()
	{
		map = LoadImageFrom.loadImageFrom(Main.class, "map_1.png");
		world = new Block[map.getWidth()][map.getHeight()];
	}
	public void update()
	{
		for(int i=0; i<map.getHeight(); i++)
		{
			for(int h=0; h<map.getWidth(); h++)
			{
				switch(map.getRGB(h, i) & 0xFFFFFF)
				{
				case 0x000000 : 
					world[h][i] = new Block((h*BLOCK_SIZE)+xOffset, (i*BLOCK_SIZE)+yOffset, BlockType.TILE);
					world[h][i].setSolid(true);
					break;
				case 0xFFFFFF : 
					world[h][i] = new Block((h*BLOCK_SIZE)+xOffset, (i*BLOCK_SIZE)+yOffset, BlockType.AIR);
					world[h][i].setSolid(false);
					break;
				}
			}
		}
	}
	public void render(Graphics g)
	{
		if(world != null)
		{
			for(int i=0; i<map.getHeight(); i++)
			{
				for(int h=0; h<map.getWidth(); h++)
				{
					world[h][i].render(g);
				}
			}
		}
	}
	public static int getBlockSize()
	{
		return BLOCK_SIZE;
	}
	public static BufferedImage getMap()
	{
		return map;
	}
	public void moveRight(float offset)
	{
		xOffset -= offset;
	}
	public void moveLeft(float offset)
	{
		xOffset += offset;
	}
	public void moveUp(float offset)
	{
		yOffset += offset;
	}
	public void moveDown(float offset)
	{
		yOffset -= offset;
	}
	public Block[][] getWorld()
	{
		return world;
	}
}






















