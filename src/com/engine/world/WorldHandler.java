package com.engine.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;

/**
 * <h1>WorldHandler</h1>
 * WorldHandler is used to control the project's worlds.
 * Whenever a World loads, its chunks are added to the
 * list here.  The those chunks are updated, rendered,
 * and all around handled here.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class WorldHandler
{
	private List<Chunk> chunks = new ArrayList<Chunk>(); // The list of total chunks
	
	private int tilesLoaded = 0; // The exact number of tiles currently loaded
	private int topTilesLoaded = 0; // The last highest number of tiles loaded
	private int totalTiles = 0; // The exact number of tiles in the whole list of chunks
	private int topTotalTiles = 0;// The last highest number of tiles in the whole list of chunks
	
	/**
	 * Tick all of the Tiles within render distance.
	 * This method runs the Tiles' tick function to update
	 * position, check for collisions, etc.
	 */
	public void tickTiles()
	{
		for(int x = 0; x < chunks.size(); x++) // Check every chunk
		{
			// Only check a chunks tiles if it is within chunk load distance
			if (chunks.get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getChunkLoadDistance())
			{
				// Check all tiles of chunks within chunk load distance
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					CoreObject obj = chunks.get(x).getTiles().get(i);
					
					// Only update tiles in range
					if (obj.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) &&
					obj.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) 
					&&
					obj.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) &&
					obj.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()))
					{
						
					}
					else
						continue;
					
					tilesLoaded++;
					chunks.get(x).getTiles().get(i).tick();
				}
			}
			
			totalTiles += chunks.get(x).getTiles().size();
		}
		
		topTilesLoaded = tilesLoaded;
		tilesLoaded = 0;
		topTotalTiles = totalTiles;
		totalTiles = 0;
	}
	
	/**
	 * Render all the Tiles within render distance.
	 * This method runs the Tiles' draw method to
	 * render it on screen.
	 */
	public void renderTiles(Graphics g)
	{
		for(int x = 0; x < chunks.size(); x++) // Check every chunk
		{
			// Only check a chunks tiles if it is within chunk load distance
			if (chunks.get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getChunkLoadDistance())
			{
				// Check all tiles of chunks within chunk load distance
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					CoreObject obj = chunks.get(x).getTiles().get(i);
					
					// Only render tiles in range
					if (obj.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) &&
					obj.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) 
					&&
					obj.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) &&
					obj.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()))
					{
						
					}
					else
						continue;
					
					chunks.get(x).getTiles().get(i).renderObject(g);
				}
			}
		}
	}
	
	/**
	 * Get a tile at the specified set of coordinates.
	 * Since there are three layers
	 * of chunks (background, mid-ground, and foreground)
	 * a section to search through must be specified.
	 * Background: 0
	 * Mid-ground: 1
	 * Foreground: 2
	 * @param xPos - the x position of the tile
	 * @param yPos - the y position of the tile
	 * @param section - the section of the world to search (background 0, mid-ground 1, foreground 2)
	 * @return Tile - the tile at those coordinates
	 */
	public Tile getTileAtCoords(float xPos, float yPos, int section)
	{
		int sectionStart = 0;
		int sectionEnd = 1023;
		
		/*
		 * Set the range of chunks to search through.
		 */
		switch (section)
		{
			case 0: // Background
				sectionStart = 0;
				sectionEnd = 1023;
				break;
				
			case 1: // Mid-ground
				sectionStart = 1024;
				sectionEnd = 2047;
				break;
				
			case 2: // Foreground
				sectionStart = 2048;
				sectionEnd = 3071;
				break;
		}
		
		for(int x = sectionStart; x < sectionEnd; x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getChunkLoadDistance())
			{
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					if (chunks.get(x).getTiles().get(i).getxPos() == xPos)
						if (chunks.get(x).getTiles().get(i).getyPos() == yPos)
							return chunks.get(x).getTiles().get(i);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Get the chunk that contains the tile at the given
	 * set of coordinates.  Since there are three layers
	 * of chunks (background, mid-ground, and foreground)
	 * a section to search through must be specified.
	 * Background: 0
	 * Mid-ground: 1
	 * Foreground: 2
	 * @param xPos - the x position of the tile
	 * @param yPos - the y position of the tile
	 * @param section - the section of the world to search (background 0, mid-ground 1, foreground 2)
	 * @return Chunk - the chunk that contains the tile at the given coordinates
	 */
	public Chunk getChunkWithTileAtCoords(float xPos, float yPos, int section)
	{
		int sectionStart = 0;
		int sectionEnd = 1023;
		
		/*
		 * Set the range of chunks to search through.
		 */
		switch (section)
		{
			case 0: // Background
				sectionStart = 0;
				sectionEnd = 1023;
				break;
				
			case 1: // Mid-ground
				sectionStart = 1024;
				sectionEnd = 2047;
				break;
				
			case 2: // Foreground
				sectionStart = 2048;
				sectionEnd = 3071;
				break;
		}
		
		for (int x = sectionStart; x < sectionEnd; x++)
		{
			Chunk chunk = ResourceLoader.core.getWorldHandler().getChunks().get(x);
			
			if (xPos >= chunk.getxPos() && xPos <= chunk.getxPos() + (16 * ResourceLoader.core.getCurrentWorld().getTileWidth()))
			{
				if (yPos >= chunk.getyPos() && yPos <= chunk.getyPos() + (16 * ResourceLoader.core.getCurrentWorld().getTileHeight()))
				{
					return chunk;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Get the Chunk that contains the given Tile.
	 * @param tile - the tile
	 * @return Chunk - the Chunk that contains the given Tile.
	 */
	public Chunk getChunkWithTile(Tile tile)
	{
		for (int x = 0; x < chunks.size(); x++) // Check every chunk
		{
			// Check all tiles of every chunk
			for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
			{
				CoreObject obj = chunks.get(x).getTiles().get(i);
				
				if (obj == tile)
					return chunks.get(x);
			}
		}
		
		return null;
	}
	
	/**
	 * Get the layer of the world the given
	 * chunk is in.
	 * @param chunk - the chunk
	 * @return int - the layer
	 */
	public int getChunkLayer(Chunk chunk)
	{
		for (int x = 0; x < chunks.size(); x++) // Check all chunks
		{
			if (chunks.get(x) == chunk) // If the given chunk matches one found in the list
			{
				if (x >= 0 && x <= 1023)
					return 0;
				else if (x >= 1024 && x <= 2047)
					return 1;
				else if (x >= 2048 && x <= 3071)
					return 2;
			}
		}
		
		return -1; // If the chunk wasn't found
	}
	
	/**
	 * Remove the given tile from the world.
	 * @param tile - the tile to remove
	 */
	public void breakTile(Tile tile)
	{
		for(int x = 0; x < chunks.size(); x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
				ResourceLoader.core.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
				ResourceLoader.core.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
				ResourceLoader.core.getChunkLoadDistance())
			{
				// Check all tiles of chunks within chunk load distance
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					CoreObject obj = chunks.get(x).getTiles().get(i);
					
					// Only check tiles in range
					if (obj.getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) &&
					obj.getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) 
					&&
					obj.getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()) &&
					obj.getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
					(ResourceLoader.core.getLoadDistance() * ResourceLoader.core.getTileWidth()))
					{
						if (obj == tile)
							chunks.get(x).getTiles().remove(tile);
					}
					else
						continue;
				}
			}
		}
	}
	
	/**
	 * Get the full list of Chunks.
	 * @return chunks
	 */
	public List<Chunk> getChunks()
	{
		return chunks;
	}
	
	/**
	 * Add a new Chunk to the Chunk list.
	 * @param chunk
	 */
	public void addChunk(Chunk chunk)
	{
		this.chunks.add(chunk);
	}

	/**
	 * Get the last highest number of tiles loaded.
	 * @return the topTilesLoaded
	 */
	public int getTopTilesLoaded()
	{
		return topTilesLoaded;
	}

	/**
	 * Get the last highest number of tiles in the whole list of chunks.
	 * @return the topTotalTiles
	 */
	public int getTopTotalTiles()
	{
		return topTotalTiles;
	}
}