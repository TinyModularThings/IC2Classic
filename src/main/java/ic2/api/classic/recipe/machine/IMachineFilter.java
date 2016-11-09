package ic2.api.classic.recipe.machine;

import ic2.api.recipe.IListRecipeManager;
import ic2.api.recipe.IRecipeInput;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IMachineFilter
{
	/**
	 * add a filter
	 */
	public void addItem(ItemStack item);
	
	/**
	 * add a custom Filter. IRecipeInput is simply a massCheck
	 */
	public void addFilter(IRecipeInput filter);
	
	/**
	 * Function to check if the Item is allowed
	 * Filters get Priority
	 */
	public boolean isItemAllowed(ItemStack stack);
	
	public List<ItemStack> getAllEntries();
	
	/**
	 * @return if the List is a White or Blacklist...
	 * Needed for JEI
	 */
	public boolean isBlackList();
	
	/**
	 * removes Items Only
	 */
	public boolean removeItem(ItemStack item);
	
	/**
	 * removes Filters Only
	 */
	public boolean removeFilter(ItemStack item);
	
	/**
	 * Removes items & filter
	 */
	public boolean remove(ItemStack item);
	
	/**
	 * simply a IC2 Exp compat...
	 * Thats the only part of combat i do to this kind of recipes
	 */
	public IListRecipeManager toExp();
}
