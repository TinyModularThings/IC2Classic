package ic2.api.classic.crops;

import ic2.api.crops.BaseSeed;
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
	public BaseSeed getBaseSeed(ItemStack stack);
}
