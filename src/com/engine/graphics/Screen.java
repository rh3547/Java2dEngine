package com.engine.graphics;

import java.awt.Graphics;

import com.engine.coreObject.CoreObject;
import com.engine.gui.GuiComponent;
import com.engine.input.EngineMouseListener;
import com.engine.input.CoreKeyListener;
import com.engine.input.CoreObjectListener;
import com.engine.input.GuiComponentListener;

/**
 * <h1>Screen</h1>
 * A Screen is a particular level/menu of a project.
 * A screen has methods to update logic and
 * graphics based on the CoreClock.  Screens
 * are handled through the ScreenHandler class.
 * <br>
 * <br>
 * @author Ryan Hochmuth
 *
 */
public abstract class Screen implements GuiComponentListener, CoreObjectListener, CoreKeyListener, EngineMouseListener
{
	private final ScreenHandler screenHandler;
	
	/**
	 * Create a new Screen.
	 * @param screenHandler
	 */
	public Screen(ScreenHandler screenHandler)
	{
		this.screenHandler = screenHandler;
	}
	
	/**
	 * Runs only once when the Screen is first created.
	 * onCreate() is used to set parameters
	 * before the onUpdate() method is called.
	 */
	public abstract void onCreate();
	
	/**
	 * Runs every time a project update occurs from
	 * CoreClock.  onUpdate() is used to
	 * update important object parameters
	 * during runtime.
	 */
	public abstract void onUpdate();
	
	/**
	 * What process should be done
	 * to unload this screen.
	 */
	public abstract void unload();
	
	/**
	 * drawStaticBackground() runs every time a project update occurs from
	 * CoreClock.  This method should be used to draw static
	 * background images.
	 * @param g
	 */
	public abstract void drawStaticBackground(Graphics g);
	/**
	 * drawBackground() runs every time a project update occurs from
	 * CoreClock.  This method should be used to draw graphics
	 * on the background layer
	 * @param g
	 * @param pixels
	 * @param offset
	 * @param row
	 */
	public abstract void drawBackground(Graphics g);
	/**
	 * drawForeground() runs every time a project update occurs from
	 * CoreClock.  This method should be used to draw graphics
	 * on the foreground layer.
	 * @param g
	 */
	public abstract void drawForeground(Graphics g);
	/**
	 * drawGui() runs every time a project update occurs from
	 * CoreClock.  This method should be used to draw gui
	 * graphics on the up most layer.
	 * @param g
	 */
	public abstract void drawGui(Graphics g);
	
	/**
	 * Get the projects's ScreenHandler.
	 * @return screenHandler
	 */
	public ScreenHandler getScreenHandler()
	{
		return screenHandler;
	}
	
	/**
	 * Called from MouseHandler whenever a GuiComponent is clicked by the mouse.
	 * @param component - the component that was clicked
	 * @param button - the number for the mouse button that was clicked
	 */
	@Override
	public abstract void guiComponentClicked(GuiComponent component, int button);
	
	/**
	 * Called from MouseHandler whenever a GuiComponent is entered by the mouse.
	 * @param component - the component that was entered
	 */
	@Override
	public abstract void guiComponentEntered(GuiComponent component);
	
	/**
	 * Called from MouseHandler whenever a GuiComponent is exited by the mouse.
	 * @param component - the component that was exited
	 */
	@Override
	public abstract void guiComponentExited(GuiComponent component);
	
	/**
	 * Called from MouseHandler whenever a CoreObjects is clicked by the mouse.
	 * @param object - the object that was clicked
	 * @param button - the number for the mouse button that was clicked
	 */
	@Override
	public abstract void coreObjectClicked(CoreObject object, int button);
	
	/**
	 * Called from MouseHandler whenever the mouse is clicked.
	 * @param button - the number for the mouse button that was clicked
	 */
	@Override
	public abstract void mouseClicked(int button);
	
	/**
	 * Called from KeyboardHandler whenever a key is pressed.
	 * @param keyCode - the code for the key that was pressed
	 */
	@Override
	public abstract void keyPressed(int keyCode);
	
	/**
	 * Called from KeyboardHandler whenever a key is released.
	 * @param keyCode - the code for the key that was released
	 */
	@Override
	public abstract void keyReleased(int keyCode);
}
