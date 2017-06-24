package com.engine.data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.gui.GuiTextField;
import com.engine.resources.ResourceLoader;

/**
 * <h1>Catalog</h1>
 * A catalog is a graphical representation
 * of a list of items.  Each item is displayed
 * on a background in the form of tiles.
 * Catalog uses generics to allow a catalog
 * of any data type.
 * <br>
 * <h1>Catalog Components</h1>
 * <ul>
 * <li>A catalog contains different pages, CatalogPage.</li>
 * <li>Each page contains a maximum of 16 items, CatalogItem.</li>
 * <li>The layout and organization of the tiles is handled automatically.</li>
 * <li>A catalog contains next and previous page buttons by default.</li>
 * <li>A catalog contains a search box to search through it by keyword.</li>
 * </ul>
 * <br>
 * NOTE: All components of a catalog can be customized.
 * It will work without setting any custom images, etc.
 * But a custom background, buttons, search box, etc. can
 * be used.
 * <br>
 * @author Ryan Hochmuth
 * 
 * @param E
 *
 */
public class Catalog<E>
{
	private int xPos = 0; // The x position of the whole catalog
	private int yPos = 0; // The y position of the whole catalog
	private int tileOffsetX = 0; // The x offset for tiles to begin
	private int tileOffsetY = 0; // The y offset for tiles to begin
	private int textOffsetX = 0; // The x offset for the title
	private int textOffsetY = 0; // The y offset for the title
	private int tileWidth = 32; // The width of a single tile
	private int tileHeight = 32; // The height of a single tile
	private int tileSpacing = 10; // The space between tiles
	
	private Image background = null; // The catalog's background image
	private Image nextBtnImage = null; // The main next page button image
	private Image nextBtnHoverImage = null; // The hover next page button image
	private Image nextBtnDisabledImage = null; // The disabled next page button image
	private Image prevBtnImage = null; // The main previous page button image
	private Image prevBtnHoverImage = null; // The hover previous page button image
	private Image prevBtnDisabledImage = null; // The disabled previous page button image
	
	private GuiButton nextBtn; // The next page button
	private GuiButton prevBtn; // The previous page button
	private int btnWidth = 80; // The width of the buttons
	private int btnHeight = 35; // The height of the buttons
	private int nextBtnOffsetX = 0; // The offsets for the next/previous buttons
	private int nextBtnOffsetY = 0; // 
	private int prevBtnOffsetX = 0; //
	private int prevBtnOffsetY = 0; //
	
	private GuiTextField searchBox = null; // A text box to search the catalog
	private int searchBoxWidth = 0; // The width of the search box
	private int searchBoxHeight = 0; // The height of the search box
	private Font searchBoxFont = null; // The font of the search box text
	private Color searchBoxColor = null; // The color of the search box text
	private Image searchBoxImage = null; // The bg image of the search box
	private int searchBoxOffsetX = 0; // The x offset of the search box in the catalog
	private int searchBoxOffsetY = 0; // The y offset of the search box in the catalog
	private int searchBoxTextOffsetX = 0; // The x text offset of the search box
	private int searchBoxTextOffsetY = 0; // The y text offset of the search box
	
	private String title = ""; // The title to display for this catalog
	private Font titleFont = null; // The font to display the title with
	private Color titleColor = Color.black; // The color to display the title in 
	
	private int catalogWidth; // The total width of the catalog
	private int catalogHeight; // The total height of the catalog
	
	private List<CatalogItem<E>> items = new ArrayList<CatalogItem<E>>(); // All of the items in this catalog
	private List<CatalogPage<E>> pages = new ArrayList<CatalogPage<E>>(); // The pages in this catalog
	
	private int currentPage = 0; // The current page of the catalog being shown
	private String searchWord = ""; // The keyword to sort by
	
	// Flags
	private boolean shown = false; // Is the catalog shown
	private boolean draw = false; // Should the catalog draw
	private boolean btnReady; // Are the buttons created
	private boolean drawItems = false; // Should catalog items be drawn
	private boolean useBackground = false; // Does this catalog have a custom background
	private boolean useCustomPageButtons = false; // Does this catalog use custom buttons

