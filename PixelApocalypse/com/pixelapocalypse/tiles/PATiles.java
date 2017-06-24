package com.pixelapocalypse.tiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;
import com.pixelapocalypse.main.PAIds;

public class PATiles
{
	/*-----------------------------
	 * Tiles
	 ----------------------------*/
	// Every tile in the game
	private static List<Tile> tiles = new ArrayList<Tile>();
	
	public static Tile tileVoid;
	public static Tile tileGrass;
	public static Tile tileGrassBg;
	public static Tile tileDirt;
	public static Tile tileDirtBg;
	public static Tile tileWoodFloor;
	public static Tile tileWoodFloorBg;
	public static Tile tileStoneBrick;
	public static Tile tileStoneBrickBg;
	public static Tile tileSmoothStone;
	public static Tile tileSmoothStoneBg;
	public static Tile tileLogSide;
	public static Tile tileLogSideBg;
	public static Tile tileWoodStairRight;
	public static TileTree tileSpruceTree;
	public static TileTree tileOakTree;
	
	// Fake Iso
	public static Tile tileStoneBrickWallVert;
	public static Tile tileStoneBrickWallHor;
	public static Tile tileStoneBrickCorner1;
	public static Tile tileStoneBrickCorner2;
	public static Tile tileStoneBrickCorner3;
	public static Tile tileStoneBrickCorner4;
	public static Tile tileStoneBrickTUp;
	public static Tile tileStoneBrickTRight;
	public static Tile tileStoneBrickTDown;
	public static Tile tileStoneBrickTLeft;
	
