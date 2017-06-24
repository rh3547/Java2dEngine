package com.cubeoTwo.tiles;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.cubeoTwo.objects.ObjectIds;
import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;

/**
 * All of CubeoTwo's Tiles are
 * created and referenced here.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CubeoTwoTiles 
{
	// Every CubeoTwo Tile
	private static List<Tile> tiles = new ArrayList<Tile>();
	
	/***************************
	 * Tiles
	 **************************/
	public static Tile voidTile;
	public static Tile dirt;
	public static Tile dirtBg;
	public static Tile grass;
	public static Tile grassBg;
	public static Tile stone;
	public static Tile stoneBg;
	public static Tile log;
	public static Tile logBg;
	public static Tile leaf;
	public static Tile leafBg;
	public static TileSand sand;
	public static TileSand sandBg;
	public static TilePlatform woodPlatform;
	public static Tile coalOre;
	public static Tile ironOre;
	public static Tile water;
	public static TileTorch torch;
	public static TileLadder ladder;
	
	/***************************
	 * Tile Images
	 **************************/
	private static Image tileVoid;
	private static Image tileDirt;
	private static Image tileDirtBg;
	private static Image tileGrass;
	private static Image tileGrassBg;
	private static Image tileStone;
	private static Image tileStoneBg;
	private static Image tileLog;
	private static Image tileLogBg;
	private static Image tileLeaf;
	private static Image tileLeafBg;
	private static Image tileSand;
	private static Image tileSandBg;
	private static Image tileWoodPlatform;
	private static Image tileCoalOre;
	private static Image tileIronOre;
	private static Image tileWater;
	private static Image tileTorch;
	private static Image tileLadder;
	
	public static void loadTiles()
	{
		// Void
		ImageIcon tileVoidII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileVoid.png");
		tileVoid = tileVoidII.getImage();
		voidTile = new Tile(ObjectIds.tileVoid, tileVoid, false, new Color(255, 255, 255), CoreObject.NO_COLLIDE, "tileVoid");
		tiles.add(voidTile); 
		
		// Dirt
		ImageIcon tileDirtII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileDirt.png");
		tileDirt = tileDirtII.getImage();
		dirt = new Tile(ObjectIds.tileDirt, tileDirt, false, new Color(100, 80, 40), CoreObject.COLLIDE, "tileDirt");
		tiles.add(dirt);
		ImageIcon tileDirtBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileDirtBg.png");
		tileDirtBg = tileDirtBgII.getImage();
		dirtBg = new Tile(ObjectIds.tileDirt, tileDirtBg, false, new Color(90, 70, 30), CoreObject.NO_COLLIDE, "tileDirtBg");
		tiles.add(dirtBg);
		
		// Grass
		ImageIcon tileGrassII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileGrass.png");
		tileGrass = tileGrassII.getImage();
		grass = new Tile(ObjectIds.tileGrass, tileGrass, false, new Color(65, 155, 30), CoreObject.COLLIDE, "tileGrass");
		tiles.add(grass);
		ImageIcon tileGrassBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileGrassBg.png");
		tileGrassBg = tileGrassBgII.getImage();
		grassBg = new Tile(ObjectIds.tileGrass, tileGrassBg, false, new Color(55, 145, 20), CoreObject.NO_COLLIDE, "tileGrassBg");
		tiles.add(grassBg);
		
		// Stone
		ImageIcon tileStoneII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStone.png");
		tileStone = tileStoneII.getImage();
		stone = new Tile(ObjectIds.tileStone, tileStone, false, new Color(115, 110, 110), CoreObject.COLLIDE, "tileStone");
		tiles.add(stone);
		ImageIcon tileStoneBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBg.png");
		tileStoneBg = tileStoneBgII.getImage();
		stoneBg = new Tile(ObjectIds.tileStone, tileStoneBg, false, new Color(105, 100, 100), CoreObject.NO_COLLIDE, "tileStoneBg");
		tiles.add(stoneBg);
		
		// Log
		ImageIcon tileLogII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileLog.png");
		tileLog = tileLogII.getImage();
		log = new Tile(ObjectIds.tileLog, tileLog, false, new Color(65, 50, 15), CoreObject.COLLIDE, "tileLog");
		tiles.add(log);
		ImageIcon tileLogBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileLogBg.png");
		tileLogBg = tileLogBgII.getImage();
		logBg = new Tile(ObjectIds.tileLog, tileLogBg, false, new Color(55, 40, 5), CoreObject.NO_COLLIDE, "tileLogBg");
		tiles.add(logBg);
		
		// Leaf
		ImageIcon tileLeafII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileLeaf.png");
		tileLeaf = tileLeafII.getImage();
		leaf = new Tile(ObjectIds.tileLeaf, tileLeaf, false, new Color(35, 150, 25), CoreObject.COLLIDE, "tileLeaf");
		tiles.add(leaf);
		ImageIcon tileLeafBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileLeafBg.png");
		tileLeafBg = tileLeafBgII.getImage();
		leafBg = new Tile(ObjectIds.tileLeaf, tileLeafBg, false, new Color(25, 140, 15), CoreObject.NO_COLLIDE, "tileLeafBg");
		tiles.add(leafBg);
		
		// Sand
		ImageIcon tileSandII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileSand.png");
		tileSand = tileSandII.getImage();
		sand = new TileSand(ObjectIds.tileSand, tileSand, true, new Color(225, 210, 175), CoreObject.COLLIDE, "tileSand");
		tiles.add(sand);
		ImageIcon tileSandBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileSandBg.png");
		tileSandBg = tileSandBgII.getImage();
		sandBg = new TileSand(ObjectIds.tileSand, tileSandBg, true, new Color(215, 130, 165), CoreObject.NO_COLLIDE, "tileSandBg");
		tiles.add(sandBg);
		
		// Wood Platform
		ImageIcon tileWoodPlatformII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileWoodPlatform.png");
		tileWoodPlatform = tileWoodPlatformII.getImage();
		woodPlatform = new TilePlatform(ObjectIds.tileWoodPlatform, tileWoodPlatform, false, new Color(155, 120, 80), CoreObject.COLLIDE, "tileWoodPlatform");
		tiles.add(woodPlatform);
		
		// Coal Ore
		ImageIcon tileCoalOreII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileCoalOre.png");
		tileCoalOre = tileCoalOreII.getImage();
		coalOre = new Tile(ObjectIds.tileCoalOre, tileCoalOre, false, new Color(50, 50, 50), CoreObject.COLLIDE, "tileCoalOre");
		tiles.add(coalOre);
		
		// Iron Ore
		ImageIcon tileIronOreII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileIronOre.png");
		tileIronOre = tileIronOreII.getImage();
		ironOre = new Tile(ObjectIds.tileIronOre, tileIronOre, false, new Color(170, 130, 95), CoreObject.COLLIDE, "tileIronOre");
		tiles.add(ironOre);
		
		// Water
		ImageIcon tileWaterII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileWater.png");
		tileWater = tileWaterII.getImage();
		water = new TileWater(ObjectIds.tileWater, tileWater, false, new Color(50, 90, 190), CoreObject.COLLIDE, "tileWater");
		tiles.add(water);
		
		// Torch
		ImageIcon tileTorchII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileTorch.png");
		tileTorch = tileTorchII.getImage();
		torch = new TileTorch(ObjectIds.tileTorch, tileTorch, false, new Color(220, 220, 40), CoreObject.NO_COLLIDE, "tileTorch");
		tiles.add(torch);
		
		// Ladder
		ImageIcon tileLadderII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileLadder.png");
		tileLadder = tileLadderII.getImage();
		ladder = new TileLadder(ObjectIds.tileLadder, tileLadder, false, new Color(160, 125, 70), CoreObject.COLLIDE, "tileLadder");
		tiles.add(ladder);
	}
	
	/**
	 * Get the list of tiles.
	 * @return tiles - The list of tiles in the game
	 */
	public static List<Tile> getTiles()
	{
		return tiles;
		
	}
}
