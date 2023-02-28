package ic2.api.items.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IEnergyShieldArmor
{
	boolean addsShieldEffect(EquipmentSlot type, LivingEntity entity, ItemStack stack);
	
	boolean isEffectAlwaysOn(EquipmentSlot type, LivingEntity entity, ItemStack stack);
	
	static boolean addsEnergyShieldEffect(LivingEntity living)
	{
		if(living == null) return false;
		for(EquipmentSlot slot : EquipmentSlot.values())
		{
			if(slot.getType() == Type.ARMOR)
			{
				ItemStack stack = living.getItemBySlot(slot);
				if(stack.getItem() instanceof IEnergyShieldArmor && ((IEnergyShieldArmor)stack.getItem()).addsShieldEffect(slot, living, stack))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean shouldAlwaysShowEffect(LivingEntity living)
	{
		if(living == null) return false;
		for(EquipmentSlot slot : EquipmentSlot.values())
		{
			if(slot.getType() == Type.ARMOR)
			{
				ItemStack stack = living.getItemBySlot(slot);
				if(stack.getItem() instanceof IEnergyShieldArmor && ((IEnergyShieldArmor)stack.getItem()).addsShieldEffect(slot, living, stack))
				{
					IEnergyShieldArmor shield = (IEnergyShieldArmor)stack.getItem();
					if(shield.addsShieldEffect(slot, living, stack) && shield.isEffectAlwaysOn(slot, living, stack))
					{
						return true;	
					}
				}
			}
		}
		return false;
	}
}
