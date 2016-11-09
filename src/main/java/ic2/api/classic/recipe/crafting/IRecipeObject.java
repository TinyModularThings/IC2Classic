package ic2.api.classic.recipe.crafting;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IRecipeObject
{
	/**
	 * @return if Shapeless it will be always -1
	 */
	public int getSlot();
	
	/**
	 * @return gets the Items of the recipe.
	 * Note: It can be more then 1 item but not oreDictionary entry
	 */
	public List<ItemStack> getItems();
	
	/**
	 * @return if the Component is a OreDicted
	 */
	public boolean isOreDicted();
	
	/**
	 * @return stacksize of that component
	 */
	public int getStackSize();
	
	/**
	 * @return oreID but can be null
	 */
	public String getOreID();
	
}
