package com.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import com.engine.main.Core;

/**
 * <h1>CoreCanvas</h1>
 * CoreCanvas is a graphical component that
 * renders all of the project's graphics.
 * This canvas calls the various methods
 * of a Screen and other components such as
 * ObjectHandler and WorldHandler in a certain 
 * order to properly layer rendering.  The rate
 * at which the CoreCanvas renders is handled
 * by CoreClock.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CoreCanvas extends Canvas
{
	private static final long serialVersionUID = 5328656016785715913L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	
	/*
	 * Project Variables
	 */
	private final Core core;
	private boolean screenLoaded = true; // Is the shown screen done loading
	private int timeCount = 0; // Count variable for time
	private Font loadingFont = null; // The font to show when loading
	
	/*
	 * Graphics Variables
	 */
	private Graphics g;
	private Graphics2D g2d;
	
	/**
	 * Create a new CoreCanvas.
	 * @param core
	 */
	public CoreCanvas(Core core)
	{
		this.core = core;
		
		// Set the sizes of this Canvas
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		// Add the core's KeyboardHandler so keys can be detected
		this.addKeyListener(core.getKeyboardHandler());
		// Add the core's MouseHandler so mouse clicks can be detected
		this.addMouseListener(core.getMouseHandler());
		this.addMouseMotionListener(core.getMouseHandler());
		
		// Add this CoreCanvas to the main window
		core.getWindow().add(this);
		
		// Place this CoreCanvas at the proper location and size
		this.setBounds(0, 0, core.getWindowX(), core.getWindowY());
	}
	
	/**
	 * This update method keeps the CoreCanvas's position
	 * updated in case of window size changes.
	 * This is automatically called in CoreClock at the rate 
	 * of core.getFps().
	 */
	public void update()
	{
		// Place this CoreCanvas at the proper location and size
		this.setBounds(0, 0, core.getWindow().getWidth(), core.getWindow().getHeight());
		
		if (timeCount > core.getFps())
			timeCount = 0;
		
		timeCount++;
	}
	
	/**
	 * This render method calls the necessary methods
	 * in the screen currently being shown and other
	 * components such as ObjectHandler and WorldHandler
	 * to render graphics on screen.  All draw calls should
	 * be placed in the proper methods outside of this
	 * CoreCanavas.
	 */
	public void render()
	{
		// Get or create the BufferStrategy to plan rendering
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics(); // Get the graphics that can be drawn to
		g2d = (Graphics2D)g; // Set the Graphics2D
		
		// Create a black rectangle to fill the canvas as a base background
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (screenLoaded) // If the current screen is loaded and ready to render
		{
			// Draw the static backgrounds
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen().drawStaticBackground(g);
			
			// Translate the screen based on the Camera
			if (core.getCamera() != null && core.usesCamera())
				g2d.translate(core.getCamera().getxPos(), 
						  	  core.getCamera().getyPos());
			
			// Draw the background layers
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen().drawBackground(g);
			
			// Render all of the Tiles
			if (core.getScreenHandler().getCurrentScreen() != null && core.usesWorld())
				core.getWorldHandler().renderTiles(g);
			
			// Render all of the CoreObjects
			if (core.getScreenHandler().getCurrentScreen() != null && core.usesCoreObjects())
				core.getObjectHandler().renderCoreObjects(g);
			
			// Draw the foreground layers
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen().drawForeground(g);
			
			// Draw the lighting
			if (core.getScreenHandler().getCurrentScreen() != null && core.usesLighting())
			g.drawImage(core.getLightHandler().getLightMap(), -1500, -3100, null);
			
			// Translate the screen based on the Camera
			if (core.getCamera() != null && core.usesCamera())
				g2d.translate(-core.getCamera().getxPos(), 
						  	  -core.getCamera().getyPos());
			
			// Draw the gui layers
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen().drawGui(g);
			
			// Draw the debug layers
			if (core.getScreenHandler().getCurrentScreen() != null
				&& core.getDebugScreen() != null
				&& core.isDebugShown())
				core.getDebugScreen().showDebug(g);
			
			// Draw the loading "curtain" if the screen is still loading
			if (core.getScreenHandler().getCurrentScreen() != null &&
				!screenLoaded)
			{
				if (timeCount < core.getFps() / 4)
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, core.getWindow().getWidth(), 
							core.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . ", (core.getWindow().getWidth() / 2) - 80, 
							(core.getWindow().getHeight() / 2) - 80);
				}
				else if (timeCount < (core.getFps() / 4) * 2)
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, core.getWindow().getWidth(), 
							core.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . ", (core.getWindow().getWidth() / 2) - 80, 
							(core.getWindow().getHeight() / 2) - 80);
				}
				else if (timeCount < (core.getFps() / 4) * 3)
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, core.getWindow().getWidth(), 
							core.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . . ", (core.getWindow().getWidth() / 2) - 80, 
							(core.getWindow().getHeight() / 2) - 80);
				}
				else
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, core.getWindow().getWidth(), 
							core.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . . . ", (core.getWindow().getWidth() / 2) - 80, 
							(core.getWindow().getHeight() / 2) - 80);
				}
			}
			
			g.dispose(); // Dispose of all graphics on the basic Graphics component 
						 // because we have them prepared on the BufferStrategy
			bs.show(); // Show all graphics prepared on the BufferStrategy
		}
		else // If the screen is not loaded, render the loading screen
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			if (timeCount < core.getFps() / 4)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, core.getWindow().getWidth(), 
						core.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . ", (core.getWindow().getWidth() / 2) - 80, 
						(core.getWindow().getHeight() / 2) - 80);
			}
			else if (timeCount < (core.getFps() / 4) * 2)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, core.getWindow().getWidth(), 
						core.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . ", (core.getWindow().getWidth() / 2) - 80, 
						(core.getWindow().getHeight() / 2) - 80);
			}
			else if (timeCount < (core.getFps() / 4) * 3)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, core.getWindow().getWidth(), 
						core.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . . ", (core.getWindow().getWidth() / 2) - 80, 
						(core.getWindow().getHeight() / 2) - 80);
			}
			else
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, core.getWindow().getWidth(), 
						core.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . . . ", (core.getWindow().getWidth() / 2) - 80, 
						(core.getWindow().getHeight() / 2) - 80);
			}
		}
		
		g.dispose(); // Dispose of all graphics on the basic Graphics component 
		 			 // because we have them prepared on the BufferStrategy
		bs.show(); // Show all graphics prepared on the BufferStrategy
	}

	/**
	 * Get the Graphics this CoreCanvas uses.
	 * @return g - the graphics
	 */
	public Graphics getGraphics()
	{
		return g;
	}

	/**
	 * Is the project's current screen loaded.
	 * @return screenLoaded
	 */
	public boolean isScreenLoaded() 
	{
		return screenLoaded;
	}
	
	/**
	 * Set if the project's current screen is loaded.
	 * @param screenLoaded
	 */
	public void setScreenLoaded(boolean screenLoaded) 
	{
		this.screenLoaded = screenLoaded;
	}

	/**
	 * @return the loadingFont
	 */
	public Font getLoadingFont() 
	{
		return loadingFont;
	}

	/**
	 * @param loadingFont the loadingFont to set
	 */
	public void setLoadingFont(Font loadingFont) 
	{
		this.loadingFont = loadingFont;
	}
}
