package Main;

import Utilities.Assets;
import Utilities.Loop;
import Utilities.Window;

public class Main
{
	public static void main(String[] args)
	{
		new Assets();

		Loop loop = new Loop(new Window());
		loop.start();
	}
}
