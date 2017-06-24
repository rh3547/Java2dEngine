package com.cubeoTwo.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import com.cubeoTwo.main.CubeoTwoResources;
import com.cubeoTwo.tiles.TileLadder;
import com.cubeoTwo.tiles.TilePlatform;
import com.cubeoTwo.tiles.TileWater;
import com.engine.coreObject.CoreObject;
import com.engine.coreObject.Pickup;
import com.engine.entity.Player;
import com.engine.graphics.AnimationSet;
import com.engine.resources.ResourceLoader;
import com.engine.world.Chunk;

public class CubeoPlayer extends Player implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int worldPosX = 0;
	private int worldPosY = 0;
	
	// Movement booleans
	private boolean canPass = false;
	private boolean inWater = false;
	private boolean onLadder = false;
	
	public CubeoPlayer(float xPos, float yPos, int id, Image[] images, boolean usePhysics, AnimationSet ani, String name)
	{
		super(xPos, yPos, id, images, usePhysics, ani, name);
		
		width = bImage.getWidth();
		height = bImage.getHeight();
	}

	@Override
	public void tick()
	{
		if (!entityDead)
		{
			if (xPos > -764 && xPos < 14053)
			{
				xPos += xVel;
			}
			else if (xPos <= -764)
			{
				xPos += 0.5F;
			}
			else if (xPos >= 14053)
			{
				xPos -= 0.5F;
			}
			
			yPos += yVel;
			
			// Determine the direction the player is facing
			if (movingDir == CoreObject.EAST)
			{
				facingDir = CoreObject.EAST;
				image = images[0];
			}
			else if (movingDir == CoreObject.WEST)
			{
				facingDir = CoreObject.WEST;
				image = images[1];
			}
			
			fall();
			
			if (aniSet != null)
			{
				aniSet.getAnimation(0).runAnimation(); // Right
				aniSet.getAnimation(1).runAnimation(); // Left
				aniSet.getAnimation(2).runAnimation(); // Jump Right
				aniSet.getAnimation(3).runAnimation(); // Jump left
			}
		}
		else
		{
			if (!CubeoTwoResources.poofParticles.isRunning())
			{
				ResourceLoader.core.getObjectHandler().removeCoreObject(this);
			}
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
					if (movingDir == CoreObject.EAST)
						aniSet.getAnimation(2).drawAnimation(g, xPos, yPos);
					else if (movingDir == CoreObject.WEST)
						aniSet.getAnimation(3).drawAnimation(g, xPos, yPos);
					else
					{
						if (facingDir == CoreObject.EAST)
							aniSet.getAnimation(2).drawAnimation(g, xPos, yPos);
						else if (facingDir == CoreObject.WEST)
							aniSet.getAnimation(3).drawAnimation(g, xPos, yPos);
					}
				}
				else if (movingDir == CoreObject.EAST && !jumping)
					aniSet.getAnimation(0).drawAnimation(g, xPos, yPos);
				else if (movingDir == CoreObject.WEST && !jumping)
					aniSet.getAnimation(1).drawAnimation(g, xPos, yPos);
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
		
		CubeoTwoResources.poofParticles.generateParticles(g, 
				CubeoTwoObjects.player.getxPos() + (float)CubeoTwoObjects.player.getObjectWidth() / 2, 
				CubeoTwoObjects.player.getyPos() + (float)CubeoTwoObjects.player.getObjectHeight() / 2);
	}
	
	@Override
	public void fall()
	{
		if (usePhysics)
			if (falling)
			{
				if (yVel < 30)
					yVel += gravity;
			}
		
		checkCollision();
	}
	
	/**
	 * Check all collisions with this GameObject.
	 */
	public void checkCollisions()
	{
		if (collisionType == CoreObject.COLLIDE 
			|| collisionType == CoreObject.CHECK_NO_COLLIDE
			|| getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER) // Only test collisions if this object has collisions enabled
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
						
						if (obj.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
							(ResourceLoader.core.getChunkLoadDistance() * 32) &&
							obj.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
							(ResourceLoader.core.getChunkLoadDistance() * 32) 
							&&
							obj.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
							(ResourceLoader.core.getChunkLoadDistance() * 32) &&
							obj.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
							(ResourceLoader.core.getChunkLoadDistance() * 32))
						{
							
						}
						else
							continue;
						
						// Check bottom collision for world position update
						if (getBottomBounds().intersects(obj.getTopBounds()))
						{
							worldPosX = (int)obj.getxPos();
							worldPosY = (int)obj.getyPos();
						}
						
						// Specific collisions for platforms
						if (obj instanceof TilePlatform)
						{
							// If the object being tested has collisions enabled and isn't this object
							if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this 
								|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE 
								|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != this)
							{
								// If this object's top, left, or right collides with another's bottom
								if (getTopBounds().intersects(obj.getBottomBounds()) ||
									getLeftBounds().intersects(obj.getBottomBounds()) ||
									getRightBounds().intersects(obj.getBottomBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										// Allow the player to pass through the platform
										canPass = true;
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								
								// If this object's bottom, left, or right collides with another's top
								else if (getBottomBounds().intersects(obj.getTopBounds()) &&
										!getLeftBounds().intersects(obj.getTopBounds()) &&
										!getRightBounds().intersects(obj.getTopBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										canPass = false;
										
										if (CubeoTwoResources.down.isPressed())
										{
											canPass = true;
										}
										
										if (!canPass)
										{
											checkFallDamage();
											
											yVel = 0; // Negate all y velocity
											// Set this object to be on top of the other
											yPos = obj.getyPos() - height;
										
											// If this object was jumping, it is not jumping any more
											jumping = false;
										}
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
							}
						}
						// Specific collisions for water
						else if (obj instanceof TileWater)
						{
							// If the object being tested has collisions enabled and isn't this object
							if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this 
								|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE
								|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != this)
							{
								// If this object's top collides with another's bottom
								if (getTopBounds().intersects(obj.getBottomBounds()) ||
									getTopBounds().intersects(obj.getTopBounds()) ||
									getTopBounds().intersects(obj.getLeftBounds()) ||
									getTopBounds().intersects(obj.getRightBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										inWater = true;
										falling = false;
										
										// Makes the player descend slowly
										yVel = 0.5F;
										
										// Allows the player to swim up
										if (CubeoTwoResources.up.isPressed())
										{
											yVel = -2;
										}
										
										// Allows the player to swim down
										if (CubeoTwoResources.down.isPressed())
										{
											yVel += 0.3F;
										}
										
										// Slows down player xVel
										if (xVel >= 2)
											xVel = 2;
										
										if (xVel <= -2)
											xVel = -2;
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								else if (inWater 
										&&
										!getTopBounds().intersects(obj.getBottomBounds()) ||
										!getTopBounds().intersects(obj.getTopBounds()) ||
										!getTopBounds().intersects(obj.getLeftBounds()) ||
										!getTopBounds().intersects(obj.getRightBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										inWater = false;
										falling = true;
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
							}
						}
						// Specific collisions for ladder
						else if (obj instanceof TileLadder)
						{
							// If the object being tested has collisions enabled and isn't this object
							if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this 
								|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE 
								|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != this)
							{
								// If this object's top collides with another's bottom
								if (getBottomBounds().intersects(obj.getBottomBounds()) ||
									getBottomBounds().intersects(obj.getTopBounds()) ||
									getBottomBounds().intersects(obj.getLeftBounds()) ||
									getBottomBounds().intersects(obj.getRightBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										onLadder = true;
										
										// Make the player slowly descend if not
										// manually moving on the ladder
										yVel = 1;
										
										// Move up the ladder
										if (CubeoTwoResources.up.isPressed())
										{
											yVel = -4;
										}
										
										// Move down the ladder
										if (CubeoTwoResources.down.isPressed())
										{
											yVel = 4;
										}
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								else if (onLadder 
										&&
										!getBottomBounds().intersects(obj.getBottomBounds()) ||
										!getBottomBounds().intersects(obj.getTopBounds()) ||
										!getBottomBounds().intersects(obj.getLeftBounds()) ||
										!getBottomBounds().intersects(obj.getRightBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										onLadder = false;
										falling = true;
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
							}
						}
						else
						{
							// If the object being tested has collisions enabled and isn't this object
							if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this 
								|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE 
								|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != this)
							{
								if (getBottomBounds().intersects(obj.getTopBounds()) &&
									getRightBounds().intersects(obj.getLeftBounds()))
								{
									// If this object was jumping, it is not jumping any more
									jumping = false;
									
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										checkFallDamage();
										
										if (jumping)
										{
											xVel = 1;
										}
										else
										{
											yVel = 0;
										}
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								else if (getBottomBounds().intersects(obj.getTopBounds()) &&
										 getLeftBounds().intersects(obj.getRightBounds()))
								{
									// If this object was jumping, it is not jumping any more
									jumping = false;
									
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										checkFallDamage();
										
										if (jumping)
										{
											xVel = -1;
										}
										else
										{
											yVel = 0.1F;
										}
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								// If this object's bottom collides with another's top
								else if (getBottomBounds().intersects(obj.getTopBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										checkFallDamage();
										
										yVel = 0; // Negate all y velocity
										// Set this object to be on top of the other
										yPos = obj.getyPos() - height;
									
										// If this object was jumping, it is not jumping any more
										jumping = false;
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								
								// If this object's top collides with another's bottom
								if (getTopBounds().intersects(obj.getBottomBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										yVel = 0;
										yPos = obj.getyPos() + obj.getObjectHeight();
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								
								// If this object's left collides with another's right
								if (getLeftBounds().intersects(obj.getRightBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										xVel = 0;
										xPos = obj.getxPos() + obj.getObjectWidth();
										
										jumping = false;
									}
									else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
									{
										
									}
								}
								
								// If this object's right collides with another's left
								if (getRightBounds().intersects(obj.getLeftBounds()))
								{
									if (obj.getCollisionType() == CoreObject.COLLIDE)
									{
										xVel = 0;
										xPos = obj.getxPos() - width;
										
										jumping = false;
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
					if (obj.getCollisionType() == CoreObject.COLLIDE && obj != this 
						|| obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE 
						|| obj.getCollisionType() == CoreObject.COLLIDE_EXCEPT_PLAYER && obj != this)
					{
						// Check bottom collision for world position update
						if (getBottomBounds().intersects(obj.getTopBounds()))
						{
							worldPosX = (int)obj.getxPos();
							worldPosY = (int)obj.getyPos();
						}
						
						if (!(obj instanceof Pickup))
						{
							if (getBottomBounds().intersects(obj.getTopBounds()) &&
								getRightBounds().intersects(obj.getLeftBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									checkFallDamage();
									
									if (jumping)
									{
										xVel = 1;
									}
									else
									{
										yVel = 0;
									}
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							else if (getBottomBounds().intersects(obj.getTopBounds()) &&
									 getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									checkFallDamage();
									
									if (jumping)
									{
										xVel = -1;
									}
									else
									{
										yVel = 0;
									}
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							// If this object's bottom collides with another's top
							else if (getBottomBounds().intersects(obj.getTopBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									checkFallDamage();
									
									yVel = 0; // Negate all y velocity
									// Set this object to be on top of the other
									yPos = obj.getyPos() - height;
								
									// If this object was jumping, it is not jumping any more
									jumping = false;
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's top collides with another's bottom
							if (getTopBounds().intersects(obj.getBottomBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									yVel = 0;
									yPos = obj.getyPos() + obj.getObjectHeight();
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's left collides with another's right
							if (getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									xVel = 0;
									xPos = obj.getxPos() + obj.getObjectWidth();
								}
								else if (obj.getCollisionType() == CoreObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's right collides with another's left
							if (getRightBounds().intersects(obj.getLeftBounds()))
							{
								if (obj.getCollisionType() == CoreObject.COLLIDE)
								{
									xVel = 0;
									xPos = obj.getxPos() - width;
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
	
	private void checkFallDamage()
	{
		if (yVel > 25)
			health -= 10;
		
		if (health <= 0)
		{
			die();
		}
	}
	
	@Override
	protected void die()
	{
		if (!CubeoTwoObjects.player.isEntityDead())
		{
			entityDead = true;
			CubeoTwoResources.poofParticles.startGenerator();
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