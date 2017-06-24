package com.engine.audio;

import java.util.ArrayList;
import java.util.List;

import com.engine.main.Core;

/**
 * <h1>AudioHandler</h1>
 * AudioHandler is the game's audio engine.
 * All sound is processed here.
 * <br>
 * <h1>Functionality:</h1>
 * The functionality of AudioHandler
 * is basic.  It can currently
 * play sounds of two types, music
 * or SFX.
 * <h1>Current Features:</h1>
 * <ul>
 * <li><b>Play</b> an audio file</li>
 * <li><b>loop</b> an audio file</li>
 * <li><b>Stop</b> an audio file:
 * <br>
 * Methods:
 * <ul>
 * <li>stopSound(): Stops every playing audio clip
 * of the same audio file.</li>
 * <li>stopUniqueSound(): Stops only the playing clip/clips
 * with the given unique name.  Used to handle individual
 * sound clips of the same audio file.</li>
 * </ul>
 * </li>
 * <li><b>Pause</b> an audio file:
 * Methods:
 * <ul>
 * <li>pauseSound(): Pauses every playing audio clip
 * of the same audio file.</li>
 * <li>pauseUniqueSound(): Pauses only the playing clip/clips
 * with the given unique name.  Used to handle individual
 * sound clips of the same audio file.</li>
 * </ul></li>
 * <li><b>Resume</b> a paused audio file:
 * Methods:
 * <ul>
 * <li>resumeSound(): Resumes every playing audio clip
 * of the same audio file.</li>
 * <li>resumeUniqueSound(): Resumes only the playing clip/clips
 * with the given unique name.  Used to handle individual
 * sound clips of the same audio file.</li>
 * </ul></li>
 * </ul>
 * <br><br>
 * <b>NOTE:</b> Only .WAV files are supported as of v1.2.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class AudioHandler // TODO version reference
{
	/*
	 * Sound Types
	 */
	/**
	 * SFX: Any sound that is generally short and considered to be an effect
	 */
	public static final int SFX = 0;
	/**
	 * MUSIC: Any sound that is generally longer and considered to be musical
	 */
	public static final int MUSIC = 1;
	
	/*
	 * Audio Variables
	 */
	private float musicVolume = 0; // The volume of MUSIC sounds
	private float sfxVolume = 0; // The volume of SFX sounds
	
	private List<CoreSound> soundList = new ArrayList<CoreSound>();
	
	/**'
	 * Create a new AudioHandler.
	 * @param core
	 */
	public AudioHandler(Core core)
	{
		
	}
	
	/**
	 * A general update method called every
	 * tick from CoreClock.  This update
	 * method is used to remove finished
	 * sounds from the soundList.
	 */
	public void update()
	{
		for (int x = 0; x < soundList.size(); x++)
		{
			if (!soundList.get(x).isRunning())
			{
				soundList.get(x).stopPlaying();
				
				try 
				{
					soundList.get(x).join();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				soundList.remove(x);
			}
			
			/*
			 * Console print used for testing.
			 */
			/*
			if (x > 0 || x <= soundList.size() - 1)
			{
				try
				{
					System.out.println(x + " - " + soundList.get(x).getSoundPath() + " - " + soundList.get(x).getRuntime());
				}
				catch (IndexOutOfBoundsException e)
				{
					
				}
			}
			*/
		}
		//System.out.println("");
	}
	
	/**
	 * Play the sound at soundPath with the given name once.
	 * @param uniqueName
	 * @param soundPath
	 * @param soundType
	 */
	public void playSound(String uniqueName, String soundPath, int soundType) 
	{
		float volume = 0F;
		
		// Set the volume for the audio clip
		switch(soundType)
		{
			case AudioHandler.SFX:
				volume = sfxVolume;
				break;
				
			case AudioHandler.MUSIC:
				volume = musicVolume;
				break;
		}
		
		CoreSound sound = new CoreSound(uniqueName, soundPath, soundType, 1, volume); // Create a new CoreSound
		
		sound.start(); // Play the sound
		soundList.add(sound); // Add the sound to the list of playing sounds
	}
	
	/**
	 * Loop the sound at soundPath as many times as count.
	 * @param uniqueName
	 * @param soundPath
	 * @param count
	 * @param soundType
	 */
	public void loopSound(String uniqueName, String soundPath, int soundType, int count) 
	{
		float volume = 0F;
		
		// Set the volume for the audio clip
		switch(soundType)
		{
			case AudioHandler.SFX:
				volume = sfxVolume;
				break;
				
			case AudioHandler.MUSIC:
				volume = musicVolume;
				break;
		}
		
		CoreSound sound = new CoreSound(uniqueName, soundPath, soundType, count, volume); // Create a new CoreSound
		
		sound.start(); // Play the sound
		soundList.add(sound); // Add the sound to the list of playing sounds
	}
	
	/**
	 * Stop the sound playing at soundPath.
	 * <br>
	 * <b>NOTE:</b> If multiple of the
	 * same sounds are playing, they will all
	 * be stopped.  To stop a specific one,
	 * stop it with stopUniqueSound().
	 * @param soundPath
	 * @param soundType
	 */
	public void stopSound(String soundPath, int soundType) 
	{
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound
		{
			/*
			 * If a sound in the soundList matches the given
			 * parameters, stop playing it, kill its thread
			 * and remove it from the soundList.
			 */
			if (soundList.get(x).getSoundPath().equals(soundPath) &&
				soundList.get(x).getSoundType() == soundType)
			{
				soundList.get(x).stopPlaying();
				
				try 
				{
					soundList.get(x).join();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				soundList.remove(x);
			}
		}
	}
	
	/**
	 * Stop the sound playing at soundPath.
	 * This method will stop only the sound
	 * with the given unique name.
	 * @param uniqueName
	 * @param soundPath
	 * @param soundType
	 */
	public void stopUniqueSound(final String uniqueName, String soundPath, int soundType) 
	{
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound
		{
			/*
			 * If a sound in the soundList matches the given
			 * parameters, stop playing it, kill its thread
			 * and remove it from the soundList.
			 */
			if (soundList.get(x).getUniqueName().equals(uniqueName) &&
				soundList.get(x).getSoundPath().equals(soundPath) &&
				soundList.get(x).getSoundType() == soundType)
			{
				soundList.get(x).stopPlaying();
				
				try 
				{
					soundList.get(x).join();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				soundList.remove(x);
			}
		}
	}
	
	/**
	 * Pause the sound playing at soundPath.
	 * <br>
	 * <b>NOTE:</b> To resume this sound, don't
	 * use playSound(), use resumeSound().
	 * <br>
	 * <b>NOTE:</b> If multiple of the
	 * same sounds are playing, they will all
	 * be paused.  To pause a specific one,
	 * pause it with pauseUniqueSound().
	 * @param soundPath
	 * @param soundType
	 */
	public void pauseSound(String soundPath, int soundType)
	{
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound
		{
			/*
			 * If a sound in the soundList matches the given
			 * parameters, pause playing it.
			 */
			if (soundList.get(x).getSoundPath().equals(soundPath) &&
				soundList.get(x).getSoundType() == soundType)
			{
				soundList.get(x).pausePlaying();
			}
		}
	}
	
	/**
	 * Pause the sound playing at soundPath.
	 * This method will pause only the sound
	 * with the given unique name.
	 * <br>
	 * <b>NOTE:</b> To resume this sound, don't
	 * use playSound(), use resumeUniqueSound().
	 * @param uniqueName
	 * @param soundPath
	 * @param soundType
	 */
	public void pauseUniqueSound(String uniqueName, String soundPath, int soundType)
	{
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound
		{
			/*
			 * If a sound in the soundList matches the given
			 * parameters, pause playing it.
			 */
			if (soundList.get(x).getUniqueName().equals(uniqueName) &&
				soundList.get(x).getSoundPath().equals(soundPath) &&
				soundList.get(x).getSoundType() == soundType)
			{
				soundList.get(x).pausePlaying();
			}
		}
	}
	
	/**
	 * Resume the sound at soundPath.
	 * <br>
	 * <b>NOTE:</b> If there are multiple
	 * of the same sounds paused, they will all
	 * be resumed.  To resume a specific one,
	 * resume it with resumeUniqueSound().
	 * @param soundPath
	 * @param soundType
	 */
	public void resumeSound(String soundPath, int soundType)
	{
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound
		{
			/*
			 * If a sound in the soundList matches the given
			 * parameters, resume playing it.
			 */
			if (soundList.get(x).getSoundPath().equals(soundPath) &&
				soundList.get(x).getSoundType() == soundType)
			{
				soundList.get(x).resumePlaying();
			}
		}
	}
	
	/**
	 * Resume the sound playing at soundPath.
	 * This method will resume only the sound
	 * with the given unique name.
	 * @param uniqueName
	 * @param soundPath
	 * @param soundType
	 */
	public void resumeUniqueSound(String uniqueName, String soundPath, int soundType)
	{
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound
		{
			/*
			 * If a sound in the soundList matches the given
			 * parameters, resume playing it.
			 */
			if (soundList.get(x).getUniqueName().equals(uniqueName) &&
				soundList.get(x).getSoundPath().equals(soundPath) &&
				soundList.get(x).getSoundType() == soundType)
			{
				soundList.get(x).resumePlaying();
			}
		}
	}
	
	/**
	 * @return the musicVolume
	 */
	public float getMusicVolume() 
	{
		return musicVolume;
	}

	/**
	 * @return the sfxVolume
	 */
	public float getSfxVolume() 
	{
		return sfxVolume;
	}

	/**
	 * Set the music volume of audio play back.
	 * To reduce volume give a negative number (ex. -10.0f).
	 * To increase volume give a positive number (ex. 6.0f).
	 * <br>
	 * <b>NOTE:</b> When increasing volume, a maximum of 6.0f can be given.
	 * @param volume
	 */
	public void setMusicVolume(float musicVolume)
	{
		this.musicVolume = musicVolume;
		
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound and update the volume
		{
			if (soundList.get(x).getSoundType() == MUSIC)
			{
				soundList.get(x).setVolume(musicVolume);
			}
		}
	}
	
	/**
	 * Set the sfx volume of audio play back.
	 * To reduce volume give a negative number (ex. -10.0f).
	 * To increase volume give a positive number (ex. 6.0f).
	 * <br>
	 * <b>NOTE:</b> When increasing volume, a maximum of 6.0f can be given.
	 * @param volume
	 */
	public void setSfxVolume(float sfxVolume)
	{
		this.sfxVolume = sfxVolume;
		
		for (int x = 0; x < soundList.size(); x++) // Search through every playing sound and update the volume
		{
			if (soundList.get(x).getSoundType() == SFX)
			{
				soundList.get(x).setVolume(sfxVolume);
			}
		}
	}
}
