package com.engine.entity;

import java.awt.Image;

import com.engine.coreObject.CoreObject;
import com.engine.resources.ResourceLoader;

/**
 * <h1>Entity</h1>
 * An entity is a core object that is
 * typically "alive".  An entity can be
 * a player, an ally, an enemy, etc.
 *  
 * <h3><b>Basic Functionality:</b></h3>
 *  The entity class is a bridge for entities
 *  between it and a core object.  Most
 *  of an entity's functionality
 *  comes from the Core Object class.
 *  This class contains "special"
 *  parameters specific to entities.
 *  Such as entity type (friendly, enemy),
 *  and ai data.
 * 
 * <br></br>
 * @author Ryan Hochmuth
 *
 */
public class Entity extends CoreObject
{
	/***************************
	 * Entity Types
	 **************************/
	public static final int PLAYER = 0;
	public static final int FRIENDLY_NPC = 1;
	public static final int ENEMY_NPC = 2;
	
	/*--------------------------
	 * Entity Specific Fields
	 -------------------------*/
	private int entityType;
	
	protected boolean entityDead = false;
	protected int health = 10;
	
	protected Image[] images;
	
	protected boolean[] flags = new boolean[10];
	
	/**
	 * Create a new Entity with an initial position.
	 * @param xPos - the x position
	 * @param yPos - the y position
	 * @param id -  the unique id
	 * @param images - the graphics to render
	 * @param usePhysics - does this entity react to gravity
	 * @param entityType - the type of this entity
	 */
	public Entity(float xPos, float yPos, int id, Image[] images, boolean usePhysics, int entityType, String name)
	{
		super(xPos, yPos, id, images[0], usePhysics, name);
		
		this.images = images;
		this.entityType = entityType;
	}
	
	/**
	 * Create a new entity with no specified position.
	 * @param id - the unique id
	 * @param image - the graphic to render
	 * @param usePhysics - does this entity react to gravity
	 * @param entityType - the type of this entity
	 */
	public Entity(int id, Image[] images, boolean usePhysics, int entityType, String name)
	{
		super(id, images[0], usePhysics, name);
		
		this.images = images;
		this.entityType = entityType;
	}
	
	/**
	 * Create a new entity based on another entity.
	 * @param xPos
	 * @param yPos
	 * @param entity
	 */
	public Entity(float xPos, float yPos, Entity entity)
	{
		super(xPos, yPos, entity.getId(), entity.getImage(), entity.doesUsePhysics(), entity.getName());
		
		this.images = entity.getImages();
	}
	
	/**
	 * Create a new empty entity.
	 * @param entityType - the type of this entity
	 */
	public Entity(int entityType)
	{
		super();
		
		this.entityType = entityType;
	}
	
	/**
	 * Called when this entity dies.
	 */
	protected void die()
	{
		if (this.isEntityDead())
		{
			entityDead = true;
			ResourceLoader.core.getObjectHandler().removeCoreObject(this);
		}
	}

	/**
	 * @return the entityType
	 */
	public int getEntityType()
	{
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(int entityType)
	{
		this.entityType = entityType;
	}

	/**
	 * @return the entityDead
	 */
	public boolean isEntityDead() 
	{
		return entityDead;
	}

	/**
	 * @param entityDead the entityDead to set
	 */
	public void setEntityDead(boolean entityDead) 
	{
		this.entityDead = entityDead;
	}

	/**
	 * @return the health
	 */
	public int getHealth() 
	{
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) 
	{
		this.health = health;
	}

	/**
	 * @return the images
	 */
	public Image[] getImages()
	{
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(Image[] images)
	{
		this.images = images;
	}

	/**
	 * @return the flags
	 */
	public boolean[] getFlags()
	{
		return flags;
	}

	/**
	 * @param flags the flags to set
	 */
	public void setFlags(boolean[] flags)
	{
		this.flags = flags;
	}
}