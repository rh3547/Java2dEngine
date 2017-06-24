package com.engine.world;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;

/**
 * <h1>Camera</h1>
 * A camera is an object that is used
 * to hold a virtual position in
 * a project's World to make
 * "moving around" a World possible.
 * This is done by translating the
 * project's graphics around this
 * camera's position.  The camera
 * should generally follow something
 * that can move around.  Typically
 * the Player.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class Camera
{
	private float xPos; // The camera's x position
	private float yPos; // The camera's y position
	private CoreObject followObject; // The object that the camera should follow
	
	/**
	 * Create a new Camera.
	 * @param xPos
	 * @param yPos
	 * @param followObject
	 */
	public Camera(float xPos, float yPos, CoreObject followObject)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.followObject = followObject;
	}
	
	/**
	 * Update the position of the camera based on the
	 * position of the followObject.  A tweaning
	 * effect is applied to the camera's movement.
	 * Tweaning is what makes the camera seem a bit
	 * "lagged".  It makes the camera move a bit slower
	 * than the followObject to create a cool effect.
	 */
	public void tick()
	{
		/*
		 * Update the core's mouse anchors because it is
		 * always the position of the camera.
		 */
		ResourceLoader.core.setMouseAnchorX(-(int)xPos);
		ResourceLoader.core.setMouseAnchorY(-(int)yPos);
		
		xPos += ((-followObject.getxPos() + 
				ResourceLoader.core.getWindow().getWidth() / 2) - xPos) * 0.0275F;
		
		yPos += ((-followObject.getyPos() + 
				ResourceLoader.core.getWindow().getHeight() / 2) - yPos) * 0.075F;
	}

	/**
	 * Get the camera's x position.
	 * @return the xPos
	 */
	public float getxPos()
	{
		return xPos;
	}

	/**
	 * get the camera's y position.
	 * @return the yPos
	 */
	public float getyPos()
	{
		return yPos;
	}
	
	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}

	/**
	 * Get the CoreObject this Camera is following.
	 * @return followObject
	 */
	public CoreObject getFollowObject()
	{
		return followObject;
	}
}
