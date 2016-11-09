package ic2.api.classic.trading.providers;

import ic2.api.recipe.IRecipeInput;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IItemTradeProvider extends ITradeProvider
{
	/**
	 * Function to request a list of items from a ItemProvider for a trade
	 * @param wanted the items you want to get
	 * @param amount you want to get
	 * @param simulate if it is only for a test (if it is not for a test then it removes it)
	 * @return a List of items that can be provided
	 */
	public List<ItemStack> getNearbyItemStack(IRecipeInput wanted, int amount, boolean simulate);
}
