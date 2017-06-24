package com.engine.world;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <h1>WORK IN PROGRESS</h1>
 * --------------------
 * <h1>LightHandler</h1>
 * The LightHandler class contains a list of every
 * Light currently in the project.  Those Lights
 * can all be ticked and rendered through here.
 * <br>
 * <h1>Light Rendering Process:</h1>
 * All of the Light objects that are placed around
 * the World are all drawn to a master image called
 * the light map.  To draw the lighting on the screen,
 * the light map must be generated first and then that
 * light map can be drawn in a normal render method.
 * <b>NOTE:</b> If a new light is added to the list,
 * it will not show on the light map until it is
 * re-generated.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class LightHandler // TODO
{
	private static ArrayList<Light> lights = new ArrayList<Light>(); // List of all the Lights
	
	private BufferedImage lightMap = null; // The image that Lights are drawn to
	private Graphics2D light2d; // The Graphics2D used to draw the Lights
	
	private int globalLightValue = 0; // The "brightness" of the world
	
	/**
	 * Create a new LightHandler.
	 */
	public LightHandler()
	{
		this.globalLightValue = 0;
	}
	
	/**
	 * Generate a light map image from all of the
	 * Lights.
	 */
	public void generateLightMap()
	{
		lightMap = new BufferedImage(232 * 32, 232 * 32, BufferedImage.TYPE_INT_ARGB);
		light2d = (Graphics2D)lightMap.getGraphics();
		
		light2d.setColor(new Color(0, 0, 0, globalLightValue)); // 240
		light2d.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
		light2d.setComposite(AlphaComposite.DstOut);
		
		for(Light light : lights) 
		{
			light.draw(light2d);
		}
	}
	
	/**
	 * Adds the given Light to the complete list.
	 * @param light
	 */
	public void addLight(Light light)
	{
		lights.add(light);
	}
	
	/**
	 * Get the complete list of Lights.
	 * @return
	 */
	public ArrayList<Light> getLights()
	{
		return lights;
	}
	
	/**
	 * Get the global light value.
	 * @return globalLightValue
	 */
	public int getGlobalLightValue()
	{
		return globalLightValue;
	}
	
	/**
	 * Set the global light value.
	 * @param value
	 */
	public void setGlobalLightValue(int value)
	{
		this.globalLightValue = value;
	}

	/**
	 * Get the project's generated light map.
	 * If a light map has not been generated,
	 * this will throw an exception.
	 * @return the light map
	 */
	public BufferedImage getLightMap() 
	{
		if (lightMap != null)
			return lightMap;
		
		return null;
	}

	/**
	 * Set the project's generated light map.
	 * @param lightmap the light map to set
	 */
	public void setLightMap(BufferedImage lightmap) 
	{
		this.lightMap = lightmap;
	}
}
