package com.engine.input;

/**
 * <h1>EngineMouseListener</h1>
 * This is an interface used with
 * screens to send mouse click events.
 * 
 * <h1>NOTE:</h1>
 * This interface isn't really set up properly...
 * just ignore it for now.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public interface EngineMouseListener
{
	/**
	 * Called when the mouse is clicked.
	 * @param button
	 */
	public void mouseClicked(int button);
}