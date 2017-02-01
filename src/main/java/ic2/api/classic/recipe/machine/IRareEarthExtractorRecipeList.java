package ic2.api.classic.recipe.machine;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IRareEarthExtractorRecipeList
{
	/**
	 * Function to register Items that have a rare Earth value:
	 * Defaults: (x out of 1000 Points)
	 * Cobble: 3.125
	 * Netherrack: 7.8125
	 * Clay: 20.84
	 * Obsidian: 100
	 * Soulsand: 100
	 * 
	 * @param value how much RareEarth you get out of the items
	 * @param items the items beeing used for rare earth extraction.
	 * Note: you can register multiple items with the same value at once
	 * Also No NBTSupport
	 */
	public void registerValue(float value, ItemStack...items);
	
	/**
	 * Function to readOnly all Recipe Entries.
	 * If you want to add / remove something use the functions for that.
	 * @return list of all entries
	 */
	public List<EarthEntry> getRecipeList();
	
	/**
	 * Function to remove entries out of the list
	 * @param items the entries you want to delete
	 */
	public void removeEntry(ItemStack...items);
	
	/**
	 * Function to get the registered Earth Value
	 * @param stack item you want to check
	 * @return the rare earth value
	 */
	public float getEarthValue(ItemStack stack);
	
	public static class EarthEntry
	{
		final float earthValue;
		final ItemStack item;
		
		public EarthEntry(float earthValue, ItemStack item)
		{
			this.earthValue = earthValue;
			this.item = item;
		}
		
		public float getEarthValue()
		{
			return earthValue;
		}
		
		public ItemStack getItem()
		{
			return item;
		}
	}
}
