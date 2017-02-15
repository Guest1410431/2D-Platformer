package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import Utilities.Assets;

public class Popup
{
	private boolean shown;
	
	private String title;
	
	private final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	private final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	
	private Color color;
	private static Color playerColor;
	private static Color bikeColor;
	
	private Button button;
	
	private static ArrayList<ColorButton>colorButtons;
	
	public Popup(boolean shown)
	{
		this.shown = shown;
		this.title = "";
		
		button = new Button(WIDTH, (int)(HEIGHT/5), 50, 100, title, true, "Customization");
		
		ArrayList<Color>colors = getColors();
		colorButtons = new ArrayList<ColorButton>();
		
		for(int i=0; i<4; i++)
		{
			for(int h=0; h<6; h++)
			{
				colorButtons.add(new ColorButton(colors.get((i*6)+h), (int)(WIDTH*.5)-(h-2)*110, (int)(HEIGHT*.52)-(i-1)*110, 90));
			}
		}
		playerColor = Color.RED;
		bikeColor = Color.YELLOW;
	}
	//This can be done algorithmically, but there is no easy way to sort in the way I want
	private ArrayList<Color>getColors()
	{
		ArrayList<Color>colors = new ArrayList<Color>();
		
		colors.add(new Color(255, 0, 0));
		colors.add(new Color(255, 64, 0));
		colors.add(new Color(255, 191, 0));
		colors.add(new Color(128, 255, 0));
		colors.add(new Color(0, 255, 128));
		colors.add(new Color(0, 128, 255));
		colors.add(new Color(255, 128, 0));
		colors.add(new Color(255, 255, 0));
		colors.add(new Color(64, 255, 0));
		colors.add(new Color(0, 255, 191));
		colors.add(new Color(0, 64, 255));
		colors.add(new Color(128, 0, 255));
		colors.add(new Color(191, 255, 0));
		colors.add(new Color(0, 255, 0));
		colors.add(new Color(0, 255, 255));
		colors.add(new Color(0, 0, 255));
		colors.add(new Color(191, 0, 255));
		colors.add(new Color(255, 0, 191));
		colors.add(new Color(0, 255, 64));
		colors.add(new Color(0, 191, 255));
		colors.add(new Color(64, 0, 255));
		colors.add(new Color(255, 0, 255));
		colors.add(new Color(255, 0, 128));
		colors.add(new Color(255, 0, 64));
		
		return colors;
	}
	public void update(int mouse_x, int mouse_y)
	{
		if(shown)
		{
			button.update();
		}
		for(ColorButton cb : colorButtons)
		{
			if(cb.contains(mouse_x, mouse_y) || cb.getIndex() >= 50 || cb.getColor() == ((title.equals("PLAYER COLOR") ? playerColor : bikeColor)))
			{
				cb.update();
			}
		}
	}
	public void click(int mouse_x, int mouse_y)
	{
		for(ColorButton cb : colorButtons)
		{
			if(shown)
			{
				if(cb.contains(mouse_x, mouse_y))
				{
					if(title.equals("PLAYER COLOR"))
					{
						playerColor = cb.getColor();
					}
					else
					{
						bikeColor = cb.getColor();
					}
				}
			}
		}
	}
	public void render(Graphics g)
	{
		if(shown)
		{
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.BLACK);
			g.fillRoundRect((int)(WIDTH/4-(HEIGHT*0.0125)), (int)(HEIGHT/4-(HEIGHT*0.0125)), (int)(WIDTH/2+(HEIGHT*0.025)), (int)(HEIGHT/2+(HEIGHT*0.025)), (int)(WIDTH*0.05), (int)(WIDTH*0.05));
			g.setColor(Color.WHITE);
			g.fillRoundRect(WIDTH/4, HEIGHT/4, WIDTH/2, HEIGHT/2, (int)(WIDTH*0.05), (int)(WIDTH*0.05));

			button.render(g);
			
			for(ColorButton cb : colorButtons)
			{
				cb.render(g);
			}
		}
	}
	public boolean contains(int xPos, int yPos)
	{
		if(shown)
		{
			return (xPos > WIDTH*.25 && xPos < WIDTH*.75 && yPos > HEIGHT*.25 && yPos < HEIGHT*.75);
		}
		return false;
	}
	public Color getColor()
	{
		return color;
	}
	public void setShown(boolean shown)
	{
		this.shown = shown;
	}
	public boolean getShown()
	{
		return shown;
	}
	public void setTitle(String title)
	{
		this.title = title;
		button.setTitle(this.title);
	}
	public static Color getPlayerColor()
	{
		return playerColor;
	}
	public static Color getBikeColor()
	{
		return bikeColor;
	}
}
