package com.cubeoTwo.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import com.cubeoTwo.main.CubeoTwoResources;
import com.engine.coreObject.CollisionHandler;
import com.engine.coreObject.CoreObject;
import com.engine.entity.Entity;
import com.engine.entity.Player;
import com.engine.graphics.AnimationSet;
import com.engine.resources.ResourceLoader;

public class BasicEnemy extends Entity implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private boolean chasing = false;
	
	public BasicEnemy(float xPos, float yPos, int id, Image[] images,
			boolean usePhysics, int entityType, String name)
	{
		super(xPos, yPos, id, images, usePhysics, entityType, name);
	}
	
	public BasicEnemy(int id, Image[] images, boolean usePhysics, int entityType, String name)
	{
		super(id, images, usePhysics, entityType, name);
	}
	
	public BasicEnemy(int id, Image[] images, boolean usePhysics, int entityType, AnimationSet ani, String name)
	{
		super(id, images, usePhysics, entityType, name);
		
		super.aniSet = ani;
	}
	
	public BasicEnemy(float xPos, float yPos, Entity entity)
	{
		super(xPos, yPos, entity);
		
		super.aniSet = entity.getAniSet();
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
			
			if (xVel > 0)
			{
				movingDir = CoreObject.EAST;
			}
			else if (xVel < 0)
			{
				movingDir = CoreObject.WEST;
			}
			else
			{
				movingDir = CoreObject.NODIR;
			}
				
			
			if (aniSet != null)
			{
				aniSet.getAnimation(0).runAnimation(); // Right
				aniSet.getAnimation(1).runAnimation(); // Left
			}
			
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
			ai();
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
					if (facingDir == CoreObject.EAST)
						aniSet.getAnimation(0).drawAnimation(g, xPos, yPos);
					else if (facingDir == CoreObject.WEST)
						aniSet.getAnimation(1).drawAnimation(g, xPos, yPos);
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
	
	private void ai()
	{
		// Test against all other GameObjects
		for (int x = 0; x < ResourceLoader.core.getObjectHandler().getCoreObjects().size(); x++)
		{
			if (ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getxPos() > 
				xPos - 200 
				&& ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getxPos() < 
				xPos + 200
				&&
				ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getyPos() > 
				yPos - 200
				&& ResourceLoader.core.getObjectHandler().getCoreObjects().get(x).getyPos() < 
				yPos + 200)
			{
				CoreObject obj = ResourceLoader.core.getObjectHandler().getCoreObjects().get(x);
				
				if (obj instanceof Player)
				{
					chasing = true;
					
					if (obj.getxPos() > xPos + width)
						xVel = 3;
					else if (obj.getxPos() < xPos + obj.getObjectWidth())
						xVel = -3;
					else
						xVel = 0;
					
					if (getBottomBounds().intersects(obj.getTopBounds())
						|| getRightBounds().intersects(obj.getLeftBounds())
						|| getLeftBounds().intersects(obj.getRightBounds()))
					{
						((Entity) obj).setHealth(((Entity) obj).getHealth() - 2);
					}
				}
			}
			else
				chasing = false;
		}
	}
	
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
				obj.setyPos(this.getyPos() - obj.getObjectHeight());
			
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				
				this.kill();
				break;
				
			case CollisionHandler.RIGHT_COL:
				obj.setxVel(0);
				obj.setxPos(this.getxPos() + this.getObjectWidth());
				break;
				
			case CollisionHandler.DOWN_COL:
				obj.setyVel(0);
				obj.setyPos(this.getyPos() + this.getObjectHeight());
				break;
				
			case CollisionHandler.LEFT_COL:
				obj.setxVel(0);
				obj.setxPos(this.getxPos() - obj.getObjectWidth());
				break;
		}
		
		obj.altCollided(collision);
	}
	
	@Override
	public void altCollided(int collision)
	{
		switch(collision)
		{
			case CollisionHandler.UP_LEFT_COL:
				if (chasing)
				{
					yVel = -10;
					this.setJumping(true);
				}
				break;
				
			case CollisionHandler.UP_RIGHT_COL:
				if (chasing)
				{
					yVel = -10;
					this.setJumping(true);
				}
				break;
			
			case CollisionHandler.UP_COL:
				break;
				
			case CollisionHandler.RIGHT_COL:
				if (chasing)
				{
					yVel = -10;
					this.setJumping(true);
				}
				break;
				
			case CollisionHandler.DOWN_COL:
				break;
				
			case CollisionHandler.LEFT_COL:
				if (chasing)
				{
					yVel = -10;
					this.setJumping(true);
				}
				break;
		}
	}
}
