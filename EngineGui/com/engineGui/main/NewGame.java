package com.engineGui.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Creates the directories and files
 * for the new game.
 * 
 * @author Ryan Hochmuth
 *
 */
public class NewGame
{
	// Constructor data
	String path;
	String title;
	String windowX;
	String windowY;
	String fps;
	String tps;
	
	String titleInitials;
	String fileTitle;
	
	/**
	 * Create a new game.
	 * @param path
	 * @param title
	 * @param windowX
	 * @param windowY
	 * @param fps
	 * @param tps
	 */
	public NewGame(String path, String title, String windowX, String windowY,
			String fps, String tps)
	{
		this.path = path;
		this.title = title;
		this.windowX = windowX;
		this.windowY = windowY;
		this.fps = fps;
		this.tps = tps;
		
		this.titleInitials = title.substring(0, 1).toUpperCase() + 
				title.substring(title.indexOf(" ") + 1, title.indexOf(" ") + 2).toUpperCase();
		this.fileTitle = title.replace(" ", "");
		
		createDirectories();
		createGameFile();
		createResourceFile();
		
		System.exit(0); // TODO Remove
	}
	
	/**
	 * Create the directories for the new game.
	 */
	private void createDirectories()
	{
		String currentPath = path;
		
		createDir(currentPath + "//" + fileTitle); // Create the base dir
		currentPath += "//" + fileTitle;
		
		createDir(currentPath + "//res"); // Create the res dir
		createDir(currentPath + "//res//resourcePacks"); // Create the resoursePacks dir
		createDir(currentPath + "//res//resourcePacks//default"); // Create the default dir
		createDir(currentPath + "//res//resourcePacks//default//audio"); // Create the audio dir
		createDir(currentPath + "//res//resourcePacks//default//audio//music"); // Create the music dir
		createDir(currentPath + "//res//resourcePacks//default//audio//sfx"); // Create the sfx dir
		createDir(currentPath + "//res//resourcePacks//default//fonts"); // Create the fonts dir
		createDir(currentPath + "//res//resourcePacks//default//graphics"); // Create the graphics dir
		createDir(currentPath + "//res//resourcePacks//default//graphics//buttonImages"); // Create the buttonImages dir
		createDir(currentPath + "//res//resourcePacks//default//graphics//icons"); // Create the icons dir
		createDir(currentPath + "//res//resourcePacks//default//graphics//sprites"); // Create the sprites dir
		createDir(currentPath + "//res//resourcePacks//default//graphics//sprites//player"); // Create the player dir
		createDir(currentPath + "//res//resourcePacks//default//graphics//tiles"); // Create the tiles dir
		createDir(currentPath + "//res//resourcePacks//default//worlds"); // Create the worlds dir
		
		createDir(currentPath + "//com"); // Create the com dir
		currentPath += "//com";
		
		createDir(currentPath + "//" + fileTitle.toLowerCase()); // Create the next package level dir
		currentPath += "//" + fileTitle.toLowerCase();
		
		createDir(currentPath + "//main"); // Create the main dir
		createDir(currentPath + "//objects"); // Create the objects dir
		createDir(currentPath + "//screens"); // Create the screens dir
		createDir(currentPath + "//tiles"); // Create the tiles dir
		createDir(currentPath + "//world"); // Create the world dir
	}
	
	/**
	 * Create one directory.
	 * @param path
	 */
	private void createDir(String path)
	{
		File dir = new File(path);
		if (!dir.exists())
			try
			{
		        dir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
	}
	
	/**
	 * Create the main game file.
	 */
	private void createGameFile()
	{
		File baseGameFile;
		File gameFile;
		PrintWriter pw = null;
		
		try
		{
			baseGameFile = new File("EngineGui/res/com/source/main/GameFile.txt");
			Scanner scan = new Scanner(baseGameFile);
			String content = scan.useDelimiter("\\Z").next();
			scan.close();
			
			content = content.replaceAll("PACKAGETITLE", fileTitle.toLowerCase());
			content = content.replaceAll("TITLEINITIALS", titleInitials);
			content = content.replaceAll("GAMETITLE", title);
			content = content.replaceAll("WINDOWX", windowX);
			content = content.replaceAll("WINDOWY", windowY);
			content = content.replaceAll("FPS", fps);
			content = content.replaceAll("TPS", tps);
			content = content.replaceAll("FILETITLE", fileTitle);
			
			gameFile = new File(path + "//" 
					+ fileTitle + "//com//" + fileTitle.toLowerCase() + "//main//" + titleInitials + "Core.java");
			
			pw = new PrintWriter(gameFile);
			
			pw.print(content);
			
			pw.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the resource file.
	 */
	private void createResourceFile()
	{
		File baseResourceFile;
		File resourceFile;
		PrintWriter pw = null;
		
		try
		{
			baseResourceFile = new File("EngineGui/res/com/source/main/ResourceFile.txt");
			Scanner scan = new Scanner(baseResourceFile);
			String content = scan.useDelimiter("\\Z").next();
			scan.close();
			
			content = content.replaceAll("PACKAGETITLE", fileTitle.toLowerCase());
			content = content.replaceAll("TITLEINITIALS", titleInitials);
			
			resourceFile = new File(path + "//" 
					+ fileTitle + "//com//" + fileTitle.toLowerCase() + "//main//" + titleInitials + "Resources.java");
			
			pw = new PrintWriter(resourceFile);
			
			pw.print(content);
			
			pw.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}