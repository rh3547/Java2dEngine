package com.cubeoTwo.tiles;

import java.awt.Color;
import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;

public class TileLadder extends Tile
{
	public TileLadder(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name)
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
	public TileLadder(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
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
	public TileLadder(float xPos, float yPos, Tile tile) 
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
		
	}
}