	/**
	 * Create a new basic catalog.
	 * @param xPos
	 * @param yPos
	 * @param tileWidth
	 * @param tileHeight
	 * @param tileSpacing
	 * @param title
	 */
	public Catalog(int tileWidth, int tileHeight, int tileSpacing, String title)
	{
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileSpacing = tileSpacing;
		this.title = title;
		
		setupCatalog();
	}
	
	/**
	 * Create a new catalog with custom images.
	 * @param tileWidth
	 * @param tileHeight
	 * @param tileSpacing
	 * @param title
	 * @param background
	 * @param nextBtnImages
	 * @param prevBtnImages
	 */
	public Catalog(int tileWidth, int tileHeight, int tileSpacing, String title,
			Image background, Image[] nextBtnImages, Image[] prevBtnImages)
	{
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileSpacing = tileSpacing;
		this.title = title;
		this.background = background;
		this.nextBtnImage = nextBtnImages[0];
		this.nextBtnHoverImage = nextBtnImages[1];
		this.nextBtnDisabledImage = nextBtnImages[2];
		this.prevBtnImage = prevBtnImages[0];
		this.prevBtnHoverImage = prevBtnImages[1];
		this.prevBtnDisabledImage = prevBtnImages[2];
		
		setupCatalog();
	}
	
	/**
	 * Set the necessary parameters for
	 * the catalog to function.  For example,
	 * prepare images if they need to be.
	 * If no images are given, setup default
	 * images.
	 */
	private void setupCatalog()
	{
		if (background != null)
			useBackground = true;
		
		if (nextBtnImage != null && nextBtnHoverImage != null &&
			prevBtnImage != null && prevBtnHoverImage != null)
			useCustomPageButtons = true;
		
		catalogWidth = (4 * tileWidth) + (5 * tileSpacing) + (4 * tileSpacing);
		catalogHeight = (4 * tileHeight) + (5 * tileSpacing) + (2 * tileHeight);
		
		if (background != null)
			background = ResourceLoader.scaleImage(background, catalogWidth, catalogHeight);
	}
	
