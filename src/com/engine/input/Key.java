package com.engine.input;

/**
 * <h1>Key</h1>
 * A key represents a single key on a keyboard.
 * Keys are used in projects to check specific
 * key states.  A key is generally defined in
 * a project's resource class.  Keys are used
 * for controlling aspects of a project like
 * a player's position or something along
 * those lines.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class Key
{
	private boolean pressed = false; // Is this key pressed
	private int keyCode; // The keyCode of this key
	private int timesPressed = 0; // How many times the key was pressed
	
	/**
	 * Creates a new Key object and sets it's keyCode.
	 * @param keyCode
	 */
	public Key(int keyCode)
	{
		this.keyCode = keyCode;
	}
	
	/**
	 * Get whether or not this key is pressed.
	 * @return
	 */
	public boolean isPressed()
	{
		return pressed;
	}
	
	/**
	 * Change whether or not this key is pressed. 
	 * @param isPressed
	 */
	public void toggle(boolean isPressed)
	{
		this.pressed = isPressed;
		if (isPressed) timesPressed++;
	}
	
	/**
	 * Get this key's key code.
	 * @return
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
	/**
	 * Set this key's key code.
	 * @param keyCode
	 */
	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
	}
	
	/**
	 * Get the number of times this Key
	 * has been pressed.
	 * @return timesPressed
	 */
	public int getTimesPressed()
	{
		return timesPressed;
	}
}