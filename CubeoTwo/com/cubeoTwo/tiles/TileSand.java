package com.cubeoTwo.tiles;

import java.awt.Color;
import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Chunk;
import com.engine.world.Tile;

public class TileSand extends Tile
{
	public TileSand(int xPos, int yPos, int id, Image image, boolean usePhysics, Color mapColor, String name)
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
	public TileSand(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
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
	public TileSand(float xPos, float yPos, Tile tile) 
	{
		super(yPos, yPos, tile);
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = tile.getId();
		this.image = tile.getImage();
		this.usePhysics = true;
		this.mapColor = tile.getMapColor();
		
		this.setCollisionType(tile.getCollisionType());
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		yVel = 0.98F;
	}
	
	@Override
	public void tick()
	{
		xPos += xVel;
		yPos += yVel;
		
		if (yVel != 0)
			fall();
	}
	
	/**
	 * Check all collisions with this GameObject.
	 */
	public void checkCollision()
	{
		if (collisionType == CoreObject.COLLIDE) // Only test collisions if this object has collisions enabled
		{
			// Test against all Tiles
			for (int x = 0; x < ResourceLoader.core.getWorldHandler().getChunks().size(); x++)
			{
				Chunk chunk = ResourceLoader.core.getWorldHandler().getChunks().get(x);
				
				if (chunk.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
					ResourceLoader.core.getChunkLoadDistance() &&
					chunk.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
					ResourceLoader.core.getChunkLoadDistance() 
					&&
					chunk.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
					ResourceLoader.core.getChunkLoadDistance() &&
					chunk.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
					ResourceLoader.core.getChunkLoadDistance())
				{
					for(int y = 0; y < chunk.getTiles().size(); y++)
					{
						CoreObject obj = chunk.getTiles().get(y);
						
						// If the object being tested has collisions enabled and isn't this object
						if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this)
						{
							// If this object's bottom collides with another's top
							if (getBottomBounds().intersects(obj.getTopBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									yVel = 0; // Negate all y velocity
									// Set this object to be on top of the other
									yPos = obj.getyPos() - height;
								
									// If this object was jumping, it is not jumping any more
									jumping = false;
								}
							}
						}
					}
				}
			}
			
			// Test against all other GameObjects
			for (int x = 0; x < ResourceLoader.core.getObjectHandler().getCoreObjects().size(); x++)
			{
				if (ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getxPos() > 
					ResourceLoader.core.getCamera().getFollowObject().getxPos() - ResourceLoader.core.getChunkLoadDistance() &&
					ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getxPos() < 
					ResourceLoader.core.getCamera().getFollowObject().getxPos() + ResourceLoader.core.getChunkLoadDistance() 
					&&
					ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getyPos() > 
					ResourceLoader.core.getCamera().getFollowObject().getyPos() - ResourceLoader.core.getChunkLoadDistance() &&
					ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getyPos() < 
					ResourceLoader.core.getCamera().getFollowObject().getyPos() + ResourceLoader.core.getChunkLoadDistance())
				{
					CoreObject obj = ResourceLoader.core.getObjectHandler().getCoreObjects().get(x);
					// If the object being tested has collisions enabled and isn't this object
					if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this)
					{
						// If this object's bottom collides with another's top
						if (getBottomBounds().intersects(obj.getTopBounds()))
						{
							if (obj.getCollisionType() == CoreObject.COLLIDE)
							{
								yVel = 0; // Negate all y velocity
								// Set this object to be on top of the other
								yPos = obj.getyPos() - height;
							
								// If this object was jumping, it is not jumping any more
								jumping = false;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void update()
	{
		if (!falling)
			falling = true;
		
		if (usePhysics)
			if (yVel == 0)
				yVel = 0.98F;
	}
}
