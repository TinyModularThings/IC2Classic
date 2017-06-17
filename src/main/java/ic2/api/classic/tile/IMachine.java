package ic2.api.classic.tile;

import java.util.Set;

import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMachine
{
	/**
	 * @return how much energy the Machine has Stored
	 */
	public double getEnergy();
	
	/**
	 * Simple Energy Drain function. Includes test mode. So you do not require
	 * a has Enough Energy Check. 
	 * @param amount you want to drain
	 * @param simulate if you want to test
	 * @return if it could drain that amount of energy
	 */
	public boolean useEnergy(double amount, boolean simulate);
	
	/**
	 * Function for enable/disable redstone controls on the Machines
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
	public Set<UpgradeType> getSupportedTypes();
	
	/**
	 * World Access because not every machine is a TileEntity
	 * @return the World (can be null in rare cases)
	 */
	public World getMachineWorld();
	
	/**
	 * Pos access of the Machine because a machine does not have to be a TileEntity
	 * @return the pos (can be null in rare cases)
	 */
	public BlockPos getMachinePos();
}
