package com.cubeoTwo.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.cubeoTwo.main.CubeoTwoResources;
import com.cubeoTwo.objects.CubeoTwoObjects;
import com.cubeoTwo.objects.PlayerController;
import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.main.Core;
import com.engine.resources.ResourceLoader;

/**
 * ScreenMainMenu is the starting Screen
 * to be displayed for Cubeo Two.
 * 
 * @author Ryan Hochmuth
 *
 */
public class ScreenCubeoWorld extends Screen
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Core core;
	
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	// Images
	private Image bgImage = CubeoTwoResources.background1;
	
	private GuiButton saveBtn = new GuiButton("Save");
	
	/**
	 * Create a new ScreenMainMenu
	 * @param screenHandler
	 */
	public ScreenCubeoWorld(ScreenHandler screenHandler)
	{
		super(screenHandler);
		
		this.core = screenHandler.getCore();
	}
	
	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		core.getCanvas().setScreenLoaded(false);
		
		// Load the World
		CubeoTwoResources.world1.loadWorld();
		
		// Setup and create the lighting
		core.getLightHandler().setGlobalLightValue(0);
		core.getLightHandler().generateLightMap();
		
		// Set to false to enable world searching
		CubeoTwoObjects.player.setCollisionType(CoreObject.COLLIDE);
		CubeoTwoObjects.player.enablePhysics(true);
		
		// Add the Player
		core.getObjectHandler().getCoreObjects().add(CubeoTwoObjects.player);
		
		// Set the starting volume
		core.getAudioHandler().setMusicVolume(-30.0F);
		core.getAudioHandler().setSfxVolume(-5.0F);
		
		// Start looping the game music
		core.getAudioHandler().playSound("mainMenuMusic", "audio/Electrodoodle.wav", AudioHandler.MUSIC);
		
		// Tell the canvas that this screen is done loading
		core.getCanvas().setScreenLoaded(true);
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
			
			bgImage = ResourceLoader.scaleImage(bgImage, windowX, windowY);
		}
	}
	
	@Override
	public void unload() 
	{
		
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		g.drawImage(bgImage, 0, 0, core.getWindowX(), core.getWindowY(), null);
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
		if (CubeoTwoObjects.player.isEntityDead())
		{
			g.setColor(new Color(0, 0, 0, 80));
			g.fillRect(0, 0, windowX, windowY);
			
			g.setColor(Color.red);
			g.setFont(CubeoTwoResources.fontAnimated);
			g.drawString("You Have Died!", (windowX / 2) - 100, (windowY / 2) - 50);
		}
		
		saveBtn.drawButton(g, 25, 25);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button) 
	{
		if (component == saveBtn)
		{
			ResourceLoader.core.getSaveHandler().save("CubeoSave1", true);
		}
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
		PlayerController.keyPressed(keyCode);
	}
	
	@Override
	public void keyReleased(int keyCode) 
	{
		PlayerController.keyReleased(keyCode);
	}

	@Override
	public void mouseClicked(int button) 
	{
		
	}
}
