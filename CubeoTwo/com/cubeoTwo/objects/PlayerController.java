package com.cubeoTwo.objects;

import com.cubeoTwo.main.CubeoTwoResources;
import com.engine.audio.AudioHandler;
import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;

public class PlayerController 
{
	private static boolean actionHeld = false;
	
	public static void keyPressed(int keyCode)
	{
		int speed = 5;
		
		// Go right
		if (CubeoTwoResources.right.isPressed())
		{
			CubeoTwoObjects.player.setxVel(speed);
			CubeoTwoObjects.player.setMovingDir(CoreObject.EAST);
		}
		// Go left
		if (CubeoTwoResources.left.isPressed())
		{
			CubeoTwoObjects.player.setxVel(-speed);
			CubeoTwoObjects.player.setMovingDir(CoreObject.WEST);
		}
		
		// Jump
		if (CubeoTwoResources.jump.isPressed() &&
		!CubeoTwoObjects.player.isJumping())
		{
			CubeoTwoObjects.player.setyVel(-15);
			CubeoTwoObjects.player.setJumping(true);
			ResourceLoader.core.getAudioHandler().playSound("jump", "audio/jump.wav", AudioHandler.SFX);
		}

		if (CubeoTwoResources.action.isPressed())
		{
			if (!actionHeld)
			{
				/*
				ResourceLoader.game.getObjectHandler()
						.addGameObject(new JumpBoostPickup(ResourceLoader.game.getMouseX(),
						ResourceLoader.game.getMouseY(),
						CubeoTwoObjects.jumpBoostPickup));
				*/
				
				ResourceLoader.core.getObjectHandler().addCoreObject(new BasicEnemy(ResourceLoader.core.getWorldMouseX(),
						ResourceLoader.core.getWorldMouseY(), CubeoTwoObjects.basicEnemy));
			}
			
			actionHeld = true;
		}
		
		if (CubeoTwoResources.one.isPressed())
			CubeoTwoObjects.player.setGravity(0.3F);
		
		if (CubeoTwoResources.two.isPressed())
			ResourceLoader.core.getLightHandler().generateLightMap();
		
		if (CubeoTwoResources.four.isPressed())
			if (ResourceLoader.core.getAudioHandler().getMusicVolume() > -50.0F)
				ResourceLoader.core.getAudioHandler().setMusicVolume(
						ResourceLoader.core.getAudioHandler().getMusicVolume() - 1.0F);
		
		if (CubeoTwoResources.five.isPressed())
			if (ResourceLoader.core.getAudioHandler().getMusicVolume() < 6.0F)
				ResourceLoader.core.getAudioHandler().setMusicVolume(
						ResourceLoader.core.getAudioHandler().getMusicVolume() + 1.0F);
	}
	
	public static void keyReleased(int keyCode)
	{
		int speed = 5;
		
		// If left is pressed but right isn't, go left
		if (CubeoTwoResources.left.isPressed() &&
		!CubeoTwoResources.right.isPressed())
		{
			CubeoTwoObjects.player.setxVel(-speed);
			CubeoTwoObjects.player.setMovingDir(CoreObject.WEST);
		}
		// If left isn't pressed but right is, go right
		if (!CubeoTwoResources.left.isPressed() &&
		CubeoTwoResources.right.isPressed())
		{
			CubeoTwoObjects.player.setxVel(speed);
			CubeoTwoObjects.player.setMovingDir(CoreObject.EAST);
		}
		// If both left and right aren't pressed, go nowhere
		if (!CubeoTwoResources.left.isPressed() &&
		!CubeoTwoResources.right.isPressed())
		{
			CubeoTwoObjects.player.setxVel(0);
			CubeoTwoObjects.player.setMovingDir(CoreObject.NODIR);
		}
		// If both left and right are pressed
		if (CubeoTwoResources.left.isPressed() &&
		CubeoTwoResources.right.isPressed())
		{
			CubeoTwoObjects.player.setxVel(0);
			CubeoTwoObjects.player.setMovingDir(CoreObject.WEST_EAST);
		}
		
		if (!CubeoTwoResources.jump.isPressed() && 
		CubeoTwoObjects.player.isJumping())
		{
			
		}
		
		if (keyCode == CubeoTwoResources.action.getKeyCode())
			actionHeld = false;
	}
}
