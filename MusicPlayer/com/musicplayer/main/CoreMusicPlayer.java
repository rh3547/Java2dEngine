package com.musicplayer.main;

import java.io.File;

import javax.swing.JOptionPane;

import com.musicplayer.screens.ScreenMusicPlayer;
import com.engine.main.Core;
import com.musicplayer.resources.MusicPlayerResources;

/**
 * Sets initial settings and 
 * creates the new Game.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CoreMusicPlayer
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Core core;
	// The title to display at the top of the window
	private String title = "Music Player";
	// The window's width
	private int windowX = 448;
	// the window's height
	private int windowY = 480;
	// The rate to update the game's rendering
	private int fps = 60;
	// The rate to update the game's ticks
	private int tps = 60;
	
	// The user's operating system
	private String OS = "";
	
	/**
	 * Create a new GameDarts object.
	 */
	public CoreMusicPlayer()
	{
		// Create the game
		core = new Core(title, windowX, windowY, fps, tps);
		
		core.setUseCamera(false);
		core.setUseCoreObjects(false);
		core.setUseLighting(false);
		core.setUseWorld(false);
		
		core.start();
		
		// Set the game's ResourceLoader
		core.setResourceLoader(new MusicPlayerResources(core));
		
		// Detect the user's operating system
		OS = core.getResourceLoader().getOS();
		// Set the game's res and file paths based on OS
		if (OS.equals("windows"))
		{
			core.setRespath("MusicPlayer/res/");
			core.setFilepath(System.getProperty("user.home") + "\\Documents\\" + title);
		}
		else if (OS.equals("mac"))
		{
			core.setRespath("MusicPlayer/res/");
			core.setFilepath(System.getProperty("user.home") + "\\" + title);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Operating system not supported");
			System.exit(0);
		}
		
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
		
		// Set the window icon
		core.setWindowIcon("icon.png");
		
		// Load the game's resources
		core.getResourceLoader().loadResources();
		
		// Create and show the Screen ScreenMainMenu
		core.getScreenHandler().showScreen(new ScreenMusicPlayer(core.getScreenHandler()));
	}
	
	public static void main(String[] args)
	{
		new CoreMusicPlayer();
	}
}
