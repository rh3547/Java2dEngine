package com.engine.resources;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

/**
 * <h1>CoreFont</h1>
 * To use a custom font with a project
 * it must be registered as a CoreFont.
 * To register a font, do so through
 * the registerFont() method in
 * ResourceLoader.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class CoreFont
{
	private String fontPath; // The local path to the font file
	private String fontName; // The name of the font as it appears in the font file
	private int fontSize; // The size of the font
	private Font font; // The actual font object
	
	/**
	 * Create a new CoreFont using the Font at the fontPath
	 * as the Font to register.
	 * @param fontPath
	 * @param fontName
	 * @param fontSize
	 * @param font
	 */
	public CoreFont(String fontPath, String fontName, int fontSize, Font font, boolean isBold)
	{
		this.fontPath = fontPath;
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.font = font;
		
		registerFont(fontPath);
		
		if (isBold)
			this.font = new Font(fontName, Font.BOLD, fontSize);
		else
			this.font = new Font(fontName, Font.PLAIN, fontSize);
	}
	
	/**
	 * Registers a new Font using the given fontPath.
	 * @param fontPath
	 */
	private void registerFont(String fontPath)
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try
		{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Get the font path.
	 * @return fontPath
	 */
	public String getFontPath()
	{
		return fontPath;
	}

	/**
	 * Set the font path.
	 * @param fontPath
	 */
	public void setFontPath(String fontPath)
	{
		this.fontPath = fontPath;
	}

	/**
	 * Get the font name.
	 * @return fontName
	 */
	public String getFontName()
	{
		return fontName;
	}

	/**
	 * Set the font name.
	 * @param fontName
	 */
	public void setFontName(String fontName)
	{
		this.fontName = fontName;
	}

	/**
	 * Get the font size.
	 * @return fontSize
	 */
	public int getFontSize()
	{
		return fontSize;
	}

	/**
	 * Set the font size.
	 * @param fontSize
	 */
	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

	/**
	 * Get the font.
	 * @return font
	 */
	public Font getFont()
	{
		return font;
	}

	/**
	 * Set the font.
	 * @param font
	 */
	public void setFont(Font font)
	{
		this.font = font;
	}
}
