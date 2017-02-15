package Utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.Player;
import Menu.CustomizationMenu;
import Menu.MainMenu;
import Menu.SettingsMenu;
import World.Map;

public class Window extends JPanel
{	
	private static String menu_state = "Game";
	private final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private int mouse_x;
	private int mouse_y;
			
	private JFrame frame = new JFrame();
	
	private MainMenu mainMenu = new MainMenu(WIDTH, HEIGHT); // "Main"
	private SettingsMenu settingsMenu = new SettingsMenu(WIDTH, HEIGHT); // "Settings"
	private CustomizationMenu customizationMenu = new CustomizationMenu(WIDTH, HEIGHT); // "Customization"
	private Player player;  // "Game"
	
	public Window()
	{
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setUndecorated(true);
		frame.setResizable(false);

		player = new Player(frame.getWidth()/2, frame.getHeight()/2);
		
		frame.add(this);
		frame.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
				
			}
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setRight(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setLeft(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_W)
				{
					player.setJump(false);
				}
			}

			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					System.exit(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setRight(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setLeft(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_W)
				{
					player.setJump(true);
				}
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseMoved(MouseEvent e)
			{
				mouse_x = e.getX();
				mouse_y = e.getY();
			}
			public void mouseDragged(MouseEvent e)
			{
			}
		});
		frame.addMouseListener(new MouseListener()
		{
			public void mouseReleased(MouseEvent e)
			{
				
			}
			public void mousePressed(MouseEvent e)
			{
				if(menu_state == "Main")
				{
					mainMenu.click(mouse_x, mouse_y);
				}
				else if(menu_state == "Settings")
				{
					settingsMenu.click(mouse_x, mouse_y);
				}
				else if(menu_state == "Customization")
				{
					customizationMenu.click(mouse_x, mouse_y);
				}
				else if(menu_state == "Game")
				{
					
				}
			}
			public void mouseExited(MouseEvent e)
			{
				
			}
			public void mouseEntered(MouseEvent e)
			{
				
			}
			public void mouseClicked(MouseEvent e)
			{
				
			}
		});
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if(menu_state == "Main")
		{
			mainMenu.render(g);
		}
		else if(menu_state == "Settings")
		{
			settingsMenu.render(g);
		}
		else if(menu_state == "Customization")
		{
			customizationMenu.render(g);
		}
		else if(menu_state == "Game")
		{
			player.render(g);
		}
		repaint();
	}

	public void update()
	{
		if(menu_state == "Main")
		{
			mainMenu.update(mouse_x, mouse_y);
		}
		else if(menu_state == "Settings")
		{
			settingsMenu.update(mouse_x, mouse_y);
		}
		else if(menu_state == "Customization")
		{
			customizationMenu.update(mouse_x, mouse_y);
		}
		else if(menu_state == "Game")
		{
			player.update();
		}
		else if(menu_state == "Quit")
		{
			System.exit(0);
		}
	}
	public static void setMenuState(String menu)
	{
		menu_state = menu;
	}
}

















