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

public class ScreenPASinglePlayerMenu extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image bgImage = ResourceLoader.scaleImage(PAResources.mainBg, windowX, windowY);
	
	private GuiButton backBtn = new GuiButton(100, 60);
	private GuiButton playBtn = new GuiButton(100, 60);
	
	public ScreenPASinglePlayerMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.core.getCanvas().setScreenLoaded(false);
		
		backBtn.setImage(PAResources.mainBtn);
		backBtn.setHoverImage(PAResources.mainBtnHover);
		backBtn.setTextColor(Color.white);
		backBtn.setText("Back");
		backBtn.setTextOffset(15, 35);
		backBtn.setFont(PAResources.fontMain);
		
		playBtn.setImage(PAResources.mainBtn);
		playBtn.setHoverImage(PAResources.mainBtnHover);
		playBtn.setTextColor(Color.white);
		playBtn.setText("Play");
		playBtn.setTextOffset(15, 35);
		playBtn.setFont(PAResources.fontMain);
		
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
		
		g.setColor(Color.gray);
		g.fillRect((windowX / 6), (windowY / 4), windowX - ((windowX / 6) * 2), windowY - ((windowY / 4) * 2));
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect((windowX / 2) - 120, 110, 240, 5);
		
		g.setColor(Color.white);
		g.setFont(PAResources.fontMain);
		g.drawString("Single Player", (windowX / 2) - 120, 100);
	}

	@Override
	public void drawGui(Graphics g)
	{
		backBtn.drawButton(g, 50, 50);
		playBtn.drawButton(g, (windowX / 2) - 50, (windowY - (windowY / 4)) + 25);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		if (component == backBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(backBtn);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(playBtn);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenPAMainMenu(ResourceLoader.core.getScreenHandler()));
		}
		
		if (component == playBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(backBtn);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(playBtn);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenPATestWorld(ResourceLoader.core.getScreenHandler()));
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