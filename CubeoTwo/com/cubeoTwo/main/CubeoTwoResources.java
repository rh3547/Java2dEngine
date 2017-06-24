package com.cubeoTwo.main;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.cubeoTwo.objects.CubeoTwoObjects;
import com.cubeoTwo.tiles.CubeoTwoTiles;
import com.cubeoTwo.world.CubeoTwoTileAdder;
import com.engine.graphics.Animation;
import com.engine.graphics.AnimationSet;
import com.engine.graphics.ParticleGenerator;
import com.engine.input.Key;
import com.engine.main.Core;
import com.engine.resources.ResourceLoader;
import com.engine.world.World;

/**
 * This resource file is specific to this game.
 * It uses ResourceLoader in the engine.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CubeoTwoResources extends ResourceLoader
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Core core;
	
	/**
	 * Create a new ResourcesCubeoTwo.
	 * @param core
	 */
	public CubeoTwoResources(Core core)
	{
		super(core);
		
		this.core = core;
	}

	/***********************************
	 * Fonts
	 **********************************/
	public static Font fontAnimated;
	public static Font fontAnimatedSmall;
	
	@Override
	public void loadFonts()
	{
		fontAnimated = registerFont("fonts/Animated.ttf", "Animated", 32, false);
		fontAnimatedSmall = registerFont("fonts/Animated.ttf", "Animated", 24, false);
	}

	/***********************************
	 * Backgrounds
	 **********************************/
	public static Image background1;
	public static Image pixelSky;
	
	public static Image forestPara0;
	public static Image forestPara1;
	public static Image forestPara2;
	public static Image forestPara3;
	
	@Override
	public void loadBackgrounds()
	{
		ImageIcon background1II = new ImageIcon(core.getRespath() 
				 + "graphics/backgrounds/background1.jpg");
		background1 = background1II.getImage();
		
		ImageIcon pixelSkyII = new ImageIcon(core.getRespath() 
				 + "graphics/backgrounds/pixelSky.jpg");
		pixelSky = pixelSkyII.getImage();
		
		// Forest Parallax
		ImageIcon forestPara0II = new ImageIcon(core.getRespath() 
				 + "graphics/backgrounds/forestPara0.png");
		forestPara0 = forestPara0II.getImage();
		
		ImageIcon forestPara1II = new ImageIcon(core.getRespath() 
				 + "graphics/backgrounds/forestPara1.png");
		forestPara1 = forestPara1II.getImage();
		
		ImageIcon forestPara2II = new ImageIcon(core.getRespath() 
				 + "graphics/backgrounds/forestPara2.png");
		forestPara2 = forestPara2II.getImage();
		
		ImageIcon forestPara3II = new ImageIcon(core.getRespath() 
				 + "graphics/backgrounds/forestPara3.png");
		forestPara3 = forestPara3II.getImage();
		
		core.getCanvas().setLoadingFont(fontAnimated);
	}
	
	/***********************************
	 * Gui Images
	 **********************************/
	public static Image basicButton;
	public static Image basicButtonHover;
	
	@Override
	public void loadGuiImages()
	{
		ImageIcon basicButtonII = new ImageIcon(core.getRespath() 
				 + "graphics/buttonImages/basicBtn.png");
		basicButton = basicButtonII.getImage();
		ImageIcon basicButtonHoverII = new ImageIcon(core.getRespath() 
				 + "graphics/buttonImages/basicBtnHover.png");
		basicButtonHover = basicButtonHoverII.getImage();
	}
	
	/***********************************
	 * Sprite Images
	 **********************************/
	// Player
	public static Image[] playerImages = new Image[2];
	
	private Animation playerWalkRightAni;
	private Animation playerWalkLeftAni;
	private Animation playerJumpRightAni;
	private Animation playerJumpLeftAni;
	public static AnimationSet playerAnimations = new AnimationSet(4);
	
	// Entities
	public static Image[] wraithImages = new Image[2];
	public static AnimationSet wraithAnimations = new AnimationSet(2);
	
	// Pickups
	public static Image jumpBoostPickup;
	
	@Override
	public void loadSpriteImages()
	{
		// Player
		ImageIcon playerImageII = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkRight4.png");
		Image playerImage = playerImageII.getImage();
		
		ImageIcon playerImage2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkLeft4.png");
		Image playerImage2 = playerImage2II.getImage();
		
		playerImages[0] = playerImage;
		playerImages[1] = playerImage2;
		
		// Player right animation images
		ImageIcon playerImageRight1II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkRight1.png");
		ImageIcon playerImageRight2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkRight2.png");
		ImageIcon playerImageRight3II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkRight3.png");
		
		BufferedImage[] playerRight = new BufferedImage[4];
		playerRight[0] = ResourceLoader.toBufferedImage(playerImageRight1II.getImage());
		playerRight[1] = ResourceLoader.toBufferedImage(playerImageRight2II.getImage());
		playerRight[2] = ResourceLoader.toBufferedImage(playerImageRight3II.getImage());
		playerRight[3] = ResourceLoader.toBufferedImage(playerImage);
		
		// Player left animation images
		ImageIcon playerImageLeft1II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkLeft1.png");
		ImageIcon playerImageLeft2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkLeft2.png");
		ImageIcon playerImageLeft3II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkLeft3.png");
		ImageIcon playerImageLeft4II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerWalkLeft4.png");
		
		BufferedImage[] playerLeft = new BufferedImage[4];
		playerLeft[0] = ResourceLoader.toBufferedImage(playerImageLeft1II.getImage());
		playerLeft[1] = ResourceLoader.toBufferedImage(playerImageLeft2II.getImage());
		playerLeft[2] = ResourceLoader.toBufferedImage(playerImageLeft3II.getImage());
		playerLeft[3] = ResourceLoader.toBufferedImage(playerImageLeft4II.getImage());
		
		// Player jump right animation images
		ImageIcon playerImageJumpRight1II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpRight1.png");
		ImageIcon playerImageJumpRight2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpRight2.png");
		ImageIcon playerImageJumpRight3II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpRight3.png");
		ImageIcon playerImageJumpRight4II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpRight4.png");
		
		BufferedImage[] playerJumpRight = new BufferedImage[4];
		playerJumpRight[0] = ResourceLoader.toBufferedImage(playerImageJumpRight1II.getImage());
		playerJumpRight[1] = ResourceLoader.toBufferedImage(playerImageJumpRight2II.getImage());
		playerJumpRight[2] = ResourceLoader.toBufferedImage(playerImageJumpRight3II.getImage());
		playerJumpRight[3] = ResourceLoader.toBufferedImage(playerImageJumpRight4II.getImage());
		
		// Player jump left animation images
		ImageIcon playerImageJumpLeft1II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpLeft1.png");
		ImageIcon playerImageJumpLeft2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpLeft2.png");
		ImageIcon playerImageJumpLeft3II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpLeft3.png");
		ImageIcon playerImageJumpLeft4II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/player/playerJumpLeft4.png");
		
		BufferedImage[] playerJumpLeft = new BufferedImage[4];
		playerJumpLeft[0] = ResourceLoader.toBufferedImage(playerImageJumpLeft1II.getImage());
		playerJumpLeft[1] = ResourceLoader.toBufferedImage(playerImageJumpLeft2II.getImage());
		playerJumpLeft[2] = ResourceLoader.toBufferedImage(playerImageJumpLeft3II.getImage());
		playerJumpLeft[3] = ResourceLoader.toBufferedImage(playerImageJumpLeft4II.getImage());
		
		playerWalkRightAni = new Animation(3, playerRight);
		playerWalkLeftAni = new Animation(3, playerLeft);
		playerJumpRightAni = new Animation(10, playerJumpRight);
		playerJumpLeftAni = new Animation(10, playerJumpLeft);
		playerAnimations.addAnimation(playerWalkRightAni);
		playerAnimations.addAnimation(playerWalkLeftAni);
		playerAnimations.addAnimation(playerJumpRightAni);
		playerAnimations.addAnimation(playerJumpLeftAni);
		
		// Entities
		ImageIcon wraithImageRightII = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/entities/wraithRight.png");
		ImageIcon wraithImageRight2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/entities/wraithRight2.png");
		
		ImageIcon wraithImageLeftII = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/entities/wraithLeft.png");
		ImageIcon wraithImageLeft2II = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/entities/wraithLeft2.png");
		
		wraithImages[0] = wraithImageRightII.getImage();
		wraithImages[1] = wraithImageLeftII.getImage();
		
		BufferedImage[] wraithMoveRightImages = new BufferedImage[2];
		wraithMoveRightImages[0] = ResourceLoader.toBufferedImage(wraithImageRightII.getImage());
		wraithMoveRightImages[1] = ResourceLoader.toBufferedImage(wraithImageRight2II.getImage());
		
		BufferedImage[] wraithMoveLeftImages = new BufferedImage[2];
		wraithMoveLeftImages[0] = ResourceLoader.toBufferedImage(wraithImageLeftII.getImage());
		wraithMoveLeftImages[1] = ResourceLoader.toBufferedImage(wraithImageLeft2II.getImage());
		
		Animation wraithMoveRight = new Animation(5, wraithMoveRightImages);
		Animation wraithMoveLeft = new Animation(5, wraithMoveLeftImages);
		wraithAnimations.addAnimation(wraithMoveRight);
		wraithAnimations.addAnimation(wraithMoveLeft);
		
		// health pickup
		ImageIcon jumpBoostPickupII = new ImageIcon(core.getRespath() 
				 + "graphics/sprites/jumpBoostPickup.png");
		jumpBoostPickup = jumpBoostPickupII.getImage();
	}
	
	/***********************************
	 * Other Images
	 **********************************/
	// World 1
	public static Image world1Image;
	public static Image world1BackImage;
	public static Image world1FrontImage;
	
	// Particle generators
	private static BufferedImage[] poofImages;
	public static ParticleGenerator poofParticles;
	public static ParticleGenerator jumpParticles;
	
	private static BufferedImage[] fireImages;
	public static ParticleGenerator fireParticles;
	
	
	@Override
	public void loadOtherImages()
	{
		// World 1
		ImageIcon world1ImageII = new ImageIcon(core.getRespath() + "worlds/world1.png");
		world1Image = world1ImageII.getImage();
		ImageIcon world1BackImageII = new ImageIcon(core.getRespath() + "worlds/world1Back.png");
		world1BackImage = world1BackImageII.getImage();
		ImageIcon world1FrontImageII = new ImageIcon(core.getRespath() + "worlds/world1Front.png");
		world1FrontImage = world1FrontImageII.getImage();
		
		// poofParticles
		ImageIcon poofImg1II = new ImageIcon(core.getRespath() 
				 + "graphics/particles/poofImg1.png");
		ImageIcon poofImg2II = new ImageIcon(core.getRespath() 
				 + "graphics/particles/poofImg2.png");
		ImageIcon poofImg3II = new ImageIcon(core.getRespath() 
				 + "graphics/particles/poofImg3.png");
		
		poofImages = new BufferedImage[3];
		poofImages[0] = ResourceLoader.toBufferedImage(poofImg1II.getImage());
		poofImages[1] = ResourceLoader.toBufferedImage(poofImg2II.getImage());
		poofImages[2] = ResourceLoader.toBufferedImage(poofImg3II.getImage());
		
		poofParticles = new ParticleGenerator(poofImages, 10F, 5F, 5F, 1F, ParticleGenerator.CONE_UP);
		
		// jumpParticles
		jumpParticles = new ParticleGenerator(poofImages, 5F, 10F, 5F, 0.5F, ParticleGenerator.CONE_DOWN);
		
		// fireParticles
		ImageIcon fireImg1II = new ImageIcon(core.getRespath() 
				 + "graphics/particles/fireImg1.png");
		ImageIcon fireImg2II = new ImageIcon(core.getRespath() 
				 + "graphics/particles/fireImg2.png");
		
		fireImages = new BufferedImage[2];
		fireImages[0] = ResourceLoader.toBufferedImage(fireImg1II.getImage());
		fireImages[1] = ResourceLoader.toBufferedImage(fireImg2II.getImage());
		
		//fireParticles = new ParticleGenerator(fireImages, 4F, 15F, 4F, 10F, ParticleGenerator.CONE_UP);
		fireParticles = new ParticleGenerator(fireImages, 10F, 1F, 2F, 1F, ParticleGenerator.CONE_DOWN);
	}
	
	/***********************************
	 * Miscellaneous Load
	 **********************************/
	public static World world1;
	
	@Override
	public void loadMisc()
	{
		CubeoTwoTiles.loadTiles();
		CubeoTwoObjects.loadObjects();
		
		Image[] world1Images = new Image[3];
		world1Images[0] = world1Image;
		world1Images[1] = world1BackImage;
		world1Images[2] = world1FrontImage;
		
		world1 = new World("cubeoWorld", 1, world1Images, CubeoTwoTiles.getTiles(), new CubeoTwoTileAdder(), CubeoTwoObjects.player, 32, 32);
		
		// Set the game's Camera
		core.setCamera(CubeoTwoObjects.camera);
	}
	
	/***********************************
	 * Post Load
	 **********************************/
	@Override
	public void postLoad()
	{
		
	}

	/***********************************
	 * Keys
	 **********************************/
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
	}
}
