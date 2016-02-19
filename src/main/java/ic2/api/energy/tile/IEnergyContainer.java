package ic2.api.energy.tile;
/**
 * 
 * @author Speiger
 * The IEnergyContainer class is a form for IEnergyStorage.
 * But less setting and more accessing.
 * This is mostly used for Waila Support and also for stuff that addons can better
 * use.
 */
public interface IEnergyContainer extends IEnergyTile
{
	public int getStoredEnergy();
	
	public int getEnergyCapacity();
	
	public int getEnergyUsage();
	
	public int getEnergyProduction();
	
	public int getMaxEnergyInput();
}
