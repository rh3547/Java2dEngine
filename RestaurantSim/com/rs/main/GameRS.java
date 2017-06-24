package com.rs.main;

import java.io.File;

import javax.swing.JOptionPane;

import com.engine.main.Core;
import com.rs.screens.ScreenRSMainMenu;

public class GameRS
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Core core;
	// The title to display at the top of the window
	private String title = "Restaurant Sim";
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
	 * Create a new game.
	 */
	public GameRS()
	{
		// Create the game
		core = new Core(title, windowX, windowY, fps, tps);
		core.start();
		
		core.getWindow().setResizable(false);
		
		core.getCanvas().setScreenLoaded(false);
		
		core.setResourceLoader(new RSResources(core));
		core.setDebugScreen(null);
		core.setResourcePack("default");
		
		// Detect the user's operating system
		OS = core.getResourceLoader().getOS();
		// Set the game's res and file paths based on OS
		if (OS.equals("windows"))
		{
			core.setRespath("CubeoTwo/res/");
			core.setFilepath(System.getProperty("user.home") + "\\Documents\\Games\\" + title);
		}
		else if (OS.equals("mac"))
		{
			core.setRespath("CubeoTwo/res/");
			core.setFilepath(System.getProperty("user.home") + "\\Games\\" + title);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Operating system not supported");
			System.exit(0);
		}
		
		core.setWindowIcon("graphics/icons/icon.png");
		
		// Create the game's base directory at filepath
		File dir = new File(core.getFilepath());
		if (!dir.exists())
			try
			{
		        dir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
		
		// Load the game resources
		core.getResourceLoader().loadResources();
		
		core.getCanvas().setLoadingFont(RSResources.fontMain32);
		
		// Create and show the Screen ScreenMainMenu
		core.getScreenHandler().showScreen(new ScreenRSMainMenu(core.getScreenHandler()));
	}
  
	public static void main(String[] args)
	{
		new GameRS();
	}
}
