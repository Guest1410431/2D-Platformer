package Utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadImageFrom 
{
	public static BufferedImage loadImageFrom(Class<?> classFile, String path)
	{
		URL url = classFile.getResource(path);
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(url);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
}
