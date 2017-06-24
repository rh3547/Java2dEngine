package com.cubeoTwo.world;

import com.cubeoTwo.tiles.TileLadder;
import com.cubeoTwo.tiles.TilePlatform;
import com.cubeoTwo.tiles.TileSand;
import com.cubeoTwo.tiles.TileTorch;
import com.cubeoTwo.tiles.TileWater;
import com.engine.world.Chunk;
import com.engine.world.Tile;
import com.engine.world.TileAdder;

public class CubeoTwoTileAdder extends TileAdder
{
	@Override
	public void addCustomTile(Tile tile, Chunk chunk, float xPos, float yPos)
	{
		if (tile instanceof TileSand)
			chunk.addTile(new TileSand(xPos, yPos, tile));
		else if (tile instanceof TilePlatform)
			chunk.addTile(new TilePlatform(xPos, yPos, tile));
		else if (tile instanceof TileWater)
			chunk.addTile(new TileWater(xPos, yPos, tile));
		else if (tile instanceof TileTorch)
			chunk.addTile(new TileTorch(xPos, yPos, tile));
		else if (tile instanceof TileLadder)
			chunk.addTile(new TileLadder(xPos, yPos, tile));
		else
			chunk.addTile(new Tile(xPos, yPos, tile));
	}
}