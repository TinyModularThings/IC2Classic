package ic2.api.tile;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IMachine
{
	/**
	 * @return how much energy the Machine has Stored
	 */
	public double getEnergy();
	
	/**
	 * Simpel Energy Drain function. Includes test mode. So you do not require
	 * a has Enough Energy Check. 
	 * @param amount you want to drain
	 * @param simulate if you want to test
	 * @return if it could drain that amount of energy
	 */
	public boolean useEnergy(double amount, boolean simulate);
	
	/**
	 * Function for enable/disable Redstone controls on the Machines
	 * @param active means redstone control
	 */
	public void setRedstoneSensitive(boolean active);
	
	/**
	 * Check for if the Machine is redstone Sensitive or not
	 * @return selfExplain
	 */
	public boolean isRedstoneSensitive();
	
	/**
	 * this function simply is for checking if the machine is processing Items
	 * that does not include for advanced machines if they use energy to maintain their speed
	 * @return is processing Items
	 */
	public boolean isProcessing();
	
	/**
	 * If you want to insert a Item this is your check.
	 * Note: This function checks only if the Item is in the Recipe list and if it matches to the main slot
	 * It is simply a helper functions for upgrades so you do not need to testInsertItems
	 * @param par1 Item you want to check
	 * @return true means you can insert it.
	 */
	public boolean isValidInput(ItemStack par1);
	
	/**
	 * Simply a list of all Valid Upgrade Types that are allowed.
	 * This function is only really used in the Container/Interface
	 * @return list of all valid UpgradeTypes
	 */
	public List<UpgradeType> getSupportedTypes();

	/**
	 * Upgrade Types, These are only basic kinds what could be an upgrade be,
	 * So please choose that what is the closest that your upgrade do.
	 * Now explaining:
	 * @ImportExport: Simply Upgrades that import Items/Fluids into the Machine
	 * or away from the machine
	 * @MachineModifierA: Anything that Increases/Decreases ProcesingTime
	 * or Energy Consumption.
	 * @MachineModifierB: Anything that Increases/Decreases Energy Storage
	 * or Machine Tier (EnergyNet)
	 * @RedstoneControl: Simply anything that handles Redstone Stuff.
	 * @Processing: Anything that simply add stuff to Machine Results, like Extra
	 * results or drops or something.
	 * @Sound: Simply anything that changes the sound of a Machine
	 * @WorldInteraction: Anything that let machines talk to other things in the World,
	 * Example: Compressor can talk to Pumps.
	 * @Custom: Anything that fit totally not to the others,
	 * please call the ModAuthor when you have something to add here.
	 *
	 */	
	public static enum UpgradeType
	{
		ImportExport,
		MachineModifierA,
		MachineModifierB,
		RedstoneControl,
		Processing,
		Sounds,
		WorldInteraction,
		Custom;
	}
}
