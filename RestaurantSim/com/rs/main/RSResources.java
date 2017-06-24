package com.rs.main;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.engine.input.Key;
import com.engine.main.Core;
import com.engine.resources.ResourceLoader;
import com.engine.world.World;
import com.rs.objects.RSObjects;
import com.rs.tiles.RSTiles;
import com.rs.world.RSTileAdder;

public class RSResources extends ResourceLoader
{
	public RSResources(Core core)
	{
		super(core);
	}
	
	/*-----------------------------------
	 * Fonts
	 ----------------------------------*/
	public static Font fontMain16;
	public static Font fontMain24;
	public static Font fontMain32;
	public static Font fontMain48;
	public static Font fontMain64;

	@Override
	public void loadFonts()
	{
		fontMain16 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 16, false);
		fontMain24 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 24, false);
		fontMain32 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 32, false);
		fontMain48 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 48, false);
		fontMain64 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 64, false);
	}

	/*-----------------------------
	 * Backgrounds
	 ----------------------------*/
	public static Image mainBg;
	public static Image basicBg;
	
	@Override
	public void loadBackgrounds()
	{
		ImageIcon mainBgII = new ImageIcon(core.getRespath() + "graphics/backgrounds/mainBg.png");
		mainBg = mainBgII.getImage();
		
		ImageIcon basicBgII = new ImageIcon(core.getRespath() + "graphics/backgrounds/basicBg.png");
		basicBg = basicBgII.getImage();
	}

	/*-----------------------------
	 * GUI
	 ----------------------------*/
	public static Image mainBtn;
	public static Image mainBtnHover;
	
	// Top GUI bar
	public static Image topBar;
	public static Image menuBtn;
	public static Image menuBtnHover;
	public static Image playBtn;
	public static Image playBtnHover;
	public static Image pauseBtn;
	public static Image pauseBtnHover;
	public static Image fastBtn;
	public static Image fastBtnHover;
	
	// Bottom GUI bar
	public static Image bottomBar;
	public static Image liveBtn;
	public static Image liveBtnHover;
	public static Image constructBtn;
	public static Image constructBtnHover;
	public static Image furnishBtn;
	public static Image furnishBtnHover;
	public static Image gridBtn;
	public static Image gridBtnHover;
	public static Image openCatalogBtn;
	public static Image openCatalogBtnHover;
	
	// Catalog GUI
	public static Image catalogBg;
	public static Image catalogBtn;
	public static Image catalogBtnHover;
	public static Image nextBtn;
	public static Image nextBtnHover;
	public static Image nextBtnDisabled;
	public static Image prevBtn;
	public static Image prevBtnHover;
	public static Image prevBtnDisabled;
	public static Image catalogInfoBg;
	public static Image buyBtn;
	public static Image buyBtnHover;
	public static Image buyBtnDisabled;
	
	@Override
	public void loadGuiImages()
	{
		ImageIcon mainBtnII = new ImageIcon(core.getRespath() + "graphics/buttonImages/mainBtn.png");
		mainBtn = mainBtnII.getImage();
		ImageIcon mainBtnHoverII = new ImageIcon(core.getRespath() + "graphics/buttonImages/mainBtnHover.png");
		mainBtnHover = mainBtnHoverII.getImage();
		
		// Top bar
		ImageIcon topBarII = new ImageIcon(core.getRespath() + "graphics/gui/topBar.png");
		topBar = topBarII.getImage();
		ImageIcon menuBtnII = new ImageIcon(core.getRespath() + "graphics/gui/menuBtn.png");
		menuBtn = menuBtnII.getImage();
		ImageIcon menuBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/menuBtnHover.png");
		menuBtnHover = menuBtnHoverII.getImage();
		ImageIcon playBtnII = new ImageIcon(core.getRespath() + "graphics/gui/playBtn.png");
		playBtn = playBtnII.getImage();
		ImageIcon playBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/playBtnHover.png");
		playBtnHover = playBtnHoverII.getImage();
		ImageIcon pauseBtnII = new ImageIcon(core.getRespath() + "graphics/gui/pauseBtn.png");
		pauseBtn = pauseBtnII.getImage();
		ImageIcon pauseBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/pauseBtnHover.png");
		pauseBtnHover = pauseBtnHoverII.getImage();
		ImageIcon fastBtnII = new ImageIcon(core.getRespath() + "graphics/gui/fastBtn.png");
		fastBtn = fastBtnII.getImage();
		ImageIcon fastBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/fastBtnHover.png");
		fastBtnHover = fastBtnHoverII.getImage();
		
		// Bottom Bar
		ImageIcon bottomBarII = new ImageIcon(core.getRespath() + "graphics/gui/bottomBar.png");
		bottomBar = bottomBarII.getImage();
		ImageIcon liveBtnII = new ImageIcon(core.getRespath() + "graphics/gui/liveBtn.png");
		liveBtn = liveBtnII.getImage();
		ImageIcon liveBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/liveBtnHover.png");
		liveBtnHover = liveBtnHoverII.getImage();
		ImageIcon constructBtnII = new ImageIcon(core.getRespath() + "graphics/gui/constructBtn.png");
		constructBtn = constructBtnII.getImage();
		ImageIcon constructBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/constructBtnHover.png");
		constructBtnHover = constructBtnHoverII.getImage();
		ImageIcon furnishBtnII = new ImageIcon(core.getRespath() + "graphics/gui/furnishBtn.png");
		furnishBtn = furnishBtnII.getImage();
		ImageIcon furnishBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/furnishBtnHover.png");
		furnishBtnHover = furnishBtnHoverII.getImage();
		ImageIcon gridBtnII = new ImageIcon(core.getRespath() + "graphics/gui/gridBtn.png");
		gridBtn = gridBtnII.getImage();
		ImageIcon gridBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/gridBtnHover.png");
		gridBtnHover = gridBtnHoverII.getImage();
		ImageIcon openCatalogBtnII = new ImageIcon(core.getRespath() + "graphics/gui/openCatalogBtn.png");
		openCatalogBtn = openCatalogBtnII.getImage();
		ImageIcon openCatalogBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/openCatalogBtnHover.png");
		openCatalogBtnHover = openCatalogBtnHoverII.getImage();
		
		// Catalog
		ImageIcon catalogBgII = new ImageIcon(core.getRespath() + "graphics/gui/catalogBg.png");
		catalogBg = catalogBgII.getImage();
		ImageIcon catalogBtnII = new ImageIcon(core.getRespath() + "graphics/gui/catalogBtn.png");
		catalogBtn = catalogBtnII.getImage();
		ImageIcon catalogBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/catalogBtnHover.png");
		catalogBtnHover = catalogBtnHoverII.getImage();
		ImageIcon nextBtnII = new ImageIcon(core.getRespath() + "graphics/gui/nextBtn.png");
		nextBtn = nextBtnII.getImage();
		ImageIcon nextBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/nextBtnHover.png");
		nextBtnHover = nextBtnHoverII.getImage();
		ImageIcon nextBtnDisabledII = new ImageIcon(core.getRespath() + "graphics/gui/nextBtnDisabled.png");
		nextBtnDisabled = nextBtnDisabledII.getImage();
		ImageIcon prevBtnII = new ImageIcon(core.getRespath() + "graphics/gui/prevBtn.png");
		prevBtn = prevBtnII.getImage();
		ImageIcon prevBtnHoverII = new ImageIcon(core.getRespath() + "graphics/gui/prevBtnHover.png");
		prevBtnHover = prevBtnHoverII.getImage();
		ImageIcon prevBtnDisabledII = new ImageIcon(core.getRespath() + "graphics/gui/prevBtnDisabled.png");
		prevBtnDisabled = prevBtnDisabledII.getImage();
		catalogInfoBg = (new ImageIcon(core.getRespath() + "graphics/gui/catalogInfoBg.png")).getImage();
		buyBtn = (new ImageIcon(core.getRespath() + "graphics/gui/buyBtn.png")).getImage();
		buyBtnHover = (new ImageIcon(core.getRespath() + "graphics/gui/buyBtnHover.png")).getImage();
		buyBtnDisabled = (new ImageIcon(core.getRespath() + "graphics/gui/buyBtnDisabled.png")).getImage();
	}
	
	/*-----------------------------
	 * Sprites
	 ----------------------------*/
	public static Image[] playerImages = new Image[1];
	
	// Construct items
	public static Image woodFloor1;
	public static Image woodFloor2;
	public static Image woodFloor3;
	
	// Furnish items
	public static Image smallWoodTable1;
	
	// Ingredient items
	public static Image breadLoaf;

	@Override
	
	public void loadSpriteImages()
	{
		ImageIcon playerImgII = new ImageIcon(core.getRespath() + "graphics/sprites/player.png");
		playerImages[0] = playerImgII.getImage();
		
		// Construct items
		woodFloor1 = (new ImageIcon(core.getRespath() + "graphics/items/construct/woodFloor1.png")).getImage();
		woodFloor2 = (new ImageIcon(core.getRespath() + "graphics/items/construct/woodFloor2.png")).getImage();
		woodFloor3 = (new ImageIcon(core.getRespath() + "graphics/items/construct/woodFloor3.png")).getImage();
		
		// Furnish items
		smallWoodTable1 = (new ImageIcon(core.getRespath() + "graphics/items/furnish/smallWoodTable1.png")).getImage();
		
		// Ingredient items
		ImageIcon breadLoafII = new ImageIcon(core.getRespath() + "graphics/items/ingredients/breadLoaf.png");
		breadLoaf = breadLoafII.getImage();
	}

	/*-----------------------------
	 * Other Images
	 ----------------------------*/
	public static Image worldImg;
	public static Image worldImgBack;
	public static Image worldImgFront;
	
	@Override
	public void loadOtherImages()
	{
		ImageIcon worldImgII = new ImageIcon(core.getRespath() + "worlds/world.png");
		worldImg = worldImgII.getImage();
		ImageIcon worldImgBackII = new ImageIcon(core.getRespath() + "worlds/worldBack.png");
		worldImgBack = worldImgBackII.getImage();
		ImageIcon worldImgFrontII = new ImageIcon(core.getRespath() + "worlds/worldFront.png");
		worldImgFront = worldImgFrontII.getImage();
	}

	/*-----------------------------
	 * Misc.
	 ----------------------------*/
	public static World world;
	
	@Override
	public void loadMisc()
	{
		RSTiles.loadTiles();
		RSObjects.setupObjects();
		
		Image[] worldImages = new Image[3];
		worldImages[0] = worldImg;
		worldImages[1] = worldImgBack;
		worldImages[2] = worldImgFront;
		
		world = new World("testRestaurant", 1, worldImages, RSTiles.getTiles(), new RSTileAdder(), RSObjects.player, 64, 64);
	}

	@Override
	public void postLoad()
	{
		core.getCanvas().setScreenLoaded(true);
	}

	/*-----------------------------
	 * Keys
	 ----------------------------*/
	public static Key up = new Key(KeyEvent.VK_W);
	public static Key down = new Key(KeyEvent.VK_S);
	public static Key left = new Key(KeyEvent.VK_A);
	public static Key right = new Key(KeyEvent.VK_D);
	public static Key jump = new Key(KeyEvent.VK_SPACE);
	public static Key action = new Key(KeyEvent.VK_E);
	
	public static Key one = new Key(KeyEvent.VK_1);
	public static Key two = new Key(KeyEvent.VK_2);
	public static Key three = new Key(KeyEvent.VK_3);
	public static Key four = new Key(KeyEvent.VK_4);
	public static Key five = new Key(KeyEvent.VK_5);
	
	public static Key escape = new Key(KeyEvent.VK_ESCAPE);
	
	@Override
	public void checkKey(int keyCode, boolean isPressed)
	{
		if (keyCode == up.getKeyCode()) // If up was pressed
			up.toggle(isPressed);
		if (keyCode == down.getKeyCode()) // If down was pressed
			down.toggle(isPressed);
		if (keyCode == left.getKeyCode()) // If left was pressed
			left.toggle(isPressed);
		if (keyCode == right.getKeyCode()) // If right was pressed
			right.toggle(isPressed);
		if (keyCode == jump.getKeyCode()) // If the jump key was pressed
			jump.toggle(isPressed);
		if (keyCode == action.getKeyCode()) // If the action key was pressed
			action.toggle(isPressed);
		if (keyCode == one.getKeyCode())
			one.toggle(isPressed);
		if (keyCode == two.getKeyCode())
			two.toggle(isPressed);
		if (keyCode == three.getKeyCode())
			three.toggle(isPressed);
		if (keyCode == four.getKeyCode())
			four.toggle(isPressed);
		if (keyCode == five.getKeyCode())
			five.toggle(isPressed);
		if (keyCode == escape.getKeyCode())
			escape.toggle(isPressed);
	}
}
