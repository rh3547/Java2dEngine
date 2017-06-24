package com.engine.coreObject;

import java.awt.Graphics;
import java.util.ArrayList;

import com.engine.resources.ResourceLoader;

/**
 * <h1>ObjectHandler</h1>
 * The ObjectHandler class contains a list of every
 * CoreObject currently in the project.  Those CoreObjects
 * can all be ticked and rendered through here.  
 * NOTE: Tile ticking/rendering is not handled here. 
 * WorldHandler controls tiles.  Only basic CoreObjects
 * and extensions of CoreObjects such as Entities
 * should be added to the list in ObjectHandler.
 * 
 * @author Ryan Hochmuth
 *
 */
public class ObjectHandler
{
	// A list of every CoreObject
	private static ArrayList<CoreObject> coreObjects = new ArrayList<CoreObject>();
	
	/*
	 * Count variables used for debugging
	 */
	private int objLoaded = 0;
	private int topObjLoaded = 0;
	private int totalObj = 0;
	private int topTotalObj = 0;
	
	/**
	 * Tick every CoreObject currently in the project.
	 * A CoreObject is only ticked if it is in range.
	 * That is if it's within +- core.getObjectLoadDistance()
	 * from the player.
	 */
	public void tickCoreObjects()
	{
		for(int x = 0; x < coreObjects.size(); x++)
		{
			if (coreObjects.get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getObjectLoadDistance() &&
				coreObjects.get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getObjectLoadDistance() 
				&&
				coreObjects.get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getObjectLoadDistance() &&
				coreObjects.get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getObjectLoadDistance())
			{
				CoreObject obj = coreObjects.get(x);
				
				// Only check objects in range
				if (obj.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				(ResourceLoader.core.getLoadDistance() * 32) &&
				obj.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				(ResourceLoader.core.getLoadDistance() * 32) 
				&&
				obj.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				(ResourceLoader.core.getLoadDistance() * 32) &&
				obj.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				(ResourceLoader.core.getLoadDistance() * 32))
				{
					
				}
				else
					continue;
				
				objLoaded++;
				coreObjects.get(x).tick();
			}
			
			totalObj++;
		}
		
		topObjLoaded = objLoaded;
		objLoaded = 0;
		topTotalObj = totalObj;
		totalObj = 0;
	}
	
	/**
	 * Render every CoreObject currently in the project.
	 * A CoreObject is only ticked if it is in range.
	 * That is if it's within +- core.getObjectLoadDistance()
	 * from the player.
	 * @param g
	 */
	public void renderCoreObjects(Graphics g)
	{
		for(int x = 0; x < coreObjects.size(); x++)
		{
			if (coreObjects.get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getObjectLoadDistance() &&
				coreObjects.get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getObjectLoadDistance() 
				&&
				coreObjects.get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getObjectLoadDistance() &&
				coreObjects.get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getObjectLoadDistance())
			{
				CoreObject obj = coreObjects.get(x);
				
				// Only check objects in range
				if (obj.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				(ResourceLoader.core.getLoadDistance() * 32) &&
				obj.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				(ResourceLoader.core.getLoadDistance() * 32) 
				&&
				obj.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				(ResourceLoader.core.getLoadDistance() * 32) &&
				obj.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				(ResourceLoader.core.getLoadDistance() * 32))
				{
					
				}
				else
					continue;
				
				coreObjects.get(x).renderObject(g);
			}
		}
	}
	
	/**
	 * Adds the given CoreObject to the complete list.
	 * @param object
	 */
	public void addCoreObject(CoreObject object)
	{
		coreObjects.add(object);
	}
	
	/**
	 * Remove the given CoreObject from the complete list.
	 * @param object
	 */
	public void removeCoreObject(CoreObject object)
	{
		coreObjects.remove(object);
	}
	
	/**
	 * Get the complete list of CoreObjects.
	 * @return
	 */
	public ArrayList<CoreObject> getCoreObjects()
	{
		return coreObjects;
	}
	
	/**
	 * @return the topObjLoaded
	 */
	public int getTopObjLoaded()
	{
		return topObjLoaded;
	}

	/**
	 * @return the topTotalObj
	 */
	public int getTopTotalObj()
	{
		return topTotalObj;
	}
}
