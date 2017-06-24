package com.colorgen.screens;

import java.awt.Graphics;
import java.util.Random;

import com.colorgen.resources.ColorGenResources;
import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;

public class ScreenMainMenu extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private int xPos = 0;
	private int yPos = 0;
	
	public ScreenMainMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		Random rand = new Random();
		int red = 0;
		int green = 0;
		int blue = 0;
		int rgb;
		
		// Generate the image
		for(int x = 0; x < 512; x++)
		{
			for(int y = 0; y < 512; y++)
			{
				red = (((rand.nextInt(255) + 1) * 1000) * 1000);
				green = ((rand.nextInt(255) + 1) * 1000);
				blue = rand.nextInt(255) + 1;
				rgb = red + green + blue;
				
				ColorGenResources.colorImage.setRGB(x, y, rgb);
			}
		}
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
		
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		g.drawImage(ColorGenResources.colorImage, 0, 0, null);
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		g.fillRect(xPos, yPos, 32, 32);
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
		if (ColorGenResources.up.isPressed())
		{
			yPos -= 5;
		}
		if (ColorGenResources.down.isPressed())
		{
			yPos += 5;
		}
		if (ColorGenResources.left.isPressed())
		{
			xPos -= 5;
		}
		if (ColorGenResources.right.isPressed())
		{
			xPos += 5;
		}
	}

	@Override
	public void keyReleased(int keyCode)
	{
		if (ColorGenResources.up.isPressed() &&
			!ColorGenResources.down.isPressed())
		{
			yPos -= 5;
		}
		if (ColorGenResources.down.isPressed() &&
			!ColorGenResources.up.isPressed())
		{
			yPos += 5;
		}
		if (ColorGenResources.left.isPressed() &&
			!ColorGenResources.right.isPressed())
		{
			xPos -= 5;
		}
		if (ColorGenResources.right.isPressed() &&
			!ColorGenResources.left.isPressed())
		{
			xPos += 5;
		}
	}

	@Override
	public void mouseClicked(int button) 
	{
		
	}
}
