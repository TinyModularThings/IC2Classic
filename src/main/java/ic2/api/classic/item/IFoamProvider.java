package ic2.api.classic.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IFoamProvider
{
	/**
	 * Custom API Class to support Foam Containers.
	 * The logic is simple: The CF Sprayer checks if you provide foam
	 * And handles it. It will not refill the CF Sprayer.
	 * @param player Player Who is using the CF Sprayer
	 * @param stack YourItem instance
	 * @param amount the Amount of Foam requested
	 * @param slotType if yourItem is in the hot-bar or armor slot or offHand or Baubles Slot
	 * @return if it provides the requested amount of foam
	 * @Note: It checks armor first
	 */
	public boolean canProvideFoam(EntityPlayer player, ItemStack stack, int amount, SlotType slotType);
	
	/**
	 * Function is called when foam is used and spraying was successful
	 * @param player Player Who Sprayed it
	 * @param stack YourItem Instance
	 * @param amount Of foam used
	 */
	public void useFoam(EntityPlayer player, ItemStack stack, int amount);
	
	/**
	 * Function that allow to canning machine or other to fill your item
	 * @param stack YourStack instance
	 * @param amount that wants to be added
	 */
	public void fillFoam(ItemStack stack, int amount);
	
	/**
	 * Function to access how much room is left
	 * @param stack Your StackInstance
	 * @return the free Space
	 */
	public int getFreeRoom(ItemStack stack);
	
	public static enum SlotType
	{
		Hotbar,
		OffHand,
		Armor,
		Baubles;
	}
}
