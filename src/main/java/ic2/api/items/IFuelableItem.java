package ic2.api.items;

import net.minecraft.world.item.ItemStack;

public interface IFuelableItem
{
	ItemStack fill(ItemStack stack, int amount);
	
	boolean canFuel(ItemStack stack);
	
	boolean hasFuel(ItemStack stack);
	
	int getFuel(ItemStack stack, int requested, boolean doDrain);

}
