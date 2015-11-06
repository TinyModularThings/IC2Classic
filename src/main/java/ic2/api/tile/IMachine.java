package ic2.api.tile;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IMachine
{
	public double getEnergy();
	
	public boolean useEnergy(double amount, boolean simulate);
	
	public void setRedstoneSensitive(boolean active);
	
	public boolean isRedstoneSensitive();
	
	public boolean isProcessing();
	
	public boolean isValidInput(ItemStack par1);
	
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
	 * Example: Compressors can talk to Pumps.
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
