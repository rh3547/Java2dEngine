package com.engine.world;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Chunk</h1>
 * A Chunk is a 16x16 division of Tiles.
 * The purpose of a Chunk is to increase
 * performance by reducing the amount of
 * Tiles to check for collisions,
 * update, and render.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Chunk
{
	private float xPos; // The x position of this chunk
	private float yPos; // The y position of this chunk
	
	// A list of all tiles in this Chunk
	private List<Tile> tiles = new ArrayList<Tile>();
	
	/**
	 * Create a new Chunk with a x and y position.
	 * @param xPos
	 * @param yPos
	 */
	public Chunk(float xPos, float yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Get the x position of this Chunk.
	 * @return the xPos
	 */
	public float getxPos()
	{
		return xPos;
	}

	/**
	 * Set the x position of this Chunk.
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}

	/**
	 * Get the y position of this Chunk.
	 * @return the yPos
	 */
	public float getyPos()
	{
		return yPos;
	}

	/**
	 * Set the y position of this Chunk.
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}

	/**
	 * Get the Tiles in this Chunk.
	 * @return the tiles
	 */
	public List<Tile> getTiles()
	{
		return tiles;
	}
	
	/**
	 * Add a new Tile to this Chunk's list
	 * of Tiles.
	 * @param tile
	 */
	public void addTile(Tile tile)
	{
		this.tiles.add(tile);
	}
}
