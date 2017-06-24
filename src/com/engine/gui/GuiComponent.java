package com.engine.gui;

import java.awt.Rectangle;

/**
 * <h1>GuiComponent</h1>
 * GuiComponent is the parent class to
 * anything considered to be an interactable
 * part of the GUI.
 * <br>
 * <h1>Examples of GuiComponents:</h1>
 * <ul>
 * <li>GuiButton: A clickable button</li>
 * <li>GuiTextField: An input box for text</li>
 * </ul>
 * 
 * @author Ryan Hochmuth
 *
 */
public abstract class GuiComponent
{
	protected boolean mouseEntered = false; // Has the mouse entered this component
	
	/*
	 * Position/size fields
	 */
	protected int xPos;
	protected int yPos;
	protected int width;
	protected int height;

	/**
	 * This method is called by MouseHandler 
	 * when an object extending this class
	 * is clicked on.
	 */
	public abstract void clicked();
	
	/**
	 * This method is called by MouseHandler 
	 * when the mouse enters an object 
	 * extending this class.
	 */
	public abstract void mouseEntered();
	
	/**
	 * This method is called by MouseHandler 
	 * when the mouse exits an object 
	 * extending this class.
	 */
	public abstract void mouseExited();

	/**
	 * @return the mouseEntered
	 */
	public boolean isMouseEntered()
	{
		return mouseEntered;
	}

	/**
	 * @param mouseEntered the mouseEntered to set
	 */
	public void setMouseEntered(boolean mouseEntered)
	{
		this.mouseEntered = mouseEntered;
	}
	
	/**
	 * Return the bounding rectangle of this GuiComponent.
	 * @return the rectangle
	 */
	public Rectangle getRect()
	{
		return new Rectangle(xPos, yPos, width, height);
	}

	/**
	 * @return the xPos
	 */
	public int getxPos()
	{
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos)
	{
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos()
	{
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos)
	{
		this.yPos = yPos;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
}
