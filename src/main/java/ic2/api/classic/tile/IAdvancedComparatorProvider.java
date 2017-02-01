package ic2.api.classic.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 * Block Inteface.
 * Class for IC2Classics sided Comparator to allowing you to give out a sided Comparator signal
 */

public interface IAdvancedComparatorProvider
{
	/**
	 * Function to get the comparator output of the Block.
	 * Note hasComparatorInput() is the trigger to call this function.
	 * And it works only with the Advanced Comparator.
	 * @param state your Block
	 * @param world the world your Block is in
	 * @param pos the pos your Block is in
	 * @param side the side the input is requested from
	 * @return the output value between 0-15
	 */
	public int getComparatorOutput(IBlockState state, World world, BlockPos pos, EnumFacing side);
}
