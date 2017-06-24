package com.rs.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;
import com.rs.main.RSResources;

public class ScreenRSPlay extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image bgImage = ResourceLoader.scaleImage(RSResources.basicBg, windowX, windowY);
	
	private GuiButton playBtn = new GuiButton(RSResources.mainBtn, RSResources.mainBtnHover, 500, 60);
	private GuiButton backBtn = new GuiButton(RSResources.mainBtn, RSResources.mainBtnHover, 500, 60);
	
	public ScreenRSPlay(ScreenHandler screenHandler)
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
		
		backBtn.setTextColor(Color.black);
		backBtn.setText("Back");
		backBtn.setTextOffset(225, 38);
		backBtn.setFont(RSResources.fontMain32);
		
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
			
			bgImage = ResourceLoader.scaleImage(RSResources.basicBg, windowX, windowY);
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
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(RSResources.fontMain64);
		g2.setColor(Color.black);
		g2.drawString("Play", (windowX / 2) - 80, 110);
		
		g.setColor(new Color(209, 60, 10));
		g.fillRect((windowX / 2) - 80, 125, 130, 6);
	}

	@Override
	public void drawGui(Graphics g)
	{
		playBtn.drawButton(g, (windowX / 2) - 300, windowY - 125);
		backBtn.drawButton(g, (windowX / 2) - 300, 200);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		if (component == playBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(playBtn);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(backBtn);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenRSGui(this.getScreenHandler()));
		}
		
		if (component == backBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnPress", "audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(playBtn);
			ResourceLoader.core.getMouseHandler().guiComponents.remove(backBtn);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenRSMainMenu(this.getScreenHandler()));
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
		
		if (component == backBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("btnRoll", "audio/sfx/btnRoll.wav", AudioHandler.SFX);
			backBtn.setTextOffset(170, 38);
		}
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		if (component == playBtn)
		{
			playBtn.setTextOffset(225, 38);
		}
		
		if (component == backBtn)
		{
			backBtn.setTextOffset(225, 38);
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
