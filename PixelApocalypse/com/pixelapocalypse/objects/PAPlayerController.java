package com.pixelapocalypse.objects;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;
import com.pixelapocalypse.main.PAResources;

public class PAPlayerController 
{
	private static boolean actionHeld = false;
	private static final int speed = 3;
	
	public static void keyPressed(int keyCode)
	{
		
		if (PAResources.up.isPressed())
		{
			PAObjects.player.setyVel(-speed);
			PAObjects.player.setMovingDir(CoreObject.NORTH);
		}
		
		if (PAResources.right.isPressed())
		{
			PAObjects.player.setxVel(speed);
			PAObjects.player.setMovingDir(CoreObject.EAST);
		}
		
		if (PAResources.down.isPressed())
		{
			PAObjects.player.setyVel(speed);
			PAObjects.player.setMovingDir(CoreObject.SOUTH);
		}
		
		if (PAResources.left.isPressed())
		{
			PAObjects.player.setxVel(-speed);
			PAObjects.player.setMovingDir(CoreObject.WEST);
		}
		
		if (PAResources.jump.isPressed() &&
		!PAObjects.player.isJumping())
		{
			//PAObjects.player.setyVel(-15);
			//PAObjects.player.setJumping(true);
		}
		
		if (PAResources.action.isPressed())
		{
			if (!actionHeld)
			{
				
			}
			
			actionHeld = true;
		}
		
		if (PAResources.one.isPressed())
			PAObjects.player.setGravity(0.3F);
		
		if (PAResources.two.isPressed())
			ResourceLoader.core.getSaveHandler().save("PA-Save1", false);
	}
	
	public static void keyReleased(int keyCode)
	{
		
		if (!PAResources.up.isPressed())
		{
			if (PAResources.right.isPressed())
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(speed);
				PAObjects.player.setMovingDir(CoreObject.EAST);
			}
			else if (PAResources.down.isPressed())
			{
				PAObjects.player.setyVel(speed);
				PAObjects.player.setMovingDir(CoreObject.SOUTH);
			}
			else if (PAResources.left.isPressed())
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(-speed);
				PAObjects.player.setMovingDir(CoreObject.WEST);
			}
			else
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		if (!PAResources.right.isPressed())
		{
			if (PAResources.down.isPressed())
			{
				PAObjects.player.setyVel(speed);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.SOUTH);
			}
			else if (PAResources.left.isPressed())
			{
				PAObjects.player.setxVel(-speed);
				PAObjects.player.setMovingDir(CoreObject.WEST);
			}
			else if (PAResources.up.isPressed())
			{
				PAObjects.player.setyVel(-speed);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.NORTH);
			}
			else
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		if (!PAResources.down.isPressed())
		{
			if (PAResources.left.isPressed())
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(-speed);
				PAObjects.player.setMovingDir(CoreObject.WEST);
			}
			else if (PAResources.up.isPressed())
			{
				PAObjects.player.setyVel(-speed);
				PAObjects.player.setMovingDir(CoreObject.NORTH);
			}
			else if (PAResources.right.isPressed())
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(speed);
				PAObjects.player.setMovingDir(CoreObject.EAST);
			}
			else
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		if (!PAResources.left.isPressed())
		{
			if (PAResources.up.isPressed())
			{
				PAObjects.player.setyVel(-speed);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.NORTH);
			}
			else if (PAResources.right.isPressed())
			{
				PAObjects.player.setxVel(speed);
				PAObjects.player.setMovingDir(CoreObject.EAST);
			}
			else if (PAResources.down.isPressed())
			{
				PAObjects.player.setyVel(speed);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.SOUTH);
			}
			else
			{
				PAObjects.player.setyVel(0);
				PAObjects.player.setxVel(0);
				PAObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		
		if (!PAResources.jump.isPressed() && 
		PAObjects.player.isJumping())
		{
			
		}
		
		if (keyCode == PAResources.action.getKeyCode())
			actionHeld = false;
	}
}
