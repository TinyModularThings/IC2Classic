package ic2.api.classic.item;

import java.util.List;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IMachine;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;

public interface IMachineUpgradeItem
{
	/**
	 * Passive Calls (Called when the Item gets applied)
	 */
	
	/**
	 * Effects the Progress Per Tick
	 */
	public int getExtraProcessSpeed(ItemStack upgrade, IMachine machine);
	
	public double getProcessSpeedMultiplier(ItemStack upgrade, IMachine machine);
	
	/**
	 * Effects the totalProgress Points needed to finish a recipe
	 */
	public int getExtraProcessTime(ItemStack upgrade, IMachine machine);
	
	public double getProcessTimeMultiplier(ItemStack upgrade, IMachine machine);
	
	/**
	 * Effects how much power is drawn per tick
	 */
	public int getExtraEnergyDemand(ItemStack upgrade, IMachine machine);
	
	public double getEnergyDemandMultiplier(ItemStack upgrade, IMachine machine);
	
	/**
	 * Effects how much power it can store
	 */
	public int getExtraEnergyStorage(ItemStack upgrade, IMachine machine);
	
	public double getEnergyStorageMultiplier(ItemStack upgrade, IMachine machine);

	/**
	 * Effects the EnergyTier of the machine
	 */
	public int getExtraTier(ItemStack upgrade, IMachine machine);

	/**
	 * Effects the Sound Volume
	 */
	public float getSoundVolumeMultiplier(ItemStack upgrade, IMachine machine);
	
	/**
	 * Invert the machine
	 */
	public boolean useRedstoneInverter(ItemStack upgrade, IMachine machine);
	
	/**
	 * this function is called before all other passive Functions simply to allow init
	 * the stack for stuff that requires it. (Validating stuff based of the machine)
	 */
	public void onInstalling(ItemStack upgrade, IMachine machine);
	
	/**
	 * Active Calls (get Called almost every Tick depending on the stuff that is happening)
	 */
		
	public void onTick(ItemStack upgrade, IMachine machine);
	
	/**
	 * This function is called before the Items get added to the Result list.
	 * Simply if your RecipeOutput class is happening then you can apply any form of
	 * Effects here. (Note the Instance will be reseted after the progress is done.
	 * But it will stay in the onProcessEndPost call)
	 * @param upgrade Item instance
	 * @param machine Machine Instance which machine its using it
	 * @param output The Current output of the Machine.
	 */
	public void onProcessEndPre(ItemStack upgrade, IMachine machine, MachineOutput output);
	
	/**
	 * This function is called after the results from the Output are applied to the Result List
	 * You can modify the List and clear stuff from that (for example if you have a export upgrade)
	 * Input is there if you want to make something special like fortune (which can be applied in Pre Calls)
	 * Just in case you want to have a Special Handler
	 * @param upgrade ItemInstance of that Upgrade
	 * @param machine Machine who is calling that
	 * @param input RecipeInput instance of that item to validate special actions bound to only special Recipes
	 * @param output The Current output (unchanged from the Pre call. So you can store data between these calls)
	 * @param results All the Results that will go into the Machine.
	 */
	public void onProcessEndPost(ItemStack upgrade, IMachine machine, IRecipeInput input, MachineOutput output, List<ItemStack> results);
	
	/**
	 * A Function called by machines which have upgrade Slots but don't have a Proper RecipeList.
	 * This call is basically when the machine has done something and the inventory would have changed
	 * so that upgrades can still work properly.
	 * Note: A Machine that calls this does not call onProcessEnd (Pre/Post)
	 * So if you have upgrades keep that in mind
	 * @param upgrade Item instance
	 * @param machine Machine Instance which machine its using it
	 */
	public void onProcessFinished(ItemStack upgrade, IMachine machine);
	
	public UpgradeType getType(ItemStack par1);
	
	/**
	 * Upgrade Types, These are only basic kinds what could be an upgrade be,
	 * So please choose that what is the closest that your upgrade do.
	 * Now explaining:
	 * @ImportExport: Simply Upgrades that import Items/Fluids into the Machine
	 * or away from the machine (Can also count for Converter Importer)
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
