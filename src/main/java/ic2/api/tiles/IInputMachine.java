package ic2.api.tiles;

import net.minecraft.world.item.ItemStack;

public interface IInputMachine
{
	int getValidRoom(ItemStack stack);
}
