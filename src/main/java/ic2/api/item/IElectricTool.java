package ic2.api.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public interface IElectricTool extends IElectricItem
{
	public EnumEnchantmentType getType(ItemStack item);
	
	public boolean isSpecialSupport(ItemStack item, Enchantment ench);
}
