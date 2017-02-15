package Utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Main;
import World.Block.BlockType;

public class Assets 
{
	private static Color col1 = Color.BLUE;
	private static Color col2 = new Color(0, 200, 255);
	
	private static Color[]colors;
	
	private static SpriteSheet spriteSheet = new SpriteSheet();
	private static SpriteSheet letterSheet = new SpriteSheet();
	
	public Assets()
	{
		init();
	}
	private void init()
	{
		letterSheet.setSpriteSheet(LoadImageFrom.loadImageFrom(Main.class, "letters.png"));
		manipLetterSheet();
		//letterSheet.setSpriteSheet(LoadImageFrom.loadImageFrom(Main.class, "Letters.png"));
		spriteSheet.setSpriteSheet(LoadImageFrom.loadImageFrom(Main.class, "sprite_sheet.png"));
	}
	private static void manipLetterSheet()
	{
		File outputfile = new File("Platformer/src/main/letters.png");
		
		colors = new Color[19];
		
		int del_red = col1.getRed() - col2.getRed();
		int del_green = col1.getGreen() - col2.getGreen();
		int del_blue = col1.getBlue() - col2.getBlue();
		
		for(int i=0; i<(colors.length+1)/2; i++)
		{
			colors[i] = new Color((col1.getRed() - (i*(del_red/(colors.length/2)))), 
									(col1.getGreen() - (i*(del_green/(colors.length/2)))), 
									(col1.getBlue() - (i*(del_blue/(colors.length/2)))));
			colors[(colors.length-1)-i] = colors[i];
		}
		
		for(int h=0; h<letterSheet.getHeight(); h++)
		{
			for(int i=0; i<letterSheet.getWidth(); i++)
			{
				if(letterSheet.getRGB(i, h) != 16777215)
				{
					letterSheet.setRGB(i, h, colors[i/(letterSheet.getWidth()/18)].getRGB());
				}
			}
		}
		try
		{
			ImageIO.write(letterSheet.spriteSheet, "png", outputfile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static ArrayList<BufferedImage>getLetters()
	{
		ArrayList<BufferedImage>letters = new ArrayList<BufferedImage>();
		
		for(int i=0; i<27; i++)
		{
			for(int h=0; h<16; h++)
			{
				letters.add(letterSheet.getTile(h*7, i*13, 7, 13));
			}
		}
		return letters;
	}
	/*public static ArrayList<BufferedImage>getLeftBlurred()
	{
		ArrayList<BufferedImage>blur = getLeft();
		
		for(BufferedImage img : blur)
		{
			 
		}		
		return blur;
	}
	public static ArrayList<BufferedImage>getRightBlurred()
	{
		ArrayList<BufferedImage>blur = getRight();
		
		for(BufferedImage img : blur)
		{
			 
		}		
		return blur;
	}*/
	public static ArrayList<BufferedImage>getLeft()
	{
		ArrayList<BufferedImage>left = new ArrayList<BufferedImage>();
		
		for(int i=0; i<8; i++)
		{
			left.add(spriteSheet.getTile(i*37, 0, 37, 34));
		}
		return left;
	}
	public static ArrayList<BufferedImage> getRight()
	{
		ArrayList<BufferedImage>right = new ArrayList<BufferedImage>();
		
		for(int i=0; i<8; i++)
		{
			right.add(spriteSheet.getTile(i*37, 34, 37, 34));
		}
		return right;
	}
	public static void changeColors(Color playerColor, Color bikeColor)
	{
		float darken = 0.6f;
		
		for(int i=0; i<spriteSheet.getHeight(); i++)
		{
			for(int h=0; h<spriteSheet.getWidth(); h++)
			{
				if(spriteSheet.spriteSheet.getRGB(h, i) == -1)
				{
					spriteSheet.spriteSheet.setRGB(h, i, playerColor.getRGB());
				}
				if(spriteSheet.spriteSheet.getRGB(h, i) == -6710887)
				{
					spriteSheet.spriteSheet.setRGB(h, i, new Color((int)(playerColor.getRed()*darken), (int)(playerColor.getGreen()*darken), (int)(playerColor.getBlue()*darken)).getRGB());
				}
				if(spriteSheet.spriteSheet.getRGB(h, i) == -11776)
				{
					spriteSheet.spriteSheet.setRGB(h, i, bikeColor.getRGB());
				}
			}
		}
	}
	//Load in sprites into a Map<Image, BlockType> - return corresponding image
	public static BufferedImage getBlock(BlockType blockType)
	{
		return null;
	}
	
}
