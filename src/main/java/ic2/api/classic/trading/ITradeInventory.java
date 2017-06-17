package ic2.api.classic.trading;

import java.util.List;

import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;

public interface ITradeInventory
{
	/**
	 * @return amount of slots that inventory has
	 */
	public int getSlotCount();
	
	/**
	 * Basic getStackInSlot function
	 * @param slot the slot
	 * @return the item.
	 */
	public ItemStack getItemStack(int slot);
	
	/**
	 * Function to count how many items are in this trade inventory
	 * @param type the items that are searched for
	 * @return how many it found
	 */
	public int getItemCount(IRecipeInput type);
	
	/**
	 * function to get items & remove them
	 * @param type the items that are searched for
	 * @param amount the amount which are requested
	 * @return the list of item that were removed
	 */
	public List<ItemStack> removeItems(IRecipeInput type, int amount);
	
	/**
	 * Function to add a item to that inventory.
	 * mainly used for Fluid/Energy Trades coin getting
	 * since they can not return a ItemStack in there
	 * @param stack the stack you want to add
	 */
	public void addItem(ItemStack stack);
}
