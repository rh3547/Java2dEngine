package com.engine.graphics;

import com.engine.main.Core;

/**
 * <h1>ScreenHandler</h1>
 * ScreenHandler handles all of a project's
 * screens.  A screen can be shown or unloaded
 * through this class.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class ScreenHandler
{
	/*
	 * Project Variables
	 */
	private final Core core;
	private Screen currentScreen; // The screen being shown
	private Screen screenOnDeck; // The screen that should be shown next (optional)
	
	/*
	 * Load Variables
	 */
	private boolean projectLoading = true; // Is the project loading, if so
										   // the screen should not be shown yet.
	
	/**
	 * Create a new ScreenHandler.
	 * @param core
	 */
	public ScreenHandler(Core core)
	{
		this.core = core;
	}
	
	/**
	 * <b>NOT USED</b>
	 * A general update method.
	 */
	public void update()
	{
		
	}
	
	/**
	 * If the project is loaded, this method
	 * sets the given currentScreen as the project's current Screen
	 * and runs it's onCreate method to show it.
	 * 
	 * @param currentScreen
	 */
	public void showScreen(Screen currentScreen)
	{
		if (!projectLoading)
		{
			// Set current screen
			this.currentScreen = currentScreen;
			// Run the current screen's onCreate method
			this.currentScreen.onCreate();
		}
	}
	
	/**
	 * Unload the screen that is currently shown.
	 */
	public void unloadCurrentScreen()
	{
		core.setLoading(true);
		core.getCanvas().setScreenLoaded(false);
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		currentScreen.unload();
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		if (screenOnDeck != null)
			showScreen(screenOnDeck);
		
		core.setLoading(false);
	}

	/**
	 * Get the currently shown Screen.
	 * @return currentScreen
	 */
	public Screen getCurrentScreen()
	{
		return currentScreen;
	}
	
	/**
	 * Set the current screen.
	 * @param Screen screen
	 */
	public void setCurrentScreen(Screen screen)
	{
		this.currentScreen = screen;
	}
	
	/**
	 * Set the next screen to show.
	 * @param Screen screen
	 */
	public void setScreenOnDeck(Screen screen)
	{
		this.screenOnDeck = screen;
	}

	/**
	 * @return the screenOnDeck
	 */
	public Screen getScreenOnDeck()
	{
		return screenOnDeck;
	}

	/**
	 * Get the Core.
	 * @return core
	 */
	public Core getCore()
	{
		return core;
	}
	
	/**
	 * Set the boolean flag projectLoading depending
	 * on if the project is loading or not.
	 * @param projectLoading
	 */
	public void setProjectLoading(boolean projectLoading)
	{
		this.projectLoading = projectLoading;
	}
}
