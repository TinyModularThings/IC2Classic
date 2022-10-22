package ic2.api.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 
 * @author Speiger
 * 
 * This interface is the Support for IC2Classics Advanced Comparator.
 * It does support the Vanilla function, and also includes Directional Output which was removed from the Vanilla functionality a few years ago.
 *
 * This interface exists to reintroduce this feature.
 */
public interface IAdvancedComparable
{
	/**
	 * This function works exactly the same as {@linkplain Block#getAnalogOutputSignal(BlockState, Level, BlockPos)} just adding directional support
	 * @param state your state
	 * @param world the block is in
	 * @param pos the block is at
	 * @param side the side the comparator comes from
	 * @return the comparator output. Which is between 0-15
	 */
	int getComparatorInputOverride(BlockState state, Level world, BlockPos pos, Direction side);
}
