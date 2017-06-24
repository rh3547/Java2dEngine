package com.engine.input;

/**
 * <h1>CoreKeyListener</h1>
 * This is an interface used with
 * screens to send key press events.
 * 
 * <h1>NOTE:</h1>
 * This interface isn't really set up properly...
 * just ignore it for now.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public interface CoreKeyListener
{
	/**
	 * Called when a key is pressed.
	 */
	public void keyPressed(int keyCode);
	
	/**
	 * Called when a key is released.
	 */
	public void keyReleased(int keyCode);
}
