package com.engine.input;

import com.engine.coreObject.CoreObject;

/**
 * <h1>CoreObjectListener</h1>
 * This is an interface used with
 * screens to send events when
 * a CoreObject is clicked.
 * 
 * <h1>NOTE:</h1>
 * This interface isn't really set up properly...
 * just ignore it for now.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public interface CoreObjectListener 
{
	/**
	 * Called when a CoreObject is clicked.
	 * @param object the CoreObject that was clicked
	 * @param button the mouse button that was clicked
	 */
	public void coreObjectClicked(CoreObject object, int button);
}
