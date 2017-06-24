package com.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.engine.gui.GuiTextField;
import com.engine.main.Core;

/**
 * <h1>KeyboardHandler</h1>
 * KeyboardHandler handles the project's 
 * keyboard input.  Events are generated
 * and sent to the proper location so the
 * project knows when a key is being used.
 * <br><br>
 * @author Ryan Hochmuth
 *
 */
public class KeyboardHandler implements KeyListener
{
	private final Core core;
	
	/*
	 * Input Variables
	 */
	// An array of key flags.  If a key is pressed it's flag will be true
	// This is for all a keyboard's keys, it is general
	private boolean[] keys = new boolean[256];
	
	private boolean wordTyping = false; // Is the keyboard being used to type a word into a text input
	private GuiTextField textFieldFocus = null; // The GuiTextField that has focus (if any)
	
	/**
	 * Creates a new KeyboardHandler and adds it
	 * as a KeyListener to the project window.
	 * @param core
	 */
	public KeyboardHandler(Core core)
	{
		this.core = core;
		
		// Add the KeyboardHandler to the window to detect key presses
		core.getWindow().addKeyListener(this);
	}
	
	/**
	 * keyPressed() is called as an event
	 * when a key on the keyboard is
	 * pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (!wordTyping)
		{
			// Set this key as pressed in the general keys
			keys[e.getKeyCode()] = true;
			// Set this specific project key as pressed
			core.getResourceLoader().checkKey(e.getKeyCode(), true);
			// Update the CoreKeyListener keyPressed() method
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen()
				.keyPressed(e.getKeyCode());
			
			// If F3 is pressed show/hide the debug window
			if (!core.isDebugShown() && e.getKeyCode() == KeyEvent.VK_F3)
			{
				core.setDebugShown(true);
			}
			else if (core.isDebugShown() && e.getKeyCode() == KeyEvent.VK_F3)
			{
				core.setDebugShown(false);
			}	
		}
		else
		{
			
		}
	}
	
	/**
	 * keyReleased() is called as an event
	 * when a key on the keyboard is
	 * released.
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (!wordTyping)
		{
			// Set this key as not pressed in the general keys
			keys[e.getKeyCode()] = false;
			// Set this specific project key as not pressed
			core.getResourceLoader().checkKey(e.getKeyCode(), false);
			// Update the CoreKeyListener keyPressed() method
			if (core.getScreenHandler().getCurrentScreen() != null)
				core.getScreenHandler().getCurrentScreen()
				.keyReleased(e.getKeyCode());
		}
		else
		{
			/*
			for (int x = 0; x < game.getMouseHandler().guiComponents.size(); x++)
			{
				if (game.getMouseHandler().guiComponents.get(x) instanceof GuiTextArea)
				{
					if (((GuiTextArea)game.getMouseHandler().guiComponents.get(x)).isTyping())
					{
						((GuiTextArea)game.getMouseHandler().guiComponents.get(x)).type(e.getKeyCode(), e.getKeyChar());
					}
				}
			}
			*/
			
			if (textFieldFocus != null)
				textFieldFocus.type(e.getKeyCode(), e.getKeyChar());
		}
	}

	/**
	 * keyTyped() is called as an event
	 * when a key on the keyboard is
	 * typed.
	 */
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	/*
	 * General Key Methods
	 */
	/**
	 * Checks if the given key code is pressed.
	 * @param key
	 * @return boolean value
	 */
	public boolean isKeyPressed(int key)
	{
		return keys[key];
	}
	
	/**
	 * Checks if the given key code is released.
	 * @param key
	 * @return boolean value
	 */
	public boolean isKeyReleased(int key)
	{
		return !keys[key];
	}

	/**
	 * @return the wordTyping
	 */
	public boolean isWordTyping()
	{
		return wordTyping;
	}

	/**
	 * @param wordTyping the wordTyping to set
	 */
	public void setWordTyping(boolean wordTyping)
	{
		this.wordTyping = wordTyping;
	}

	/**
	 * @return the textFieldFocus
	 */
	public GuiTextField getTextFieldFocus()
	{
		return textFieldFocus;
	}

	/**
	 * @param textFieldFocus the textFieldFocus to set
	 */
	public void setTextFieldFocus(GuiTextField textFieldFocus)
	{
		this.textFieldFocus = textFieldFocus;
	}
}
