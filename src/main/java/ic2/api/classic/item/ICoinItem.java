package ic2.api.classic.item;

import net.minecraft.item.ItemStack;

public interface ICoinItem
{
	/**
	 * function that allows you to tell the money-value of a coin
	 * @param stack The Item you want to check.
	 * @return the money value. can be 0
	 */
	public int getMoneyValue(ItemStack stack);
}
