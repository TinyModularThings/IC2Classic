package ic2.api.item;

import net.minecraft.item.ItemStack;

public interface IItemReactorPlanStorage
{
	/**
	 * Function to set a ReactorSetup to the item
	 * @param stack YourStack
	 * @param setup the SetupData
	 * @return true if it was added. False if it failed
	 */
	public boolean setSetup(ItemStack stack, String setup);
	
	/**
	 * Helper function return true if you have a setup.
	 * @param stack yourStack
	 * @return true it has a setup.
	 */
	public boolean hasSetup(ItemStack stack);
	
	/**
	 * Function to get the ReactorSetup
	 * @param stack yourStack
	 * @return the Reactor-setup. (Not null!)
	 */
	public String getSetup(ItemStack stack);
}
