package Entities;

import java.awt.Color;
import java.awt.Graphics;

public class Particle
{
	private final float PARTICLE_GRAVITY = 0.75f;

	public int opacity;
	public float xPos;
	public float yPos;
	public float size;
	
	private int fadeSpeed = 3;
	private float oPos;
	private float ySpeed;
	
	public Particle(float x, float y)
	{
		xPos = (float) (Math.random()*((x+25)-(x-25))+(x-25));
		yPos = (float) (Math.random()*((y+5)-(y-25))+(y-25));
	
		size = (float) (Math.random()*10);
		opacity = (int)(Math.random()*128) + 128;
		
		oPos = yPos;
		ySpeed = 1.5f;
	}
	public void update()
	{
		yPos-=ySpeed;
		
		if(yPos > oPos)
		{
			fadeSpeed *= 2;
		}
		if(ySpeed > -PARTICLE_GRAVITY)
		{
			ySpeed -= 0.08;
		}
		
		if(opacity > 0)
		{
			opacity -= fadeSpeed;
		}
		if(opacity <= 0)
		{
			opacity = 0;
		}
	}
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, opacity));
		g.fillRect((int)(xPos-(size/2)), (int)(yPos-(size/2)), (int)(size), (int)(size));
	}
}
