package ic2.api.blocks.wrench;

import ic2.api.blocks.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PillarWrenchHandler extends BaseWrenchHandler
{
	public static final IWrenchable INSTANCE = new PillarWrenchHandler();
	
	@Override
	public Direction getFacing(BlockState state, Level world, BlockPos pos) {
		switch(state.getValue(RotatedPillarBlock.AXIS))
		{
			case X: return Direction.EAST;
			case Y: return Direction.UP;
			case Z: return Direction.SOUTH;
			default: return null;
		}
	}
	
	@Override
	public boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		return state.getValue(RotatedPillarBlock.AXIS) != side.getAxis();
	}
	
	@Override
	public boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		return world.setBlockAndUpdate(pos, state.setValue(RotatedPillarBlock.AXIS, side.getAxis()));
	}
	
}
