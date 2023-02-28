package ic2.api.items;

import net.minecraft.world.item.ItemStack;

public interface ITagItem
{
	boolean matches(ItemStack self, ItemStack toCompare);
}
