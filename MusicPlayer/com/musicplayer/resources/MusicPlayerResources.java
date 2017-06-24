package com.musicplayer.resources;

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
public class MusicPlayerResources extends ResourceLoader
{
	/**
	 * Create a new DartsResources
	 * @param core
	 */
	public MusicPlayerResources(Core core)
	{
		super(core);
	}
	
	/***********************************
	 * Fonts
	 **********************************/
	public static Font timeBurner;
	
	@Override
	public void loadFonts()
	{
		timeBurner = registerFont("timeburner_regular.ttf", "TimeBurner", 64, false);
	}

	/***********************************
	 * Backgrounds
	 **********************************/
	public static Image bg;
	
	@Override
	public void loadBackgrounds()
	{
		ImageIcon bgII = new ImageIcon(core.getRespath() + "bg.png");
		bg = bgII.getImage();
	}

	/***********************************
	 * Gui Images
	 **********************************/
	public static Image playImg;
	public static Image playImgHover;
	public static Image pauseImg;
	public static Image pauseImgHover;
	public static Image nextImg;
	public static Image nextImgHover;
	public static Image prevImg;
	public static Image prevImgHover;
	
	@Override
	public void loadGuiImages()
	{
		ImageIcon playImgII = new ImageIcon(core.getRespath() + "play.png");
		playImg = playImgII.getImage();
		
		ImageIcon playImgHoverII = new ImageIcon(core.getRespath() + "playHover.png");
		playImgHover = playImgHoverII.getImage();
		
		ImageIcon pauseImgII = new ImageIcon(core.getRespath() + "pause.png");
		pauseImg = pauseImgII.getImage();
		
		ImageIcon pauseImgHoverII = new ImageIcon(core.getRespath() + "pauseHover.png");
		pauseImgHover = pauseImgHoverII.getImage();
		
		ImageIcon nextImgII = new ImageIcon(core.getRespath() + "next.png");
		nextImg = nextImgII.getImage();
		
		ImageIcon nextImgHoverII = new ImageIcon(core.getRespath() + "nextHover.png");
		nextImgHover = nextImgHoverII.getImage();
		
		ImageIcon prevImgII = new ImageIcon(core.getRespath() + "prev.png");
		prevImg = prevImgII.getImage();
		
		ImageIcon prevImgHoverII = new ImageIcon(core.getRespath() + "prevHover.png");
		prevImgHover = prevImgHoverII.getImage();
	}

	@Override
	public void loadSpriteImages()
	{
		
	}
	
	/***********************************
	 * Other Images
	 **********************************/
	public static Image genAlbum;

	@Override
	public void loadOtherImages()
	{
		ImageIcon genAlbumII = new ImageIcon(core.getRespath() + "music_art/album.jpg");
		genAlbum = ResourceLoader.scaleImage(genAlbumII.getImage(), 200, 200);
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
