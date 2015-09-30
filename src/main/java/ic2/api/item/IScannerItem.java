package ic2.api.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IScannerItem extends IElectricItem
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
	 * @param block the Block that could be valuable
	 * @param metaData the Block metadata that could be valuable
	 * @return if the Block/Ore is valueable
	 */
	public boolean isValuableOre(ItemStack par1, Block block, int metaData);
	
	/**
	 * This function is not called by the Miner but it can be useful at some point
	 * @param par1 represents the Scanner Item
	 * @param block the Block that could be valuable
	 * @param metaData the Block metadata that could be valuable
	 * @return the value of the Ore
	 */
	public int getOreValue(ItemStack par1, Block block, int metaData);
	
	/**
	 * This function is not called by the Miner but it can be useful at some point
	 * @param par1 represents the Scanner Item, so that you can get range and co
	 * @param world which dimension it is searching for ore value
	 * @param x which xCoord you want to search
	 * @param y which yCoord you want to search
	 * @param z which zCoord you want to search
	 * @return the total ore value which is around you. Based on how you scan^^
	 */
	public int getOreValueOfArea(ItemStack par1, World world, int x, int y, int z);
	
}
