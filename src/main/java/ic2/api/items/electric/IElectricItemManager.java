package ic2.api.items.electric;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IElectricItemManager 
{
	int charge(ItemStack stack, int amount, int tier, boolean ignoreTransferLimit, boolean simulate);

	int discharge(ItemStack stack, int amount, int tier, boolean ignoreTransferLimit, boolean externally, boolean simulate);

	int getCharge(ItemStack stack);

	int getCapacity(ItemStack stack);
		
	boolean canUse(ItemStack stack, int amount);

	boolean use(ItemStack stack, int amount, LivingEntity entity);

	void chargeFromArmor(ItemStack stack, LivingEntity entity);

	int getTier(ItemStack stack);
	
	int getTransferLimit(ItemStack stack);
	
	Component getToolTip(ItemStack stack);
}
