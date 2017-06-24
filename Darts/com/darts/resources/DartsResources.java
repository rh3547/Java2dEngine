package com.darts.resources;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

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
public class DartsResources extends ResourceLoader
{
	/**
	 * Create a new DartsResources
	 * @param core
	 */
	public DartsResources(Core core)
	{
		super(core);
	}
	
	/***********************************
	 * Fonts
	 **********************************/
	public static Font fontChalk;
	public static Font fontChalkBig;
	
	@Override
	public void loadFonts()
	{
		fontChalk = registerFont("fonts/CFMarieEve.ttf", "CF Marie Eve", 48, false);
		fontChalkBig = registerFont("fonts/CFMarieEve.ttf", "CF Marie Eve", 64, false);
	}

	/***********************************
	 * Backgrounds
	 **********************************/
	public static Image chalkboard;
	
	@Override
	public void loadBackgrounds()
	{
		ImageIcon chalkboardII = new ImageIcon(core.getRespath() + "graphics/backgrounds/chalkboardBg.png");
		chalkboard = chalkboardII.getImage();
	}

	/***********************************
	 * Gui Images
	 **********************************/
	public static Image chalkLine;
	public static Image chalkLineVert;
	
	@Override
	public void loadGuiImages()
	{
		ImageIcon chalkLineII = new ImageIcon(core.getRespath() + "graphics/chalkLine.png");
		chalkLine = ResourceLoader.scaleImage(chalkLineII.getImage(), 600, 100);
		
		ImageIcon chalkLineVertII = new ImageIcon(core.getRespath() + "graphics/chalkLineVert.png");
		chalkLineVert = ResourceLoader.scaleImage(chalkLineVertII.getImage(), 100, 600);
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
	
	@Override
	public void checkKey(int keyCode, boolean isPressed)
	{
		if (keyCode == space.getKeyCode()) // If space was pressed
			space.toggle(isPressed);
	}
}
