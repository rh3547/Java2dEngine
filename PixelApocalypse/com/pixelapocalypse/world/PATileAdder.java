package com.pixelapocalypse.world;

import com.engine.world.Chunk;
import com.engine.world.Tile;
import com.engine.world.TileAdder;
import com.pixelapocalypse.tiles.TileTree;

public class PATileAdder extends TileAdder
{
	@Override
	public void addCustomTile(Tile tile, Chunk chunk, float xPos, float yPos)
	{
		if (tile instanceof TileTree)
		{
			chunk.addTile(new TileTree(xPos - (tile.getObjectWidth() / 3), yPos - (2 * (tile.getObjectHeight() / 3)), tile));
		}
		else
			chunk.addTile(new Tile(xPos, yPos, tile));
	}
}
