package ic2.api.items;

import net.minecraft.world.item.ItemStack;

public interface IBoxableItem
{
	boolean canStoreIntoBox(ItemStack stack);
}
