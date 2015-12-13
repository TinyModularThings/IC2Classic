package ic2.api.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public interface IElectricTool extends IElectricItem
{
	/**
	 * This class is mostly meant to enchant ElectricTools from IC2
	 * So if you want to support it you need to implement the IElectricItem
	 * Class. If you use it it is your thing but still it is required
	 */
	
	/**
	 * This function is simply a Override. Since Minecraft requires Special Item
	 * Classes as base to get special Enchantments, i got with this function ride of it.
	 * You return the Type of Item you want to simulate (no Special Class Requirement) 
	 * and it will try to implement these.
	 * @Note: ArmorParts will get also normal Enchantments so you can use ArmorHelmet or something without worry
	 * @param item representation of your item
	 * @return the Type of Enchantments that should get supported
	 */
	public EnumEnchantmentType getType(ItemStack item);
	
	/**
	 * This function is simply a Emergency override.
	 * If you really want to have a Special Enchantment then this function will allow it
	 * It will override also the function above.
	 * At the moment no need. Also maybe adding a opposite function for same use.
	 * Not this version
	 * @param item representation of your item
	 * @param ench that maybe could be supported
	 * @return if special Support required true everything else false
	 */
	public boolean isSpecialSupport(ItemStack item, Enchantment ench);
	
	/**
	 * This function blocks a enchantment to be applied. That includes anvil Mode.
	 * Simply on Electric Armor to block the basic Thorns Enchantment because it does distroy it and is not really
	 * compatible...
	 * @param item representation of your item
	 * @param ench that could be blocked
	 * @return if blocked or not. (true = blocked)
	 */
	public boolean isExcluded(ItemStack item, Enchantment ench);
}
