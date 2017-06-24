package com.engine.coreObject;

import com.engine.resources.ResourceLoader;
import com.engine.world.Chunk;

/**
 * <h1>CollisionHandler</h1>
 * A collision handler calculates collisions
 * for an object.  This is done here in its own
 * class for the sake of making changes easily
 * and allowing different objects to have
 * different collision detection.
 * <br>
 * @author Ryan Hochmuth
 *
 */
public class CollisionHandler 
{
	/*
	 * Collision Types
	 */
	/**
	 * <b>ALT_COL:</b> Run the alt collision for this object, 
	 * not for the object that collided with this one.
	 */
	public static final int ALT_COL = 100;
	/**
	 * <b>UP_COL:</b> An object collided with this object's top.
	 */
	public static final int UP_COL = 0;
	/**
	 * <b>RIGHT_COL:</b> An object collided with this object's right.
	 */
	public static final int RIGHT_COL = 1;
	/**
	 * <b>DOWN_COL:</b> An object collided with this object's bottom.
	 */
	public static final int DOWN_COL = 2;
	/**
	 * <b>LEFT_COL:</b> An object collided with this object's left.
	 */
	public static final int LEFT_COL = 3;
	/**
	 * <b>UP_RIGHT_COL:</b> An object collided with this object's top and right.
	 */
	public static final int UP_RIGHT_COL = 4;
	/**
	 * <b>UP_LEFT_COL:</b> An object collided with this object's top and left.
	 */
	public static final int UP_LEFT_COL = 5;
	/**
	 * <b>DOWN_RIGHT_COL:</b> An object collided with this object's bottom and right.
	 */
	public static final int DOWN_RIGHT_COL = 6;
	/**
	 * <b>DOWN_LEFT_COL:</b> An object collided with this object's bottom and left.
	 */
	public static final int DOWN_LEFT_COL = 7;
	
	/**
	 * Checks if this core object is colliding with
	 * other tiles that collide.
	 * <br>
	 * <h3>Basic Collision Detection Process</h3>
	 * The list of core objects that 
	 * are in view are searched
	 * through one by one.  Each core object
	 * can detect collision from all four
	 * directions by generating a rectangle
	 * on a specific side.  This core object
	 * and the one being tested both generate
	 * rectangles to test collision.  For example,
	 * this object may generate it's bottom rectangle
	 * and may test the rectangle generated on top
	 * of the other core object.  If the rectangles 
	 * are colliding then this object's bottom is 
	 * colliding with the other's top.  Once a
	 * collision is detected, the velocities of
	 * the objects are altered depending on the
	 * case of collision.
	 */
	public void checkBasicTileCollision(CoreObject colObj)
	{
		// Only test collisions if this object has collisions enabled
		if (colObj.getCollisionType() == CoreObject.COLLIDE || 
				colObj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE) 
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
						
						// Only check tiles in range
						if (obj.getxPos() > colObj.getxPos() - 
							(ResourceLoader.core.getCollisionCheckDistance() * 32) &&
							obj.getxPos() < colObj.getxPos() + 
							(ResourceLoader.core.getCollisionCheckDistance() * 32) 
							&&
							obj.getyPos() > colObj.getyPos() - 
							(ResourceLoader.core.getCollisionCheckDistance() * 32) &&
							obj.getyPos() < colObj.getyPos() + 
							(ResourceLoader.core.getCollisionCheckDistance() * 32))
						{
							
						}
						else
							continue;
						
						// If the object being tested has collisions enabled and isn't this object
						if (obj.getCollisionType() == CoreObject.COLLIDE && obj != colObj 
							|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE 
							|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != colObj)
						{
							if (colObj.getBottomBounds().intersects(obj.getTopBounds()) &&
								colObj.getRightBounds().intersects(obj.getLeftBounds()))
							{		
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.UP_LEFT_COL);
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							else if (colObj.getBottomBounds().intersects(obj.getTopBounds()) &&
									 colObj.getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.UP_RIGHT_COL);
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							// If this object's bottom collides with another's top
							else if (colObj.getBottomBounds().intersects(obj.getTopBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.UP_COL);
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's top collides with another's bottom
							if (colObj.getTopBounds().intersects(obj.getBottomBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.DOWN_COL);
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's left collides with another's right
							if (colObj.getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.RIGHT_COL);
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's right collides with another's left
							if (colObj.getRightBounds().intersects(obj.getLeftBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.LEFT_COL);
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Checks if this core object is colliding with
	 * other core objects that collide.
	 * <br>
	 * <h3>Basic Collision Detection Process</h3>
	 * The list of core objects that 
	 * are in view are searched
	 * through one by one.  Each core object
	 * can detect collision from all four
	 * directions by generating a rectangle
	 * on a specific side.  This core object
	 * and the one being tested both generate
	 * rectangles to test collision.  For example,
	 * this object may generate it's bottom rectangle
	 * and may test the rectangle generated on top
	 * of the other core object.  If the rectangles 
	 * are colliding then this object's bottom is 
	 * colliding with the other's top.  Once a
	 * collision is detected, the velocities of
	 * the objects are altered depending on the
	 * case of collision.
	 */
	public void checkBasicObjectCollision(CoreObject colObj)
	{
		// Test against all other CoreObjects
		for (int x = 0; x < ResourceLoader.core.getObjectHandler().getCoreObjects().size(); x++)
		{
			if (ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getxPos() > 
				ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getxPos() < 
				ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getChunkLoadDistance() 
				&&
				ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getyPos() > 
				ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getyPos() < 
				ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getChunkLoadDistance())
			{
				CoreObject obj = ResourceLoader.core.getObjectHandler().getCoreObjects().get(x);
				
				// Only check objects in range
				if (obj.getxPos() > colObj.getxPos() - 
					(ResourceLoader.core.getCollisionCheckDistance() * 32) &&
					obj.getxPos() < colObj.getxPos() + 
					(ResourceLoader.core.getCollisionCheckDistance() * 32) 
					&&
					obj.getyPos() > colObj.getyPos() - 
					(ResourceLoader.core.getCollisionCheckDistance() * 32) &&
					obj.getyPos() < colObj.getyPos() + 
					(ResourceLoader.core.getCollisionCheckDistance() * 32))
				{
					
				}
				else
					continue;
				
				// If the object being tested has collisions enabled and isn't this object
				if (obj.getCollisionType() == CoreObject.COLLIDE && obj != colObj 
					|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE
					|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != colObj)
				{
					if (!(obj instanceof Pickup))
					{
						// If this object's bottom collides with another's top
						if (colObj.getBottomBounds().intersects(obj.getTopBounds()))
						{
							if (obj.getCollisionType() == CoreObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.UP_COL);
							}
							else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
						
						// If this object's top collides with another's bottom
						if (colObj.getTopBounds().intersects(obj.getBottomBounds()))
						{
							if (obj.getCollisionType() == CoreObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.DOWN_COL);
							}
							else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
						
						// If this object's left collides with another's right
						if (colObj.getLeftBounds().intersects(obj.getRightBounds()))
						{
							if (obj.getCollisionType() == CoreObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.RIGHT_COL);
							}
							else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
						
						// If this object's right collides with another's left
						if (colObj.getRightBounds().intersects(obj.getLeftBounds()))
						{
							if (obj.getCollisionType() == CoreObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.LEFT_COL);
							}
							else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
					}
				}
			}
		}
	}
}