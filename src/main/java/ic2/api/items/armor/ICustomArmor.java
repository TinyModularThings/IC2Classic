package ic2.api.items.armor;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface ICustomArmor
{
	AbsorptionProperties getProperties(LivingEntity entity, ItemStack armor, DamageSource source, double damage, EquipmentSlot slot);
	
	void damageArmor(LivingEntity player, ItemStack stack, DamageSource source, int damage, EquipmentSlot slot, DamageType type);

	default boolean canBlockDamageSource(LivingEntity player, ItemStack stack, DamageSource source, EquipmentSlot slot) {
		return !source.isBypassArmor();
	}

	enum DamageType
	{
		MODDED,
		VANILLA,
		THORNS_SELF
	}
	
	class AbsorptionProperties implements Comparable<AbsorptionProperties>
	{
		public int priority;
		public int absorbMax;
		public double absorbRatio;
		public EquipmentSlot slot = null;
		
		public AbsorptionProperties(int priority, double ratio, int max)
		{
			this.priority = priority;
			absorbMax = max;
			absorbRatio = ratio;
		}
		
		private AbsorptionProperties(AbsorptionProperties orig)
		{
			priority = orig.priority;
			absorbMax = orig.absorbMax;
			absorbRatio = orig.absorbRatio;
			slot = orig.slot;
		}
		
		private int calcMaxAbsorption()
		{
			return (int)(absorbRatio == 0 ? 0 : absorbMax * 100.0 / absorbRatio);
		}
		
		@Override
		public int compareTo(AbsorptionProperties o)
		{
			if(o.priority != priority)
				return o.priority - priority;
			return calcMaxAbsorption() - o.calcMaxAbsorption();
		}
		
		@Override
		public String toString()
		{
			return String.format("%d, %d, %f, %d", priority, absorbMax, absorbRatio, calcMaxAbsorption());
		}
		
		public AbsorptionProperties copy()
		{
			return new AbsorptionProperties(this);
		}
	}
}
