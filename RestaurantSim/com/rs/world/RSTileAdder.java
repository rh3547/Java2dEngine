package com.rs.world;

import com.engine.world.Chunk;
import com.engine.world.Tile;
import com.engine.world.TileAdder;
import com.rs.tiles.RSTile;

public class RSTileAdder extends TileAdder
{
	@Override
	public void addCustomTile(Tile tile, Chunk chunk, float xPos, float yPos)
	{
		chunk.addTile(new RSTile(xPos, yPos, tile));
	}
}