package Menu;

import java.awt.Graphics;
import java.util.ArrayList;

import Utilities.Window;

public class SettingsMenu
{
	private int width;
	private int height;
	
	ArrayList<Button>buttons = new ArrayList<Button>();
	
	public SettingsMenu(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		buttons.add(new Button(this.width, 150, 190, 200, "Settings", false, "Settings"));
		buttons.add(new Button(this.width, 440, 50, 100, "Audio", true, "Settings"));
		buttons.add(new Button(this.width, 570, 50, 100, "Video", true, "Settings"));
		buttons.add(new Button(this.width, 700, 50, 100, "Controls", true, "Settings"));
		buttons.add(new Button(this.width, 850, 50, 100, "Back", true, "Main"));
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
