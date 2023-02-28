package ic2.api.items;

import net.minecraft.world.item.ItemStack;

public interface ICoinItem
{
	int getMoneyValue(ItemStack stack);
}
