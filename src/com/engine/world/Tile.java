package com.engine.world;

import java.awt.Color;
import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;

/**
 * <h1>Tile</h1>
 * A Tile is a physical tile in the project World.
 * Tiles have different textures and properties
 * and they are what make up the world.
 * <br>
 * <h1>Tile Features:</h1>
 * <ul>
 * <li>All the features of a basic CoreObject.</li>
 * <li>All tiles in a world have the same width.</li>
 * <li>All tiles in a world have the same height.</li>
 * <li>A tile has a map color.  This is used to distinguish
 * different tile types from one another on a tile map
 * or a world template.</li>
 * </ul>
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class Tile extends CoreObject
{
	protected Color mapColor; // The color of this Tile on a world map

	/**
	 * Create a new Tile from scratch.
	 * @param xPos
	 * @param yPos
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public Tile(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name) 
	{
		super(xPos, yPos, id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		this.mapColor = mapColor;
	}
	
	/**
	 * Create a new Tile with no specified position.
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public Tile(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
	{
		super(id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		this.mapColor = mapColor;
		this.collisionType = collisionType;
	}
	
	/**
	 * Create a new Tile based on another Tile.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public Tile(float xPos, float yPos, Tile tile) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = tile.getId();
		this.image = tile.getImage();
		this.usePhysics = tile.doesUsePhysics();
		this.mapColor = tile.getMapColor();
		this.name = tile.getName();
		
		this.setCollisionType(tile.getCollisionType());
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
	}
	
	/**
	 * <h1>WORK IN PROGRESS</h1>
	 * When a tile is clicked on it updates the
	 * 8 surrounding tiles.  Currently the tile
	 * that was clicked on is also destroyed.
	 * <b>NOTE:</b> The updating of the 8 surrounding
	 * tiles isn't currently working properly as
	 * of v1.1.
	 */
	@Override
	public void clicked() // TODO version reference
	{
		Tile tempTile = null;
		float tempX = 0;
		float tempY = 0;
		
		// Update surrounding tiles
		for (int x = 0; x < 8; x++)
		{
			switch (x)
			{
				case 0:
					tempX = xPos - width;
					tempY = yPos - height;
					break;
					
				case 1:
					tempX = xPos;
					tempY = yPos - height;
					break;
					
				case 2:
					tempX = xPos + width;
					tempY = yPos - height;
					break;
					
				case 3:
					tempX = xPos + width;
					tempY = yPos;
					break;
					
				case 4:
					tempX = xPos + width;
					tempY = yPos + height;
					break;
					
				case 5:
					tempX = xPos;
					tempY = yPos + height;
					break;
					
				case 6:
					tempX = xPos - width;
					tempY = yPos + height;
					break;
					
				case 7:
					tempX = xPos - width;
					tempY = yPos;
					break;
			}
			
			tempTile = ResourceLoader.core.getWorldHandler().getTileAtCoords(tempX, tempY, 
					ResourceLoader.core.getWorldHandler().getChunkLayer(ResourceLoader.core.getWorldHandler().getChunkWithTile(this)));
			
			if (tempTile != null)
			{
				tempTile.update();
			}
		}
		
		ResourceLoader.core.getWorldHandler().breakTile(this); // Remove the tile when clicked TODO: This is temporary
	}
	
	/**
	 * A basic Tile should not check for collisions.
	 * This is being overridden so when it is called,
	 * an empty method is ran so no collisions
	 * are checked.
	 */
	@Override
	public void checkCollision()
	{
		
	}

	/**
	 * Get the color of this tile on a world map.
	 * @return the mapColor
	 */
	public Color getMapColor()
	{
		return mapColor;
	}

	/**
	 * Set the color of this tile on a world map.
	 * @param mapColor the mapColor to set
	 */
	public void setMapColor(Color mapColor)
	{
		this.mapColor = mapColor;
	}
}
