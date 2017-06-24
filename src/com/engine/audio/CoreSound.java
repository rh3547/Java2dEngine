package com.engine.audio;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.engine.resources.ResourceLoader;

/**
 * <h1>CoreSound</h1>
 * A CoreSound is an object that holds
 * information needed to handle audio
 * playing, pausing, and stopping.
 * CoreSound is its own Thread.
 * The soundPath is the path
 * (after core.getResPath()) to the
 * audio clip.  soundType is the type
 * of sound (see AudioHandler).
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class CoreSound extends Thread
{
	/*
	 * Constructor Variables
	 */
	private Runnable runnable; // The process to run that plays the audio clip
	private String uniqueName = null; // The optional unique name of this audio clip
	private String soundPath; // The path (after core.getResPath()) to the audio clip
	private int soundType; // The type of sound (see AudioHandler)
	private int loopCount = 0; // How many times to loop this audio clip
	
	/*
	 * Audio
	 */
	private Clip clip;
	private FloatControl gainControl; // The gainControl to control volume
	private float volume;
	
	/*
	 * Control Variables
	 */
	private double duration; // The duration of the audio clip in seconds
	private boolean running = false;
	private int runtime = 0; // The amount of time this audio clip has been running
	private int loopTime = 0; // The number of loops that have occurred
	private ScheduledExecutorService executor; // Used to schedule the update of runtime every second
	
	private boolean paused = false; // Is this audio clip paused
	private int pausedFrame = 0;
	
	/**
	 * Create a new CoreSound.
	 * @param soundPath - the path (after core.getResPath()) to the audio clip
	 * @param soundType - the type of sound (see AudioHandler)
	 * @param loopCount - how many times to loop this audio clip
	 */
	public CoreSound(String soundPath, int soundType, int loopCount, float volume)
	{
		this.soundPath = soundPath;
		this.soundType = soundType;
		this.loopCount = loopCount;
		this.volume = volume;
		
		setupThread();
	}
	
	/**
	 * Create a new CoreSound with a unique name.
	 * @param uniqueName - the unique name of this audio clip
	 * @param soundPath - the path (after core.getResPath()) to the audio clip
	 * @param soundType - the type of sound (see AudioHandler)
	 * @param loopCount - how many times to loop this audio clip
	 */
	public CoreSound(String uniqueName, String soundPath, int soundType, int loopCount, float volume)
	{
		this.uniqueName = uniqueName;
		this.soundPath = soundPath;
		this.soundType = soundType;
		this.loopCount = loopCount;
		this.volume = volume;
		
		setupThread();
	}
	
	/**
	 * Set up the needed Runnable process
	 * and get the audio clip set.
	 */
	private void setupThread()
	{
		try // Get the duration of the given audio clip in seconds
		{ 
			// Obtain the proper audio file
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(ResourceLoader.core.getRespath() + soundPath));
			
			// Get the AudioFormat from the input stream
			AudioFormat format = inputStream.getFormat();
			
			long frames = inputStream.getFrameLength();
			duration = (frames + 0.0) / format.getFrameRate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		/*
		 * Set up the runnable process so it gets the
		 * the proper audio input stream and prepares the
		 * audio clip for playback.
		 */
		runnable = new Runnable() 
		{
			public void run()
		    {
				try 
				{
					clip = AudioSystem.getClip();
					
					// Obtain the proper audio file
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(ResourceLoader.core.getRespath() + soundPath));
					
					// Open the clip and prepare for play back
					clip.open(inputStream);
					
					// Get the volume control
					gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(volume);
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		};
		
		Runnable secondTimer = new Runnable() // Increase the runtime every second
		{
		    public void run() 
		    {
		    	if (running && !paused)
		    	{
					runtime += 1;
		    	
			    	if (runtime > duration)
			    	{
			    		runtime = 0;
			    		loopTime += 1;
			    		
			    		if (loopTime >= loopCount)
			    			running = false;
			    	}
		    	}
		    }
		};
		
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(secondTimer, 0, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * Start this thread.  This will
	 * run the Runnable and start playing
	 * the audio clip.  If wanting to
	 * resume play after being paused
	 * use resumePlaying() instead of this.
	 */
	@Override
	public void start()
	{
		if (this.getState() != Thread.State.RUNNABLE)
		{
			runnable.run();
			
			if (loopCount > 1)
				clip.loop(loopCount - 1);
			else
				clip.start();
			
			running = true;
			paused = false;
		}
	}
	
	/**
	 * Stop this Runnable and this Thread.
	 * This will stop the audio clip from
	 * playing.
	 */
	public void stopPlaying()
	{
		clip.stop();
		
		running = false;
		executor.shutdownNow();
	
		runtime = 0;
	}
	
	/**
	 * Pause the audio clip at its current point.
	 */
	public void pausePlaying()
	{
		paused = true;
		pausedFrame = clip.getFramePosition();
		clip.stop();
	}
	
	/**
	 * Resume the audio clip from where
	 * it was paused.
	 */
	public void resumePlaying()
	{
		if (paused)
		{
			paused = false;
			
			if (loopCount > 1)
				clip.loop((loopCount - 1) - loopTime);
			else
				clip.start();
			
			clip.setFramePosition(pausedFrame);
		}
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
	 * @return the soundType
	 */
	public int getSoundType() 
	{
		return soundType;
	}

	/**
	 * @param soundType the soundType to set
	 */
	public void setSoundType(int soundType) 
	{
		this.soundType = soundType;
	}

	/**
	 * @return the duration
	 */
	public double getDuration() 
	{
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(double duration) 
	{
		this.duration = duration;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() 
	{
		return running;
	}

	/**
	 * @return the runnable
	 */
	public Runnable getRunnable() 
	{
		return runnable;
	}

	/**
	 * @return the clip
	 */
	public Clip getClip() 
	{
		return clip;
	}

	/**
	 * @return the runtime
	 */
	public double getRuntime() 
	{
		return runtime;
	}

	/**
	 * @return the uniqueName
	 */
	public String getUniqueName() 
	{
		return uniqueName;
	}

	/**
	 * @param uniqueName the uniqueName to set
	 */
	public void setUniqueName(String uniqueName) 
	{
		this.uniqueName = uniqueName;
	}
	
	/**
	 * Set the volume of this clip.
	 * @param volume
	 */
	public void setVolume(float volume)
	{
		this.volume = volume;
		gainControl.setValue(volume);
	}
}