package ic2.api.classic.recipe.custom;

import ic2.api.recipe.IScrapboxManager;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IClassicScrapBoxManager extends IScrapboxManager
{
	/**
	 * Function to access the Drops that are implemented
	 * Do not delete/add via this list the drops
	 * It does not change anything since its a copy of the Actual List.
	 * (Its this way to solve some issues)
	 * @return the list of the DropInstances
	 */
	public List<IDrop> getEntries();
	
	/**
	 * One and only function to remove drops
	 * Its only there allow custom Modpacks to modify stuff
	 * @param drop the Drop You want to delete (get them from the getEntries function else it wont work)
	 * @Note: Laggy function because it has to recalculate all drop chances for every item removed
	 */
	public void removeDrop(IDrop drop);
	
	/**
	 * Gets you the Random Entry.
	 * If you need the also the Chance of that
	 */
	public IDrop getRandomDrop(ItemStack stack, boolean consume);
	
	
	public static interface IDrop
	{
		/**
		 * @return the item that will drop
		 */
		public ItemStack getDrop();
		
		/**
		 * @return the chance it has. (Modifies everytime a drop gets added/removed)
		 */
		public float getChance();
		
		/**
		 * @return the chance that the item got registered with
		 */
		public float getRawChance();
	}
}
