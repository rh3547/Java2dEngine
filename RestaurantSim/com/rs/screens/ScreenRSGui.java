package com.rs.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;

import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.data.CatalogItem;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.resources.ResourceLoader;
import com.rs.main.RSFlags;
import com.rs.main.RSResources;
import com.rs.objects.RSCatalogItem;
import com.rs.objects.RSObjects;
import com.rs.objects.RSPlayerController;

public class ScreenRSGui extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.core.getWindow().getWidth();
	private int windowY = ResourceLoader.core.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image topBar = ResourceLoader.scaleImage(RSResources.topBar, windowX, 100);
	private Image bottomBar = ResourceLoader.scaleImage(RSResources.bottomBar, windowX, 100);
	private int bottomBarY = windowY;
	
	// Top bar
	private GuiButton menuBtn = new GuiButton(RSResources.menuBtn, RSResources.menuBtnHover, 32, 33);
	private GuiButton playBtn = new GuiButton(RSResources.playBtn, RSResources.playBtnHover, 40, 31);
	private GuiButton fastBtn = new GuiButton(RSResources.fastBtn, RSResources.fastBtnHover, 40, 31);
	
	// Bottom bar
	private GuiButton liveBtn = new GuiButton(RSResources.liveBtnHover, RSResources.liveBtnHover, 45, 45);
	private GuiButton constructBtn = new GuiButton(RSResources.constructBtn, RSResources.constructBtnHover, 45, 45);
	private GuiButton furnishBtn = new GuiButton(RSResources.furnishBtn, RSResources.furnishBtnHover, 45, 45);
	private GuiButton gridBtn = new GuiButton(RSResources.gridBtn, RSResources.gridBtnHover, 35, 35);
	private GuiButton catalogBtn = new GuiButton(RSResources.openCatalogBtn, RSResources.openCatalogBtnHover, 140, 45);
	
	// Flags
	private boolean paused = true;
	private boolean catalogOpen = false;
	
	public ScreenRSGui(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.core.getCanvas().setScreenLoaded(false);
		
		// Load the World
		RSResources.world.loadWorld();
		
		// Add the Player
		ResourceLoader.core.getObjectHandler().getCoreObjects().add(RSObjects.player);
		
		// Set the game's Camera
		ResourceLoader.core.setCamera(RSObjects.camera);
		
		ResourceLoader.core.getCamera().setxPos(RSObjects.player.getxPos());
		ResourceLoader.core.getCamera().setyPos(RSObjects.player.getyPos());
		
		// Tell the canvas that this screen is done loading
		ResourceLoader.core.getCanvas().setScreenLoaded(true);
	}

	@Override
	public void onUpdate()
	{
		// Update the local window size
		windowX = ResourceLoader.core.getWindow().getWidth();
		windowY = ResourceLoader.core.getWindow().getHeight();
		
		if (windowX != lastWindowX)
		{
			lastWindowX = windowX;
			
			if (windowY != lastWindowY)
			{
				lastWindowY = windowY;
			}
		}
		
		if (!catalogOpen)
			RSFlags.usingGui = false;
		
		if (ResourceLoader.core.getWindow().getMousePosition() != null)
		{
			Point mousePoint = ResourceLoader.core.getWindow().getMousePosition();
			
			if (mousePoint.getY() >= (windowY - 150))
			{
				RSFlags.usingGui = true;
				
				if (bottomBarY > (windowY - 100))
					bottomBarY -= 4;
			}
			else
			{
				if (bottomBarY < windowY)
					bottomBarY += 4;
			}
			
			if (mousePoint != null)
				if (mousePoint.getY() <= 100)
					RSFlags.usingGui = true;
			
			if (mousePoint != null)
				if (mousePoint.getY() > 100 &&
					mousePoint.getY() < (windowY - 100))
					RSFlags.usingGui = false;
		}
		
		if (catalogOpen)
			RSFlags.usingGui = true;
		
		if (RSFlags.mode == RSFlags.PLAY_MODE)
			catalogBtn.setEnabled(false);
		else
			catalogBtn.setEnabled(true);
	}

	@Override
	public void unload()
	{
		ResourceLoader.core.getWorldHandler().getChunks().clear();
		ResourceLoader.core.getObjectHandler().getCoreObjects().clear();
		
		ResourceLoader.core.getScreenHandler()
		.showScreen(new ScreenRSMainMenu(ResourceLoader.core.getScreenHandler()));
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		//g.setColor(Color.red);
		//g.fillRect(ResourceLoader.game.getMouseX(), ResourceLoader.game.getMouseY(), 4, 4);
	}

	@Override
	public void drawGui(Graphics g)
	{
		g.drawImage(topBar, 0, 0, null);
		g.drawImage(bottomBar, 0, bottomBarY, null);
		
		// Top bar buttons
		menuBtn.drawButton(g, 20, 12);
		playBtn.drawButton(g, 167, 13);
		fastBtn.drawButton(g, 203, 13);
		
		// Bottom bar buttons
		liveBtn.drawButton(g, 20, bottomBarY + 20);
		constructBtn.drawButton(g, 95, bottomBarY + 20);
		furnishBtn.drawButton(g, 170, bottomBarY + 20);
		gridBtn.drawButton(g, windowX - 55, bottomBarY + 25);
		catalogBtn.drawButton(g, (windowX / 2) - 70, bottomBarY + 25);
		
		// Draw the GUI strings
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(RSResources.fontMain16);
		g2.setColor(new Color(53, 53, 53));
		
		g2.drawString("10:00 am", 70, 35); // The time
		g2.drawString("M", 150, 35); // The day of the week
		g2.drawString("Update...", 370, 35); // The notification
		g2.drawString("6,804.05", 1120, 35); // The money
		
		if (RSObjects.panel.isShown())
			RSObjects.panel.drawPanel(g, (windowX / 2) + 230, 160);
		
		if (catalogOpen)
		{
			RSObjects.constructCatalog.drawCatalog(g, (windowX / 2) - 260, 75);
			RSObjects.furnishCatalog.drawCatalog(g, (windowX / 2) - 260, 75);
			RSObjects.ingredientsCatalog.drawCatalog(g, (windowX / 2) - 260, 75);
		}
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		// Top bar buttons
		if (component == menuBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
			
			getScreenHandler().unloadCurrentScreen();
		}
		
		if (component == playBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
			
			if (paused)
			{
				playBtn.setImage(RSResources.pauseBtn);
				playBtn.setHoverImage(RSResources.pauseBtnHover);
				paused = false;
			}
			else
			{
				playBtn.setImage(RSResources.playBtn);
				playBtn.setHoverImage(RSResources.playBtnHover);
				paused = true;
			}
		}
		
		if (component == fastBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
		}
		
		// Bottom bar buttons
		if (component == liveBtn)
		{
			if (RSFlags.mode != RSFlags.PLAY_MODE)
			{
				// Hide the furnish catalog
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					
					if (RSObjects.constructCatalog.isShown())
						RSObjects.constructCatalog.hideCatalog();
					if (RSObjects.furnishCatalog.isShown())
						RSObjects.furnishCatalog.hideCatalog();
				}
				
				ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.PLAY_MODE; // Change the game to live mode
				liveBtn.setImage(RSResources.liveBtnHover); // Change the default button image
				
				constructBtn.setImage(RSResources.constructBtn); // Reset the default button for the other mode buttons
				furnishBtn.setImage(RSResources.furnishBtn); // Reset the default button for the other mode buttons
			}
		}
		
		if (component == constructBtn)
		{
			if (RSFlags.mode != RSFlags.BUILD_MODE)
			{
				// Hide the furnish catalog
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.furnishCatalog.hideCatalog();
				}
				
				ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.BUILD_MODE; // Change the game to construct mode
				constructBtn.setImage(RSResources.constructBtnHover); // Change the default button image
				
				liveBtn.setImage(RSResources.liveBtn); // Reset the default button for the other mode buttons
				furnishBtn.setImage(RSResources.furnishBtn); // Reset the default button for the other mode buttons
			}
		}
		
		if (component == furnishBtn)
		{
			if (RSFlags.mode != RSFlags.BUY_MODE)
			{
				// Hide the construct catalog
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.constructCatalog.hideCatalog();
				}
				
				ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.BUY_MODE; // Change the game to furnish mode
				furnishBtn.setImage(RSResources.furnishBtnHover); // Change the default button image
				
				constructBtn.setImage(RSResources.constructBtn); // Reset the default button for the other mode buttons
				liveBtn.setImage(RSResources.liveBtn); // Reset the default button for the other mode buttons
			}
		}
		
		if (component == gridBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
			
			if (!RSFlags.showGrid)
			{
				RSFlags.showGrid = true;
				gridBtn.setImage(RSResources.gridBtnHover);
			}
			else
			{
				RSFlags.showGrid = false;
				gridBtn.setImage(RSResources.gridBtn);
			}
		}
		
		// If the catalog button was clicked
		if (component == catalogBtn)
		{
			ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/click.wav", AudioHandler.SFX);
			
			if (RSFlags.mode == RSFlags.BUILD_MODE) // If currently in construct mode
			{
				if (catalogOpen)
				{
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.constructCatalog.hideCatalog();
					catalogOpen = false;
				}
				else
				{
					RSObjects.constructCatalog.showCatalog();
					catalogOpen = true;
				}
			}
			else if (RSFlags.mode == RSFlags.BUY_MODE) // If currently in furnish mode
			{
				if (catalogOpen)
				{
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.furnishCatalog.hideCatalog();
					catalogOpen = false;
				}
				else
				{
					RSObjects.furnishCatalog.showCatalog();
					catalogOpen = true;
				}
			}
		}
		
		CatalogItem<RSCatalogItem> clickedItem = null;
		
		if (catalogOpen)
		{
			if (RSFlags.mode == RSFlags.BUILD_MODE) // If currently in construct mode
			{
				try
				{
					clickedItem = RSObjects.constructCatalog.checkItemClicked(component);
				}
				catch(NullPointerException e)
				{
							
				}
			}
			else if (RSFlags.mode == RSFlags.BUY_MODE) // If currently in furnish mode
			{
				try
				{
					clickedItem = RSObjects.furnishCatalog.checkItemClicked(component);
				}
				catch(NullPointerException e)
				{
							
				}
			}
			
			if (clickedItem != null)
			{
				if (RSObjects.panel.isShown())
				{
					RSObjects.panel.hidePanel();
					RSObjects.panel.showPanel(clickedItem);
				}
				else
					RSObjects.panel.showPanel(clickedItem);
			}
			
			// Buy btn
			if (RSObjects.panel.isShown())
				if (component == RSObjects.panel.getBuyBtn())
				{
					ResourceLoader.core.getAudioHandler().playSound("click", "audio/sfx/btnPress.wav", AudioHandler.SFX);
					
					catalogOpen = false;
					
					RSObjects.panel.hidePanel();
					
					if (RSObjects.constructCatalog.isShown())
						RSObjects.constructCatalog.hideCatalog();
					if (RSObjects.furnishCatalog.isShown())
						RSObjects.furnishCatalog.hideCatalog();
				}
		}
	}

	@Override
	public void guiComponentEntered(GuiComponent component)
	{
		
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		
	}

	@Override
	public void coreObjectClicked(CoreObject object, int button)
	{
		
	}

	@Override
	public void keyPressed(int keyCode)
	{
		RSPlayerController.keyPressed(keyCode);
		
		if (keyCode == RSResources.escape.getKeyCode())
		{
			if (catalogOpen)
			{
				if (RSFlags.mode == RSFlags.BUILD_MODE) // If currently in construct mode
				{
					RSObjects.constructCatalog.hideCatalog();
					catalogOpen = false;
				}
				else if (RSFlags.mode == RSFlags.BUY_MODE) // If currently in furnish mode
				{
					RSObjects.furnishCatalog.hideCatalog();
					catalogOpen = false;
				}
			}
		}
	}

	@Override
	public void keyReleased(int keyCode)
	{
		RSPlayerController.keyReleased(keyCode);
	}

	@Override
	public void mouseClicked(int button) 
	{
		
	}
}