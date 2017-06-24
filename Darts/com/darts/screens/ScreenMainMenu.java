package com.darts.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.darts.resources.DartsResources;
import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;

public class ScreenMainMenu extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	// Images
	private Image bgImage = DartsResources.chalkboard;
	
	// Buttons
	private GuiButton playCricketBtn = new GuiButton(300, 50);
	private GuiButton exitBtn = new GuiButton(300, 50);
	
	public ScreenMainMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		playCricketBtn.setImage(null);
		playCricketBtn.setHoverImage(null);
		playCricketBtn.setFont(DartsResources.fontChalk);
		playCricketBtn.setTextColor(new Color(233, 230, 142));
		playCricketBtn.setText("Play Cricket");
		playCricketBtn.setTextOffset(0, 35);
		
		exitBtn.setImage(null);
		exitBtn.setHoverImage(null);
		exitBtn.setFont(DartsResources.fontChalk);
		exitBtn.setTextColor(new Color(233, 230, 142));
		exitBtn.setText("Exit Game");
		exitBtn.setTextOffset(20, 35);
		
		ResourceLoader.core.getAudioHandler().loopSound("mainMusic", "audio/Ice Flow.wav", 5, AudioHandler.MUSIC);
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
		// Draw the chalk board background
		g.drawImage(bgImage, 0, 0, null);
		
		// Draw the word Cricket
		g.setColor(Color.white);
		g.setFont(DartsResources.fontChalkBig);
		g.drawString("Darts", (windowX / 2) - 100, 50);
		
		// Draw the horizontal line under the text
		g.drawImage(DartsResources.chalkLine, (windowX / 2) - 300, 50, null);
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
		playCricketBtn.drawButton(g, (windowX / 2) - 150, 200);
		
		exitBtn.drawButton(g, (windowX / 2) - 150, 300);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		if (component == playCricketBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("chalk", "audio/chalkStroke.wav", AudioHandler.SFX);
			ResourceLoader.core.getScreenHandler().showScreen(new ScreenCricket(ResourceLoader.core.getScreenHandler()));
		}
		
		if (component == exitBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("chalk", "audio/chalkStroke.wav", AudioHandler.SFX);
			System.exit(0);
		}
	}

	@Override
	public void guiComponentEntered(GuiComponent component)
	{
		if (component == playCricketBtn)
		{
			playCricketBtn.setTextColor(new Color(85, 144, 91));
		}
		
		if (component == exitBtn)
		{
			exitBtn.setTextColor(new Color(85, 144, 91));
		}
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		if (component == playCricketBtn)
		{
			playCricketBtn.setTextColor(new Color(233, 230, 142));
		}
		
		if (component == exitBtn)
		{
			exitBtn.setTextColor(new Color(233, 230, 142));
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
