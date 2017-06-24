package com.pixelapocalypse.objects;

import java.awt.Graphics;
import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.entity.Player;
import com.engine.graphics.AnimationSet;

public class PAPlayer extends Player
{
	private int worldPosX = 0;
	private int worldPosY = 0;
	
	public PAPlayer(float xPos, float yPos, int id, Image[] images, boolean usePhysics, AnimationSet ani, String name)
	{
		super(xPos, yPos, id, images, usePhysics, ani, name);
		
		width = bImage.getWidth();
		height = bImage.getHeight();
		
		movingDir = CoreObject.NODIR;
		facingDir = CoreObject.EAST;
		image = images[0];
	}

	@Override
	public void tick()
	{
		if (!entityDead)
		{
			xPos += xVel;
			yPos += yVel;
			
			// Determine the direction the player is facing
			if (movingDir == CoreObject.EAST)
			{
				facingDir = CoreObject.EAST;
				image = images[0];
			}
			if (movingDir == CoreObject.WEST)
			{
				facingDir = CoreObject.WEST;
				image = images[1];
			}
			if (movingDir == CoreObject.NORTH)
			{
				facingDir = CoreObject.NORTH;
				image = images[2];
			}
			if (movingDir == CoreObject.SOUTH)
			{
				facingDir = CoreObject.SOUTH;
				image = images[3];
			}
			if (movingDir == CoreObject.NORTH_EAST)
			{
				facingDir = CoreObject.NORTH_EAST;
				image = images[0];
			}
			if (movingDir == CoreObject.SOUTH_EAST)
			{
				facingDir = CoreObject.SOUTH_EAST;
				image = images[0];
			}
			if (movingDir == CoreObject.SOUTH_WEST)
			{
				facingDir = CoreObject.SOUTH_WEST;
				image = images[1];
			}
			if (movingDir == CoreObject.NORTH_WEST)
			{
				facingDir = CoreObject.NORTH_WEST;
				image = images[1];
			}
			if (movingDir == CoreObject.WEST_EAST)
			{
				facingDir = CoreObject.EAST;
				image = images[0];
			}
			if (movingDir == CoreObject.NORTH_SOUTH)
			{
				facingDir = CoreObject.SOUTH;
				image = images[3];
			}
			
			fall();
			
			if (aniSet != null)
			{
				aniSet.getAnimation(0).runAnimation(); // Right
				aniSet.getAnimation(1).runAnimation(); // Left
				aniSet.getAnimation(2).runAnimation(); // Forward
				aniSet.getAnimation(3).runAnimation(); // Back
			}
		}
		else
		{
			
		}
	}

	@Override
	public void renderObject(Graphics g)
	{
		if (!entityDead)
		{
			if (aniSet != null)
			{
				if (jumping)
				{
					/*
					if (movingDir == GameObject.EAST)
						aniSet.getAnimation(2).drawAnimation(g, xPos, yPos);
					else if (movingDir == GameObject.WEST)
						aniSet.getAnimation(3).drawAnimation(g, xPos, yPos);
					else
					{
						if (facingDir == GameObject.EAST)
							aniSet.getAnimation(2).drawAnimation(g, xPos, yPos);
						else if (facingDir == GameObject.WEST)
							aniSet.getAnimation(3).drawAnimation(g, xPos, yPos);
					}
					*/
				}
				
				if (movingDir == CoreObject.EAST && !jumping)
					aniSet.getAnimation(0).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.WEST && !jumping)
					aniSet.getAnimation(1).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.NORTH && !jumping)
					aniSet.getAnimation(2).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.SOUTH && !jumping)
					aniSet.getAnimation(3).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.NORTH_EAST && !jumping)
					aniSet.getAnimation(0).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.SOUTH_EAST && !jumping)
					aniSet.getAnimation(0).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.SOUTH_WEST && !jumping)
					aniSet.getAnimation(1).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.NORTH_WEST && !jumping)
					aniSet.getAnimation(1).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.NORTH_SOUTH && !jumping)
					g.drawImage(image, (int)xPos, (int)yPos, null);
				else if (movingDir == CoreObject.WEST_EAST && !jumping)
					g.drawImage(image, (int)xPos, (int)yPos, null);
				else
				{
					g.drawImage(image, (int)xPos, (int)yPos, null);
				}
			}
			else
			{
				g.drawImage(image, (int)xPos, (int)yPos, null);
			}
		}
	}
	
	@Override
	protected void die()
	{
		if (!PAObjects.player.isEntityDead())
		{
			entityDead = true;
		}
	}

	/**
	 * @return the worldPosX
	 */
	public int getWorldPosX()
	{
		return worldPosX;
	}

	/**
	 * @return the worldPosY
	 */
	public int getWorldPosY()
	{
		return worldPosY;
	}
}