	/**
	 * Draw the catalog on the screen.
	 * @param g
	 * @param xPos
	 * @param yPos
	 */
	public void drawCatalog(Graphics g, int xPos, int yPos)
	{	
		if (draw)
		{
			// Button prep
			if (!btnReady)
			{
				if (useCustomPageButtons)
				{
					nextBtn = new GuiButton(nextBtnImage, nextBtnHoverImage, nextBtnDisabledImage, btnWidth, btnHeight);
					prevBtn = new GuiButton(prevBtnImage, prevBtnHoverImage, prevBtnDisabledImage, btnWidth, btnHeight);
				}
				else
				{
					nextBtn = new GuiButton(btnWidth, btnHeight);
					prevBtn = new GuiButton(btnWidth, btnHeight);
				}
				
				if (searchBoxImage != null)
				{
					searchBox = new GuiTextField(searchBoxWidth, searchBoxHeight, searchBoxFont, searchBoxColor, searchBoxImage);
				}
				else if (searchBoxFont != null && searchBoxColor != null)
				{
					searchBox = new GuiTextField(searchBoxWidth, searchBoxHeight, searchBoxFont, searchBoxColor);
				}
				else
				{
					searchBox = new GuiTextField(searchBoxWidth, searchBoxHeight);
				}
				
				searchBox.setTextOffsetX(searchBoxTextOffsetX);
				searchBox.setTextOffsetY(searchBoxTextOffsetY);
				
				btnReady = true;
			}
			
			if (!searchWord.equals(searchBox.getText()))
			{
				setSearchWord(searchBox.getText());
			}
			
			this.xPos = xPos;
			this.yPos = yPos;
			
			// Background
			if (useBackground)
				g.drawImage(background, xPos, yPos, null);
			else
			{
				g.setColor(Color.black);
				g.fillRect(xPos, yPos, catalogWidth, catalogHeight);
			}
			
			// Title
			if (titleColor != null)
				g.setColor(titleColor);
			if (titleFont != null)
				g.setFont(titleFont);
			
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2.drawString(title, xPos + textOffsetX, yPos + textOffsetY);
			
			int tileOffset = 0;
			
			// Search box
			searchBox.draw(g, xPos + searchBoxOffsetX, yPos + searchBoxOffsetY);
			
			// Items
			if (pages.size() > 0 && drawItems)
			{
				for (int x = 0; x < pages.get(currentPage).getNumItems(); x++)
				{
					if (x % 4 == 0)
						tileOffset = 0;
					
					if (x < 4)
					{
						pages.get(currentPage).getItem(x).drawItem(g, 
								xPos + tileOffsetX + (2 * tileSpacing) + (tileOffset * tileSpacing) + (tileOffset * tileWidth), 
								yPos + tileOffsetY);
						
						tileOffset++;
					}
					else if (x >= 4 && x < 8)
					{
						pages.get(currentPage).getItem(x).drawItem(g, 
								xPos + tileOffsetX + (2 * tileSpacing) + (tileOffset * tileSpacing) + (tileOffset * tileWidth), 
								yPos + tileOffsetY + (1 * tileHeight) + (1 * tileSpacing));
						
						tileOffset++;
					}
					else if (x >= 8 && x < 12)
					{
						pages.get(currentPage).getItem(x).drawItem(g, 
								xPos + tileOffsetX + (2 * tileSpacing) + (tileOffset * tileSpacing) + (tileOffset * tileWidth), 
								yPos + tileOffsetY + (2 * tileHeight) + (2 * tileSpacing));
						
						tileOffset++;
					}
					else if (x >= 12 && x < 16)
					{
						pages.get(currentPage).getItem(x).drawItem(g, 
								xPos + tileOffsetX + (2 * tileSpacing) + (tileOffset * tileSpacing) + (tileOffset * tileWidth), 
								yPos + tileOffsetY + (3 * tileHeight) + (3 * tileSpacing));
						
						tileOffset++;
					}
				}
			}
			
			if (currentPage == 0)
				prevBtn.setEnabled(false);
			if (currentPage == pages.size() - 1)
				nextBtn.setEnabled(false);
			if (currentPage > 0)
				prevBtn.setEnabled(true);
			if (currentPage < pages.size() - 1)
				nextBtn.setEnabled(true);
			
			nextBtn.drawButton(g, xPos + catalogWidth + nextBtnOffsetX, yPos + catalogHeight + nextBtnOffsetY);
			prevBtn.drawButton(g, xPos + prevBtnOffsetX, yPos + catalogHeight + prevBtnOffsetY);
		}
	}
	
	/**
	 * Hide the catalog so it isn't showing
	 * on screen anymore.
	 */
	public void hideCatalog()
	{
		btnReady = false;
		draw = false;
		shown = false;
		
		removePages();
		
		if (nextBtn != null || prevBtn != null || searchBox != null)
		{
			ResourceLoader.core.getMouseHandler().guiComponents.remove(nextBtn);
			nextBtn = null;
			ResourceLoader.core.getMouseHandler().guiComponents.remove(prevBtn);
			prevBtn = null;
			ResourceLoader.core.getMouseHandler().guiComponents.remove(searchBox);
			searchBox = null;
		}
		
		currentPage = 0;
	}
	
	/**
	 * Show the catalog on the screen.
	 */
	public void showCatalog()
	{
		createPages();
		
		btnReady = false;
		draw = true;
		shown = true;
		drawItems = true;
	}
	
	/**
	 * Display the next page of items.
	 */
	public void nextPage()
	{
		draw = false;
		drawItems = false;
		
		for (int x = 0; x < pages.get(currentPage).getNumItems(); x++)
		{
			pages.get(currentPage).getItems()[x].hide();
		}
		
		currentPage++;
		
		draw = true;
		drawItems = true;
	}
	
	/**
	 * Display the previous page of items.
	 */
	public void previousPage()
	{
		draw = false;
		drawItems = false;
		
		for (int x = 0; x < pages.get(currentPage).getNumItems(); x++)
		{
			pages.get(currentPage).getItems()[x].hide();
		}
		
		currentPage--;
		
		draw = true;
		drawItems = true;
	}
	