	/**
	 * Load the tile data.
	 */
	public static void loadTiles()
	{
		// Void
		ImageIcon tileVoidII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileVoid.png");
		tileVoid = new Tile(PAIds.tileVoid, tileVoidII.getImage(), false, new Color(255, 255, 255), CoreObject.NO_COLLIDE, "tileVoid");
		tiles.add(tileVoid); 
		
		// Grass
		ImageIcon tileGrassII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileGrass.png");
		tileGrass = new Tile(PAIds.tileGrass, tileGrassII.getImage(), false, new Color(65, 155, 30), CoreObject.COLLIDE, "tileGrass");
		tiles.add(tileGrass);
		ImageIcon tileGrassBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileGrassBg.png");
		tileGrassBg = new Tile(PAIds.tileGrass, tileGrassII.getImage(), false, new Color(55, 145, 20), CoreObject.NO_COLLIDE, "tileGrassBg");
		tiles.add(tileGrassBg);
		
		// Dirt
		ImageIcon tileDirtII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileDirt.png");
		tileDirt = new Tile(PAIds.tileDirt, tileDirtII.getImage(), false, new Color(100, 80, 40), CoreObject.COLLIDE, "tileDirt");
		tiles.add(tileDirt);
		ImageIcon tileDirtBgII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileDirtBg.png");
		tileDirtBg = new Tile(PAIds.tileDirt, tileDirtII.getImage(), false, new Color(90, 70, 30), CoreObject.NO_COLLIDE, "tileDirtBg");
		tiles.add(tileDirtBg);
		
		// Wood floor
		ImageIcon tileWoodFloorII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileWoodFloor.png");
		tileWoodFloor = new Tile(PAIds.tileWoodFloor, tileWoodFloorII.getImage(), false, new Color(210, 160, 100), CoreObject.COLLIDE, "tileWoodFloor");
		tiles.add(tileWoodFloor);
		tileWoodFloorBg = new Tile(PAIds.tileWoodFloor, tileWoodFloorII.getImage(), false, new Color(200, 150, 90), CoreObject.NO_COLLIDE, "tileWoodFloorBg");
		tiles.add(tileWoodFloorBg);
		
		// Stone brick
		ImageIcon tileStoneBrickII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrick.png");
		tileStoneBrick = new Tile(PAIds.tileStoneBrick, tileStoneBrickII.getImage(), false, new Color(108, 104, 100), CoreObject.COLLIDE, "tileStoneBrick");
		tiles.add(tileStoneBrick);
		tileStoneBrickBg = new Tile(PAIds.tileStoneBrick, tileStoneBrickII.getImage(), false, new Color(98, 94, 90), CoreObject.NO_COLLIDE, "tileStoneBrickbg");
		tiles.add(tileStoneBrickBg);
		
		// Smooth stone
		ImageIcon tileSmoothStoneII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileSmoothStone.png");
		tileSmoothStone = new Tile(PAIds.tileSmoothStone, tileSmoothStoneII.getImage(), false, new Color(135, 130, 125), 
		CoreObject.COLLIDE, "tileSmoothStone");
		tiles.add(tileSmoothStone);
		tileSmoothStoneBg = new Tile(PAIds.tileSmoothStone, tileSmoothStoneII.getImage(), false, new Color(125, 120, 115), 
		CoreObject.NO_COLLIDE, "tileSmoothStoneBg");
		tiles.add(tileSmoothStoneBg);
		
		// Log side
		ImageIcon tileLogSideII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileLogSide.png");
		tileLogSide = new Tile(PAIds.tileLogSide, tileLogSideII.getImage(), false, new Color(80, 60, 20), CoreObject.COLLIDE, "tileLogSide");
		tiles.add(tileLogSide);
		tileLogSideBg = new Tile(PAIds.tileLogSide, tileLogSideII.getImage(), false, new Color(70, 50, 10), CoreObject.NO_COLLIDE, "tileLogSideBg");
		tiles.add(tileLogSideBg);
		
		// Wood stairs right
		ImageIcon tileWoodStairRightII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileWoodStairRight.png");
		tileWoodStairRight = new Tile(PAIds.tileWoodStairRight, tileWoodStairRightII.getImage(), false, new Color(88, 56, 16), 
		CoreObject.COLLIDE, "tileWoodStairsRight");
		tiles.add(tileWoodStairRight);
		
		// Spruce tree
		ImageIcon tileSpruceTreeII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileSpruceTree.png");
		tileSpruceTree = new TileTree(PAIds.tileSpruceTree, tileSpruceTreeII.getImage(), false, new Color(23, 70, 14), CoreObject.COLLIDE, "tileSpruceTree");
		tiles.add(tileSpruceTree);
		
		// Oak tree
		ImageIcon tileOakTreeII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileOakTree.png");
		tileOakTree = new TileTree(PAIds.tileOakTree, tileOakTreeII.getImage(), false, new Color(12, 196, 16), CoreObject.COLLIDE, "tileOakTree");
		tiles.add(tileOakTree);
		
		/*
		 * Fake Iso
		 */
		
		// Stone brick wall vert
		ImageIcon tileStoneBrickWallVertII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickWallVert.png");
		tileStoneBrickWallVert = new Tile(PAIds.tileStoneBrickWallVert, tileStoneBrickWallVertII.getImage(), false, new Color(108, 104, 101), 
		CoreObject.COLLIDE, "tileStoneBrickWallVert");
		tiles.add(tileStoneBrickWallVert);
		
		// Stone brick wall hor
		ImageIcon tileStoneBrickWallHorII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickWallHor.png");
		tileStoneBrickWallHor = new Tile(PAIds.tileStoneBrickWallHor, tileStoneBrickWallHorII.getImage(), false, new Color(108, 104, 102), 
		CoreObject.COLLIDE, "tileStoneBrickWallHor");
		tiles.add(tileStoneBrickWallHor);
		
		// Stone brick corner 1
		ImageIcon tileStoneBrickCorner1II = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickCorner1.png");
		tileStoneBrickCorner1 = new Tile(PAIds.tileStoneBrickCorner1, tileStoneBrickCorner1II.getImage(), false, new Color(108, 104, 103), 
		CoreObject.COLLIDE, "tileStoneBrickCorner1");
		tiles.add(tileStoneBrickCorner1);
		
		// Stone brick corner 2
		ImageIcon tileStoneBrickCorner2II = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickCorner2.png");
		tileStoneBrickCorner2 = new Tile(PAIds.tileStoneBrickCorner2, tileStoneBrickCorner2II.getImage(), false, new Color(108, 104, 104), 
		CoreObject.COLLIDE, "tileStoneBrickCorner2");
		tiles.add(tileStoneBrickCorner2);
		
		// Stone brick corner 3
		ImageIcon tileStoneBrickCorner3II = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickCorner3.png");
		tileStoneBrickCorner3 = new Tile(PAIds.tileStoneBrickCorner3, tileStoneBrickCorner3II.getImage(), false, new Color(108, 104, 105), 
		CoreObject.COLLIDE, "tileStoneBrickCorner3");
		tiles.add(tileStoneBrickCorner3);
		
		// Stone brick corner 4
		ImageIcon tileStoneBrickCorner4II = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickCorner4.png");
		tileStoneBrickCorner4 = new Tile(PAIds.tileStoneBrickCorner4, tileStoneBrickCorner4II.getImage(), false, new Color(108, 104, 106), 
		CoreObject.COLLIDE, "tileStoneBrickCorner4");
		tiles.add(tileStoneBrickCorner4);
		
		// Stone brick t up
		ImageIcon tileStoneBrickTUpII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickTUp.png");
		tileStoneBrickTUp = new Tile(PAIds.tileStoneBrickTUp, tileStoneBrickTUpII.getImage(), false, new Color(108, 104, 107), 
		CoreObject.COLLIDE, "tileStoneBrickTUp");
		tiles.add(tileStoneBrickTUp);
		
		// Stone brick t right
		ImageIcon tileStoneBrickTRightII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickTRight.png");
		tileStoneBrickTRight = new Tile(PAIds.tileStoneBrickTRight, tileStoneBrickTRightII.getImage(), false, new Color(108, 104, 108), 
		CoreObject.COLLIDE, "tileStoneBrickTRight");
		tiles.add(tileStoneBrickTRight);
		
		// Stone brick t down
		ImageIcon tileStoneBrickTDownII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickTDown.png");
		tileStoneBrickTDown = new Tile(PAIds.tileStoneBrickTDown, tileStoneBrickTDownII.getImage(), false, new Color(108, 104, 109), 
		CoreObject.COLLIDE, "tileStoneBrickTDown");
		tiles.add(tileStoneBrickTDown);
		
		// Stone brick t left
		ImageIcon tileStoneBrickTLeftII = new ImageIcon(ResourceLoader.core.getRespath() + "graphics/tiles/tileStoneBrickTLeft.png");
		tileStoneBrickTLeft = new Tile(PAIds.tileStoneBrickTLeft, tileStoneBrickTLeftII.getImage(), false, new Color(108, 104, 110), 
		CoreObject.COLLIDE, "tileStoneBrickTLeft");
		tiles.add(tileStoneBrickTLeft);
	}

	/**
	 * @return the tiles
	 */
	public static List<Tile> getTiles()
	{
		return tiles;
	}
}