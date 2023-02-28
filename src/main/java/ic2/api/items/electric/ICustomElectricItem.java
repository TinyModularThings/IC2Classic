package ic2.api.items.electric;

import net.minecraft.world.item.ItemStack;

public interface ICustomElectricItem
{
	IElectricItemManager getManager(ItemStack stack);
}
