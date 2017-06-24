package com.rs.screens;

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
import com.rs.main.RSResources;

public class ScreenRSMainMenu extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image bgImage = ResourceLoader.scaleImage(RSResources.mainBg, windowX, windowY);
	
	private GuiButton playBtn = new GuiButton(RSResources.mainBtn, RSResources.mainBtnHover, 500, 60);
	private GuiButton exitBtn = new GuiButton(RSResources.mainBtn, RSResources.mainBtnHover, 500, 60);
	
	public ScreenRSMainMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.core.getCanvas().setScreenLoaded(false);
		
		playBtn.setTextColor(Color.black);
		playBtn.setText("Play");
		playBtn.setTextOffset(225, 38);
		playBtn.setFont(RSResources.fontMain32);
		
		exitBtn.setTextColor(Color.black);
		exitBtn.setText("Exit");
		exitBtn.setTextOffset(225, 38);
		exitBtn.setFont(RSResources.fontMain32);
		
		// Set the starting volume
		ResourceLoader.core.getAudioHandler().setMusicVolume(-5.0F);
		ResourceLoader.core.getAudioHandler().setSfxVolume(-5.0F);
		
		ResourceLoader.core.getAudioHandler().playSound("mainMusic", "audio/music/Whiskey on the Mississippi.wav", AudioHandler.MUSIC);
		
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
			
			bgImage = ResourceLoader.scaleImage(RSResources.mainBg, windowX, windowY);
		}
	}

	@Override
	public void unload()
	{
		
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
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
		
	}

	@Override
	public void drawGui(Graphics g)
	{
		playBtn.drawButton(g, (windowX / 2) - 300, 300);
		exitBtn.drawButton(g, (windowX / 2) - 300, 400);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		if (component == playBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(playBtn);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(exitBtn);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenRSPlay(this.getScreenHandler()));
		}
		
		if (component == exitBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			System.exit(0);
		}
	}

	@Override
	public void guiComponentEntered(GuiComponent component)
	{
		if (component == playBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnRoll", "audio/sfx/btnRoll.wav", AudioHandler.SFX);
			playBtn.setTextOffset(170, 38);
		}
		
		if (component == exitBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnRoll", "audio/sfx/btnRoll.wav", AudioHandler.SFX);
			exitBtn.setTextOffset(170, 38);
		}
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		if (component == playBtn)
		{
			playBtn.setTextOffset(225, 38);
		}
		
		if (component == exitBtn)
		{
			exitBtn.setTextOffset(225, 38);
		}
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