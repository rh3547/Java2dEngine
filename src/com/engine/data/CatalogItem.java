package com.engine.data;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.engine.gui.GuiButton;
import com.engine.resources.ResourceLoader;

/**
 * <h1>CatalogItem</h1>
 * A catalog item is an object that is used
 * in conjunction with a Catalog.  This class 
 * is used to hold data for individual items
 * easily.
 * <br>
 * <h1>Catalog Item Properties</h1>
 * <ul>
 * <li>Name</li>
 * <li>Icon</li>
 * <li>GuiButton</li>
 * </ul>
 * 
 * Other characteristics can be included
 * by making a custom CatalogItem class 
 * that extends this one.
 * <br>
 * @author Ryan Hochmuth
 * 
 * @param E
 *
 */
public class CatalogItem<E>
{
	private E data; // The data of this item
	private Image icon = null; // The icon to represent this item
	private int imageOffsetX = 0; // The x offset of the icon
	private int imageOffsetY = 0; // The y offset of the icon
	private int itemWidth = 32; // The width of this item tile
	private int itemHeight = 32; // The height of this item tile
	
	private GuiButton btn = null; // The button used in the catalog
	private Image btnImage = null; // The main image for this button
	private Image btnHoverImage = null; // The hover image for this button
	
	private List<String> keyWords = new ArrayList<String>(); // The key words associated with this item
	
	private boolean shown = false; // Is this item shown
	
	/**
	 * Create a new catalog item.
	 * @param name
	 * @param itemWidth
	 * @param itemHeight
	 */
	public CatalogItem(E data, int itemWidth, int itemHeight)
	{
		this.data = data;
		this.itemWidth = itemWidth;
		this.itemHeight = itemHeight;
	}
	
	/**
	 * Create a new catalog item.
	 * @param name
	 * @param icon
	 * @param itemWidth
	 * @param itemHeight
	 */
	public CatalogItem(E data, Image icon, int itemWidth, int itemHeight)
	{
		this.data = data;
		this.icon = icon;
		this.itemWidth = itemWidth;
		this.itemHeight = itemHeight;
		
		if (icon != null)
			icon = ResourceLoader.scaleImage(icon, itemWidth, itemHeight);
	}
	
	/**
	 * Create a new catalog item with custom images.
	 * @param name
	 * @param icon
	 * @param itemWidth
	 * @param itemHeight
	 * @param btnImage
	 * @param btnhoverImage
	 */
	public CatalogItem(E data, Image icon, int imageOffsetX, int imageOffsetY, int itemWidth, int itemHeight, Image btnImage, Image btnHoverImage)
	{
		this.data = data;
		this.icon = icon;
		this.imageOffsetX = imageOffsetX;
		this.imageOffsetY = imageOffsetY;
		this.itemWidth = itemWidth;
		this.itemHeight = itemHeight;
		this.btnImage = btnImage;
		this.btnHoverImage = btnHoverImage;
	}
	
	/**
	 * Draw this catalog item to the screen.
	 */
	public void drawItem(Graphics g, int xPos, int yPos)
	{
		if (!shown)
		{
			if (btnImage == null)
				btn = new GuiButton(itemWidth, itemHeight);
			else
				btn = new GuiButton(btnImage, btnHoverImage, itemWidth, itemHeight);
			
			shown = true;
		}
		
		btn.drawButton(g, xPos, yPos);
		
		if (icon != null)
			g.drawImage(icon, xPos + imageOffsetX, yPos + imageOffsetY, null);
	}
	
	public void hide()
	{
		if (btn != null)
		{
			ResourceLoader.core.getMouseHandler().guiComponents.remove(this.btn);
			btn = null;
		}
		
		shown = false;
	}

	/**
	 * @return the name
	 */
	public E getData()
	{
		return data;
	}

	/**
	 * @param name the name to set
	 */
	public void setData(E data)
	{
		this.data = data;
	}

	/**
	 * @return the icon
	 */
	public Image getIcon()
	{
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(Image icon)
	{
		this.icon = icon;
	}

	/**
	 * @return the imageOffsetX
	 */
	public int getImageOffsetX()
	{
		return imageOffsetX;
	}

	/**
	 * @param imageOffsetX the imageOffsetX to set
	 */
	public void setImageOffsetX(int imageOffsetX)
	{
		this.imageOffsetX = imageOffsetX;
	}

	/**
	 * @return the imageOffsetY
	 */
	public int getImageOffsetY()
	{
		return imageOffsetY;
	}

	/**
	 * @param imageOffsetY the imageOffsetY to set
	 */
	public void setImageOffsetY(int imageOffsetY)
	{
		this.imageOffsetY = imageOffsetY;
	}

	/**
	 * @return the btn
	 */
	public GuiButton getBtn()
	{
		return btn;
	}

	/**
	 * @param btn the btn to set
	 */
	public void setBtn(GuiButton btn)
	{
		this.btn = btn;
	}

	/**
	 * @return the btnImage
	 */
	public Image getBtnImage()
	{
		return btnImage;
	}

	/**
	 * @param btnImage the btnImage to set
	 */
	public void setBtnImage(Image btnImage)
	{
		this.btnImage = btnImage;
	}

	/**
	 * @return the btnHoverImage
	 */
	public Image getBtnHoverImage()
	{
		return btnHoverImage;
	}

	/**
	 * @param btnHoverImage the btnHoverImage to set
	 */
	public void setBtnHoverImage(Image btnHoverImage)
	{
		this.btnHoverImage = btnHoverImage;
	}

	/**
	 * @return the keyWords
	 */
	public List<String> getKeyWords()
	{
		return keyWords;
	}

	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(List<String> keyWords)
	{
		this.keyWords = keyWords;
	}
	
	/**
	 * Add a new keyword to this list.
	 * @param keyWord
	 */
	public void addKeyWord(String keyWord)
	{
		this.keyWords.add(keyWord.toLowerCase());
	}
}