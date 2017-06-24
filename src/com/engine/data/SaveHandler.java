package com.engine.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.engine.coreObject.ObjectHandler;
import com.engine.resources.ResourceLoader;
import com.thoughtworks.xstream.XStream;

/**
 * <h1>SaveHandler</h1>
 * All project saving is handled here.  Certain
 * things can be saved automatically every time 
 * the project is saved by setting flags in
 * SaveHandler.  When save() is called,
 * every flag set to true will automatically save
 * as well as any other staged saves.
 * 
 * @author Ryan Hochmuth
 *
 */
public class SaveHandler 
{
	/*
	 * Save Flags
	 */
	private boolean saveCurrentWorld = true; // When the project is saved should the world be saved
	private boolean saveCoreObjects = true; // When the project is saved should the list of CoreObjects be saved
	
	/*
	 * Serialization
	 */
	private XStream xstream = new XStream();
	
	/**
	 * Create the SaveHandler and set some xStream aliases.
	 */
	public SaveHandler()
	{
		xstream.alias("ObjectHandler", ObjectHandler.class);
	}
	
	/**
	 * Save the project with the current flags and staged saves.
	 * @param saveName - the name to save under
	 * @param encrypt - should the files be encrypted
	 */
	public void save(String saveName, boolean encrypt)
	{
		createSaveDirectories(saveName);
		
		/*
		try
	    {
	        FileOutputStream fileOut =
	        new FileOutputStream(ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default\\core-objects.save");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(ResourceLoader.core.getObjectHandler().getCoreObjects());
	        out.close();
	        fileOut.close();
	    }
		catch(IOException i)
	    {
	        i.printStackTrace();
	    }
		*/
		/*
		 * Object Handler
		 */
		
		String coreObjects = xstream.toXML(ResourceLoader.core.getObjectHandler().getCoreObjects());
		
		try // Create the basic save file
		{
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(
					ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default\\core-objects.save"));
			bWriter.write(coreObjects);
			bWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		if (encrypt) // If this file should be encrypted
		{
			try // Encrypt the file
			{
				FileInputStream fin = new FileInputStream(
						ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default\\core-objects.save");
				FileOutputStream fout = new FileOutputStream(
						ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default\\core-objects-encrypted.save");
				ResourceLoader.core.getCipherHandler().encrypt(
						ResourceLoader.core.getCipherHandler().getKey(), fin, fout);
				
				FileInputStream fin2 = new FileInputStream(
						ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default\\core-objects-encrypted.save");
				FileOutputStream fout2 = new FileOutputStream(
						ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default\\core-objects-decrypted.save");
				ResourceLoader.core.getCipherHandler().decrypt(
						ResourceLoader.core.getCipherHandler().getKey(), fin2, fout2);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Creates the proper save directories
	 * if they do not exist.
	 */
	private void createSaveDirectories(String saveName)
	{
		// Create the proper save directories if they do not exist
		File savesDir = new File(ResourceLoader.core.getFilepath() + "\\Saves");
		if (!savesDir.exists())
			try
			{
				savesDir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
		File saveNameDir = new File(ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName);
		if (!saveNameDir.exists())
			try
			{
				saveNameDir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
		File defaultDir = new File(ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\default");
		if (!defaultDir.exists())
			try
			{
				defaultDir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
		File worldsDir = new File(ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\worlds");
		if (!worldsDir.exists())
			try
			{
				worldsDir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
		File customDir = new File(ResourceLoader.core.getFilepath() + "\\Saves\\" + saveName + "\\custom");
		if (!customDir.exists())
			try
			{
				customDir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
	}
	
	/**
	 * When the project is saved should the world be saved.
	 * @return the saveCurrentWorld
	 */
	public boolean shouldWorldSave() 
	{
		return saveCurrentWorld;
	}
	/**
	 * When the project is saved should the world be saved.
	 * @param saveCurrentWorld the saveCurrentWorld to set
	 */
	public void setSaveCurrentWorld(boolean saveCurrentWorld) 
	{
		this.saveCurrentWorld = saveCurrentWorld;
	}
	/**
	 * When the project is saved should the list of CoreObjects be saved.
	 * @return the saveCoreObjects
	 */
	public boolean shouldObjectListSave() 
	{
		return saveCoreObjects;
	}
	/**
	 * When the project is saved should the list of CoreObjects be saved.
	 * @param saveCoreObjects the saveCoreObjects to set
	 */
	public void setSaveCoreObjects(boolean saveCoreObjects) 
	{
		this.saveCoreObjects = saveCoreObjects;
	}
}