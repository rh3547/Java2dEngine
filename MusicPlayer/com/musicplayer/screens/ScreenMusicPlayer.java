package com.musicplayer.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;
import com.musicplayer.audio.Song;
import com.musicplayer.audio.SongDatabase;
import com.musicplayer.resources.MusicPlayerResources;

public class ScreenMusicPlayer extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	// Images
	private Image bgImage = MusicPlayerResources.bg;
	
	// Song database
	private SongDatabase database = new SongDatabase();
	
	// Player data
	private boolean playing = false;
	private Song currentSong;
	
	// Buttons
	private GuiButton playBtn = new GuiButton(MusicPlayerResources.playImg,
			MusicPlayerResources.playImgHover, 
			75, 75);
	private GuiButton nextBtn = new GuiButton(MusicPlayerResources.nextImg,
			MusicPlayerResources.nextImgHover, 
			50, 50);
	private GuiButton prevBtn = new GuiButton(MusicPlayerResources.prevImg,
			MusicPlayerResources.prevImgHover, 
			50, 50);
	
	public ScreenMusicPlayer(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{	
		playBtn.setImage(MusicPlayerResources.playImg);
		playBtn.setHoverImage(MusicPlayerResources.playImgHover);
		
		database.addNewSong("Ice Flow", "music/Ice Flow.wav", null);
		//database.addNewSong("Stairway To Heaven", "music/Ice Flow.wav", "music_art/ledzep4.png");
		database.addNewSong("Ice Flow 2", "music/Ice Flow.wav", null);
		
		currentSong = database.getSong();
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
				
				bgImage = ResourceLoader.scaleImage(bgImage, windowX, windowY);
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
		// Draw the background
		g.drawImage(bgImage, 0, 0, null);
	}

	@Override
	public void drawBackground(Graphics g)
	{
		g.setColor(Color.white);
		g.setFont(MusicPlayerResources.timeBurner);
		g.drawString(currentSong.getName(), (windowX / 2) - 125, 60);
	}

	@Override
	public void drawForeground(Graphics g)
	{
		g.drawImage(currentSong.getAlbumArt(), (windowX / 2) - 100, 100, null);
	}

	@Override
	public void drawGui(Graphics g)
	{
		playBtn.drawButton(g, (windowX / 2) - 37, windowY - 150);
		nextBtn.drawButton(g, (windowX / 2) - 25 + 60, windowY - 130);
		prevBtn.drawButton(g, (windowX / 2) - 25 - 60, windowY - 130);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		if (component == playBtn)
		{
			if (!playing)
			{
				playBtn.setImage(MusicPlayerResources.pauseImg);
				playBtn.setHoverImage(MusicPlayerResources.pauseImgHover);
				
				//currentSong.playSong();
				
				playing = true;
			}
			else
			{
				playBtn.setImage(MusicPlayerResources.playImg);
				playBtn.setHoverImage(MusicPlayerResources.playImgHover);
				
				//currentSong.pauseSong();
				
				playing = false;
			}
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
