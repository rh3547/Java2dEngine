package com.musicplayer.audio;

import java.util.ArrayList;
import java.util.List;

public class SongDatabase 
{
	private List<Song> songs = new ArrayList<Song>();
	private int currentIndex = 0;
	
	public SongDatabase()
	{
		
	}
	
	public void addSong(Song song)
	{
		songs.add(song);
	}
	
	public void addNewSong(String name, String path, String art)
	{
		songs.add(new Song(name, path, art));
	}
	
	public Song getSong()
	{
		if (songs.size() > 0)
			return songs.get(currentIndex);
		
		return null;
	}
	
	public int getIndex()
	{
		return currentIndex;
	}
	
	public void nextSong()
	{
		if ((currentIndex + 1) <= songs.size())
			currentIndex++;
	}
	
	public void prevSong()
	{
		if ((currentIndex - 1) >= 0)
			currentIndex--;
	}
}
