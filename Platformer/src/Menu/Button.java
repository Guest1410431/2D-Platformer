package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Utilities.Assets;
import javafx.scene.shape.Rectangle;

public class Button
{
	private boolean hover;
	
	private int screenWidth;
	private int xPos;
	private int yPos;
	private int scale_x;
	private int scale_y;
	private int width;
	private int height;
	
	private float index;
	
	private String word;
	private String menu;
	
	private char[]letters;
	
	private ArrayList<BufferedImage> img;
	
	public Button(int screenWidth, int yPos, int scale_x, int scale_y, String word, boolean hover, String menu)
	{
		this.hover = hover;
		this.screenWidth = screenWidth;
		this.yPos = yPos;
		this.scale_x = scale_x;
		this.scale_y = scale_y;
		this.word = word.toUpperCase();
		this.menu = menu;
		
		letters = this.word.toCharArray();
		index = 0;
		width = 0;
		height = scale_y;
		
		for(int i=0; i<letters.length; i++)
		{
			width += scale_x;
		}
		xPos = (screenWidth/2) - (width/2);
		img = Assets.getLetters();
	}
	public boolean contains(int x, int y)
	{
		return new Rectangle(xPos, yPos, width, height).contains(x, y);
	}
	public void update()
	{
		if(hover)
		{
			index += 0.15;
			
			if(index >= 16)
			{
				index = 0;
			}
		}
	}
	public void render(Graphics g)
	{		
		g.setColor(new Color(0, 0, 0, 125));
		g.fillRoundRect(xPos, yPos, (int)(width+(width*0.04)), (int)(height+(width*0.04)), (int)(height*0.5), (int)(height*0.5));
		g.setColor(Color.BLACK);
		g.fillRoundRect((int)(xPos-(width*0.03)), (int)(yPos-(width*0.03)), (int)(width+(width*0.06)), (int)(height+(width*0.06)), (int)(height*0.5), (int)(height*0.5));
		g.setColor(Color.WHITE);
		g.fillRoundRect((int)(xPos-(width*0.02)), (int)(yPos-(width*0.02)), (int)(width+(width*0.04)), (int)(height+(width*0.04)), (int)(height*0.5), (int)(height*0.5));
		
		for(int i=0; i<letters.length; i++)
		{	
			g.drawImage(((int)(letters[i]) < 65 || (int)(letters[i]) > 90) ? 
				(img.get(img.size()-1)) : 
				(img.get((int)(((img.size()/27)*(letters[i]-65))+index))), (i*scale_x) + xPos, yPos, scale_x, scale_y, null);
		}
	}
	public String getWord()
	{
		return word;
	}
	public String getMenu()
	{
		return menu;
	}
	public float getIndex()
	{
		return index;
	}
	public void setTitle(String title)
	{	
		word = title.toUpperCase();
		letters = this.word.toCharArray();

		width = 0;
		
		for(int i=0; i<letters.length; i++)
		{
			width += scale_x;
		}
		xPos = (screenWidth/2) - (width/2);
	}
}