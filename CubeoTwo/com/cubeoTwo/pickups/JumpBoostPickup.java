package com.cubeoTwo.pickups;

import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.coreObject.Pickup;
import com.engine.resources.ResourceLoader;

public class JumpBoostPickup extends Pickup
{
	/**
	 * Create a new pickup.
	 * @param xPos
	 * @param yPos
	 * @param id
	 * @param image
	 * @param usePhysics
	 */
	public JumpBoostPickup(float xPos, float yPos, int id, Image image, boolean usePhysics, String name) 
	{
		super(xPos, yPos, id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		ResourceLoader.core.getObjectHandler().getCoreObjects().add(this);
	}
	
	/**
	 * Create a new pickup with no specified position.
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public JumpBoostPickup(int id, Image image, boolean usePhysics, int collisionType, String name) 
	{
		super(id, image, usePhysics, collisionType, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		this.collisionType = collisionType;
	}
	
	/**
	 * Create a new pickup based on another pickup.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public JumpBoostPickup(float xPos, float yPos, Pickup pickup) 
	{
		super(xPos, yPos, pickup);
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = pickup.getId();
		this.image = pickup.getImage();
		this.usePhysics = pickup.doesUsePhysics();
		
		this.setCollisionType(pickup.getCollisionType());
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		ResourceLoader.core.getObjectHandler().getCoreObjects().add(this);
	}
	
	@Override
	public void pickUp(CoreObject obj)
	{
		obj.setyVel(obj.getyVel() - 8);
		obj.setxVel(obj.getxVel() + 2);
		kill();
	}
}
