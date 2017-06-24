package com.engine.graphics;

import java.io.Serializable;

/**
 * <h1>AnimationSet</h1>
 * An animation set is a set of
 * animations combined into a single
 * array for ease of use.
 * 
 * @author Ryan Hochmuth
 *
 */
public class AnimationSet implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Animation[] animations; // The animations in this set
	private int numAni = 0; // The number of animations in this set
	
	/**
	 * Create a new animation set with the given initial size.
	 * @param size
	 */
	public AnimationSet(int size)
	{
		this.animations = new Animation[size];
	}
	
	/**
	 * Add a new animation to this set.
	 * @param ani - the animation to add
	 */
	public void addAnimation(Animation ani)
	{
		if ((numAni + 1) > animations.length) // If the array isn't large enough
		{
			/*
			 * Create a new bigger array, copy the
			 * old array into it, and overwrite
			 * the old one.
			 */
			Animation[] newAni = new Animation[numAni + 1];
			System.arraycopy(animations, 0, newAni, 0, numAni);
			newAni[numAni] = ani;
			animations = newAni;
			numAni++;
		}
		else // If the array is big enough, just add it
		{
			animations[numAni] = ani;
			numAni++;
		}
	}
	
	/**
	 * Get the animation in this set at the specified index.
	 * @param index - the index of the requested animation
	 * @return the animation at the given index
	 */
	public Animation getAnimation(int index)
	{
		if (index > numAni - 1)
			throw new IndexOutOfBoundsException("There is no animation at that index");
		
		return animations[index];
	}
}