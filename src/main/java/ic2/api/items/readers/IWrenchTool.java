package ic2.api.items.readers;

import net.minecraft.world.item.ItemStack;

public interface IWrenchTool
{
	double getActualLoss(ItemStack stack, double originalLoss);
	default boolean shouldRenderOverlay(ItemStack stack) { return true; }
}
