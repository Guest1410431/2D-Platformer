package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Utilities.Window;

public class CustomizationMenu
{
	private int width;
	private int height;
	
	private Popup popup;
	
	private ArrayList<Button>buttons = new ArrayList<Button>();
	
	public CustomizationMenu(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		buttons.add(new Button(this.width, 150, 190, 200, "abcdef", false, "Customization"));
		buttons.add(new Button(this.width, 500, 50, 100, "Player Color", true, "Customization"));
		buttons.add(new Button(this.width, 650, 50, 100, " Bike Color ", true, "Customization"));
		buttons.add(new Button(this.width, 800, 50, 100, "    Play    ", true, "Game"));
		buttons.add(new Button(this.width, 950, 50, 100, "    Back    ", true, "Main"));
		
		popup = new Popup(false);
	}
	public void update(int mouse_x, int mouse_y)
	{
		for(Button b: buttons)
		{
			if(b.contains(mouse_x, mouse_y))
			{
				b.update();
			}
			else if(b.getIndex() != 0)
			{
				b.update();
			}
		}	
		popup.update(mouse_x, mouse_y);
	}
	public void click(int mouse_x, int mouse_y)
	{
		if(!popup.getShown())
		{
			for(Button b: buttons)
			{
				if(b.contains(mouse_x, mouse_y))
				{
					Window.setMenuState(b.getMenu());
					
					if(b.getWord().equals("PLAYER COLOR"))
					{
						popup.setTitle("Player color");
						popup.setShown(true);
					}
					else if (b.getWord().equals(" BIKE COLOR "))
					{
						popup.setTitle(" Bike color ");
						popup.setShown(true);
					}
				}
			}
		}
		if(popup.getShown())
		{
			if(!popup.contains(mouse_x, mouse_y))
			{
				popup.setShown(false);
			}
			popup.click(mouse_x, mouse_y);
		}
	}
	public void render(Graphics g)
	{
		for(Button b : buttons)
		{
			b.render(g);
		}
		popup.render(g);
	}
}
