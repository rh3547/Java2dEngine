package com.rs.objects;

/**
 * This class is used to represent
 * an object containing all of the
 * information for a catalog item
 * in the game.
 * 
 * Each item has the following:
 * - name
 * - description
 * - price
 * - type
 * 
 * @author Ryan Hochmuth
 *
 */
public class RSCatalogItem
{
	private String name; // The name of this item
	private String description; // The description for this item
	private double price; // The price of this item
	private int type; // The type of item (See RSTile)
	
	/**
	 * Create a new catalog item.
	 * @param name
	 * @param description
	 * @param price
	 * @param type
	 */
	public RSCatalogItem(String name, String description, double price, int type)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * @return the type
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type)
	{
		this.type = type;
	}
}