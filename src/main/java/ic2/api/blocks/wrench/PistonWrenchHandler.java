package ic2.api.blocks.wrench;

import ic2.api.blocks.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PistonWrenchHandler extends BaseWrenchHandler
{
	public static final IWrenchable INSTANCE = new PistonWrenchHandler();

	@Override
	public Direction getFacing(BlockState state, Level world, BlockPos pos) {
		return state.getValue(PistonBaseBlock.FACING);
	}
	
	@Override
	public boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		return state.getValue(PistonBaseBlock.FACING) != side && !state.getValue(PistonBaseBlock.EXTENDED);
	}
	
	@Override
	public boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		if(state.getValue(PistonBaseBlock.EXTENDED)) return false;
		return world.setBlockAndUpdate(pos, state.setValue(PistonBaseBlock.FACING, side));
	}
	@Override
	public boolean canRemoveBlock(BlockState state, Level world, BlockPos pos, Player player) { return true; }
	@Override
	public double getDropRate(BlockState state, Level world, BlockPos pos, Player player) { return 1; }
}
