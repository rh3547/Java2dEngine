package com.cubeoTwo.tiles;

import java.awt.Color;
import java.awt.Image;

import com.engine.resources.ResourceLoader;
import com.engine.world.Light;
import com.engine.world.Tile;

public class TileTorch extends Tile
{
	public TileTorch(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name)
	{
		super(xPos, yPos, id, image, usePhysics, mapColor, name);
		
		// Add a Light at this tile's location
		ResourceLoader.core.getLightHandler().addLight(new Light((int)xPos + 1520, (int)yPos + 3100, 150, 5));
	}
	
	/**
	 * Create a new Tile with no specified position.
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public TileTorch(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
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
	public TileTorch(float xPos, float yPos, Tile tile) 
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
		
		// Add a Light at this tile's location
		ResourceLoader.core.getLightHandler().addLight(new Light((int)xPos + 1520, (int)yPos + 3100, 150, 5));
	}
}
