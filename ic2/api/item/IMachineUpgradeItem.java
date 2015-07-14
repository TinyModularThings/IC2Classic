package ic2.api.item;

import ic2.api.tile.IMachine;
import ic2.api.tile.IMachine.UpgradeType;

import java.util.List;

import net.minecraft.item.ItemStack;
/**
 * 
 * @author Speiger
 * Classic only!
 */
public interface IMachineUpgradeItem
{
	/**
	 * all these functions (up to the getExtraTier) will be only called when you insert a Upgrade to the machine)
	 */
	public int getExtraProcessTime(ItemStack upgrade, IMachine machine);
	
	public double getProcessTimeMultiplier(ItemStack upgrade, IMachine machine);
	
	public int getExtraEnergyDemand(ItemStack upgrade, IMachine machine);
	
	public double getEnergyDemandMultiplier(ItemStack upgrade, IMachine machine);
	
	public int getExtraEnergyStorage(ItemStack upgrade, IMachine machine);
	
	public double getEnergyStorageMultiplier(ItemStack upgrade, IMachine machine);
	
	public int getExtraTier(ItemStack upgrade, IMachine machine);
	
	public boolean onTick(ItemStack upgrade, IMachine machine);
	
	public void onProcessEnd(ItemStack upgrade, IMachine machine, List<ItemStack> results);
	
	public boolean useRedstoneinverter(ItemStack upgrade, IMachine machine);
	
	public void onInstalling(ItemStack upgrade, IMachine machine);
	
	public float getSoundVolumeMultiplier(ItemStack upgrade, IMachine machine);
	
	public UpgradeType getType(ItemStack par1);
}
