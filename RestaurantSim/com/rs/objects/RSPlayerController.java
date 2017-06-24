package com.rs.objects;

import com.engine.coreObject.CoreObject;
import com.rs.main.RSFlags;
import com.rs.main.RSResources;

public class RSPlayerController
{
	private static boolean actionHeld = false;
	private static final int speed = 8;
	
	public static void keyPressed(int keyCode)
	{
		
		if (RSResources.up.isPressed())
		{
			RSObjects.player.setyVel(-speed);
			RSObjects.player.setMovingDir(CoreObject.NORTH);
		}
		
		if (RSResources.right.isPressed())
		{
			RSObjects.player.setxVel(speed);
			RSObjects.player.setMovingDir(CoreObject.EAST);
		}
		
		if (RSResources.down.isPressed())
		{
			RSObjects.player.setyVel(speed);
			RSObjects.player.setMovingDir(CoreObject.SOUTH);
		}
		
		if (RSResources.left.isPressed())
		{
			RSObjects.player.setxVel(-speed);
			RSObjects.player.setMovingDir(CoreObject.WEST);
		}
		
		if (RSResources.jump.isPressed() &&
		!RSObjects.player.isJumping())
		{
			//RSObjects.player.setyVel(-15);
			//RSObjects.player.setJumping(true);
		}
		
		if (RSResources.action.isPressed())
		{
			if (!actionHeld)
			{
				
			}
			
			actionHeld = true;
		}
		
		if (RSResources.one.isPressed())
			RSFlags.mode = RSFlags.PLAY_MODE;
		if (RSResources.two.isPressed())
			RSFlags.mode = RSFlags.BUILD_MODE;
		if (RSResources.three.isPressed())
			RSFlags.mode = RSFlags.BUY_MODE;
	}
	
	public static void keyReleased(int keyCode)
	{
		
		if (!RSResources.up.isPressed())
		{
			if (RSResources.right.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(speed);
				RSObjects.player.setMovingDir(CoreObject.EAST);
			}
			else if (RSResources.down.isPressed())
			{
				RSObjects.player.setyVel(speed);
				RSObjects.player.setMovingDir(CoreObject.SOUTH);
			}
			else if (RSResources.left.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(-speed);
				RSObjects.player.setMovingDir(CoreObject.WEST);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		if (!RSResources.right.isPressed())
		{
			if (RSResources.down.isPressed())
			{
				RSObjects.player.setyVel(speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.SOUTH);
			}
			else if (RSResources.left.isPressed())
			{
				RSObjects.player.setxVel(-speed);
				RSObjects.player.setMovingDir(CoreObject.WEST);
			}
			else if (RSResources.up.isPressed())
			{
				RSObjects.player.setyVel(-speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.NORTH);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		if (!RSResources.down.isPressed())
		{
			if (RSResources.left.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(-speed);
				RSObjects.player.setMovingDir(CoreObject.WEST);
			}
			else if (RSResources.up.isPressed())
			{
				RSObjects.player.setyVel(-speed);
				RSObjects.player.setMovingDir(CoreObject.NORTH);
			}
			else if (RSResources.right.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(speed);
				RSObjects.player.setMovingDir(CoreObject.EAST);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		if (!RSResources.left.isPressed())
		{
			if (RSResources.up.isPressed())
			{
				RSObjects.player.setyVel(-speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.NORTH);
			}
			else if (RSResources.right.isPressed())
			{
				RSObjects.player.setxVel(speed);
				RSObjects.player.setMovingDir(CoreObject.EAST);
			}
			else if (RSResources.down.isPressed())
			{
				RSObjects.player.setyVel(speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.SOUTH);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(CoreObject.NODIR);
			}
		}
		
		if (!RSResources.jump.isPressed() && 
				RSObjects.player.isJumping())
		{
			
		}
		
		if (keyCode == RSResources.action.getKeyCode())
			actionHeld = false;
	}
}