package com.pixelapocalypse.main;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.engine.graphics.Animation;
import com.engine.graphics.AnimationSet;
import com.engine.input.Key;
import com.engine.main.Core;
import com.engine.resources.ResourceLoader;
import com.engine.world.World;
import com.pixelapocalypse.objects.PAObjects;
import com.pixelapocalypse.tiles.PATiles;
import com.pixelapocalypse.world.PATileAdder;

public class PAResources extends ResourceLoader
{
	private Core core;
	
	public PAResources(Core core)
	{
		super(core);
		
		this.core = core;
	}

	/*-----------------------------------
	 * Fonts
	 ----------------------------------*/
	public static Font fontMain;
	public static Font fontMainLarge;
	
	@Override
	public void loadFonts()
	{
		fontMain = registerFont("fonts/MyUnderwood.ttf", "My Underwood", 32, false);
		fontMainLarge = registerFont("fonts/MyUnderwood.ttf", "My Underwood", 64, false);
	}
	
	/*-----------------------------
	 * Backgrounds
	 ----------------------------*/
	public static Image mainBg;

	@Override
	public void loadBackgrounds()
	{
		ImageIcon mainBgII = new ImageIcon(core.getRespath() + "graphics/backgrounds/mainBg.png");
		mainBg = mainBgII.getImage();
	}
	
	/*-----------------------------
	 * GUI
	 ----------------------------*/
	public static Image mainBtn;
	public static Image mainBtnHover;

	@Override
	public void loadGuiImages()
	{
		ImageIcon mainBtnII = new ImageIcon(core.getRespath() + "graphics/buttonImages/mainBtn.png");
		mainBtn = mainBtnII.getImage();
		ImageIcon mainBtnHoverII = new ImageIcon(core.getRespath() + "graphics/buttonImages/mainBtnHover.png");
		mainBtnHover = mainBtnHoverII.getImage();
	}
	
	/*-----------------------------
	 * Sprite Resources
	 ----------------------------*/
	public static Image[] playerImages = new Image[4];
	public static AnimationSet playerAnimations = new AnimationSet(4);

	@Override
	public void loadSpriteImages()
	{
		// Player
		ImageIcon playerImageII = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerRight2.png");
		ImageIcon playerImage2II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerLeft2.png");
		ImageIcon playerImage3II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerForward2.png");
		ImageIcon playerImage4II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerDown2.png");
		
		playerImages[0] = playerImageII.getImage();
		playerImages[1] = playerImage2II.getImage();
		playerImages[2] = playerImage3II.getImage();
		playerImages[3] = playerImage4II.getImage();
		
		// Player right animation images
		ImageIcon playerImageRight1II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerRight1.png");
		ImageIcon playerImageRight3II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerRight3.png");
		
		BufferedImage[] playerRight = new BufferedImage[3];
		playerRight[0] = ResourceLoader.toBufferedImage(playerImageRight1II.getImage());
		playerRight[1] = ResourceLoader.toBufferedImage(playerImageII.getImage());
		playerRight[2] = ResourceLoader.toBufferedImage(playerImageRight3II.getImage());
		
		// Player left animation images
		ImageIcon playerImageLeft1II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerLeft1.png");
		ImageIcon playerImageLeft3II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerLeft3.png");
		
		BufferedImage[] playerLeft = new BufferedImage[3];
		playerLeft[0] = ResourceLoader.toBufferedImage(playerImageLeft1II.getImage());
		playerLeft[1] = ResourceLoader.toBufferedImage(playerImage2II.getImage());
		playerLeft[2] = ResourceLoader.toBufferedImage(playerImageLeft3II.getImage());
		
		// Player forward animation images
		ImageIcon playerImageForward1II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerForward1.png");
		ImageIcon playerImageForward3II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerForward3.png");
		
		BufferedImage[] playerForward = new BufferedImage[3];
		playerForward[0] = ResourceLoader.toBufferedImage(playerImageForward1II.getImage());
		playerForward[1] = ResourceLoader.toBufferedImage(playerImage3II.getImage());
		playerForward[2] = ResourceLoader.toBufferedImage(playerImageForward3II.getImage());
		
		// Player back animation images
		ImageIcon playerImageBack1II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerDown1.png");
		ImageIcon playerImageBack3II = new ImageIcon(core.getRespath() + "graphics/sprites/player/playerDown3.png");
		
		BufferedImage[] playerBack = new BufferedImage[3];
		playerBack[0] = ResourceLoader.toBufferedImage(playerImageBack1II.getImage());
		playerBack[1] = ResourceLoader.toBufferedImage(playerImage4II.getImage());
		playerBack[2] = ResourceLoader.toBufferedImage(playerImageBack3II.getImage());
		
		Animation playerWalkRightAni = new Animation(5, playerRight);
		Animation playerWalkLeftAni = new Animation(5, playerLeft);
		Animation playerWalkForwardAni = new Animation(5, playerForward);
		Animation playerWalkBackAni = new Animation(5, playerBack);
		playerAnimations.addAnimation(playerWalkRightAni);
		playerAnimations.addAnimation(playerWalkLeftAni);
		playerAnimations.addAnimation(playerWalkForwardAni);
		playerAnimations.addAnimation(playerWalkBackAni);
	}

	/*------------------------------------
	 * World Images
	 -----------------------------------*/
	public static Image testWorldImg;
	public static Image testWorldImgBack;
	public static Image testWorldImgFront;
	
	@Override
	public void loadOtherImages()
	{
		ImageIcon testWorldImgII = new ImageIcon(core.getRespath() + "worlds/testWorld.png");
		testWorldImg = testWorldImgII.getImage();
		ImageIcon testWorldImgBackII = new ImageIcon(core.getRespath() + "worlds/testWorldBack.png");
		testWorldImgBack = testWorldImgBackII.getImage();
		ImageIcon testWorldImgFrontII = new ImageIcon(core.getRespath() + "worlds/testWorldFront.png");
		testWorldImgFront = testWorldImgFrontII.getImage();
	}
	
	/*---------------------------------
	 * Worlds
	 --------------------------------*/
	public static World testWorld;

	@Override
	public void loadMisc()
	{
		PATiles.loadTiles();
		PAObjects.setupObjects();
		
		Image[] testWorldImages = new Image[3];
		testWorldImages[0] = testWorldImg;
		testWorldImages[1] = testWorldImgBack;
		testWorldImages[2] = testWorldImgFront;
		
		/*
		 * Tile sizes:
		 * - 2d top-down: 32x32
		 * - isometric: 62x30
		 */
		testWorld = new World("testWorld", 1, testWorldImages, PATiles.getTiles(), new PATileAdder(), PAObjects.player, 64, 64);
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
