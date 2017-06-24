package com.engine.resources;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import com.engine.main.Core;

/**
 * <h1>ResourceLoadert</h1>
 * ResourceLoader is where all of a project's resources
 * are loaded such as graphics, fonts, etc.
 * This class should be extended in a custom class
 * made specifically for your project.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public abstract class ResourceLoader
{
	public static Core core; // The core that this is a part of
	
	public static Robot robot; // A "robot" to control mouse positioning
	
	// The operating system being used
	private String OS = "generic";
	
	@SuppressWarnings("static-access")
	public ResourceLoader(Core core)
	{
		this.core = core;
		
		try
		{
			robot = new Robot();
		} 
		catch (AWTException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Load all of the project's resources.
	 * This method calls all of ResourceLoader's 
	 * sub-methods that load specific project
	 * resources.
	 */
	public void loadResources()
	{
		loadFonts();
		loadBackgrounds();
		loadGuiImages();
		loadSpriteImages();
		loadOtherImages();
		loadMisc();
		postLoad();
		
		core.setLoading(false);
		core.getScreenHandler().setProjectLoading(false);
	}
	
	/*
	 * System
	 */
	/**
	 * Detect and obtain the operating 
	 * system in use.  
	 * Returns as a String.
	 * @return OS
	 */
	public String getOS()
	{
		this.OS = System.getProperty("os.name", "generic").toLowerCase();
		
		if (OS.startsWith("windows")) 
		{
			this.OS = "windows";
		} 
		else if (OS.startsWith("linux")) 
		{
			this.OS = "linux";
		} 
		else if (OS.startsWith("sunos")) 
		{
			this.OS = "solaris";
		} 
		else if (OS.startsWith("mac") || OS.startsWith("darwin")) 
		{
			this.OS = "mac";
		} 
		else if (OS.startsWith("aix")) 
		{
			this.OS = "aix";
		} 
		else 
			this.OS = "generic";
		
		return OS;
	}
	
	/*
	 * Fonts
	 */
	/**
	 * Put every font registration in here.
	 * To register a font as a CoreFont, 
	 * (which must be done to work with the engine)
	 * call the registerFont() method in
	 * ResourceLoader.
	 */
	public abstract void loadFonts();
	
	/**
	 * Register a font as a CoreFont with the 
	 * project using the font at
	 * a file path as the font to register.
	 * @param fontPath
	 * @param fontName
	 * @param fontSize
	 * @return Font - the java font object to use for setting text fonts
	 */
	public Font registerFont(String fontPath, String fontName, int fontSize, boolean isBold)
	{
		Font font = new CoreFont(core.getRespath() + fontPath, fontName, fontSize, null, isBold).getFont();
		
		return font;
	}
	
	/*
	 * Backgrounds
	 */
	/**
	 * Put every background registration in here.
	 */
	public abstract void loadBackgrounds();
	
	/*
	 * Gui Images
	 */
	/**
	 * Put every gui image registration in here.
	 */
	public abstract void loadGuiImages();
	
	/*
	 * Sprite Images
	 */
	/**
	 * Put every sprite image registration in here.
	 */
	public abstract void loadSpriteImages();
	
	/*
	 * Other Images
	 */
	/**
	 * Put every random image registration in here.
	 */
	public abstract void loadOtherImages();
	
	/*
	 * Miscellaneous Loading
	 */
	/**
	 * Load other random things here.
	 */
	public abstract void loadMisc();
	
	/*
	 * Post Load
	 */
	/**
	 * Called after everything loads.
	 */
	public abstract void postLoad();
	
	/*
	 * Keys
	 */
	/**
	 * Checks the project's configured keys to see
	 * if any of them were pressed or released
	 * based on isPressed.  If a Key changed, it
	 * updates it accordingly.
	 * @param keyCode
	 * @param isPressed
	 */
	public abstract void checkKey(int keyCode, boolean isPressed);
	
	/**
	 * Converts a given Image into a BufferedImage.
	 *
	 * @param img - The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D g2d = bimage.createGraphics();
	    g2d.drawImage(img, 0, 0, null);
	    g2d.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	/**
	 * <h1>Best Method</h1>
	 * Scales an image to the given size.
	 * Uses the ImgSclr library.
	 * @param image
	 * @param width
	 * @param height
	 * @return the scaled image as an Image
	 */
	public static Image scaleImage(Image image, float width, float height)
	{
		return Scalr.resize(ResourceLoader.toBufferedImage(image), Method.BALANCED, (int)width, (int)height);
	}
	
	/**
	 * <h1>Simple Method</h1>
	 * Scales an image to the given size.
	 * Does not use the ImgSclr library.
	 * @param originalImage
	 * @param width
	 * @param height
	 * @return the scaled image as a BufferedImage
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height)
	{
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
	 
		return resizedImage;
	}
	 
	/**
	 * <h1>Good Method</h1>
	 * Scales an image to the given size and sets quality rendering hints.
	 * Does not use the ImgSclr library.
	 * @param originalImage
	 * @param width
	 * @param height
	 * @return the scaled image as a BufferedImage
	 */
    public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int width, int height)
    {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);
	 
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	 
		return resizedImage;
   }	
    
   /**
    * Get the graphics configuration for the screen.
    * @return GraphicsConfiguration
    */
   public static GraphicsConfiguration getGraphicsConfiguration() 
   {
       return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
   }

   /**
    * Create a compatible image.
    * @param width
    * @param height
    * @param transparency
    * @return BufferedImage
    */
   public static BufferedImage createCompatibleImage(int width, int height, int transparency) 
   {
       BufferedImage image = getGraphicsConfiguration().createCompatibleImage(width, height, transparency);
       image.coerceData(true);
       return image;
   }

   /**
    * Applies various quality rendering hints to the given Graphics2D.
    * @param g2d
    */
   public static void applyQualityRenderingHints(Graphics2D g2d) 
   {
       g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
       g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
       g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
       g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
       g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
       g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
   }

   /**
    * Generate a mask image based on the given
    * image with the given color and transparency.
    * @param imgSource - the original image
    * @param color - the color of the new mask
    * @param alpha - the transparency of the new mask
    * @return BufferedImage - the new image mask
    */
   public static BufferedImage generateMask(BufferedImage imgSource, Color color, float alpha) 
   {
       int imgWidth = imgSource.getWidth();
       int imgHeight = imgSource.getHeight();

       BufferedImage imgMask = createCompatibleImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);
       Graphics2D g2 = imgMask.createGraphics();
       applyQualityRenderingHints(g2);

       g2.drawImage(imgSource, 0, 0, null);
       g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, alpha));
       g2.setColor(color);

       g2.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight());
       g2.dispose();

       return imgMask;
   }

   /**
    * Create a new image that is a combination
    * of the given master image and the given
    * tint image.
    * @param master - the master image
    * @param tint - the tint image to be drawn over the master image
    * @return BufferedImage - the tinted image
    */
   public static BufferedImage tint(BufferedImage master, BufferedImage tint) 
   {
       int imgWidth = master.getWidth();
       int imgHeight = master.getHeight();

       BufferedImage tinted = createCompatibleImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);
       Graphics2D g2 = tinted.createGraphics();
       applyQualityRenderingHints(g2);
       g2.drawImage(master, 0, 0, null);
       g2.drawImage(tint, 0, 0, null);
       g2.dispose();

       return tinted;
   }
}