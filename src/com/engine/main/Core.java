package com.engine.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.engine.audio.AudioHandler;
import com.engine.coreObject.ObjectHandler;
import com.engine.data.CipherHandler;
import com.engine.data.SaveHandler;
import com.engine.graphics.CoreCanvas;
import com.engine.graphics.DebugScreen;
import com.engine.graphics.ScreenHandler;
import com.engine.input.KeyboardHandler;
import com.engine.input.MouseHandler;
import com.engine.resources.ResourceLoader;
import com.engine.world.Camera;
import com.engine.world.LightHandler;
import com.engine.world.World;
import com.engine.world.WorldHandler;

/**
 * <h1>Core</h1>
 * <br>
 * <b>Version:</b> 1.2
 * <br><br>
 * Core handles all engine components and
 * acts like a "bridge" between them.
 * All engine components are accessed
 * through this class.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class Core implements Runnable
{
	/*
	 * Core Variables
	 */
	private JFrame window = new JFrame(); // The main window
	private String title; // The title of the project
	private int windowX; // The window width
	private int windowY; // The window height
	private int fps; // The speed to render at
	private int tps; // The speed to update at
	private boolean loading = true; // Is the project loading something
	private boolean running; // Is the project running
	private int worldMouseX = 0; // The x position of the mouse relative to world coordinates
	private int worldMouseY = 0; // The y position of the mouse relative to world coordinates
	private int mouseAnchorX = 0; // The x position relative to world coordinates that the mouse is anchored
	private int mouseAnchorY = 0; // The y position relative to world coordinates that the mouse is anchored
	private int mousePosX = 0; // The x position of the mouse on screen
	private int mousePosY = 0; // The x position of the mouse on screen
	private boolean mouseMoving = false; // Is the mouse being moved
	private int mouseMoveCount = 0; // Count variable to reset mouseMoving
	private boolean debugShown = false; // Is the debug screen shown
	private String resourcePack = ""; // The currently used resource pack
	
	private int loadDistance; // The distance from the player that tiles should be updated
	private int chunkLoadDistance; // The distance from the player that chunks should be loaded
	private int objectLoadDistance; // The distance from the player that objects should be loaded
	private int collisionCheckDistance; // The distance from the player that collisions should be checked
	private int tileWidth = 32; // The width of tiles
	private int tileHeight = 32; // The height of tiles
	private World currentWorld = null; // The World currently loaded
	
	/*
	 * Core Clocks
	 */
	private CoreClock coreClock; // The main project clock
	private Thread coreThread; // The thread to run the clock in
	
	/*
	 * Core Components
	 */
	private ScreenHandler screenHandler; // Handles screens in a project
	private ResourceLoader resourceLoader; // Handles resource loading
	private AudioHandler audioHandler; // Handles project audio
	private KeyboardHandler keyboardHandler; // Handles keyboard input
	private MouseHandler mouseHandler; // Handles mouse input
	private CoreCanvas coreCanvas; // Renders graphics on screen
	private ObjectHandler objectHandler; // Handles project objects
	private WorldHandler worldHandler; // Handles the project's worlds
	private LightHandler lightHandler; // Handles the project's lighting
	private DebugScreen debugScreen = null; // The debug screen for the project
	private SaveHandler saveHandler; // Handles project saving
	private CipherHandler cipherHandler; // Handles file encryption, etc.
	
	/*
	 * Modules
	 */
	private Camera camera; // The "camera" that follows the player and translates the project graphics
	
	/*
	 * File Paths
	 */
	private String respath = ""; // The path to the project's resources (images, audio, etc.)
	private String filepath = ""; // The root path to the project install location
	
	/*
	 * Component Enablers
	 */
	private boolean useCoreObjects = true; // Does this project use core objects
	private boolean useWorld = true; // Does this project use a world
	private boolean useLighting = true; // Does this project use lighting
	private boolean useCamera = true; // Does this project use a camera
	
	/**
	 * Create a new Core.
	 * @param title
	 * @param windowX
	 * @param windowY
	 * @param fps
	 */
	public Core(String title, int windowX, int windowY, int fps, int tps)
	{
		this.title = title;
		this.windowX = windowX;
		this.windowY = windowY;
		this.fps = fps;
		this.tps = tps;
		
		// Set the size of the frame
		this.window.setSize(windowX, windowY);
		// Set the title of the frame
		this.window.setTitle(title);
		// Center the frame on the center of the screen
		this.window.setLocationRelativeTo(null);
		// Set the frame to exit when manually closed
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the window's layout to null
		this.window.setLayout(null);
		// Set the window to be focusable
		this.window.setFocusable(true);
		// Show the frame
		this.window.setVisible(true);
		
		/*
		 *  Create core components
		 */
		screenHandler = new ScreenHandler(this); // Create a new ScreenHandler
		audioHandler = new AudioHandler(this); // Create a new AudioHandler
		keyboardHandler = new KeyboardHandler(this); // Create a new KeyboardHandler
		mouseHandler = new MouseHandler(this); // Create a new MouseHandler
		coreCanvas = new CoreCanvas(this); // Create a new GameCanvas
		objectHandler = new ObjectHandler(); // Create a new ObjectHandler
		worldHandler = new WorldHandler(); // Create a new WorldHandler
		lightHandler = new LightHandler(); // Create a new LightHandler
		saveHandler = new SaveHandler(); // Create a new SaveHandler
		cipherHandler = new CipherHandler(); // Create a new CipherHandler
		
		/*
		 * Set the load distances
		 */
		loadDistance = (windowX / tileWidth) + 8;
		chunkLoadDistance = 4000;
		objectLoadDistance = 4000;
		collisionCheckDistance = 8;
		
		/*
		 *  Create and set up CoreClock objects
		 */
		// Create and add main CoreClock
		coreClock = new CoreClock(this, fps, tps);
		// Create the coreThread and pair coreClock to it
		coreThread = new Thread(coreClock);
		
		// Set the focus of input to the window
		this.window.requestFocus();
		
		// Repaint the components in the window
		window.repaint();
	}
	
	/**
	 * An optional clock.
	 * Disabled by default, to enable, change
	 * the variable 'running' in the start method
	 * to true.
	 */
	@Override
	public void run()
	{
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			if (shouldRender)
			{
				frames++;
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000)
			{
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				lastTimer += 1000;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	/**
	 * An optional update method controlled by the optional
	 * clock.
	 * Disabled by default.  See run() to enable.
	 */
	public void tick()
	{
		
	}
	
	/**
	 * An optional render method controlled by the optional
	 * clock.
	 * Disabled by default.  See run() to enable.
	 */
	public void render()
	{
		
	}
	
	/**
	 * Run this core in a new Thread and start that Thread.
	 */
	public synchronized void start()
	{
		running = false; // Change this to true to enable the optional clock
		new Thread(this).start();
		
		// Start the CoreClock
		coreThread.start();
	}
	
	/**
	 * Stop the optional clock from running.
	 */
	public synchronized void stop()
	{
		running = false;
	}
	
	/*
	 * Core Variables
	 */
	/**
	 * Get the project's main JFrame.
	 * @return window
	 */
	public JFrame getWindow()
	{
		return window;
	}

	/**
	 * Set the project's main JFrame.
	 * @param window
	 */
	public void setWindow(JFrame window)
	{
		this.window = window;
	}

	/**
	 * Get the project's title.
	 * @return title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Set the project's title.
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
		this.window.setTitle(title);
	}

	/**
	 * Get the window's current width.
	 * @return windowX
	 */
	public int getWindowX()
	{
		return windowX;
	}

	/**
	 * Set the window's width.
	 * @param windowX
	 */
	public void setWindowX(int windowX)
	{
		this.windowX = windowX;
		updateWindowSize();
	}

	/**
	 * Get the window's current height.
	 * @return windowY
	 */
	public int getWindowY()
	{
		return windowY;
	}

	/**
	 * Set the window's height.
	 * @param windowY
	 */
	public void setWindowY(int windowY)
	{
		this.windowY = windowY;
		updateWindowSize();
	}
	
	/**
	 * Update the project's current window size.
	 */
	public void updateWindowSize()
	{
		this.window.setSize(windowX, windowY);
	}

	/**
	 * Get the project's current fps.
	 * @return fps
	 */
	public int getFps()
	{
		return fps;
	}

	/**
	 * Set the project's fps.
	 * @param fps
	 */
	public void setFps(int fps)
	{
		this.fps = fps;
	}
	
	/**
	 * Get the project's current tps.
	 * @return tps
	 */
	public int getTps()
	{
		return tps;
	}

	/**
	 * Set the project's tps.
	 * @param tps
	 */
	public void setTps(int tps)
	{
		this.tps = tps;
	}

	/**
	 * Get whether the project is loading or not.
	 * @return loading
	 */
	public boolean isLoading()
	{
		return loading;
	}

	/**
	 * Set whether the project is loading or not.
	 * @param loading
	 */
	public void setLoading(boolean loading)
	{
		this.loading = loading;
	}
	
	/**
	 * Get the x position of the mouse
	 * relative to a position in
	 * the world.
	 * @return the mouseX
	 */
	public int getWorldMouseX()
	{
		return worldMouseX;
	}

	/**
	 * Set the x position of the mouse
	 * in the world.
	 * @param mouseX the mouseX to set
	 */
	public void setWorldMouseX(int mouseX)
	{
		this.worldMouseX = mouseX;
	}

	/**
	 * Get the y position of the mouse
	 * relative to a position in
	 * the world.
	 * @return the mouseY
	 */
	public int getWorldMouseY()
	{
		return worldMouseY;
	}

	/**
	 * Set the y position of the mouse
	 * in the world.
	 * @param mouseY the mouseY to set
	 */
	public void setWorldMouseY(int mouseY)
	{
		this.worldMouseY = mouseY;
	}

	/**
	 * Get the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world x position.
	 * @return the mousePointX
	 */
	public int getMouseAnchorX()
	{
		return mouseAnchorX;
	}

	/**
	 * Set the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world x position.
	 * @param mousePointX the mousePointX to set
	 */
	public void setMouseAnchorX(int mousePointX)
	{
		this.mouseAnchorX = mousePointX;
	}

	/**
	 * Get the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world y position.
	 * @return the mousePointY
	 */
	public int getMouseAnchorY()
	{
		return mouseAnchorY;
	}

	/**
	 * Set the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world y position.
	 * @param mousePointY the mousePointY to set
	 */
	public void setMouseAnchorY(int mousePointY)
	{
		this.mouseAnchorY = mousePointY;
	}
	
	/**
	 * Get the real x position
	 * of the mouse on screen.
	 * @return the mousePosX
	 */
	public int getMousePosX()
	{
		return mousePosX;
	}

	/**
	 * Set the real x position
	 * of the mouse on screen.
	 * @param mousePosX the mousePosX to set
	 */
	public void setMousePosX(int mousePosX)
	{
		this.mousePosX = mousePosX;
	}

	/**
	 * Get the real y position
	 * of the mouse on screen.
	 * @return the mousePosY
	 */
	public int getMousePosY()
	{
		return mousePosY;
	}

	/**
	 * Set the real y position
	 * of the mouse on screen.
	 * @param mousePosY the mousePosY to set
	 */
	public void setMousePosY(int mousePosY)
	{
		this.mousePosY = mousePosY;
	}

	/**
	 * Is the mouse being moved?
	 * @return the mouseMoving
	 */
	public boolean isMouseMoving()
	{
		return mouseMoving;
	}

	/**
	 * Set whether the mouse is moving or not.
	 * @param mouseMoving the mouseMoving to set
	 */
	public void setMouseMoving(boolean mouseMoving)
	{
		this.mouseMoving = mouseMoving;
	}

	/**
	 * This is a count variable used in determining
	 * if the mouse is moving.
	 * @return the mouseMoveCount
	 */
	public int getMouseMoveCount()
	{
		return mouseMoveCount;
	}

	/**
	 * This is a count variable used in determining
	 * if the mouse is moving.
	 * @param mouseMoveCount the mouseMoveCount to set
	 */
	public void setMouseMoveCount(int mouseMoveCount)
	{
		this.mouseMoveCount = mouseMoveCount;
	}
	
	/**
	 * Update the count of mouseMoveCount.
	 * This is triggered every time the mouse moves.
	 * If this stops incrementing, then the
	 * mouse is not moving.
	 */
	public void updateMouseCount()
	{
		if ((mouseMoveCount + 1) < Integer.MAX_VALUE)
			this.mouseMoveCount++;
		else
			this.mouseMoveCount = 0;
	}

	/**
	 * Is the debug window currently being shown?
	 * @return the debugShown
	 */
	public boolean isDebugShown()
	{
		return debugShown;
	}

	/**
	 * Set whether the debug window is being shown.
	 * @param debugShown the debugShown to set
	 */
	public void setDebugShown(boolean debugShown)
	{
		this.debugShown = debugShown;
	}

	/**
	 * Get the name of the resource pack being used.
	 * @return the path to the resource pack
	 */
	public String getResourcePack() 
	{
		return resourcePack;
	}

	/**
	 * Set the name of the resource pack being used.
	 * @param set the path to the resource path
	 */
	public void setResourcePack(String resourcePack) 
	{
		this.resourcePack = "resourcePacks/" + resourcePack;
	}

	/**
	 * Set the window icon of this project.
	 * @param path -  the path to the icon
	 */
	public void setWindowIcon(String path)
	{
		// Set the window's icon
		BufferedImage img = null;
	    try 
	    {
	       img = ImageIO.read(new File(getRespath() + path));
	    } 
	    catch (IOException e) 
	    {
	       System.out.println("\nImage not found");
	    }
	    window.setIconImage(img);
		window.repaint();
	}

	/*
	 * Core Clocks
	 */
	/**
	 * Get the project's CoreClock.
	 * @return coreClock
	 */
	public CoreClock getCoreClock()
	{
		return coreClock;
	}

	/**
	 * Set the project's CoreClock.
	 * @param coreClock
	 */
	public void setCoreClock(CoreClock coreClock)
	{
		this.coreClock = coreClock;
	}

	/*
	 * Core Components
	 */
	/**
	 * Get the project's ScreenHandler.
	 * @return screenHandler
	 */
	public ScreenHandler getScreenHandler()
	{
		return screenHandler;
	}

	/**
	 * @param screenHandler the screenHandler to set
	 */
	public void setScreenHandler(ScreenHandler screenHandler)
	{
		this.screenHandler = screenHandler;
	}

	/**
	 * Get the project's ResourceLoader.
	 * @return resourceLoader
	 */
	public ResourceLoader getResourceLoader()
	{
		return resourceLoader;
	}

	/**
	 * Set the project's ResourceLoader.
	 * @param resourceLoader
	 */
	public void setResourceLoader(ResourceLoader resourceLoader)
	{
		this.resourceLoader = resourceLoader;
	}

	/**
	 * Get the project's AudioHandler.
	 * @return audioHandler
	 */
	public AudioHandler getAudioHandler()
	{
		return audioHandler;
	}
	
	/**
	 * @param audioHandler the audioHandler to set
	 */
	public void setAudioHandler(AudioHandler audioHandler)
	{
		this.audioHandler = audioHandler;
	}

	/**
	 * Get the project's KeyboardHandler.
	 * @return keyboardHandler
	 */
	public KeyboardHandler getKeyboardHandler()
	{
		return keyboardHandler;
	}
	
	/**
	 * @param keyboardHandler the keyboardHandler to set
	 */
	public void setKeyboardHandler(KeyboardHandler keyboardHandler)
	{
		this.keyboardHandler = keyboardHandler;
	}

	/**
	 * Get the project's MouseHandler.
	 * @return mouseHandler
	 */
	public MouseHandler getMouseHandler()
	{
		return mouseHandler;
	}

	/**
	 * @param mouseHandler the mouseHandler to set
	 */
	public void setMouseHandler(MouseHandler mouseHandler)
	{
		this.mouseHandler = mouseHandler;
	}

	/**
	 * Get the project's CoreCanvas.
	 * @return canvas
	 */
	public CoreCanvas getCanvas()
	{
		return coreCanvas;
	}

	/**
	 * @return the coreCanvas
	 */
	public CoreCanvas getCoreCanvas()
	{
		return coreCanvas;
	}

	/**
	 * @param coreCanvas the coreCanvas to set
	 */
	public void setCoreCanvas(CoreCanvas coreCanvas)
	{
		this.coreCanvas = coreCanvas;
	}

	/**
	 * Get the project's ObjectHandler.
	 * @return objectHandler
	 */
	public ObjectHandler getObjectHandler()
	{
		return objectHandler;
	}
	
	/**
	 * @param objectHandler the objectHandler to set
	 */
	public void setObjectHandler(ObjectHandler objectHandler)
	{
		this.objectHandler = objectHandler;
	}

	/**
	 * Get this project's WorldHandler.
	 * @return the worldHandler
	 */
	public WorldHandler getWorldHandler()
	{
		return worldHandler;
	}

	/**
	 * Get the project's LightHandler.
	 * @return objectHandler
	 */
	public LightHandler getLightHandler()
	{
		return lightHandler;
	}

	/**
	 * @param lightHandler the lightHandler to set
	 */
	public void setLightHandler(LightHandler lightHandler)
	{
		this.lightHandler = lightHandler;
	}

	/**
	 * Get the project's DebugScreen.
	 * @return the debugScreen
	 */
	public DebugScreen getDebugScreen()
	{
		return debugScreen;
	}

	/**
	 * Set the project's DebugScreen.
	 * @param debugScreen the debugScreen to set
	 */
	public void setDebugScreen(DebugScreen debugScreen)
	{
		this.debugScreen = debugScreen;
	}

	/**
	 * @return the saveHandler
	 */
	public SaveHandler getSaveHandler()
	{
		return saveHandler;
	}

	/**
	 * @param saveHandler the saveHandler to set
	 */
	public void setSaveHandler(SaveHandler saveHandler)
	{
		this.saveHandler = saveHandler;
	}

	/**
	 * @return the cipherHandler
	 */
	public CipherHandler getCipherHandler()
	{
		return cipherHandler;
	}

	/**
	 * @param cipherHandler the cipherHandler to set
	 */
	public void setCipherHandler(CipherHandler cipherHandler)
	{
		this.cipherHandler = cipherHandler;
	}

	/*
	 * Modules
	 */
	/**
	 * Get the project's Camera.
	 * @return the camera
	 */
	public Camera getCamera()
	{
		return camera;
	}

	/**
	 * Set the project's Camera.
	 * @param camera the camera to set
	 */
	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}

	/*
	 * File Paths
	 */
	/**
	 * Get the project's resource path.
	 * @return respath
	 */
	public String getRespath()
	{
		return respath + resourcePack + "/";
	}
	
	/**
	 * Set the project's resource path.
	 * @param respath
	 */
	public void setRespath(String respath)
	{
		this.respath = respath;
	}

	/**
	 * Get the project's file path.
	 * @return filepath
	 */
	public String getFilepath()
	{
		return filepath;
	}
	
	/**
	 * Set the project's file path.
	 * @param filepath
	 */
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

	/**
	 * Does this project use CoreObjects?
	 * @return the useCoreObjects
	 */
	public boolean usesCoreObjects()
	{
		return useCoreObjects;
	}

	/**
	 * Set whether this project uses CoreObjects.
	 * @param useCoreObjects the useCoreObjects to set
	 */
	public void setUseCoreObjects(boolean useCoreObjects)
	{
		this.useCoreObjects = useCoreObjects;
	}

	/**
	 * Does this project use Worlds?
	 * @return the useWorld
	 */
	public boolean usesWorld()
	{
		return useWorld;
	}

	/**
	 * Set whether this world uses Worlds.
	 * @param useWorld the useWorld to set
	 */
	public void setUseWorld(boolean useWorld)
	{
		this.useWorld = useWorld;
	}

	/**
	 * Does this project use lighting?
	 * @return the useLighting
	 */
	public boolean usesLighting()
	{
		return useLighting;
	}

	/**
	 * Set whether this project uses lighting.
	 * @param useLighting the useLighting to set
	 */
	public void setUseLighting(boolean useLighting)
	{
		this.useLighting = useLighting;
	}

	/**
	 * Does this project use a camera?
	 * @return the useCamera
	 */
	public boolean usesCamera()
	{
		return useCamera;
	}

	/**
	 * Set whether this project uses a camera.
	 * @param useCamera the useCamera to set
	 */
	public void setUseCamera(boolean useCamera)
	{
		this.useCamera = useCamera;
	}

	/**
	 * Get the distance from the player that tiles should be updated.
	 * @return the loadDistance
	 */
	public int getLoadDistance()
	{
		return loadDistance;
	}

	/**
	 * Set the distance from the player that tiles should be updated.
	 * @param loadDistance the loadDistance to set
	 */
	public void setLoadDistance(int loadDistance)
	{
		this.loadDistance = loadDistance;
	}

	/**
	 * Get the distance from the player that chunks should be loaded.
	 * @return the chunkLoadDistance
	 */
	public int getChunkLoadDistance()
	{
		return chunkLoadDistance;
	}

	/**
	 * Set the distance from the player that chunks should be loaded.
	 * @param chunkLoadDistance the chunkLoadDistance to set
	 */
	public void setChunkLoadDistance(int chunkLoadDistance)
	{
		this.chunkLoadDistance = chunkLoadDistance;
	}

	/**
	 * Get the distance from the player that objects should be loaded.
	 * @return the objectLoadDistance
	 */
	public int getObjectLoadDistance() 
	{
		return objectLoadDistance;
	}

	/**
	 * Set the distance from the player that objects should be loaded.
	 * @param objectLoadDistance the objectLoadDistance to set
	 */
	public void setObjectLoadDistance(int objectLoadDistance) 
	{
		this.objectLoadDistance = objectLoadDistance;
	}

	/**
	 * Get the distance from the player that collisions should be checked.
	 * @return the collisionCheckDistance
	 */
	public int getCollisionCheckDistance()
	{
		return collisionCheckDistance;
	}

	/**
	 * Set the distance from the player that collisions should be checked.
	 * @param collisionCheckDistance the collisionCheckDistance to set
	 */
	public void setCollisionCheckDistance(int collisionCheckDistance)
	{
		this.collisionCheckDistance = collisionCheckDistance;
	}

	/**
	 * Get the width of a single tile.
	 * @return the tileWidth
	 */
	public int getTileWidth()
	{
		return tileWidth;
	}

	/**
	 * Set the width of a single tile.
	 * @param tileWidth the tileWidth to set
	 */
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
		this.loadDistance = (windowX / tileWidth) + 8;
		if (currentWorld != null)
			this.currentWorld.setTileWidth(tileWidth);
	}

	/**
	 * Get the height of a single tile.
	 * @return the tileHeight
	 */
	public int getTileHeight()
	{
		return tileHeight;
	}

	/**
	 * Set the height of a single tile.
	 * @param tileHeight the tileHeight to set
	 */
	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
		if (currentWorld != null)
			this.currentWorld.setTileHeight(tileHeight);
	}

	/**
	 * Get the world that is currently loaded.
	 * @return the currentWorld
	 */
	public World getCurrentWorld() 
	{
		return currentWorld;
	}

	/**
	 * Set the world that is currently loaded.
	 * @param currentWorld the currentWorld to set
	 */
	public void setCurrentWorld(World currentWorld) 
	{
		this.currentWorld = currentWorld;
	}
}
