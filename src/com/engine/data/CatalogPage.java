package com.engine.data;

/**
 * <h1>CatalogPage</h1>
 * A catalog page represents an entire page
 * in a catalog.  Each page contains a
 * maximum of 16 CatalogItems.
 * <br>
 * @author Ryan Hochmuth
 *
 * @param <E>
 */
public class CatalogPage<E>
{
	@SuppressWarnings("unchecked")
	private CatalogItem<E>[] items = new CatalogItem[16]; // The CatalogItems in this page
	
	private int numItems = 0; // The number of items in this page
	
	/**
	 * Add a CatalogItem to this page.
	 * @param item
	 */
	public void addItem(CatalogItem<E> item)
	{
		numItems++;
		
		items[numItems - 1] = item;
	}
	
	/**
	 * Get the CatalogItem at the given
	 * index.
	 * @param index
	 * @return CatalogItem
	 */
	public CatalogItem<E> getItem(int index)
	{
		return items[index];
	}

	/**
	 * Get the array of CatalogItems.
	 * @return the items
	 */
	public CatalogItem<E>[] getItems()
	{
		return items;
	}

	/**
	 * Set the array of CatalogItems.
	 * @param items the items to set
	 */
	public void setItems(CatalogItem<E>[] items)
	{
		this.items = items;
	}

	/**
	 * @return the numItems
	 */
	public int getNumItems()
	{
		return numItems;
	}

	/**
	 * @param numItems the numItems to set
	 */
	public void setNumItems(int numItems)
	{
		this.numItems = numItems;
	}
}