package ic2.api.blocks.wrench;

import ic2.api.blocks.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HorizontalWrenchHandler extends BaseWrenchHandler
{
	public static final IWrenchable INSTANCE = new HorizontalWrenchHandler();
	
	@Override
	public Direction getFacing(BlockState state, Level world, BlockPos pos) {
		return state.getValue(HorizontalDirectionalBlock.FACING);
	}
	
	@Override
	public boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		return side.getAxis().isHorizontal() && state.getValue(HorizontalDirectionalBlock.FACING) != side;
	}
	
	@Override
	public boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		return side.getAxis().isHorizontal() && world.setBlockAndUpdate(pos, state.setValue(HorizontalDirectionalBlock.FACING, side));
	}
	
}
