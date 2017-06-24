package com.rs.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;
import com.rs.main.RSFlags;

public class RSTile extends Tile
{
	public static final int UNEDITABLE = -1;
	public static final int FLOOR = 0;
	public static final int WALL = 1;
	public static final int FURNITURE = 2;
	
	private int tileType;
	
	public RSTile(float xPos, float yPos, int id, Image image,
			boolean usePhysics, Color mapColor, String name)
	{
		super(xPos, yPos, id, image, usePhysics, mapColor, name);
	}
	
	public RSTile(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, image, usePhysics, mapColor, collisionType, name);
		
		this.tileType = tileType;
	}
	
	/**
	 * Create a new Tile based on another Tile.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public RSTile(float xPos, float yPos, Tile tile) 
	{
		super(xPos, yPos, tile);
		
		this.tileType = ((RSTile)tile).getTileType();
	}
	
	@Override
	public void renderObject(Graphics g)
	{
		g.drawImage(image, (int)xPos, (int)yPos, null);
		
		if (tileType == RSTile.FLOOR || tileType == RSTile.WALL)
			if (RSFlags.showGrid == true)
				g.drawImage(RSTiles.tileGrid.getImage(), (int)xPos, (int)yPos, null);
	}
	
	@Override
	public void clicked()
	{
		if (RSFlags.mode == RSFlags.BUILD_MODE && RSFlags.usingGui == false)
		{
			if ((tileType != RSTile.UNEDITABLE) && (tileType == RSTile.FLOOR || tileType == RSTile.WALL))
			{
				// Remove the tile when clicked
				ResourceLoader.core.getWorldHandler().breakTile(this);
			}
		}
		else if (RSFlags.mode == RSFlags.BUY_MODE && RSFlags.usingGui == false)
		{
			if ((tileType != RSTile.UNEDITABLE) && (tileType == RSTile.FURNITURE))
			{
				// Remove the tile when clicked
				ResourceLoader.core.getWorldHandler().breakTile(this);
			}
		}
	}

	/**
	 * @return the tileType
	 */
	public int getTileType()
	{
		return tileType;
	}

	/**
	 * @param tileType the tileType to set
	 */
	public void setTileType(int tileType)
	{
		this.tileType = tileType;
	}
}