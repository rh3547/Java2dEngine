package com.engine.input;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.gui.GuiTextField;
import com.engine.main.Core;
import com.engine.resources.ResourceLoader;

/**
 * <h1>MouseHandler</h1>
 * MouseHandler is used to detect mouse clicks and 
 * mouse movement.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class MouseHandler extends MouseAdapter
{
	/*
	 * Mouse Button Constants
	 */
	/**
	 * LEFT_MOUSE: The left mouse button.
	 */
	public static final int LEFT_MOUSE = 1;
	/**
	 * MIDDLE_MOUSE: The middle mouse button.
	 */
	public static final int MIDDLE_MOUSE = 2;
	/**
	 * RIGHT_MOUSE: The right mouse button.
	 */
	public static final int RIGHT_MOUSE = 3;
	
	// A list of all GuiComponents
	public List<GuiComponent> guiComponents = new ArrayList<GuiComponent>();
	
	private final Core core;
	
	private boolean textAreaClicked = false; // Has a text area been clicked
	private boolean dragging = false; // Is the mouse being dragged
	
	/**
	 * Create a new MouseHandler
	 * @param core
	 */
	public MouseHandler(Core core)
	{
		// Add the proper listeners to the project
		core.getWindow().addMouseListener(this);
		core.getWindow().addMouseMotionListener(this);
		
		this.core = core;
	}
	
	/**
	 * Called whenever a mouse button is clicked.
	 * Each Tile, CoreObject, and GuiComponent is
	 * checked to see if it was clicked.
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		Point ogPoint = new Point(e.getX(), e.getY());
		Point point = new Point((int) (core.getMouseAnchorX() + ogPoint.getX()), (int) (core.getMouseAnchorY() + ogPoint.getY()));
		
		core.setWorldMouseX((int)point.getX());
		core.setWorldMouseY((int)point.getY());
		
		// Get which mouse button was clicked
		int mouseButton = e.getButton();
		// Create a new invisible Rectangle where the mouse pointer is
		Rectangle rect = new Rectangle((int)point.getX(), (int)point.getY(), 1, 1);
		
		if (mouseButton != MouseHandler.MIDDLE_MOUSE)
		{
			// Test if any Tiles were clicked
			if (core.usesWorld())
				for(int x = 0; x < core.getWorldHandler().getChunks().size(); x++)
				{
					if (core.getWorldHandler().getChunks().get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
						ResourceLoader.core.getChunkLoadDistance() &&
						core.getWorldHandler().getChunks().get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
						ResourceLoader.core.getChunkLoadDistance() 
						&&
						core.getWorldHandler().getChunks().get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
						ResourceLoader.core.getChunkLoadDistance() &&
						core.getWorldHandler().getChunks().get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
						ResourceLoader.core.getChunkLoadDistance())
					{	
						//if (game.getWorldHandler().getChunks().get(x).getxPos())
						for(int i = 0; i < core.getWorldHandler().getChunks().get(x).getTiles().size(); i++)
						{	
							// Test intersection between the mouse Rectangle and the Tile
							if (rect.intersects(core.getWorldHandler().getChunks().get(x).getTiles().get(i).getRect()))
							{
								// Send the signal that a mouse button was pressed to CoreObjectListener
								if (core.getScreenHandler().getCurrentScreen() != null)
									ResourceLoader.core.getScreenHandler().getCurrentScreen()
									.coreObjectClicked(core.getWorldHandler().getChunks().get(x).getTiles().get(i), mouseButton);
								
								// Run the Tile's clicked() method
								core.getWorldHandler().getChunks().get(x).getTiles().get(i).clicked();
								break;
							}
						}
					}
				}
			
			// Go through every CoreObject currently being shown
			if (core.usesCoreObjects())
				for(int x = 0; x < core.getObjectHandler().getCoreObjects().size(); x++)
				{
					// Test intersection between the mouse Rectangle and the CoreObject
					if (rect.intersects(core.getObjectHandler().getCoreObjects().get(x).getRect()))
					{
						// Send the signal that a mouse button was pressed to CoreObjectListener
						if (core.getScreenHandler().getCurrentScreen() != null)
							ResourceLoader.core.getScreenHandler().getCurrentScreen()
							.coreObjectClicked(core.getObjectHandler().getCoreObjects().get(x), mouseButton);
						
						// Run the CoreObject's clicked() method
						core.getObjectHandler().getCoreObjects().get(x).clicked();
						break;
					}
				}
			
			rect = new Rectangle((int) ogPoint.getX(), (int) ogPoint.getY(), 1, 1);
			
			// Go through every GuiComponent currently being shown
			for(int x = 0; x < guiComponents.size(); x++)
			{
				// Test intersection between the mouse Rectangle and the GuiComponent
				if (rect.intersects(guiComponents.get(x).getRect()))
				{
					if (guiComponents.get(x) instanceof GuiButton)
					{
						if (((GuiButton)guiComponents.get(x)).isEnabled())
						{
							// Run the GuiComponent's clicked method
							guiComponents.get(x).clicked();
							
							// Send the signal that a mouse button was pressed to GuiComponentListener
							if (core.getScreenHandler().getCurrentScreen() != null)
								ResourceLoader.core.getScreenHandler().getCurrentScreen()
								.guiComponentClicked(guiComponents.get(x), mouseButton);
						}
						
						textAreaClicked = false;
					}
					else if (guiComponents.get(x) instanceof GuiTextField)
					{
						// Run the GuiComponent's clicked method
						guiComponents.get(x).clicked();
						
						textAreaClicked = true;
					}
					else
					{
						// Send the signal that a mouse button was pressed to GuiComponentListener
						if (core.getScreenHandler().getCurrentScreen() != null)
							ResourceLoader.core.getScreenHandler().getCurrentScreen()
							.guiComponentClicked(guiComponents.get(x), mouseButton);
						
						// Run the GuiComponent's clicked method
						guiComponents.get(x).clicked();
						
						textAreaClicked = false;
					}
					break;
				}
			}
			
			if (!textAreaClicked)
			{
				ResourceLoader.core.getKeyboardHandler().setWordTyping(false);
				
				if (ResourceLoader.core.getKeyboardHandler().getTextFieldFocus() != null)
					ResourceLoader.core.getKeyboardHandler().getTextFieldFocus().setTyping(false);
			}
		}
		else
		{
			textAreaClicked = false;
			ResourceLoader.core.getKeyboardHandler().setWordTyping(false);
			if (ResourceLoader.core.getKeyboardHandler().getTextFieldFocus() != null)
				ResourceLoader.core.getKeyboardHandler().getTextFieldFocus().setTyping(false);
		}
		
		// Send the signal that a mouse button was pressed
		if (core.getScreenHandler().getCurrentScreen() != null)
			ResourceLoader.core.getScreenHandler().getCurrentScreen()
			.mouseClicked(mouseButton);
		
	}
	
	/**
	 * Called whenever the mouse moves.
	 * Only GuiComponents are checked 
	 * to see the mouse moved over it.
	 * @param e
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
		core.setMouseMoving(true);
		core.updateMouseCount();
		
		Point ogPoint = new Point(e.getX(), e.getY());
		Point point = new Point((int) (core.getMouseAnchorX() + ogPoint.getX()), (int) (core.getMouseAnchorY() + ogPoint.getY()));
		
		core.setWorldMouseX((int)point.getX());
		core.setWorldMouseY((int)point.getY());
		
		core.setMousePosX((int)ogPoint.getX());
		core.setMousePosY((int)ogPoint.getY());
		
		// Create a new invisible Rectangle where the mouse pointer is
		Rectangle rect = new Rectangle((int)ogPoint.getX(), (int)ogPoint.getY(), 1, 1);
		
		// Go through every GuiComponent currently being shown
		for(int x = 0; x < guiComponents.size(); x++)
		{
			// Test intersection between the mouse Rectangle and the GuiComponent
			if (rect.intersects(guiComponents.get(x).getRect())) // If it is intersecting
			{
				if (!guiComponents.get(x).isMouseEntered())
				{
					// Send the signal that the mouse entered a GuiComponent
					ResourceLoader.core.getScreenHandler().getCurrentScreen()
					.guiComponentEntered(guiComponents.get(x));
				
					// Run the GuiComponent's entered method
					guiComponents.get(x).mouseEntered();
				}
			}
			else // If it is not intersecting
			{
				if (guiComponents.get(x).isMouseEntered())
				{
					// Run the GuiComponent's exited method
					guiComponents.get(x).mouseExited();
					
					// Send the signal that the mouse exited a GuiComponent
					ResourceLoader.core.getScreenHandler().getCurrentScreen()
					.guiComponentExited(guiComponents.get(x));
				}
			}
		}
	}
	
	/**
	 * Called when a mouse button is released.
	 * When a mouse button is released, it
	 * is considered to not be dragging anymore.
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		dragging = false;
	}
	
	/**
	 * Called when the mouse is clicked and dragged.
	 * Each Tile, CoreObject, and GuiComponent
	 * is checked to see the mouse moved over it
	 * while being click-dragged.
	 * @param e
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		core.setMouseMoving(true);
		core.updateMouseCount();
		
		Point ogPoint = new Point(e.getX(), e.getY());
		Point point = new Point((int) (core.getMouseAnchorX() + ogPoint.getX()), (int) (core.getMouseAnchorY() + ogPoint.getY()));
		
		core.setWorldMouseX((int)point.getX());
		core.setWorldMouseY((int)point.getY());
		
		core.setMousePosX((int)ogPoint.getX());
		core.setMousePosY((int)ogPoint.getY());
		
		// Get which mouse button was clicked
		int mouseButton = e.getButton();
		// Create a new invisible Rectangle where the mouse pointer is
		Rectangle rect = new Rectangle((int)point.getX(), (int)point.getY(), 1, 1);
		
		if (mouseButton != MouseHandler.MIDDLE_MOUSE)
		{
			// Test if any Tiles were clicked
			if (core.usesWorld())
				for(int x = 0; x < core.getWorldHandler().getChunks().size(); x++)
				{
					if (core.getWorldHandler().getChunks().get(x).getxPos() > ResourceLoader.core.getCamera().getFollowObject().getxPos() - 
						ResourceLoader.core.getChunkLoadDistance() &&
						core.getWorldHandler().getChunks().get(x).getxPos() < ResourceLoader.core.getCamera().getFollowObject().getxPos() + 
						ResourceLoader.core.getChunkLoadDistance() 
						&&
						core.getWorldHandler().getChunks().get(x).getyPos() > ResourceLoader.core.getCamera().getFollowObject().getyPos() - 
						ResourceLoader.core.getChunkLoadDistance() &&
						core.getWorldHandler().getChunks().get(x).getyPos() < ResourceLoader.core.getCamera().getFollowObject().getyPos() + 
						ResourceLoader.core.getChunkLoadDistance())
					{	
						//if (game.getWorldHandler().getChunks().get(x).getxPos())
						for(int i = 0; i < core.getWorldHandler().getChunks().get(x).getTiles().size(); i++)
						{	
							// Test intersection between the mouse Rectangle and the Tile
							if (rect.intersects(core.getWorldHandler().getChunks().get(x).getTiles().get(i).getRect()))
							{
								// Send the signal that a mouse button was pressed to CoreObjectListener
								if (core.getScreenHandler().getCurrentScreen() != null)
									ResourceLoader.core.getScreenHandler().getCurrentScreen()
									.coreObjectClicked(core.getWorldHandler().getChunks().get(x).getTiles().get(i), mouseButton);
								
								// Run the Tile's clicked() method
								core.getWorldHandler().getChunks().get(x).getTiles().get(i).clicked();
								break;
							}
						}
					}
				}
			
			// Go through every CoreObject currently being shown
			if (core.usesCoreObjects())
				for(int x = 0; x < core.getObjectHandler().getCoreObjects().size(); x++)
				{
					// Test intersection between the mouse Rectangle and the CoreObject
					if (rect.intersects(core.getObjectHandler().getCoreObjects().get(x).getRect()))
					{
						// Send the signal that a mouse button was pressed to CoreObjectListener
						if (core.getScreenHandler().getCurrentScreen() != null)
							ResourceLoader.core.getScreenHandler().getCurrentScreen()
							.coreObjectClicked(core.getObjectHandler().getCoreObjects().get(x), mouseButton);
						
						// Run the CoreObject's clicked() method
						core.getObjectHandler().getCoreObjects().get(x).clicked();
						break;
					}
				}
			
			if (!dragging)
			{
				rect = new Rectangle((int)ogPoint.getX(), (int)ogPoint.getY(), 1, 1);
				
				// Go through every GuiComponent currently being shown
				for(int x = 0; x < guiComponents.size(); x++)
				{
					// Test intersection between the mouse Rectangle and the GuiComponent
					if (rect.intersects(guiComponents.get(x).getRect()))
					{
						if (guiComponents.get(x) instanceof GuiButton)
						{
							if (((GuiButton)guiComponents.get(x)).isEnabled())
							{
								// Run the GuiComponent's clicked method
								guiComponents.get(x).clicked();
								
								// Send the signal that a mouse button was pressed to GuiComponentListener
								if (core.getScreenHandler().getCurrentScreen() != null)
									ResourceLoader.core.getScreenHandler().getCurrentScreen()
									.guiComponentClicked(guiComponents.get(x), mouseButton);
							}
							
							textAreaClicked = false;
						}
						else if (guiComponents.get(x) instanceof GuiTextField)
						{
							// Run the GuiComponent's clicked method
							guiComponents.get(x).clicked();
							
							textAreaClicked = true;
						}
						else
						{
							// Send the signal that a mouse button was pressed to GuiComponentListener
							if (core.getScreenHandler().getCurrentScreen() != null)
								ResourceLoader.core.getScreenHandler().getCurrentScreen()
								.guiComponentClicked(guiComponents.get(x), mouseButton);
							
							// Run the GuiComponent's clicked method
							guiComponents.get(x).clicked();
							
							textAreaClicked = false;
						}
						break;
					}
				}
			}
			
			dragging = true;
			
			if (!textAreaClicked)
			{
				ResourceLoader.core.getKeyboardHandler().setWordTyping(false);
				
				if (ResourceLoader.core.getKeyboardHandler().getTextFieldFocus() != null)
					ResourceLoader.core.getKeyboardHandler().getTextFieldFocus().setTyping(false);
			}
		}
		else
		{
			textAreaClicked = false;
			ResourceLoader.core.getKeyboardHandler().setWordTyping(false);
			if (ResourceLoader.core.getKeyboardHandler().getTextFieldFocus() != null)
				ResourceLoader.core.getKeyboardHandler().getTextFieldFocus().setTyping(false);
		}
		
		// Send the signal that a mouse button was pressed
		if (core.getScreenHandler().getCurrentScreen() != null)
			ResourceLoader.core.getScreenHandler().getCurrentScreen()
			.mouseClicked(mouseButton);
	}
}
