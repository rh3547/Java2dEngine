package com.colorgen.resources;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.engine.input.Key;
import com.engine.main.Core;
import com.engine.resources.ResourceLoader;

/**
 * All game resources are loaded and
 * accessed through this class.
 * 
 * @author Ryan Hochmuth
 *
 */
public class ColorGenResources extends ResourceLoader
{
	/**
	 * Create a new DartsResources
	 * @param core
	 */
	public ColorGenResources(Core core)
	{
		super(core);
	}
	
	/***********************************
	 * Fonts
	 **********************************/
	@Override
	public void loadFonts()
	{
		
	}

	/***********************************
	 * Backgrounds
	 **********************************/
	public static BufferedImage colorImage = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
	
	@Override
	public void loadBackgrounds()
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
				
				colorImage.setRGB(x, y, rgb);
			}
		}
	}

	/***********************************
	 * Gui Images
	 **********************************/
	@Override
	public void loadGuiImages()
	{
		
	}

	@Override
	public void loadSpriteImages()
	{
		
	}

	@Override
	public void loadOtherImages()
	{
		
	}

	@Override
	public void loadMisc()
	{
		
	}

	@Override
	public void postLoad()
	{
		
	}
	
	/***********************************
	 * Keys
	 **********************************/
	public final static Key space = new Key(KeyEvent.VK_SPACE);
	public final static Key up = new Key(KeyEvent.VK_W);
	public final static Key down = new Key(KeyEvent.VK_S);
	public final static Key left = new Key(KeyEvent.VK_A);
	public final static Key right = new Key(KeyEvent.VK_D);
	
	@Override
	public void checkKey(int keyCode, boolean isPressed)
	{
		if (keyCode == space.getKeyCode()) // If space was pressed
			space.toggle(isPressed);
		
		if (keyCode == up.getKeyCode()) // If up was pressed
			up.toggle(isPressed);
		
		if (keyCode == down.getKeyCode()) // If down was pressed
			down.toggle(isPressed);
		
		if (keyCode == left.getKeyCode()) // If left was pressed
			left.toggle(isPressed);
		
		if (keyCode == right.getKeyCode()) // If right was pressed
			right.toggle(isPressed);
	}
}
