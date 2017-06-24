package com.engine.coreObject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.engine.graphics.AnimationSet;
import com.engine.resources.ResourceLoader;

/**
 * <h1>CoreObject</h1>
 * A core object is anything in the project that
 * can move or be interacted with.  
 * Anything that requires
 * more features than the basics of a
 * core object should be a separate
 * class that extends this one.  For
 * example, Player.java or Tile.java.
 * 
 * <h3><b>Basic Functionality:</b></h3>
 *  The base functionality of a core object
 *  is as follows:
 *  <ul>
 *  <li>X and Y positions in the world</li>
 *  <li>X and Y velocities that control movement</li>
 *  <li>The ability to fall downwards in the Y direction due to gravity</li>
 *  <li>Basic collisions with other core objects and tiles</li>
 *  <li>The ability to be clicked on and perform an action</li>
 *  </ul>
 * 
 * @author Ryan Hochmuth
 *
 */
public abstract class CoreObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/*
	 * Collision Constants:
	 * These constants are used
	 * to determine the collision
	 * type of this core object.
	 */
	/**
	 * NO_COLLIDE (0): This core object
	 * doesn't check for collisions at all.
	 */
	public static final int NO_COLLIDE = 0;
	/**
	 * COLLIDE (1): This core object
	 * checks for collisions and physically
	 * collides with other core objects.
	 */
	public static final int COLLIDE = 1;
	/**
	 * CHECK_NO_COLLIDE (2): This core object
	 * checks for collisions but doesn't
	 * physically collide.
	 */
	public static final int CHECK_NO_COLLIDE = 2;
	/**
	 * COLLIDE_EXCEPT_PLAYER (3): This core object
	 * checks for collisions and will
	 * physically collide with other core objects
	 * except for the player.
	 */
	public static final int COLLIDE_EXCEPT_PLAYER = 3;
	
	/*
	 * Facing/Moving Constants:
	 */
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public static final int NORTH_EAST = 4;
	public static final int SOUTH_EAST = 5;
	public static final int SOUTH_WEST = 6;
	public static final int NORTH_WEST = 7;
	public static final int NORTH_SOUTH = 8;
	public static final int WEST_EAST = 9;
	public static final int NODIR = -1;
	
	/*
	 * Constructor Set Variables
	 */
	protected float xPos; // The x position
	protected float yPos; // The y position
	protected float xVel; // The x velocity
	protected float yVel; // The y velocity
	protected int id; // The unique id
	protected String name; // The unique name
	protected Image image; // The texture
	protected BufferedImage bImage; // The texture as a BufferedImage
	protected boolean usePhysics; // Should this CoreObject be affected by physics
	
	protected int movingDir = 0;
	protected int facingDir = 0;
	
	/*
	 * Object Properties
	 */
	protected int width;
	protected int height;
	
	/*
	 * Gravity Variables
	 */
	protected float gravity = 0.98F;
	protected boolean falling = true;
	protected boolean jumping = false;
	
	/*
	 * Collision Variables
	 */
	protected int collisionType = 0;
	protected CollisionHandler col = null;
	
	/*
	 * Graphic Variables
	 */
	protected AnimationSet aniSet = null;
	
	/**
	 * Create a new CoreObject with an initial position.
	 * @param xPos - the x position
	 * @param yPos - the y position
	 * @param id -  the unique id
	 * @param image - the graphic to render
	 * @param usePhysics - does this object react to gravity
	 */
	public CoreObject(float xPos, float yPos, int id, Image image, boolean usePhysics, String name)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = id;
		this.image = image;
		this.usePhysics = usePhysics;
		this.name = name;
		
		if (usePhysics)
			collisionType = CoreObject.COLLIDE;
		
		bImage = ResourceLoader.toBufferedImage(image);
		
		width = bImage.getWidth();
		height = bImage.getHeight();
		
		col = new CollisionHandler();
	}
	
	/**
	 * Create a new CoreObject with no specified position.
	 * @param id - the unique id
	 * @param image - the graphic to render
	 * @param usePhysics - does this object react to gravity
	 */
	public CoreObject(int id, Image image, boolean usePhysics, String name)
	{
		this.id = id;
		this.image = image;
		this.usePhysics = usePhysics;
		this.name = name;
		
		if (usePhysics)
			collisionType = CoreObject.COLLIDE;
		
		bImage = ResourceLoader.toBufferedImage(image);
		
		width = bImage.getWidth();
		height = bImage.getHeight();
		
		col = new CollisionHandler();
	}
	
	/**
	 * Create a new empty CoreObject.
	 */
	public CoreObject()
	{
		if (usePhysics)
			collisionType = CoreObject.COLLIDE;
		
		col = new CollisionHandler();
	}
	
	/**
	 * Updates this core object's fields
	 * every tick based on the core clock's
	 * fps.
	 * <br></br>
	 * This tick method updates the object's
	 * position based on it's velocity.
	 * It then tells the object to act as
	 * if it is falling if it reacts to gravity.
	 */
	public void tick()
	{
		xPos += xVel;
		yPos += yVel;
		
		fall();
	}
	
	/**
	 * Update this core object as though it was falling.
	 * An object is considered to be falling at all
	 * times it is affected by gravity.
	 * <br></br>
	 * This method changes the object's y velocity
	 * based on the gravity if it is affected
	 * by gravity.
	 * If this object collides, a collision check
	 * is also performed.
	 */
	public void fall()
	{
		if (usePhysics)
			if (falling)
				if (yVel < 20)
					yVel += gravity;
		
		if (doesCollide())
			checkCollision();
	}
	
	/**
	 * Render the graphic of this core object
	 * at it's x and y position.
	 * @param g -  the java graphics to draw with
	 */
	public void renderObject(Graphics g)
	{
		g.drawImage(image, (int)xPos, (int)yPos, null);
	}
	
	
	/**
	 * Check this object's collisions.
	 */
	public void checkCollision()
	{
		if (col != null)
		{
			col.checkBasicTileCollision(this);
			col.checkBasicObjectCollision(this);
		}
	}
	
	/**
	 * Ran if an object collides with this object.
	 * @param obj
	 */
	public void collided(CoreObject obj, int collision)
	{
		switch(collision)
		{
			case CollisionHandler.UP_LEFT_COL:
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				
				if (obj.isJumping())
				{
					obj.setxVel(1);
				}
				else
				{
					obj.setyVel(0);
				}
				break;
				
			case CollisionHandler.UP_RIGHT_COL:
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				
				if (obj.isJumping())
				{
					obj.setxVel(-1);
				}
				else
				{
					obj.setyVel(0.1F);
				}
				break;
			
			case CollisionHandler.UP_COL:
				obj.setyVel(0); // Negate all y velocity
				// Set this object to be on top of the other
				obj.setyPos(this.getyPos() - obj.getObjectHeight());
			
				// If this object was jumping, it is not jumping any more
				obj.setJumping(false);
				break;
				
			case CollisionHandler.RIGHT_COL:
				obj.setxVel(0);
				obj.setxPos(this.getxPos() + this.getObjectWidth());
				break;
				
			case CollisionHandler.DOWN_COL:
				obj.setyVel(0);
				obj.setyPos(this.getyPos() + this.getObjectHeight());
				break;
				
			case CollisionHandler.LEFT_COL:
				obj.setxVel(0);
				obj.setxPos(this.getxPos() - obj.getObjectWidth());
				break;
		}
		
		obj.altCollided(collision);
	}
	
	/**
	 * This is an alternate collision reaction to run.
	 * Used to make the object that is doing the
	 * colliding do something specific.
	 * @param collision
	 */
	public void altCollided(int collision)
	{
		switch(collision)
		{
			case CollisionHandler.UP_LEFT_COL:
				break;
				
			case CollisionHandler.UP_RIGHT_COL:
				break;
			
			case CollisionHandler.UP_COL:
				break;
				
			case CollisionHandler.RIGHT_COL:
				break;
				
			case CollisionHandler.DOWN_COL:
				break;
				
			case CollisionHandler.LEFT_COL:
				break;
		}
	}
	
	/**
	 * Called when this CoreObject is clicked.
	 */
	public void clicked()
	{
		
	}
	
	/**
	 * This is a forced update to
	 * this core object.  Typically
	 * called from a tile update
	 * to update this core objects
	 * fall state.
	 */
	public void update()
	{
		
	}
	
	/**
	 * Kill this object by removing
	 * it from the world.
	 */
	public void kill()
	{
		ResourceLoader.core.getObjectHandler().removeCoreObject(this);
	}
	
	/**
	 * Is the collision type of
	 * this core object one that
	 * should check collisions.
	 * @return Boolean
	 */
	public boolean doesCollide()
	{
		if (collisionType == CoreObject.COLLIDE
			|| collisionType == CoreObject.CHECK_NO_COLLIDE
			|| collisionType == CoreObject.COLLIDE_EXCEPT_PLAYER)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Get the overall bounding rectangle
	 * of this core object.  This is used to
	 * detect things like clicks, not collisions.
	 * @return Rectangle - The core object's bounding Rectangle
	 */
	public Rectangle getRect()
	{
		return new Rectangle((int)xPos, (int)yPos, bImage.getWidth(), bImage.getHeight());
	}
	
	/**
	 * Get the top collision bounds of this CoreObject.
	 * @return Rectangle -  the top bounds of this CoreObject
	 */
	public Rectangle getTopBounds()
	{
		return new Rectangle((int)xPos + 6, (int)yPos, width - 12, 10);
	}
	
	/**
	 * Get the bottom collision bounds of this CoreObject.
	 * @return Rectangle - the bottom bounds of this CoreObject
	 */
	public Rectangle getBottomBounds()
	{
		return new Rectangle((int)xPos, (int)yPos + height - 10, width, 10);
	}
	
	/**
	 * Get the left collision bounds of this CoreObject.
	 * @return Rectangle -  the left bounds of this CoreObject
	 */
	public Rectangle getLeftBounds()
	{
		return new Rectangle((int)xPos, (int)yPos, 6, height - 10);
	}
	
	/**
	 * Get the right collision bounds of this CoreObject.
	 * @return Rectangle - the right bounds of this CoreObject
	 */
	public Rectangle getRightBounds()
	{
		return new Rectangle((int)xPos + width - 6, (int)yPos, 6, height - 10);
	}

	/**
	 * Get the x position of this CoreObject.
	 * @return xPos - the x position
	 */
	public float getxPos()
	{
		return xPos;
	}

	/**
	 * Set the x position of this CoreObject.
	 * @param xPos - the x position
	 */
	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}

	/**
	 * Get the y position of this CoreObject.
	 * @return yPos - the y position
	 */
	public float getyPos()
	{
		return yPos;
	}

	/**
	 * Set the y position of this CoreObject.
	 * @param yPos - the y position
	 */
	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}

	/**
	 * Get the x velocity of this CoreObject.
	 * @return xVel - the x velocity
	 */
	public float getxVel()
	{
		return xVel;
	}

	/**
	 * Set the x velocity of this CoreObject.
	 * @param xVel - the x velocity
	 */
	public void setxVel(float xVel)
	{
		this.xVel = xVel;
	}

	/** Get the y velocity of this GameObject.
	 * @return yVel - the y velocity
	 */
	public float getyVel()
	{
		return yVel;
	}

	/**
	 * Set the y velocity of this GameObject.
	 * @param yVel - the y velocity
	 */
	public void setyVel(float yVel)
	{
		this.yVel = yVel;
	}

	/**
	 * Get the id of this GameObject.
	 * @return id - this GameObject's unique id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Get the image of this GameObject.
	 * @return image - the texture of this GameObject
	 */
	public Image getImage()
	{
		return image;
	}

	/**
	 * Set the image of this GameObject.
	 * @param image - the texture of this GameObject
	 */
	public void setImage(Image image)
	{
		this.image = image;
		this.bImage = ResourceLoader.toBufferedImage(this.image);
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
	}

	/**
	 * Get whether this CoreObject is affected by physics or not.
	 * @return usePhysics - whether this CoreObject is affected by physics
	 */
	public boolean doesUsePhysics()
	{
		return usePhysics;
	}

	/**
	 * Set if this CoreObject should be affected by physics.
	 * @param usePhysics - the boolean to determine if physics are used
	 */
	public void enablePhysics(boolean usePhysics)
	{
		this.usePhysics = usePhysics;
	}

	/**
	 * Is this core object currently falling?
	 * @return falling - true if the object is falling
	 */
	public boolean isFalling()
	{
		return falling;
	}

	/**
	 * Set whether or not this core object is falling.
	 * @param falling -  the boolean to set
	 */
	public void setFalling(boolean falling)
	{
		this.falling = falling;
	}

	/**
	 * Get the collision type of this object.
	 * @return collisionType - the type of collision
	 */
	public int getCollisionType()
	{
		return collisionType;
	}

	/**
	 * Set the collision type of this object.
	 * @param collisionType - the integer collision type
	 */
	public void setCollisionType(int collisionType)
	{
		this.collisionType = collisionType;
	}

	/**
	 * Get this object's collision handler.
	 * @return col - this object's collision handler
	 */
	public CollisionHandler getCollisionHandler() 
	{
		return col;
	}

	/**
	 * Set this object's collision handler.
	 * @param col - a CollisionHandler
	 */
	public void setCollisionHandler(CollisionHandler col) 
	{
		this.col = col;
	}

	/**
	 * Get this CoreObject's width.
	 * @return width - the width of this core object
	 */
	public int getObjectWidth()
	{
		return width;
	}

	/**
	 * Set this CoreObject's width.
	 * @param width - the width to set
	 */
	public void setObjectWidth(int width)
	{
		this.width = width;
	}

	/**
	 * Get this CoreObject's height.
	 * @return height - the height of this core object
	 */
	public int getObjectHeight()
	{
		return height;
	}

	/**
	 * Set this CoreObject's height.
	 * @param height - the height to set
	 */
	public void setObjectHeight(int height)
	{
		this.height = height;
	}

	/**
	 * Get this CoreObject's gravity.
	 * @return gravity - the gravity level affecting this core object
	 */
	public float getGravity()
	{
		return gravity;
	}

	/**
	 * Set this CoreObject's gravity.
	 * @param gravity -  the gravity to set
	 */
	public void setGravity(float gravity)
	{
		this.gravity = gravity;
	}

	/**
	 * Get whether or not this GameObject is jumping.
	 * @return jumping - true if this object is jumping
	 */
	public boolean isJumping()
	{
		return jumping;
	}

	/**
	 * Set whether or not this GameObject is jumping.
	 * @param jumping - the boolean to set
	 */
	public void setJumping(boolean jumping)
	{
		this.jumping = jumping;
	}

	/**
	 * @return the aniSet
	 */
	public AnimationSet getAniSet() 
	{
		return aniSet;
	}

	/**
	 * @param aniSet the aniSet to set
	 */
	public void setAniSet(AnimationSet aniSet) 
	{
		this.aniSet = aniSet;
	}

	/**
	 * @return the movingDir
	 */
	public int getMovingDir()
	{
		return movingDir;
	}

	/**
	 * @param movingDir the movingDir to set
	 */
	public void setMovingDir(int movingDir)
	{
		this.movingDir = movingDir;
	}

	/**
	 * @return the facingDir
	 */
	public int getFacingDir()
	{
		return facingDir;
	}

	/**
	 * @param facingDir the facingDir to set
	 */
	public void setFacingDir(int facingDir)
	{
		this.facingDir = facingDir;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}