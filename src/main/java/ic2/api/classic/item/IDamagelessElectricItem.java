package ic2.api.classic.item;

import ic2.api.item.IElectricItem;
/**
 * 
 * @author Speiger
 * Implementing this class to your
 * Electric Item will disable the Damage setting in the ElectricItemManager
 * so you do not need to have damage included with Items.
 * (Durability bar has to be done manually when using this)
 */
public interface IDamagelessElectricItem extends IElectricItem
{
	
}
