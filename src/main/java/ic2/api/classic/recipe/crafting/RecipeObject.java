package ic2.api.classic.recipe.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RecipeObject implements IRecipeObject
{
	int amount;
	int slot;
	List<ItemStack> items;
	
	public RecipeObject(int slot, ItemStack item)
	{
		this(slot, Arrays.asList(item), item.getCount());
	}
	
	public RecipeObject(int slot, List<ItemStack> items, int stacksize)
	{
		this.slot = slot;
		this.items = items;
		amount = stacksize;
	}
	
	public int getSlot()
	{
		return slot;
	}
	
	public List<ItemStack> getItems()
	{
		return items;
	}
	
	@Override
	public boolean isOreDicted()
	{
		return false;
	}
	
	@Override
	public String getOreID()
	{
		return null;
	}
	
	@Override
	public int getStackSize()
	{
		return amount;
	}
}
