package com.pixelapocalypse.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;
import com.pixelapocalypse.main.PAResources;

public class ScreenPAMainMenu extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image bgImage = ResourceLoader.scaleImage(PAResources.mainBg, windowX, windowY);
	
	private GuiButton singlePlayerBtn = new GuiButton(300, 60);
	private GuiButton exitBtn = new GuiButton(300, 60);
	
	public ScreenPAMainMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.core.getCanvas().setScreenLoaded(false);
		
		// Set the starting volume
		ResourceLoader.core.getAudioHandler().setMusicVolume(-10.0F);
		ResourceLoader.core.getAudioHandler().setSfxVolume(-5.0F);
		
		singlePlayerBtn.setImage(PAResources.mainBtn);
		singlePlayerBtn.setHoverImage(PAResources.mainBtnHover);
		singlePlayerBtn.setTextColor(Color.white);
		singlePlayerBtn.setText("Single Player");
		singlePlayerBtn.setTextOffset(40, 30);
		singlePlayerBtn.setFont(PAResources.fontMain);
		
		exitBtn.setImage(PAResources.mainBtn);
		exitBtn.setHoverImage(PAResources.mainBtnHover);
		exitBtn.setTextColor(Color.white);
		exitBtn.setText("Exit");
		exitBtn.setTextOffset(115, 30);
		exitBtn.setFont(PAResources.fontMain);
		
		ResourceLoader.core.getAudioHandler().playSound("mainMusic", "audio/music/deepHaze.wav", AudioHandler.MUSIC);
		
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
			
			bgImage = ResourceLoader.scaleImage(PAResources.mainBg, windowX, windowY);
		}
	}
	
	@Override
	public void unload() 
	{
		
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, windowX, windowY);
		
		// Draw the image background
		g.drawImage(bgImage, 0, 0, null);
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect((windowX / 2) - 230, 110, 450, 5);
		
		g.setColor(Color.white);
		g.setFont(PAResources.fontMainLarge);
		g.drawString("Broken Earth", (windowX / 2) - 225, 100);
	}

	@Override
	public void drawGui(Graphics g)
	{
		singlePlayerBtn.drawButton(g, (windowX / 2) - 150, 200);
		exitBtn.drawButton(g, (windowX / 2) - 150, 280);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		// If the exit button was clicked
		if (component == exitBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			System.exit(0);
		}
		
		// If the single player button was clicked
		if (component == singlePlayerBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(singlePlayerBtn);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(exitBtn);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenPASinglePlayerMenu(ResourceLoader.core.getScreenHandler()));
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
		
	}

	@Override
	public void keyReleased(int keyCode)
	{
		
	}

	@Override
	public void mouseClicked(int button) 
	{
		
	}
}