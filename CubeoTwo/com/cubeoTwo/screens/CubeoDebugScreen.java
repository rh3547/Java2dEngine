package com.cubeoTwo.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

import com.cubeoTwo.main.CubeoTwoResources;
import com.cubeoTwo.objects.CubeoTwoObjects;
import com.engine.graphics.DebugScreen;
import com.engine.resources.ResourceLoader;

/**
 * The DebugScreen specific to CubeoTwo.
 * 
 * @author Ryan Hochmuth
 */
public class CubeoDebugScreen extends DebugScreen
{
	private DecimalFormat dec = new DecimalFormat("########");
	
	@Override
	public void showDebug(Graphics g)
	{
		// Debug:
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimated);
		g.drawString("Debug:", 20, 30);
		
		// Version: x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("Version: ", 20, 60);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.setColor(Color.cyan);
		g.drawString("Cubeo Two Alpha v0.1", 100, 60);
		
		// FPS: x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("FPS: ", 20, 90);
		g.setColor(Color.green);
		g.drawString(String.valueOf(ResourceLoader.core.getCoreClock().getTopFrames()), 60, 90);
		
		// TPS: x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("TPS: ", 20, 120);
		g.setColor(Color.green);
		g.drawString(String.valueOf(ResourceLoader.core.getCoreClock().getTopTicks()), 60, 120);
		
		// Pos X: x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("Pos X: ", 20, 170);
		g.setColor(Color.green);
		g.drawString(String.valueOf(dec.format(CubeoTwoObjects.player.getxPos())), 80, 170);
		
		// Pos Y: x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("Pos Y: ", 20, 200);
		g.setColor(Color.green);
		g.drawString(String.valueOf(dec.format(CubeoTwoObjects.player.getyPos())), 80, 200);
		
		// Tiles (Rendered/Total): x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("Tiles (Rendered/Total): ", 20, 250);
		g.setColor(Color.green);
		g.drawString(String.valueOf(ResourceLoader.core.getWorldHandler().getTopTilesLoaded()), 240, 250);
		g.drawString(" / ", 300, 250);
		g.drawString(String.valueOf(ResourceLoader.core.getWorldHandler().getTopTotalTiles()), 350, 250);
		
		// GameObjects (Rendered/Total): x
		g.setColor(Color.white);
		g.setFont(CubeoTwoResources.fontAnimatedSmall);
		g.drawString("Objects (Rendered/Total): ", 20, 300);
		g.setColor(Color.green);
		g.drawString(String.valueOf(ResourceLoader.core.getObjectHandler().getTopObjLoaded()), 270, 300);
		g.drawString(" / ", 330, 300);
		g.drawString(String.valueOf(ResourceLoader.core.getObjectHandler().getTopTotalObj()), 380, 300);
	}
}