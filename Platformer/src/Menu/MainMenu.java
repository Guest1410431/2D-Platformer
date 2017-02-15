package Menu;

import java.awt.Graphics;
import java.util.ArrayList;

import Utilities.Window;

public class MainMenu
{
	private int width;
	private int height;
	
	ArrayList<Button>buttons = new ArrayList<Button>();
	
	public MainMenu(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		buttons.add(new Button(this.width, 150, 190, 200, "abcdef", false, "Main"));
		buttons.add(new Button(this.width, 510, 50, 100, "Start", true, "Customization"));
		buttons.add(new Button(this.width, 650, 50, 100, "Settings", true, "Settings"));
		buttons.add(new Button(this.width, 790, 50, 100, "Quit", true, "Quit"));	
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
	}
	public void click(int mouse_x, int mouse_y)
	{
		for(Button b: buttons)
		{
			if(b.contains(mouse_x, mouse_y))
			{
				Window.setMenuState(b.getMenu());
			}
		}
	}
	public void render(Graphics g)
	{
		for(Button b : buttons)
		{
			b.render(g);
		}
	}
}
