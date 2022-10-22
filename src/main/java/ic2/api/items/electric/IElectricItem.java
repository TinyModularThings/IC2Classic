package ic2.api.items.electric;

import net.minecraft.world.item.ItemStack;

public interface IElectricItem
{
	boolean canProvideEnergy(ItemStack stack);
		
	int getCapacity(ItemStack stack);
	
	int getTier(ItemStack stack);
	
	int getTransferLimit(ItemStack stack);
}

