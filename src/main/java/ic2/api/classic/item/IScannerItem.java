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
	 * @param scanner represents the Scanner Item
	 * @return the area it is scanning (radius)
	 */
	public int startLayerScan(ItemStack scanner);
	
	/**
	 * function that defines if the scanner needs the world&Pos isValueableOre function
	 * For machines or other stuff.. 
	 * but the other function has to be supported if possible
	 * @param scanner yourItem
	 * @return if it needs the advanced version
	 */
	public boolean isAdvancedScanner(ItemStack scanner);
	
	/**
	 * This function get called when the Miner wants to know if that Block is a valid thing,
	 * to mine. true means yes wrong is false.
	 * @param scanner represents the Scanner Item
	 * @param state the Block that was found there
	 * @param world the world the Block is in
	 * @param pos the Position the block needs to be checkt
	 * @return if the Block/Ore is valueable
	 */
	public boolean isValuableOre(ItemStack scanner, IBlockState state, World world, BlockPos pos);
	
	/**
	 * This function get called when the Miner wants to know if that Block is a valid thing,
	 * to mine. true means yes wrong is false.
	 * @param scanner represents the Scanner Item
	 * @param state the BlockState to check
	 * @return if the Block/Ore is valueable
	 */
	public boolean isValuableOre(ItemStack scanner, IBlockState state);
	
	/**
	 * This function is not called by the Miner but it can be useful at some point
	 * @param scanner represents the Scanner Item
	 * @param state the BlockState to check
	 * @return the value of the Ore
	 */
	public int getOreValue(ItemStack scanner, IBlockState state);
	
	/**
	 * This function is not called by the Miner but it can be useful at some point
	 * @param scanner represents the Scanner Item, so that you can get range and co
	 * @param world which dimension it is searching for ore value
	 * @param pos the Position where you want to search
	 * @return the total ore value which is around you. Based on how you scan^^
	 */
	public int getOreValueOfArea(ItemStack scanner, World world, BlockPos pos);
}
