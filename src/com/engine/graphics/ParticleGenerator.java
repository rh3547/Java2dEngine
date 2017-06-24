package com.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <h1>WORK IN PROGRESS</h1>
 * --------------------
 * <h1>ParticleGenerator</h1>
 * ParticleGenerator generates Particles
 * and handles how they act.  This is used
 * to create particle effects such as
 * rain, dust, smoke, etc.
 * 
 * <h1>Particle Generator Parameters:</h1>
 * <ul>
 * <li>Spread: The typical distance between each particle</li>
 * <li>Rate: The rate at which particles are emitted</li>
 * <li>Speed: The speed of particle movement</li>
 * <li>Duration: The amount of time to generate particles</li>
 * <li>Scope: The particle generation type.  (See ParticleGenerator constants)</li>
 * </ul>
 * 
 * <h1>Generation Scopes:</h1>
 * <ul>
 * <li>CONE_UP: Emit particles in an upward cone with point facing down</li>
 * <li>CONE_DOWN: Emit particles in a downward cone with point facing up</li>
 * <li>CONE_LEFT: Emit particles in a cone with point facing right</li>
 * <li>CONE_RIGHT: Emit particles in a cone with point facing left</li>
 * <li>CIRCLE_SINGLE: Emit particles in a single circular</li>
 * </ul>
 * <br>
 * @author Ryan Hochmuth
 *
 */
public class ParticleGenerator // TODO
{
	/**
	 * <b>CONE_UP:</b> Emit particles in an upward cone with point facing down
	 */
	public static final int CONE_UP = 0;
	/**
	 * <b>CONE_DOWN:</b> Emit particles in a downward cone with point facing up
	 */
	public static final int CONE_DOWN = 1;
	/**
	 * <b>CONE_LEFT:</b> Emit particles in a cone with point facing right
	 */
	public static final int CONE_LEFT = 2;
	/**
	 * <b>CONE_RIGHT:</b> Emit particles in a cone with point facing left
	 */
	public static final int CONE_RIGHT = 3;
	/**
	 * <b>CIRCLE_SINGLE:</b> Emit particles in a single circular
	 */
	public static final int CIRCLE_SINGLE = 4;
	
	/*
	 * Particle Data
	 */
	private BufferedImage[] images; // The possible images of particles
	private float spread; // The amount of spread between particles
	private float rate; // The rate at which to emit particles
	private float speed; // The movement speed of particles
	private float duration; // How long to generate particles
	private int scope; // The scope at which to emit particles
	
	/*
	 * Generator Data
	 */
	private List<Particle> particles = new ArrayList<Particle>(); // The alive particles being generated
	private boolean running = false; // Is this generator running
	
	/*
	 * Timing
	 */
	private int time = 0;
	private int count; // The amount of particles currently "alive"
	private int speedCount = 0;
	
	/**
	 * Create a new Particle generator.
	 * @param images
	 * @param spread
	 * @param rate
	 * @param duration
	 * @param scope
	 */
	public ParticleGenerator(BufferedImage[] images, float spread, float rate, float speed, float duration, int scope)
	{
		this.images = images;
		this.spread = spread;
		this.rate = rate;
		this.speed = speed;
		this.duration = duration;
		this.scope = scope;
		
		this.count = (int)duration * (int)rate;
		this.speedCount = (int)speed;
	}
	
	/**
	 * Start the generator.  This doesn't
	 * actually generate particles, it's just
	 * used to control the generation.  Like
	 * turning it on.
	 */
	public void startGenerator()
	{
		if (!running)
		{
			running = true;
		}
	}
	
	/**
	 * Stop the generator.  Note this will
	 * clear all of the current particles.
	 */
	public void stopGenerator()
	{
		if (running)
		{
			running = false;
			time = 0;
			particles.clear();
		}
	}

	/**
	 * Generate and randomize particles.  This is where the
	 * actual generation of particles happens.  In
	 * order for this to work, this method must be continuously
	 * called in a render method.
	 * @param g - the graphics to use for drawing
	 * @param xPos - the x position to draw at
	 * @param yPos - the y position to draw at
	 */
	public void generateParticles(Graphics g, float xPos, float yPos)
	{
		// Only generate when supposed to
		if (running)
		{
			Random rand = new Random();
			int imageNum = 0; // The randomized image for a new particle
			
			// Increment time for duration control
			time++;
			
			// Generate particles if during the duration
			if (time < duration * 60)
			{
				// Increment the rate count
				count++;
				
				// Generate a new particle if the count exceeds
				// or equals the rate of this particle generator
				if (count >= duration * rate)
				{
					// Reset the count
					count = 0;
					
					// Randomize a new imageNum
					imageNum = rand.nextInt(images.length);
					
					// Create a new particle
					particles.add(new Particle(images[imageNum], xPos, yPos));
				}
				
				speedCount++;
				
				if (speedCount >= speed)
				{
					speedCount = 0;
					
					// Increment through every particle currently in this
					// particle generator
					for (int x = 0; x < particles.size(); x++)
					{
						// The amount to offset the particle
						int xAmount = 0;
						int yAmount = 0;
						
						// Change the particle path based on the generators scope
						switch(scope)
						{
							case 0: // CONE_UP
								switch(rand.nextInt(2))
								{
									case 0:
										xAmount = -rand.nextInt((int)spread);
										break;
										
									case 1:
										xAmount = rand.nextInt((int)spread);
										break;
								}
								
								yAmount = -(rand.nextInt((int)spread));
								break;
								
							case 1: // CONE_DOWN
								switch(rand.nextInt(2))
								{
									case 0:
										xAmount = -rand.nextInt((int)spread);
										break;
										
									case 1:
										xAmount = rand.nextInt((int)spread);
										break;
								}
								
								yAmount = (rand.nextInt((int)spread) + (int)spread);
								break;
								
							case 2: // CONE_LEFT
								xAmount = -(rand.nextInt((int)spread));
								
								switch(rand.nextInt(2))
								{
									case 0:
										yAmount = -(rand.nextInt((int)spread) + (int)spread);
										break;
										
									case 1:
										yAmount = (rand.nextInt((int)spread) + (int)spread);
										break;
								}
								break;
								
							case 3: // CONE_RIGHT
								xAmount = (rand.nextInt((int)spread));
								
								switch(rand.nextInt(2))
								{
									case 0:
										yAmount = -(rand.nextInt((int)spread) + (int)spread);
										break;
										
									case 1:
										yAmount = (rand.nextInt((int)spread) + (int)spread);
										break;
								}
								break;
								
							case 4: // CIRCLE_SINGLE
								switch(rand.nextInt(2))
								{
									case 0:
										xAmount = -rand.nextInt((int)spread);
										break;
										
									case 1:
										xAmount = rand.nextInt((int)spread);
										break;
								}
								
								switch(rand.nextInt(2))
								{
									case 0:
										yAmount = -(rand.nextInt((int)spread) + (int)spread);
										break;
										
									case 1:
										yAmount = (rand.nextInt((int)spread) + (int)spread);
										break;
								}
								break;
						}
						
						// Update the particle positions
						particles.get(x).updateXPos(xPos, xAmount);
						particles.get(x).updateYPos(yPos, yAmount);
					}
				}
				
				for (int x = 0; x < particles.size(); x++)
				{
					// Draw the particle
					particles.get(x).drawParticle(g);
				}
			}
			else // When the duration is over
			{
				// Stop the particles
				stopGenerator();
			}
		}
	}

	/**
	 * @return the running
	 */
	public boolean isRunning()
	{
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}
}
