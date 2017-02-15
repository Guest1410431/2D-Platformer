package Utilities;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	public BufferedImage spriteSheet;
	
	public SpriteSheet()
	{
		
	}
	
	public void setSpriteSheet(BufferedImage spriteSheet)
	{
		this.spriteSheet = spriteSheet;
	}
	
	public BufferedImage getTile(int xPos, int yPos, int width, int height)
	{
		return spriteSheet.getSubimage(xPos, yPos, width, height);
	}
	public int getRGB(int x, int y)
	{
		return spriteSheet.getRGB(x, y);
	}
	public void setRGB(int x,  int y, int rgb)
	{
		spriteSheet.setRGB(x, y, rgb);
	}
	public int getHeight()
	{
		return spriteSheet.getHeight();
	}
	public int getWidth()
	{
		return spriteSheet.getWidth();
	}
}
