package ic2.api.classic.recipe.custom;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public interface IOreRegistry
{
	/**
	 * Custom Registry that allows you to register
	 * OreValues for Scanners & Miner
	 * @param block The Block that gets the value
	 * @param value of the Ore
	 * @Note: Wild-card function. Using that will apply it to all ores
	 */
	public void registerValueableOre(Block block, int value);
	
	/**
	 * Function to register a Value for the state you provide
	 * @param state is used for the value (has to be a meta state)
	 * @param value of the State
	 */
	public void registerValueableOre(IBlockState state, int value);
	
	/**
	 * Function to get the OreValue of the state.
	 * This function does also check for Wild-cards
	 * @param state you provide
	 * @return oreValue
	 */
	public int getOreValue(IBlockState state);
	
	/**
	 * Wild-card request function
	 * @param block you request the value of
	 * @return the Wild-Card value of the Block
	 */
	public int getOreValue(Block block);
	
	/**
	 * Function needed for Internal Stuff.
	 * If you decide to override the API this has to be supported
	 * @return the highest ever registered OreValue ever
	 */
	public int getHighestOreValue();
	
	/**
	 * Access function for the registry
	 * @return all entries of the Registry
	 */
	public Map<Block, Map<Integer, Integer>> getOreValueMap();
	
	/**
	 * Function to delete 1 entry of the Block
	 * @param state the entry you want to delete
	 */
	public void removeOreEntry(IBlockState state);
	
	/**
	 * Function to delete all entries of that block
	 * @param block entry you want to delete
	 */
	public void removeOre(Block block);
}
