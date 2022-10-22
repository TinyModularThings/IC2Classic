package ic2.api.reactor;

import net.minecraft.world.item.ItemStack;

public interface IReactorProduct
{
	boolean isValidForReactor(ItemStack stack, IReactor reactor);
}
