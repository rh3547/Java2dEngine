package com.engine.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <h1>WORK IN PROGRESS</h1>
 * --------------------
 * <h1>Light</h1>
 * A Light is anything that should emit
 * a lighting effect in a project World.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class Light // TODO
{
	private int xPos; // The x position of this light
	private int yPos; // The y position of this light
	private int radius; // The radius that light should be emitted 
	
	private BufferedImage image; // The buffered image that is used to simulate the light
	private Graphics2D g2d; // The Graphics2D that will draw the light
	
	/**
	 * Create a new Light.
	 * @param xPos - the x position of the light
	 * @param yPos - the y position of the light
	 * @param radius - the radius of the light
	 * @param luminosity - the brightness of the light
	 */
	public Light(int xPos, int yPos, int radius, int luminosity)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
		
		// Create the image based on the given parameters
		image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D)image.getGraphics();
		
		/*
		 * Set variables to make the light fade as it spreads
		 */
		int step = 4;
		int numSteps = radius / step;
		
		/*
		 * Draw the proper graphics to simulate the light
		 */
		g2d.setColor(new Color(231, 222, 74, luminosity));
		for(int x = 0; x < numSteps; x++)
		{
			g2d.fillOval(radius - x * step, radius - x * step, x * step * 2, x * step * 2);
		}
	}
	
	/**
	 * Draw this light on the screen.
	 * @param g
	 */
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, xPos - radius, yPos - radius, null);
	}
}
