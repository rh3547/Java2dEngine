package com.cubeoTwo.main;

import java.io.File;

import javax.swing.JOptionPane;

import com.cubeoTwo.screens.CubeoDebugScreen;
import com.cubeoTwo.screens.ScreenCubeoWorld;
import com.engine.main.Core;

/**
 * GameCubeoTwo creates a new game, Cubeo Two
 * using the Game class.
 * 
 * @author Ryan Hochmuth
 *
 */
public class GameCubeoTwo
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Core core;
	// The title to display at the top of the window
	private String title = "Cubeo Two";
	// The window's width
	private int windowX = 1280;
	// the window's height
	private int windowY = 720;
	// The rate to update the game's rendering
	private int fps = 60;
	// The rate to update the game's ticks
	private int tps = 60;
	
	// The user's operating system
	private String OS = "";
	
	/**
	 * Create a new GameCubeoTwo
	 */
	public GameCubeoTwo()
	{
		// Create the game
		core = new Core(title, windowX, windowY, fps, tps);
		core.start();
		
		core.setResourceLoader(new CubeoTwoResources(core));
		core.setDebugScreen(new CubeoDebugScreen());
		core.setResourcePack("default");
		
		// Detect the user's operating system
		OS = core.getResourceLoader().getOS();
		// Set the game's res and file paths based on OS
		if (OS.equals("windows"))
		{
			core.setRespath("CubeoTwo/res/");
			core.setFilepath(System.getProperty("user.home") + "\\Documents\\Games\\" + title);
			
			// Create the game's base directory at filepath
			File dir = new File(System.getProperty("user.home") + "\\Documents\\Games");
			if (!dir.exists())
				try
				{
			        dir.mkdir();
			    } 
				catch(SecurityException se)
				{
			        
			    }
			// Create the game's base directory at filepath
			File dir2 = new File(System.getProperty("user.home") + "\\Documents\\Games\\" + title);
			if (!dir2.exists())
				try
				{
			        dir2.mkdir();
			    } 
				catch(SecurityException se)
				{
			        
			    }
		}
		else if (OS.equals("mac"))
		{
			core.setRespath("CubeoTwo/res/");
			core.setFilepath(System.getProperty("user.home") + "\\Games\\" + title);
			
			// Create the game's base directory at filepath
			File dir = new File(System.getProperty("user.home") + "\\Documents\\Games");
			if (!dir.exists())
				try
				{
			        dir.mkdir();
			    } 
				catch(SecurityException se)
				{
			        
			    }
			// Create the game's base directory at filepath
			File dir2 = new File(System.getProperty("user.home") + "\\Documents\\Games\\" + title);
			if (!dir2.exists())
				try
				{
			        dir2.mkdir();
			    } 
				catch(SecurityException se)
				{
			        
			    }
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Operating system not supported");
			System.exit(0);
		}
		
		core.setWindowIcon("graphics/icons/gameIcon.png");
		
		// Create a new ResourcesCubeoTwo for loading the game's resources
		core.getResourceLoader().loadResources();
		
		// Create and show the Screen ScreenMainMenu
		core.getScreenHandler().showScreen(new ScreenCubeoWorld(core.getScreenHandler()));
	}
  
	public static void main(String[] args)
	{
		new GameCubeoTwo();
	}
}
