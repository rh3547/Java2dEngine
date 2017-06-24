package com.darts.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.darts.resources.DartsResources;
import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;

public class ScreenCricket extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	// The team names
	private String team1 = "Ryan";
	private String team2 = "Jason";
	
	// Images
	private Image bgImage = DartsResources.chalkboard;
	
	private boolean drawn = true;
	private boolean drawSlash = false;
	private boolean drawX = false;
	private boolean drawO = false;
	
	public ScreenCricket(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
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
		g.setFont(DartsResources.fontChalk);
		g.drawString("Cricket", (windowX / 2) - 100, 50);
		
		// Draw the horizontal line under the text
		g.drawImage(DartsResources.chalkLine, (windowX / 2) - 300, 50, null);
		
		// Draw the vs. text
		g.setColor(Color.white);
		g.setFont(DartsResources.fontChalk);
		g.drawString("vs.", (windowX / 2) - 40, 125);
		
		// Draw the horizontal line under the vs.
		g.drawImage(ResourceLoader.scaleImage(DartsResources.chalkLine, 600, 25), (windowX / 2) - 310, 125, null);
		
		// The the outer left and right lines
		g.drawImage(ResourceLoader.scaleImage(DartsResources.chalkLineVert, 100, windowY - 300), (windowX / 2) - 260, 200, null);
		g.drawImage(ResourceLoader.scaleImage(DartsResources.chalkLineVert, 100, windowY - 300), (windowX / 2) - 260 + 470, 200, null);
		
		// The the inner left and right lines
		g.drawImage(ResourceLoader.scaleImage(DartsResources.chalkLineVert, 100, windowY - 300), (windowX / 2) - 90, 200, null);
		g.drawImage(ResourceLoader.scaleImage(DartsResources.chalkLineVert, 100, windowY - 300), (windowX / 2) - 100 + 140, 200, null);
		
		// Draw the target indicators
		int spacing = 75;
		
		g.setColor(new Color(233, 230, 142));
		g.setFont(DartsResources.fontChalkBig);
		
		g.drawString("20", (windowX / 2) - 150 + 100, 240);
		g.drawString("19", (windowX / 2) - 150 + 105, 240 + (spacing * 1));
		g.drawString("18", (windowX / 2) - 150 + 105, 240 + (spacing * 2));
		g.drawString("17", (windowX / 2) - 150 + 105, 240 + (spacing * 3));
		g.drawString("16", (windowX / 2) - 150 + 105, 240 + (spacing * 4));
		g.drawString("15", (windowX / 2) - 150 + 105, 240 + (spacing * 5));
		g.drawString("B", (windowX / 2) - 150 + 115, 240 + (spacing * 6));
	}

	@Override
	public void drawBackground(Graphics g)
	{
		// Draw the team names
		g.setColor(Color.white);
		g.setFont(DartsResources.fontChalk);
		g.drawString(team1, (windowX / 2) - 275, 125);
		
		g.setColor(Color.white);
		g.setFont(DartsResources.fontChalk);
		g.drawString(team2, (windowX / 2) - 275 + 375, 125);
	}

	@Override
	public void drawForeground(Graphics g)
	{
		// Draw the /, X, and O
		g.setColor(new Color(85, 144, 91));
		g.setFont(DartsResources.fontChalkBig);
		
		if (drawSlash && !drawX)
		{
			g.drawString("/", (windowX / 2) - 400 + 220, 240);
		}
			
		if (drawX)
		{
			g.drawString("X", (windowX / 2) - 400 + 220, 240);
		}
		
		if (drawO)
		{
			g.drawString("O", (windowX / 2) - 400 + 217, 240);
		}
		
		if (!drawn)
		{
			ResourceLoader.core.getAudioHandler().playSound("chalk", "audio/chalkStroke.wav", AudioHandler.SFX);
			drawn = true;
		}
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
		if (DartsResources.space.isPressed())
		{
			if (!drawSlash)
			{
				drawSlash = true;
				drawn = false;
			}
			else if (!drawX)
			{
				drawX = true;
				drawn = false;
			}
			else if (!drawO)
			{
				drawO = true;
				drawn = false;
			}
		}		
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
