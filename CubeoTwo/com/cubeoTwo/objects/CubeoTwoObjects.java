package com.cubeoTwo.objects;

import com.cubeoTwo.main.CubeoTwoResources;
import com.cubeoTwo.pickups.JumpBoostPickup;
import com.engine.coreObject.CoreObject;
import com.engine.entity.Entity;
import com.engine.world.Camera;

/**
 * All of CubeoTwo's GameObjects are
 * created and referenced here.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CubeoTwoObjects
{
	// The controlled Player of the game
	public static CubeoPlayer player = new CubeoPlayer(100, 100, 
			ObjectIds.playerId, CubeoTwoResources.playerImages, true,
			CubeoTwoResources.playerAnimations, "player1");
	
	// A basic wraith enemy
	public static BasicEnemy basicEnemy = new BasicEnemy(ObjectIds.basicEnemyId, 
			CubeoTwoResources.wraithImages, 
			true, 
			Entity.ENEMY_NPC,
			CubeoTwoResources.wraithAnimations, "wraith");
	
	// The game Camera that will follow the Player
	public static Camera camera = new Camera(0, 0, player);
	
	// Pickups
	public static JumpBoostPickup jumpBoostPickup = new JumpBoostPickup(ObjectIds.healthPickup, 
			CubeoTwoResources.jumpBoostPickup, true, CoreObject.COLLIDE, "jumpBoost");
	
	public static void loadObjects()
	{
		
	}
}