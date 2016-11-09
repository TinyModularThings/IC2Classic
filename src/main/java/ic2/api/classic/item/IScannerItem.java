package ic2.api.classic.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IScannerItem
{
	/**
	 * This function is called by the miner when he try to find his target.
	 * Here you drain your energy and set the radius it is scanning in.
	 * @param par1 represents the Scanner Item
	 * @return the area it is scanning (radius)
	 */
	public int startLayerScan(ItemStack par1);
	
	/**
	 * This function get called when the Miner wants to know if that Block is a valid thing,
	 * to mine. true means yes wrong is false.
	 * @param par1 represents the Scanner Item
	 * @param state the BlockState to check
	 * @return if the Block/Ore is valueable
	 */
	public boolean isValuableOre(ItemStack par1, IBlockState state);
	
	/**
	 * This function is not called by the Miner but it can be useful at some point
	 * @param par1 represents the Scanner Item
	 * @param state the BlockState to check
	 * @return the value of the Ore
	 */
	public int getOreValue(ItemStack par1, IBlockState state);
	
	/**
	 * This function is not called by the Miner but it can be useful at some point
	 * @param par1 represents the Scanner Item, so that you can get range and co
	 * @param world which dimension it is searching for ore value
	 * @param pos the Position where you want to search
	 * @return the total ore value which is around you. Based on how you scan^^
	 */
	public int getOreValueOfArea(ItemStack par1, World world, BlockPos pos);
	
}
