package com.engine.input;

import com.engine.gui.GuiComponent;

/**
 * <h1>GuiComponentListener</h1>
 * This is an interface used with
 * screens to send events when
 * a GuiComponent is clicked or
 * entered/exited.
 * 
 * <h1>NOTE:</h1>
 * This interface isn't really set up properly...
 * just ignore it for now.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public interface GuiComponentListener 
{
	/**
	 * Called when a component is clicked.
	 * @param component
	 * @param button
	 */
	public void guiComponentClicked(GuiComponent component, int button);
	/**
	 * Called when the mouse enters a component.
	 * @param component
	 */
	public void guiComponentEntered(GuiComponent component);
	/**
	 * Called when the mouse exits a component.
	 * @param component
	 */
	public void guiComponentExited(GuiComponent component);
}
