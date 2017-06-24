package com.cubeoTwo.tiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import com.cubeoTwo.main.CubeoTwoResources;
import com.engine.coreObject.CollisionHandler;
import com.engine.coreObject.CoreObject;
import com.engine.entity.Entity;
import com.engine.entity.Player;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;

public class TilePlatform extends Tile
{
	public TilePlatform(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name)
	{
		super(xPos, yPos, id, image, usePhysics, mapColor, name);
	}
	
	/**
	 * Create a new Tile with no specified position.
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public TilePlatform(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
	{
		super(id, image, usePhysics, mapColor, collisionType, name);
		
		this.mapColor = mapColor;
		this.collisionType = collisionType;
	}
	
	/**
	 * Create a new Tile based on another Tile.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public TilePlatform(float xPos, float yPos, Tile tile) 
	{
		super(yPos, yPos, tile);
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = tile.getId();
		this.image = tile.getImage();
		this.usePhysics = tile.doesUsePhysics();
		this.mapColor = tile.getMapColor();
		
		this.setCollisionType(tile.getCollisionType());
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
	}
	
	@Override
	public void collided(CoreObject obj, int collision)
	{
		// If the object's top, left, or right collides with another's bottom
		if (collision == CollisionHandler.DOWN_COL)
		{
			if (obj instanceof Entity)
			{
				// Allow the player to pass through the platform
				((Entity) obj).getFlags()[0] = true;
			}
			else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
			{
				
			}
		}
		
		// If the object's bottom, left, or right collides with another's top
		else if (collision == CollisionHandler.UP_COL)
		{
			if (obj instanceof Entity)
			{
				((Entity) obj).getFlags()[0] = false;
				
				if ((obj instanceof Player) && CubeoTwoResources.down.isPressed())
				{
					((Entity) obj).getFlags()[0] = true;
				}
				
				if (((Entity) obj).getFlags()[0] == false)
				{
					obj.setyVel(0); // Negate all y velocity
					// Set the object to be on top of the other
					obj.setyPos(getyPos() - obj.getObjectHeight());
				
					// If this object was jumping, it is not jumping any more
					obj.setJumping(false);
				}
			}
			else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
			{
				
			}
		}
		
		obj.altCollided(collision);
	}
	
	/**
	 * Get the top bounds of this GameObject.
	 * @return Rectangle The top bounds of this GameObject
	 */
	@Override
	public Rectangle getTopBounds()
	{
		return new Rectangle((int)xPos + 6, (int)yPos, width - 12, 10);
	}
	
	/**
	 * Get the bottom bounds of this GameObject.
	 * @return Rectangle The bottom bounds of this GameObject
	 */
	@Override
	public Rectangle getBottomBounds()
	{
		return new Rectangle((int)xPos, ((int)yPos + height - 10) - 10, width, 10);
	}
	
	/**
	 * Get the left bounds of this GameObject.
	 * @return Rectangle The left bounds of this GameObject
	 */
	@Override
	public Rectangle getLeftBounds()
	{
		return new Rectangle((int)xPos, (int)yPos, 6, height - 10);
	}
	
	/**
	 * Get the right bounds of this GameObject.
	 * @return Rectangle The right bounds of this GameObject
	 */
	@Override
	public Rectangle getRightBounds()
	{
		return new Rectangle((int)xPos + width - 6, (int)yPos, 6, height - 10);
	}
}
