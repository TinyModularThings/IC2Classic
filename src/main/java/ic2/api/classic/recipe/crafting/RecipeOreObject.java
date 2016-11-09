package ic2.api.classic.recipe.crafting;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeOreObject implements IRecipeObject
{
	String oreID;
	int slot;
	int amount;
	
	public RecipeOreObject(String id, int slotID, int stacksize)
	{
		oreID = id;
		slot = slotID;
		amount = stacksize;
	}
	
	@Override
	public int getSlot()
	{
		return slot;
	}
	
	@Override
	public List<ItemStack> getItems()
	{
		return OreDictionary.getOres(oreID, false);
	}
	
	@Override
	public boolean isOreDicted()
	{
		return true;
	}
	
	@Override
	public String getOreID()
	{
		return oreID;
	}
	
	@Override
	public int getStackSize()
	{
		return amount;
	}
	
}
