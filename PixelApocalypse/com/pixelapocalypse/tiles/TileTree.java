package com.pixelapocalypse.tiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import com.engine.coreObject.CollisionHandler;
import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;

public class TileTree extends Tile
{
	public TileTree(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name)
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
	public TileTree(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
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
	public TileTree(float xPos, float yPos, Tile tile) 
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
	
	/**
	 * Ran if an object collides with this object.
	 * @param obj
	 */
	@Override
	public void collided(CoreObject obj, int collision)
	{
		switch(collision)
		{
			case CollisionHandler.UP_LEFT_COL:
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				
				if (obj.isJumping())
				{
					obj.setxVel(1);
				}
				else
				{
					obj.setyVel(0);
				}
				break;
				
			case CollisionHandler.UP_RIGHT_COL:
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				
				if (obj.isJumping())
				{
					obj.setxVel(-1);
				}
				else
				{
					obj.setyVel(0.1F);
				}
				break;
			
			case CollisionHandler.UP_COL:
				obj.setyVel(0); // Negate all y velocity
				// Set this object to be on top of the other
				obj.setyPos((this.getyPos() + (2 * (height / 3))) - obj.getObjectHeight());
			
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				break;
				
			case CollisionHandler.RIGHT_COL:
				obj.setxVel(0);
				obj.setxPos(((int)xPos + (2 * (width / 3))));
				break;
				
			case CollisionHandler.DOWN_COL:
				obj.setyVel(0);
				obj.setyPos(this.getyPos() + this.getObjectHeight());
				break;
				
			case CollisionHandler.LEFT_COL:
				obj.setxVel(0);
				obj.setxPos(((int)xPos + (width / 3)) - obj.getObjectWidth() + 2);
				break;
		}
	}
	
	/**
	 * Get the top collision bounds of this GameObject.
	 * @return Rectangle -  the top bounds of this GameObject
	 */
	@Override
	public Rectangle getTopBounds()
	{
		return new Rectangle(((int)xPos + 6) + (width / 3), (int)yPos +  (2 * (height / 3)), (width / 3) - 12, 10);
	}
	
	/**
	 * Get the bottom collision bounds of this GameObject.
	 * @return Rectangle - the bottom bounds of this GameObject
	 */
	@Override
	public Rectangle getBottomBounds()
	{
		return new Rectangle((int)xPos + (width / 3), (int)yPos + height - 10, (width - (2 * (width / 3))), 10);
	}
	
	/**
	 * Get the left collision bounds of this GameObject.
	 * @return Rectangle -  the left bounds of this GameObject
	 */
	@Override
	public Rectangle getLeftBounds()
	{
		return new Rectangle((int)xPos + (width / 3), (int)yPos +  (2 * (height / 3)), 6, (height / 3) - 10);
	}
	
	/**
	 * Get the right collision bounds of this GameObject.
	 * @return Rectangle - the right bounds of this GameObject
	 */
	@Override
	public Rectangle getRightBounds()
	{
		return new Rectangle((int)xPos + (2 * (width / 3)) - 6, (int)yPos +  (2 * (height / 3)), 6, (height / 3) - 10);
	}
}
