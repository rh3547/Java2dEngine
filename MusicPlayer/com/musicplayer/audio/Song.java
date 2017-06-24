package com.musicplayer.audio;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.engine.audio.AudioHandler;
import com.engine.resources.ResourceLoader;
import com.musicplayer.resources.MusicPlayerResources;

public class Song 
{
	private String name;
	private String soundPath;
	private Image albumArt;
	
	public Song(String name, String soundPath, String artPath)
	{
		this.name = name;
		this.soundPath = soundPath;
		
		if (artPath == null)
		{
			albumArt = MusicPlayerResources.genAlbum;
		}
		else
		{
			ImageIcon artII = new ImageIcon(ResourceLoader.core.getRespath() + artPath);
			albumArt = ResourceLoader.scaleImage(artII.getImage(), 200, 200);
		}
	}
	
	public void playSong()
	{
		ResourceLoader.core.getAudioHandler().playSound("song", soundPath, AudioHandler.MUSIC);
	}
	
	public void pauseSong()
	{
		ResourceLoader.core.getAudioHandler().stopSound(soundPath, AudioHandler.MUSIC);
	}

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return the soundPath
	 */
	public String getSoundPath() 
	{
		return soundPath;
	}

	/**
	 * @param soundPath the soundPath to set
	 */
	public void setSoundPath(String soundPath) 
	{
		this.soundPath = soundPath;
	}

	/**
	 * @return the albumArt
	 */
	public Image getAlbumArt() 
	{	
		return albumArt;
	}

	/**
	 * @param albumArt the albumArt to set
	 */
	public void setAlbumArt(Image albumArt) 
	{
		this.albumArt = albumArt;
	}
}