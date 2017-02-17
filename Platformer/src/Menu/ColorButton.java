package Menu;

import java.awt.Color;
import java.awt.Graphics;

public class ColorButton
{	
	private boolean active;
	
	private int index;
	private int increment;
	
	private int xPos;
	private int yPos;
	private int size;

	private Color color;

	public ColorButton(Color color, int xPos, int yPos, int size)
	{
		this.color = color;
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
		
		index = 41;
		increment = 10;
		
		active = false;
	}

	public void update()
	{
		index+=increment;
		
		if(index >= 245)
		{
			increment*= (-1);
		}
		if(index <= 50)
		{
			increment*= (-1);
		}
	}

	public boolean contains(int mouse_x, int mouse_y)
	{
		return (mouse_x < (xPos + size) && mouse_x > xPos && mouse_y < (yPos + size) && mouse_y > yPos);
	}

	public void render(Graphics g)
	{
		g.setColor(new Color(index, index, 0));
		g.fillRect(xPos, yPos, size, size);

		g.setColor(this.color);
		g.fillRect((int) (xPos + (size / 30)), (int) (yPos + (size / 30)), (int) (size - (size / 15)), (int) (size - (size / 15)));
	}

	public Color getColor()
	{
		return color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public int getIndex()
	{
		return index;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
}












