package com.cubeoTwo.tiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import com.cubeoTwo.main.CubeoTwoResources;
import com.engine.coreObject.CollisionHandler;
import com.engine.coreObject.CoreObject;
import com.engine.entity.Entity;
import com.engine.entity.Player;
import com.engine.resources.ResourceLoader;
import com.engine.world.Chunk;
import com.engine.world.Tile;

public class TileWater extends Tile
{
	public TileWater(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name)
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
	public TileWater(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
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
	public TileWater(float xPos, float yPos, Tile tile) 
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
		if (obj instanceof Entity)
		{
			if (collision == CollisionHandler.DOWN_COL ||
				collision == CollisionHandler.UP_COL ||
				collision == CollisionHandler.LEFT_COL ||
				collision == CollisionHandler.RIGHT_COL)
			{
				((Entity) obj).getFlags()[1] = true;
				obj.setFalling(false);
				
				// Makes the player descend slowly
				obj.setyVel(0.5F);
				
				// Allows the player to swim up
				if ((obj instanceof Player) && CubeoTwoResources.up.isPressed())
				{
					obj.setyVel(-2);
				}
				
				// Allows the player to swim down
				if ((obj instanceof Player) && CubeoTwoResources.down.isPressed())
				{
					obj.setyVel(obj.getyVel() + 0.3F);
				}
				
				// Slows down player xVel
				if (obj.getxVel() >= 2)
					obj.setxVel(2);
				
				if (obj.getxVel() <= -2)
					obj.setxVel(-2);
			}
			else if (((Entity)obj).getFlags()[1] == true
					&&
					collision != CollisionHandler.DOWN_COL ||
					collision != CollisionHandler.UP_COL ||
					collision != CollisionHandler.LEFT_COL ||
					collision != CollisionHandler.RIGHT_COL)
			{
				obj.setFalling(true);
				((Entity) obj).getFlags()[1] = false;
			}
		}
	}
	
	@Override
	public void update()
	{
		System.out.println("Updating Water...");
		
		Tile tempTile = null;
		float tempX = 0;
		float tempY = 0;
		
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
			
			tempTile = ResourceLoader.core.getWorldHandler()
					.getTileAtCoords(tempX, tempY, 1);
			
			if (tempTile == null)
			{
				System.out.println("Tile at " + tempX + " and " + tempY + " is null...");
				
				Chunk tempChunk = null;
				tempChunk = ResourceLoader.core.getWorldHandler()
				.getChunkWithTileAtCoords(tempX, tempY, 1);
				
				if (tempChunk != null)
				{
					System.out.println("Chunk is not null...");
					tempChunk.addTile(new TileWater(tempX, tempY, CubeoTwoTiles.water));
					System.out.println("Water tile added...");
				}
			}
		}
	}
	
	/**
	 * Get the top bounds of this GameObject.
	 * @return Rectangle The top bounds of this GameObject
	 */
	@Override
	public Rectangle getTopBounds()
	{
		return new Rectangle((int)xPos + 6, (int)yPos, width - 12, 10);
	}
	
	/**
	 * Get the bottom bounds of this GameObject.
	 * @return Rectangle The bottom bounds of this GameObject
	 */
	@Override
	public Rectangle getBottomBounds()
	{
		return new Rectangle((int)xPos, (int)yPos + height - 10, width, 10);
	}
	
	/**
	 * Get the left bounds of this GameObject.
	 * @return Rectangle The left bounds of this GameObject
	 */
	@Override
	public Rectangle getLeftBounds()
	{
		return new Rectangle((int)xPos, (int)yPos, 6, height - 10);
	}
	
	/**
	 * Get the right bounds of this GameObject.
	 * @return Rectangle The right bounds of this GameObject
	 */
	@Override
	public Rectangle getRightBounds()
	{
		return new Rectangle((int)xPos + width - 6, (int)yPos, 6, height - 10);
	}
}
