package com.engine.main;

/**
 * <h1>CoreClock</h1>
 * CoreClock is a controlled clock that updates
 * and renders the project's resources. 
 * <br> 
 * The rate at which the clock updates the project 
 * is based upon tps (core.getTps()).  
 * <br>
 * The rate at which the clock renders the project
 * is based upon fps (core.getFps()).
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class CoreClock implements Runnable
{
	/*
	 * Core Variables
	 */
	private final Core core;
	
	/*
	 * Clock Variables
	 */
	private int fps; // The frames per second
	private int tps; // The ticks per second
	private boolean running = false; // Is this clock running
	private boolean shouldRender = false; // Should a render update occur
	private int ticks = 0; // The number of ticks that are actually being updated per second
	private int frames = 0; // The number of frames that are actually being rendered per second
	private int topTicks = 0; // The total number of ticks that occurred last second
	private int topFrames = 0; // The total number of frames that were rendered last second
	
	/*
	 * These are used for the fps clock
	 */
	private long lastTime = System.nanoTime(); // The last time checked in nanoseconds
	private double nsPerTick = 1000000000.0; // The number of nanoseconds per tick
	private long lastTimer = System.currentTimeMillis(); // The last time checked in milliseconds
	private double delta = 0; // A count variable used to track change in time
	
	/*
	 * These are used for the tps clock
	 */
	private long lastTime2 = System.nanoTime(); // The last time checked in nanoseconds
	private double nsPerTick2 = 1000000000.0; // The number of nanoseconds per tick
	private long lastTimer2 = System.currentTimeMillis(); // The last time checked in milliseconds
	private double delta2 = 0; // A count variable used to track change in time
	
	private int totalTickCount = 0; // The count of total ticks that have occurred during this session
	
	private int lastMouseMoveCount = 0; // The last recorded amount of mouse movements
	
	/**
	 * Create a new CoreClock.
	 * @param core
	 * @param fps
	 */
	public CoreClock(Core core, int fps, int tps)
	{
		this.core = core;
		this.fps = fps;
		this.tps = tps;
		
		running = true;
	}
	
	/**
	 * Starts the CoreClock timer
	 */
	public void run()
	{
		// Start comparison time in nanoseconds
		lastTime = System.nanoTime();
		// How many nanoseconds per tick the clock should update at
		// Based on fps
		nsPerTick = 1000000000.0 / fps;
		// Start comparison time in milliseconds
		lastTimer = System.currentTimeMillis();
		// Change in time between updates
		delta = 0;
		
		// Start comparison time in nanoseconds
		lastTime2 = System.nanoTime();
		// How many nanoseconds per tick the clock should update at
		// Based on fps
		nsPerTick2 = 1000000000.0 / tps;
		// Start comparison time in milliseconds
		lastTimer2 = System.currentTimeMillis();
		// Change in time between updates
		delta2 = 0;
		
		// Waits until the project is loaded
		while(core.isLoading())
		{
			try
			{
				Thread.sleep(10);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Start of the clock loop
		while(running)
		{
			/*
			 * FPS Timer
			 */
			
			// The current system time in nanoseconds (resets on update)
			long now = System.nanoTime();
			// Update delta every update based on difference in time
			delta += (now - lastTime) / nsPerTick;
			// Set the start comparison time to the last tested time
			lastTime = now;
			// Turn rendering off
			shouldRender = false;
			
			// Enters loop if an update should occur (fps ticks have occurred in a second)
			while(delta >= 1)
			{
				delta -= 1; // Reset delta for next update
				shouldRender = true; // Allow rendering
			}
			
			// If an update occurs, render graphics
			if (shouldRender)
			{
				render();
				
				frames++; // Increment frames
			}
			
			// If a second has gone by, reset all counts
			if (System.currentTimeMillis() - lastTimer >= 1000)
			{
				//System.out.println("Frames: " + frames + " Ticks: " + ticks);
				lastTimer += 1000;
				
				topFrames = frames;
				
				frames = 0;
			}
			
			/*
			 * Ticks Timer
			 */
			
			// The current system time in nanoseconds (resets on update)
			long now2 = System.nanoTime();
			// Update delta every update based on difference in time
			delta2 += (now2 - lastTime2) / nsPerTick2;
			// Set the start comparison time to the last tested time
			lastTime2 = now2;
			
			// Enters loop if an update should occur (tps ticks have occurred in a second)
			while(delta2 >= 1)
			{
				tick();
				
				totalTickCount++;
				ticks++; // Increment ticks
				delta2 -= 1; // Reset delta for next update
			}
			
			// Update half as often as a normal tick
			if ((ticks % 2) == 0)
			{
				halfTick();
			}
			
			// If a second has gone by, reset all counts
			if (System.currentTimeMillis() - lastTimer2 >= 1000)
			{
				lastTimer2 += 1000;
				
				topTicks = ticks;
				
				ticks = 0;
			}
		}
	}
	
	/**
	 * Perform a tick update.  This updates various
	 * project methods that perform basic updates.
	 * Rendering should NOT be done here.
	 * <br><br>
	 * This method is called automatically every tick
	 * (tps times per second)
	 */
	public void tick()
	{
		if (core.getCanvas().isScreenLoaded())
		{
			// If there is a Screen being shown, run it's onUpdate() method
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen().onUpdate();
			
			// If a Camera exists, tick it
			if (core.getCamera() != null && core.usesCamera())
				core.getCamera().tick();
			
			// Only update these if the project uses worlds
			if (core.usesWorld())
			{
				// Update all of the Tiles
				core.getWorldHandler().tickTiles();
			}
			
			// Tick all of the CoreObjects
			if (core.usesCoreObjects())
				core.getObjectHandler().tickCoreObjects();
		}
		
		if (core.isMouseMoving())
		{
			if (lastMouseMoveCount != core.getMouseMoveCount())
			{
				lastMouseMoveCount = core.getMouseMoveCount();
			}
			else
			{
				core.setMouseMoving(false);
				core.setMouseMoveCount(0);
			}
		}
		
		// Update the CoreCanvas
		core.getCanvas().update();
		
		// Update the AudioHandler
		core.getAudioHandler().update();
		
		// Update the ScreenHandler
		core.getScreenHandler().update();
	}
	
	/**
	 * Perform a tick update half as often.  This updates various
	 * project methods that perform basic updates.
	 * Rendering should NOT be done here.
	 * <br><br>
	 * This method is called automatically every other tick
	 * ((tps/2) times per second)
	 */
	public void halfTick()
	{
		
	}
	
	/**
	 * Perform a render update.  This tells
	 * the CoreCanvas to render graphics this update.
	 * <br><br>
	 * This method is called automatically every frame
	 * (fps times per second)
	 */
	public void render()
	{
		// Render the CoreCanvas
		core.getCanvas().render();
	}
	
	/**
	 * Get the total amount of ticks that have happened 
	 * in the project so far during this session.
	 * @return
	 */
	public int getTotalTickCount()
	{
		return totalTickCount;
	}

	/**
	 * Get the number of ticks that have occurred
	 * during this current update cycle.  This number
	 * could be anywhere between 0 and tps depending
	 * on when it is called.
	 * @return the ticks
	 */
	public int getTicks()
	{
		return ticks;
	}

	/**
	 * Get the number of frames that have occurred
	 * during this current update cycle.  This number
	 * could be anywhere between 0 and fps depending
	 * on when it is called.
	 * @return the frames
	 */
	public int getFrames()
	{
		return frames;
	}
	
	/**
	 * Get the total number of ticks that occurred
	 * during the last update.
	 * @return the top ticks
	 */
	public int getTopTicks()
	{
		return topTicks;
	}

	/**
	 * Get the total number of frames that occurred
	 * during the last update.
	 * @return the top frames
	 */
	public int getTopFrames()
	{
		return topFrames;
	}
}