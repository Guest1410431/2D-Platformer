package World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utilities.Assets;

public class Block
{
	private boolean onScreen;
	private boolean solid;

	private final int BLOCK_SIZE = Map.getBlockSize();

	private float xPos;
	private float yPos;

	private BlockType blockType;

	private BufferedImage block;

	public Block(float xPos, float yPos, BlockType blockType)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.blockType = blockType;

		block = Assets.getBlock(blockType);
		
		solid = false;
		
		onScreen = true;
	}

	public void render(Graphics g)
	{
		if (onScreen)
		{
			switch (blockType)
			{
			case AIR:
				g.setColor(Color.WHITE);
				break;
			case TILE:
				g.setColor(Color.BLUE);
				break;
			default:
				g.setColor(Color.RED);
			}
			g.fillRect((int) xPos, (int) yPos, BLOCK_SIZE, BLOCK_SIZE);
		}
	}
	public void setOnScreen(boolean onScreen)
	{
		this.onScreen = onScreen;
	}
	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
	public boolean isSolid()
	{
		return solid;
	}
	public BlockType getBlockType()
	{
		return blockType;
	}
	public enum BlockType
	{
		AIR, TILE
	}
}
