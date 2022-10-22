package ic2.api.recipes.ingridients.queue;

import net.minecraft.world.item.ItemStack;

public interface IInputter
{
	void addItemIntoSlot(int slot, ItemStack stack);
}
