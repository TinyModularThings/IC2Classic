package ic2.api.item;

import net.minecraft.item.ItemStack;

public interface IItemReactorPlanStorage
{
	/**
	 * Meta Compatible function to allow meta items to be plans
	 * @param stack yourMetaItem
	 * @return true if it is a PlanStorage
	 */
	public boolean isPlanStorage(ItemStack stack);
	
	/**
	 * Function to set a ReactorSetup to the item
	 * Also when this function is called and it would override/set the setup
	 * delete the plan name
	 * @param stack YourStack
	 * @param setup the SetupData
	 * @return true if it was added. False if it failed
	 */
	public boolean setSetup(ItemStack stack, String setup);

	/**
	 * function to add a ToolTipName for the setup if you want to support this
	 * this function will be only called after setSetup & if there is a custom Name
	 * @param stack YourStack
	 * @param setup the CustomName of the setup
	 */
	public void setPlanName(ItemStack stack, String setup);
	
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
