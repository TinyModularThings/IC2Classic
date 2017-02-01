package ic2.api.classic.item;

import net.minecraft.item.ItemStack;

public interface IFuelableItem
{
	/**
	 * If you have a Item that should get Filled by a Canner (with Fuel)
	 * Then you can use this Interface
	 * @param emptyItem Item which will be processed.
	 * @param amount of Fuel the canner want to fill
	 * @return the Resulting ItemStack.
	 * @Note: emptyItem is a Copy
	 * @Note: ContainerItems will be set to null no getContainerItemCall. 
	 * None ContainerItems get only a stacksizeDecreasment (when 0 stack size it will be null)
	 */
	public ItemStack fill(ItemStack emptyItem, int amount);
	
	/**
	 * Function to check if the item can take any amount of fuel.
	 * This function will be called by the Canner
	 * @param stack that is used
	 * @return if the item can be filled
	 */
	public boolean canFill(ItemStack stack);
	
	/**
	 * Function to check if the fuelable Item has fuel to give.
	 * Its basicly the check if you can drain fuel for it.
	 * @param stack the FueableStack
	 * @return true if it can provide & has fuel
	 */
	public boolean hasFuel(ItemStack stack);
	
	/**
	 * Function to drain fuel from the IFueableItem.
	 * The returned amount is how much you want to have.
	 * @param stack YourStack basicly.
	 * @param requested the amount that is wanted
	 * @param doDrain if it is only for a test or if it should be drained
	 * @return the amount that is provided
	 */
	public int getFuel(ItemStack stack, int requested, boolean doDrain);
}
