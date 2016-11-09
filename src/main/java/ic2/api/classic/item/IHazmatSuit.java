package ic2.api.classic.item;

import net.minecraft.item.ItemStack;

public interface IHazmatSuit
{
	/**
	 * function to add custom HazmatSuit parts
	 * @param stack Instance of the stack to check
	 * @return if the current part is a HazmatSuit
	 */
	public boolean isHazmatSuit(ItemStack stack);
}
