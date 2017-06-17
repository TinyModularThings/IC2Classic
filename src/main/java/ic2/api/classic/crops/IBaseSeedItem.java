package ic2.api.classic.crops;

import net.minecraft.item.ItemStack;

public interface IBaseSeedItem
{
	/**
	 * Simple class to support NBT on Crop Seeds
	 * These are just start helpers like Vanilla seeds
	 * can be used on Crops. Just the starter States
	 * by this item
	 * @param stack yourInstance
	 * @return the Base Seed. Can be null
	 */
	public ClassicBaseSeed getBaseSeed(ItemStack stack);
}
