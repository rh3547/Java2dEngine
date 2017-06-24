package com.engine.graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <h1>WORK IN PROGRESS</h1>
 * ----------------
 * <h1>Particle</h1>
 * A particle is a small graphic with
 * stats like velocities and life span
 * to simulate particle effects like
 * smoke, fire, rain, etc.  A Particle
 * however is not an entire set of rain
 * or a whole smoke cloud, a single Particle
 * could be something like a drop of rain
 * or a spec of dust.  Particles are combined
 * and emitted by ParticleGenerator to create
 * full particle effects.
 * <br>
 * <h1>Particle Parameters</h1>
 * <ul>
 * <li>xPos: The x position of the particle.</li>
 * <li>yPos: The y position of the particle.</li>
 * </ul>
 * <br>
 * @author Ryan Hochmuth
 *
 */
public class Particle // TODO
{
	/*
	 * Render Data
	 */
	private BufferedImage image;
	private float opacity = 1.0F;
	
	/*
	 * Position Data
	 */
	private float xPos; // The x position of the particle
	private float yPos; // The y position of the particle
	private float lastXAmount = 0; // The amount of x movement the particle last did
	private float lastYAmount = 0; // The amount of y movement the particle last did
	
	/**
	 * Create a new particle.
	 * @param image - the graphical image to show
	 * @param xPos - the starting x position
	 * @param yPos - the starting y position
	 */
	public Particle(BufferedImage image, float xPos, float yPos)
	{
		this.image = image;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Draw this particle on screen at its x
	 * and y position.
	 * @param g
	 * @param newOpacity
	 */
	public void drawParticle(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g2.drawImage(image, (int)xPos, (int)yPos, null);
	}
	
	/**
	 * Updates the x position of this particle
	 * by the passed amount.
	 * @param amount - the amount to move the particle
	 */
	public void updateXPos(float xPos, float amount)
	{
		this.xPos = this.xPos + amount;
		this.lastXAmount = amount;
	}
	
	/**
	 * Updates the y position of this particle
	 * by the passed amount.
	 * @param amount - the amount to move the particle
	 */
	public void updateYPos(float yPos, float amount)
	{
		this.yPos = this.yPos + amount;
		this.lastYAmount = amount;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	/**
	 * @return the xPos
	 */
	public float getxPos()
	{
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public float getyPos()
	{
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}

	/**
	 * @return the lastXAmount
	 */
	public float getLastXAmount()
	{
		return lastXAmount;
	}

	/**
	 * @param lastXAmount the lastXAmount to set
	 */
	public void setLastXAmount(float lastXAmount)
	{
		this.lastXAmount = lastXAmount;
	}

	/**
	 * @return the lastYAmount
	 */
	public float getLastYAmount()
	{
		return lastYAmount;
	}

	/**
	 * @param lastYAmount the lastYAmount to set
	 */
	public void setLastYAmount(float lastYAmount)
	{
		this.lastYAmount = lastYAmount;
	}

	/**
	 * @return the opacity
	 */
	public float getOpacity() 
	{
		return opacity;
	}

	/**
	 * @param opacity the opacity to set
	 */
	public void setOpacity(float opacity) 
	{
		this.opacity = opacity;
	}
}