	/**
	 * Add a new CatalogItem to the
	 * catalog.
	 * @param item
	 */
	public void addToCatalog(CatalogItem<E> item)
	{
		items.add(item);
	}
	
	/**
	 * Create the pages based on a certain sorting.
	 */
	public void createPages()
	{
		drawItems = false;
		
		boolean itemAdded = false;
		
		for (int x = 0; x < items.size(); x++)
		{
			itemAdded = false;
			
			for (int y = 0; y < items.get(x).getKeyWords().size(); y++)
			{
				if (items.get(x).getKeyWords().get(y).contains(this.searchWord) || searchWord.equals(""))
				{
					if (pages.size() == 0)
						pages.add(new CatalogPage<E>());
					
					for (int i = 0; i < pages.size(); i++)
					{
						if (pages.get(i).getNumItems() < 16)
						{
							pages.get(i).addItem(items.get(x));
							itemAdded = true;
						}
					}
					
					if (!itemAdded)
					{
						pages.add(new CatalogPage<E>());
						pages.get(pages.size() - 1).addItem(items.get(x));
						itemAdded = true;
					}
				}
				
				if (itemAdded)
					break;
			}
		}
		
		drawItems = true;
	}
	
	/**
	 * Remove the pages and data of this catalog.
	 */
	public void removePages()
	{
		drawItems = false;
		
		if (pages.size() > 0)
		{
			for (int x = 0; x < pages.size(); x++)
			{
				for (int y = 0; y < pages.get(x).getNumItems(); y++)
				{
					pages.get(x).getItems()[y].hide();
				}
			}
		}
		
		pages.clear();
		currentPage = 0;
	}
	
	/**
	 * Check if a component in this catalog
	 * was clicked.  If so, it gets returned.
	 * @param component
	 * @return the clicked item or null
	 */
	public CatalogItem<E> checkItemClicked(GuiComponent component)
	{
		if (shown)
		{
			if (component == nextBtn)
			{
				nextPage();
			}
			
			if (component == prevBtn)
			{
				previousPage();
			}
			
			if (pages.size() > 0)
			{
				if(pages.get(currentPage).getNumItems() < 1)
					return null;
				
				for (int x = 0; x < pages.get(currentPage).getNumItems(); x++)
				{
					if (component == pages.get(currentPage).getItem(x).getBtn())
					{
						return pages.get(currentPage).getItem(x);
					}
				}
			}
		}
		
		return null;
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
	 * @return the tileOffsetX
	 */
	public int getTileOffsetX()
	{
		return tileOffsetX;
	}

	/**
	 * @param tileOffsetX the tileOffsetX to set
	 */
	public void setTileOffsetX(int tileOffsetX)
	{
		this.tileOffsetX = tileOffsetX;
	}

	/**
	 * @return the tileOffsetY
	 */
	public int getTileOffsetY()
	{
		return tileOffsetY;
	}

	/**
	 * @param tileOffsetY the tileOffsetY to set
	 */
	public void setTileOffsetY(int tileOffsetY)
	{
		this.tileOffsetY = tileOffsetY;
	}

	/**
	 * @return the tileWidth
	 */
	public int getTileWidth()
	{
		return tileWidth;
	}

	/**
	 * @param tileWidth the tileWidth to set
	 */
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
	}

	/**
	 * @return the tileHeight
	 */
	public int getTileHeight()
	{
		return tileHeight;
	}

