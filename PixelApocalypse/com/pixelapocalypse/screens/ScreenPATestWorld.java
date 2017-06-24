package com.pixelapocalypse.screens;

import java.awt.Graphics;

import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;
import com.pixelapocalypse.main.PAResources;
import com.pixelapocalypse.objects.PAObjects;
import com.pixelapocalypse.objects.PAPlayerController;

public class ScreenPATestWorld extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	public ScreenPATestWorld(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.core.getCanvas().setScreenLoaded(false);
		
		// Load the World
		PAResources.testWorld.loadWorld();
		
		// Add the Player
		ResourceLoader.core.getObjectHandler().getCoreObjects().add(PAObjects.player);
		
		// Set the game's Camera
		ResourceLoader.core.setCamera(PAObjects.camera);
		
		ResourceLoader.core.getCamera().setxPos(PAObjects.player.getxPos());
		ResourceLoader.core.getCamera().setyPos(PAObjects.player.getyPos());
		
		// Tell the canvas that this screen is done loading
		ResourceLoader.core.getCanvas().setScreenLoaded(true);
	}

	@Override
	public void onUpdate()
	{
		// Update the local window size
		windowX = ResourceLoader.core.getWindow().getWidth();
		windowY = ResourceLoader.core.getWindow().getHeight();
		
		if (windowX != lastWindowX)
		{
			lastWindowX = windowX;
			
			if (windowY != lastWindowY)
			{
				lastWindowY = windowY;
			}
		}
	}
	
	@Override
	public void unload() 
	{
		ResourceLoader.core.getWorldHandler().getChunks().clear();
		ResourceLoader.core.getObjectHandler().getCoreObjects().clear();
		
		ResourceLoader.core.getScreenHandler()
		.showScreen(new ScreenPAMainMenu(ResourceLoader.core.getScreenHandler()));
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		
	}

	@Override
	public void drawGui(Graphics g)
	{
		
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		
	}

	@Override
	public void guiComponentEntered(GuiComponent component)
	{
		
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		
	}

	@Override
	public void coreObjectClicked(CoreObject object, int button)
	{
		
	}

	@Override
	public void keyPressed(int keyCode)
	{
		PAPlayerController.keyPressed(keyCode);
		
		if (keyCode == PAResources.escape.getKeyCode())
		{	
			getScreenHandler().unloadCurrentScreen();
		}
	}

	@Override
	public void keyReleased(int keyCode)
	{
		PAPlayerController.keyReleased(keyCode);
	}

	@Override
	public void mouseClicked(int button) 
	{
		
	}
}