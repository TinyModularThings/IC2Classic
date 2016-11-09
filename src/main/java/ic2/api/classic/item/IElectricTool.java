package ic2.api.classic.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public interface IElectricTool
{
	
	/**
	 * This function is simply a Override. Since Minecraft requires Special Item
	 * Classes as base to get special Enchantment, i got with this function ride of it.
	 * You return the Type of Item you want to simulate (no Special Class Requirement) 
	 * and it will try to implement these.
	 * @Note: ArmorParts will get also normal Enchantment so you can use ArmorHelmet or something without worry
	 * @param item representation of your item
	 * @return the Type of Enchantment that should get supported
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
	public boolean isSpecialSupported(ItemStack item, Enchantment ench);
	
	/**
	 * This function blocks a enchantment to be applied. That includes anvil Mode.
	 * Simply on Electric Armor to block the basic Thorns Enchantment because it does destroy it and is not really
	 * compatible...
	 * @param item representation of your item
	 * @param ench that could be blocked
	 * @return if blocked or not. (true == blocked)
	 */
	public boolean isExcluded(ItemStack item, Enchantment ench);
}