	/**
	 * @param tileHeight the tileHeight to set
	 */
	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
	}

	/**
	 * @return the tileSpacing
	 */
	public int getTileSpacing()
	{
		return tileSpacing;
	}

	/**
	 * @param tileSpacing the tileSpacing to set
	 */
	public void setTileSpacing(int tileSpacing)
	{
		this.tileSpacing = tileSpacing;
	}

	/**
	 * @return the background
	 */
	public Image getBackground()
	{
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground(Image background)
	{
		if (background != null)
		{
			this.background = ResourceLoader.scaleImage(background, catalogWidth, catalogHeight);
			useBackground = true;
		}
	}

	public void setPageBtnImages(Image[] nextBtnImages, Image[] prevBtnImages)
	{
		this.nextBtnImage = nextBtnImages[0];
		this.nextBtnHoverImage = nextBtnImages[1];
		this.nextBtnDisabledImage = nextBtnImages[2];
		this.prevBtnImage = prevBtnImages[0];
		this.prevBtnHoverImage = prevBtnImages[1];
		this.prevBtnDisabledImage = prevBtnImages[2];
		useCustomPageButtons = true;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the items
	 */
	public List<CatalogItem<E>> getItems()
	{
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<CatalogItem<E>> items)
	{
		this.items = items;
	}

	/**
	 * @return the pages
	 */
	public List<CatalogPage<E>> getPages()
	{
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<CatalogPage<E>> pages)
	{
		this.pages = pages;
	}

	/**
	 * @return the textOffsetX
	 */
	public int getTextOffsetX()
	{
		return textOffsetX;
	}

	/**
	 * @param textOffsetX the textOffsetX to set
	 */
	public void setTextOffsetX(int textOffsetX)
	{
		this.textOffsetX = textOffsetX;
	}

	/**
	 * @return the textOffsetY
	 */
	public int getTextOffsetY()
	{
		return textOffsetY;
	}

	/**
	 * @param textOffsetY the textOffsetY to set
	 */
	public void setTextOffsetY(int textOffsetY)
	{
		this.textOffsetY = textOffsetY;
	}

	/**
	 * @return the titleFont
	 */
	public Font getTitleFont()
	{
		return titleFont;
	}

	/**
	 * @param titleFont the titleFont to set
	 */
	public void setTitleFont(Font titleFont)
	{
		this.titleFont = titleFont;
	}

	/**
	 * @return the titleColor
	 */
	public Color getTitleColor()
	{
		return titleColor;
	}

	/**
	 * @param titleColor the titleColor to set
	 */
	public void setTitleColor(Color titleColor)
	{
		this.titleColor = titleColor;
	}

	/**
	 * @return the btnWidth
	 */
	public int getBtnWidth()
	{
		return btnWidth;
	}

	/**
	 * @param btnWidth the btnWidth to set
	 */
	public void setBtnWidth(int btnWidth)
	{
		this.btnWidth = btnWidth;
	}

	/**
	 * @return the btnHeight
	 */
	public int getBtnHeight()
	{
		return btnHeight;
	}

	/**
	 * @param btnHeight the btnHeight to set
	 */
	public void setBtnHeight(int btnHeight)
	{
		this.btnHeight = btnHeight;
	}

	/**
	 * @return the nextBtnOffsetX
	 */
	public int getNextBtnOffsetX()
	{
		return nextBtnOffsetX;
	}

	/**
	 * @param nextBtnOffsetX the nextBtnOffsetX to set
	 */
	public void setNextBtnOffsetX(int nextBtnOffsetX)
	{
		this.nextBtnOffsetX = nextBtnOffsetX;
	}

	/**
	 * @return the nextBtnOffsetY
	 */
	public int getNextBtnOffsetY()
	{
		return nextBtnOffsetY;
	}

	/**
	 * @param nextBtnOffsetY the nextBtnOffsetY to set
	 */
	public void setNextBtnOffsetY(int nextBtnOffsetY)
	{
		this.nextBtnOffsetY = nextBtnOffsetY;
	}

	/**
	 * @return the prevBtnOffsetX
	 */
	public int getPrevBtnOffsetX()
	{
		return prevBtnOffsetX;
	}

	/**
	 * @param prevBtnOffsetX the prevBtnOffsetX to set
	 */
	public void setPrevBtnOffsetX(int prevBtnOffsetX)
	{
		this.prevBtnOffsetX = prevBtnOffsetX;
	}

	/**
	 * @return the prevBtnOffsetY
	 */
	public int getPrevBtnOffsetY()
	{
		return prevBtnOffsetY;
	}

	/**
	 * @param prevBtnOffsetY the prevBtnOffsetY to set
	 */
	public void setPrevBtnOffsetY(int prevBtnOffsetY)
	{
		this.prevBtnOffsetY = prevBtnOffsetY;
	}

	/**
	 * @return the searchBox
	 */
	public GuiTextField getSearchBox()
	{
		return searchBox;
	}

	/**
	 * @param searchBox the searchBox to set
	 */
	public void setSearchBox(int width, int height, Font font, Color color, Image image)
	{
		this.searchBoxWidth = width;
		this.searchBoxHeight = height;
		this.searchBoxFont = font;
		this.searchBoxColor = color;
		this.searchBoxImage = image;
	}

	/**
	 * @return the searchBoxX
	 */
	public int getSearchBoxOffsetX()
	{
		return searchBoxOffsetX;
	}

	/**
	 * @param searchBoxX the searchBoxX to set
	 */
	public void setSearchBoxOffsetX(int searchBoxX)
	{
		this.searchBoxOffsetX = searchBoxX;
	}

	/**
	 * @return the searchBoxY
	 */
	public int getSearchBoxOffsetY()
	{
		return searchBoxOffsetY;
	}

	/**
	 * @param searchBoxY the searchBoxY to set
	 */
	public void setSearchBoxOffsetY(int searchBoxY)
	{
		this.searchBoxOffsetY = searchBoxY;
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
	 * @return the searchWord
	 */
	public String getSearchWord()
	{
		return searchWord;
	}

	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord)
	{
		this.searchWord = searchWord;
		
		removePages();
		createPages();
	}
	
	/**
	 * @return the searchBoxWidth
	 */
	public int getSearchBoxWidth()
	{
		return searchBoxWidth;
	}

	/**
	 * @param searchBoxWidth the searchBoxWidth to set
	 */
	public void setSearchBoxWidth(int searchBoxWidth)
	{
		this.searchBoxWidth = searchBoxWidth;
	}

	/**
	 * @return the searchBoxHeight
	 */
	public int getSearchBoxHeight()
	{
		return searchBoxHeight;
	}

	/**
	 * @param searchBoxHeight the searchBoxHeight to set
	 */
	public void setSearchBoxHeight(int searchBoxHeight)
	{
		this.searchBoxHeight = searchBoxHeight;
	}

	/**
	 * @return the searchBoxFont
	 */
	public Font getSearchBoxFont()
	{
		return searchBoxFont;
	}

	/**
	 * @param searchBoxFont the searchBoxFont to set
	 */
	public void setSearchBoxFont(Font searchBoxFont)
	{
		this.searchBoxFont = searchBoxFont;
	}

	/**
	 * @return the searchBoxColor
	 */
	public Color getSearchBoxColor()
	{
		return searchBoxColor;
	}

	/**
	 * @param searchBoxColor the searchBoxColor to set
	 */
	public void setSearchBoxColor(Color searchBoxColor)
	{
		this.searchBoxColor = searchBoxColor;
	}

	/**
	 * @return the searchBoxImage
	 */
	public Image getSearchBoxImage()
	{
		return searchBoxImage;
	}

	/**
	 * @param searchBoxImage the searchBoxImage to set
	 */
	public void setSearchBoxImage(Image searchBoxImage)
	{
		this.searchBoxImage = searchBoxImage;
	}

	/**
	 * @return the searchBoxTextOffsetX
	 */
	public int getSearchBoxTextOffsetX()
	{
		return searchBoxTextOffsetX;
	}

	/**
	 * @param searchBoxTextOffsetX the searchBoxTextOffsetX to set
	 */
	public void setSearchBoxTextOffsetX(int searchBoxTextOffsetX)
	{
		this.searchBoxTextOffsetX = searchBoxTextOffsetX;
	}

	/**
	 * @return the searchBoxTextOffsetY
	 */
	public int getSearchBoxTextOffsetY()
	{
		return searchBoxTextOffsetY;
	}

	/**
	 * @param searchBoxTextOffsetY the searchBoxTextOffsetY to set
	 */
	public void setSearchBoxTextOffsetY(int searchBoxTextOffsetY)
	{
		this.searchBoxTextOffsetY = searchBoxTextOffsetY;
	}

	/**
	 * @return the nextBtn
	 */
	public GuiButton getNextBtn() 
	{
		return nextBtn;
	}

	/**
	 * @return the prevBtn
	 */
	public GuiButton getPrevBtn() 
	{
		return prevBtn;
	}
}