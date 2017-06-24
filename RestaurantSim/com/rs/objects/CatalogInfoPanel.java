package com.rs.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import com.engine.data.CatalogItem;
import com.engine.gui.GuiButton;
import com.engine.resources.ResourceLoader;
import com.rs.main.RSResources;

/**
 * A catalog info panel
 * shows info about a particular
 * catalog item.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CatalogInfoPanel<E>
{
	private Image panelImg = ResourceLoader.scaleImage(RSResources.catalogInfoBg, 400, 400); // The bg image
	private GuiButton buyBtn;
	
	private RSCatalogItem item = null; // The item being shown
	private String title = "";
	private String[] titleWords;
	private Image icon;
	
	private boolean shown = false; // Is the panel shown
	private boolean draw = false; // Should the panel be drawn
	private boolean btnReady = false; // Is the buy button created
	
	/**
	 * Draw the info panel with
	 * the data from the given item.
	 * @param g - the graphics to draw with
	 * @param xPos - the x position to draw at
	 * @param yPos - the y position to draw at
	 */
	public void drawPanel(Graphics g, int xPos, int yPos)
	{
		if (draw)
		{
			if (!btnReady)
			{
				buyBtn = new GuiButton(RSResources.buyBtn, RSResources.buyBtnHover, RSResources.buyBtnDisabled, 120, 40);
				
				btnReady = true;
			}
	
			g.drawImage(panelImg, xPos, yPos, null);
			
			if (item != null)
			{
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g2.setColor(new Color(53, 53, 53));
				g2.setFont(RSResources.fontMain24);
				
				// Draw the item name
				if (titleWords.length < 2)
				{
					if (title.length() <= 6)
						g2.drawString(title, xPos + 58, yPos + 52);
					else
						g2.drawString(title, xPos + 58, yPos + 52);
				}
				else if (titleWords.length == 2)
				{
					if (titleWords[0].length() <= 6 && titleWords[1].length() <= 6)
					{
						g2.drawString(titleWords[0] + " " + titleWords[1], xPos + 58, yPos + 52);
					}
					else
					{
						g2.drawString(titleWords[0], xPos + 58, yPos + 42);
						g2.drawString(titleWords[1], xPos + 58, yPos + 65);
					}
				}
				else if(titleWords.length == 3)
				{
					if (titleWords[0].length() <= 6 && titleWords[1].length() <= 6)
					{
						g2.drawString(titleWords[0] + " " + titleWords[1], xPos + 58, yPos + 42);
						g2.drawString(titleWords[2], xPos + 58, yPos + 65);
					}
					else
					{
						g2.drawString(titleWords[0], xPos + 58, yPos + 42);
						g2.drawString(titleWords[1] + " " + titleWords[2], xPos + 58, yPos + 65);
					}
				}
				else if (titleWords.length == 4)
				{
					g2.drawString(titleWords[0] + " " + titleWords[1], xPos + 58, yPos + 42);
					g2.drawString(titleWords[2] + " " + titleWords[3], xPos + 58, yPos + 65);
				}
				
				// Draw the item icon
				g.drawImage(icon, xPos + 73, yPos + 77, null);
				
				// Draw the description
				g2.setFont(RSResources.fontMain16);
				
				if (item.getDescription().length() < 18)
					g2.drawString(item.getDescription(), xPos + 48, yPos + 245);
				else
				{
					String subDesc = item.getDescription().substring(item.getDescription().indexOf(" ", 17) + 1, item.getDescription().length());
					
					g2.drawString(item.getDescription().substring(0, item.getDescription().indexOf(" ", 17) + 1), xPos + 48, yPos + 245);
					g2.drawString(subDesc, xPos + 48, yPos + 265);
				}
				
				// Draw the item cost
				g2.setFont(RSResources.fontMain24);
				g2.drawString(String.valueOf(item.getPrice()), xPos + 60, yPos + 324);
			}
			
			if (buyBtn != null)
				buyBtn.drawButton(g, xPos + 80, yPos + 350);
		}
	}
	
	/**
	 * Set the panel to be shown.
	 * @param item - the item to show
	 */
	public void showPanel(CatalogItem<RSCatalogItem> catItem)
	{
		this.item = catItem.getData();
		
		this.title = item.getName();
		this.titleWords = title.split(" ");
		
		this.icon = ResourceLoader.scaleImage(catItem.getIcon(), 132, 132);
		
		btnReady = false;
		shown = true;
		draw = true;
	}
	
	/**
	 * Hide the panel so it isn't showing.
	 */
	public void hidePanel()
	{
		item = null;
		title = "";
		
		btnReady = false;
		shown = false;
		draw = false;
		
		if (buyBtn != null)
		{
			ResourceLoader.core.getMouseHandler().guiComponents.remove(this.buyBtn);
			buyBtn = null;
		}
	}

	/**
	 * @return the shown
	 */
	public boolean isShown()
	{
		return shown;
	}

	/**
	 * @param shown the shown to set
	 */
	public void setShown(boolean shown)
	{
		this.shown = shown;
	}

	/**
	 * @return the buyBtn
	 */
	public GuiButton getBuyBtn()
	{
		return buyBtn;
	}

	/**
	 * @return the item
	 */
	public RSCatalogItem getItem()
	{
		return item;
	}
}