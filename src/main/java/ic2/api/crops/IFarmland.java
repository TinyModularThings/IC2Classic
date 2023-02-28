package ic2.api.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 
 * @author Speiger
 *
 * Farmlands are the Blocks directly underneath the Crop Itself.
 * These Provide a rather large boni to the crop they are standing on.
 * 
 * The internal rule of thumb is.
 * A Farmland under normal conditions is only allowed to give a +3 to a stat.
 * If you want to give more then you have to have down side that ensures a re-balance of a +3
 * For example: Soulsand gives a +5 in Nutriens, but a -2 in Humidity. 
 * 
 * There is exception to the rule where the block has special conditions that makes the building of said Blocks
 * much much harder. Where the area around the Block itself has to maintain also special conditions.
 * Normal Vanilla Farmland fulfills said condition.
 * 
 * For that the rule of thumb is that there is a +4 or a +5 total boost if there is a significant building limitation.
 */
public interface IFarmland
{
	/**
	 * Function to give a boni towards the humidity stat.
	 * This will be capped between 0-20
	 * @param world the Farmland is in.
	 * @param pos the Farmland is at.
	 * @return the humidity Boni.
	 */
	default int getHumidity(Level world, BlockPos pos)
	{
		return getHumidity(world.getBlockState(pos));
	}
	
	/**
	 * @see IFarmland#getHumidity(Level, BlockPos)
	 */
	int getHumidity(BlockState state);
	
	/**
	 * Function to give a boni towards the nutrient stat.
	 * This will be capped between 0-20
	 * @param world the Farmland is in.
	 * @param pos the Farmland is at.
	 * @return the nutrient Boni.
	 */
	default int getNutrients(Level world, BlockPos pos)
	{
		return getNutrients(world.getBlockState(pos));
	}
	
	/**
	 * @see IFarmland#getNutrients(Level, BlockPos)
	 */
	int getNutrients(BlockState state);
	
	/**
	 * If a Farmland has a Special BlockState required to actually apply its effect then return this true.
	 * The Farmland Block for example uses this.
	 * @return true if you need a Special BlockState to require its effect to take place
	 */
	default boolean isSpecial() { return false; }
	
	/**
	 * The special State required to make its effect happen.
	 * This is only used for Tooltips
	 * @param block that has a Farmland Block.
	 * @return the BlockState that does the best effect.
	 */
	default BlockState getSpecialState(Block block) { return block.defaultBlockState(); }
}
