package ic2.api.classic.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeHooks;
/**
 * 
 * @author Speiger
 * 
 * This API Class is for Items. If you have a custom Drill that you want to be used
 * in a IC2 Classic (ATM only Classic!) miner then here you have this class.
 * Implement it and then you should be able to use this inside of a miner.
 */
public interface IMiningDrill
{
	
	
	/**
	 * @param drill: Representation of a drill
	 * @return if you return true it will ignore all Extra Functions
	 * and do not apply a speed boost. Simply uses the for normal Mining.
	 */
	public boolean isBasicDrill(ItemStack drill);
	
	/**
	 * Every Mining Operation takes 200 ticks to happen. If you want to speed up or slow down the progress
	 * then use this function.
	 * @param drill representation of the drill
	 * @return the Extra Ticks that get added to the mining Operation.
	 * @Note: Requires a none Basic Drill.
	 * @Note: Diamond Drill applies every time 3 Extra Ticks of speed.
	 */
	public int getExtraSpeed(ItemStack drill);
	
	/**
	 * This function is there to increase the Cost of a Mining Operation.
	 * So if you want to make a Miner cost more power this function will help.
	 * @param drill Representation of the Drill
	 * @return the Extra Amount of EU that is required.
	 * @Note: Diamond Drill adds 14EU Extra Cost per Tick.
	 */
	public int getExtraEnergyCost(ItemStack drill);
	
	/**
	 * @param drill representation of the Item in the miner
	 * This function is the most important one. It will be called after the Miner mined a block
	 * You simply remove Electricity/Durability/Magic/etc from the Drill which counts as fuel for him
	 * when this function gets called.
	 * Note: No EU Requirement/Forced but EU Tools will be autoRecharged (up to tier 2)
	 * Example:
	 * Mining drill would do that.
	 * 
	 * ElectricItem.manager.use(drill, 50, null);
	 */
	public void useDrill(ItemStack drill);
	
	/**
	 * This function checks simply if the Mining Drill is able to Mine a Block (has Enough Charge/Durability to do that)
	 * @param drill representation of the Drill
	 * @return true for Yes it can mine and false for it can not mine.
	 * @Note: Even if the Mining Drill is Unbreakable if he requires charge to run return only true if the energy he uses is enough to operate at least once
	 */
	public boolean canMine(ItemStack drill);
	
	/**
	 * Function to check if the tool can mine the block
	 * by default just call here the ForgeHooks.canToolHarvestBlock function.
	 * This function just exist for anything special.
	 * If this returns false it will still call. ItemStack.canHarvestBlock()
	 * @param drill your Tool
	 * @param the block that wants to be mined
	 * @param access the world,
	 * @param pos the Pos
	 * @return if it can mine the block
	 */
	public boolean canMineBlock(ItemStack drill, IBlockState state, IBlockAccess access, BlockPos pos);
}
