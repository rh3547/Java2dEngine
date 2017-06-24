package com.rs.objects;

import java.awt.Color;
import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.data.Catalog;
import com.engine.data.CatalogItem;
import com.engine.entity.Player;
import com.engine.world.Camera;
import com.rs.main.RSResources;
import com.rs.tiles.RSTile;

public class RSObjects
{
	// Player
	public static Player player = new Player(0, 0, RSIds.player, 
			RSResources.playerImages, 
			false, null, "player1");
	
	// The game Camera that will follow the Player
	public static Camera camera = new Camera(0, 0, player);
	
	// Catalogs
	public static CatalogInfoPanel<RSCatalogItem> panel;
	
	public static Catalog<RSCatalogItem> constructCatalog;
	public static Catalog<RSCatalogItem> furnishCatalog;
	public static Catalog<RSCatalogItem> ingredientsCatalog;
	
	public static void setupObjects()
	{
		player.setCollisionType(CoreObject.NO_COLLIDE);
		player.enablePhysics(false);
		
		Image[] nextBtnImages = new Image[3];
		Image[] prevBtnImages = new Image[3];
		nextBtnImages[0] = RSResources.nextBtn;
		nextBtnImages[1] = RSResources.nextBtnHover;
		nextBtnImages[2] = RSResources.nextBtnDisabled;
		prevBtnImages[0] = RSResources.prevBtn;
		prevBtnImages[1] = RSResources.prevBtnHover;
		prevBtnImages[2] = RSResources.prevBtnDisabled;
		
		// Catalog info panel
		panel = new CatalogInfoPanel<RSCatalogItem>();
		
		// Catalog: Construct
		constructCatalog = new Catalog<RSCatalogItem>(75, 75, 20, "Construct", RSResources.catalogBg, nextBtnImages, prevBtnImages);
		constructCatalog.setTitleFont(RSResources.fontMain48);
		constructCatalog.setTitleColor(new Color(53, 53, 53));
		constructCatalog.setTextOffsetX(137);
		constructCatalog.setTextOffsetY(50);
		constructCatalog.setTileOffsetX(30);
		constructCatalog.setTileOffsetY(110);
		constructCatalog.setNextBtnOffsetX(-90);
		constructCatalog.setNextBtnOffsetY(-40);
		constructCatalog.setPrevBtnOffsetX(35);
		constructCatalog.setPrevBtnOffsetY(-40);
		 

		
		// Catalog: Furnish
		furnishCatalog = new Catalog<RSCatalogItem>(75, 75, 20, "Furnish", RSResources.catalogBg, nextBtnImages, prevBtnImages);
		furnishCatalog.setTitleFont(RSResources.fontMain48);
		furnishCatalog.setTitleColor(new Color(53, 53, 53));
		furnishCatalog.setTextOffsetX(175);
		furnishCatalog.setTextOffsetY(50);
		furnishCatalog.setTileOffsetX(30);
		furnishCatalog.setTileOffsetY(110);
		furnishCatalog.setNextBtnOffsetX(-90);
		furnishCatalog.setNextBtnOffsetY(-40);
		furnishCatalog.setPrevBtnOffsetX(35);
		furnishCatalog.setPrevBtnOffsetY(-40);
		
		
		// Catalog: Ingredients
		ingredientsCatalog = new Catalog<RSCatalogItem>(75, 75, 20, "Ingredients", RSResources.catalogBg, nextBtnImages, prevBtnImages);
		ingredientsCatalog.setTitleFont(RSResources.fontMain48);
		ingredientsCatalog.setTitleColor(new Color(53, 53, 53));
		ingredientsCatalog.setTextOffsetX(125);
		ingredientsCatalog.setTextOffsetY(50);
		ingredientsCatalog.setTileOffsetX(30);
		ingredientsCatalog.setTileOffsetY(110);
		ingredientsCatalog.setNextBtnOffsetX(-90);
		ingredientsCatalog.setNextBtnOffsetY(-40);
		ingredientsCatalog.setPrevBtnOffsetX(35);
		ingredientsCatalog.setPrevBtnOffsetY(-40);
		
	}
}