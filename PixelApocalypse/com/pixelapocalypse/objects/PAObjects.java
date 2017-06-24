package com.pixelapocalypse.objects;

import com.engine.coreObject.CoreObject;
import com.engine.world.Camera;
import com.pixelapocalypse.main.PAIds;
import com.pixelapocalypse.main.PAResources;

public class PAObjects
{
	// Player
	public static PAPlayer player = new PAPlayer(100, 100, PAIds.player, 
			PAResources.playerImages, 
			false, PAResources.playerAnimations, "player1");
	
	// The game Camera that will follow the Player
	public static Camera camera = new Camera(0, 0, player);
	
	public static void setupObjects()
	{
		player.setCollisionType(CoreObject.COLLIDE);
		player.enablePhysics(false);
	}
}