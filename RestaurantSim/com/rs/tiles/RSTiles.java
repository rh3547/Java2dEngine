package com.rs.tiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;
import com.rs.objects.RSIds;

public class RSTiles
{
	private static final int TILE_WIDTH = 64;
	private static final int TILE_HEIGHT = 64;
	
	/*-----------------------------
	 * Tiles
	 ----------------------------*/
	// Every tile in the game
	private static List<Tile> tiles = new ArrayList<Tile>();
	
	public static RSTile tileGrid;
	
	// Interior
	public static RSTile tileWoodFloor1;
	public static RSTile tileWallPlasterWhite;
	
	// Exterior
	public static RSTile tileGrass;
	public static RSTile tileRoad;
	public static RSTile tileSidewalk;
	
	/**
	 * Load the tile data.
	 */
	public static void loadTiles()
	{
		// Grid
		ImageIcon tileGridII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileGrid.png");
		tileGrid = new RSTile(RSIds.tileGrid, ResourceLoader.scaleImage(tileGridII.getImage(), TILE_WIDTH, TILE_HEIGHT), 
				false, new Color(255, 255, 255), CoreObject.NO_COLLIDE, "Grid", RSTile.FLOOR);
		tiles.add(tileGrid); 
		
		// Wood floor 1
		ImageIcon tileWoodFloor1II = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileWoodFloor1.png");
		tileWoodFloor1 = new RSTile(RSIds.tileWoodFloor1, ResourceLoader.scaleImage(tileWoodFloor1II.getImage(), TILE_WIDTH, TILE_HEIGHT), 
				false, new Color(175, 150, 112), CoreObject.NO_COLLIDE, "Wood Floor 1", RSTile.FLOOR);
		tiles.add(tileWoodFloor1); 
		
		// Plaster Wall White
		ImageIcon tileWallPlasterWhiteII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileWallPlasterWhite.png");
		tileWallPlasterWhite = new RSTile(RSIds.tileWallPlasterWhite, ResourceLoader.scaleImage(tileWallPlasterWhiteII.getImage(), TILE_WIDTH, TILE_HEIGHT), 
				false, new Color(125, 115, 100), CoreObject.NO_COLLIDE, "White Plaster Wall", RSTile.WALL);
		tiles.add(tileWallPlasterWhite);
		
		// Grass
		ImageIcon tileGrassII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileGrass.png");
		tileGrass = new RSTile(RSIds.tileGrass, ResourceLoader.scaleImage(tileGrassII.getImage(), TILE_WIDTH, TILE_HEIGHT), 
				false, new Color(32, 160, 50), CoreObject.NO_COLLIDE, "Grass", RSTile.UNEDITABLE);
		tiles.add(tileGrass);
		
		// Road
		ImageIcon tileRoadII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileRoad.png");
		tileRoad = new RSTile(RSIds.tileRoad, ResourceLoader.scaleImage(tileRoadII.getImage(), TILE_WIDTH, TILE_HEIGHT), 
				false, new Color(27, 27, 27), CoreObject.NO_COLLIDE, "Road", RSTile.UNEDITABLE);
		tiles.add(tileRoad);
		
		// Sidewalk
		ImageIcon tileSidewalkII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileSidewalk.png");
		tileSidewalk = new RSTile(RSIds.tileSidewalk, ResourceLoader.scaleImage(tileSidewalkII.getImage(), TILE_WIDTH, TILE_HEIGHT), 
				false, new Color(77, 77, 77), CoreObject.NO_COLLIDE, "Sidewalk", RSTile.UNEDITABLE);
		tiles.add(tileSidewalk);
	}

	/**
	 * @return the tiles
	 */
	public static List<Tile> getTiles()
	{
		return tiles;
	}
}