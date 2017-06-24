package com.engineGui.main;

public class StartEngine 
{
	private final int WINDOW_X = 300;
	private final int WINDOW_Y = 200;
	private final String TITLE = "Thale Engine";
	
	private MainGui gui;
	
	public StartEngine()
	{
		gui = new MainGui(WINDOW_X, WINDOW_Y, TITLE);
		gui.buildGui();
		gui.showGui();
	}
	
	public static void main(String[] args)
	{
		new StartEngine();
	}
}