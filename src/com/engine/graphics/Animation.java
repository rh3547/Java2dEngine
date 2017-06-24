package com.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * <h1>Animation</h1>
 * An animation is a set of images
 * shown in succession to simulate
 * "movement".  An animation is
 * played at the given speed, which
 * determines the delay between
 * image changes.
 * <br>
 * <b>NOTE:</b> A higher speed number
 * will make the animation run
 * slower.
 * <br>
 * @author Ryan Hochmuth
 *
 */
public class Animation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int index = 0; // The index of the current frame
	private int count = 0; // A timing control variable
	private int speed; // The speed to show the images at
	private int frames; // How many frames are there in this animation
	
	private BufferedImage currentImage; // The current frame being shown
	private BufferedImage[] animation; // All frames of the animation
	
	/**
	 * Create a new animation.
	 * @param speed - the speed at which to show the animation.
	 * 				- NOTE: The higher the speed number, the slower
	 * 				- the animation
	 * @param animation - the set of images to show
	 */
	public Animation(int speed, BufferedImage[] animation)
	{
		this.speed = speed;
		this.animation = animation;
		this.frames = animation.length;
	}
	
	/**
	 * Run the animation.
	 */
	public void runAnimation()
	{
		count++;
		
		if (count > speed)
		{
			count = 0;
			
			nextFrame();
		}
	}
	
	/**
	 * Jump to the next frame of the animation.
	 */
	private void nextFrame()
	{
		for(int x = 0; x < frames; x++)
		{
			if (index == x)
				currentImage = animation[x];
		}
		
		index++;
		
		if (index > frames)
			index = 0;
	}
	
	/**
	 * Draw the animation on the screen.
	 * @param g
	 * @param xPos
	 * @param yPos
	 */
	public void drawAnimation(Graphics g, float xPos, float yPos)
	{
		g.drawImage(currentImage, (int)xPos, (int)yPos, null);
	}
}
