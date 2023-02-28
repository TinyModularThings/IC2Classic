package ic2.api.blocks.wrench;

import ic2.api.blocks.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;

public class StairWrenchHandler extends BaseWrenchHandler
{
	public static final IWrenchable INSTANCE = new StairWrenchHandler();
	
	@Override
	public Direction getFacing(BlockState state, Level world, BlockPos pos) {
		return state.getValue(StairBlock.FACING).getOpposite();
	}
	
	@Override
	public boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		if(side.getAxis().isVertical()) {
			return side == Direction.DOWN ? state.getValue(StairBlock.HALF) == Half.TOP : state.getValue(StairBlock.HALF) == Half.BOTTOM; 
		}
		return state.getValue(StairBlock.FACING).getOpposite() != side;
	}
	
	@Override
	public boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side) {
		if(side.getAxis().isVertical()) {
			return world.setBlockAndUpdate(pos, state.setValue(StairBlock.HALF, (side == Direction.UP ? Half.TOP : Half.BOTTOM)));
		}
		return world.setBlockAndUpdate(pos, state.setValue(StairBlock.FACING, side.getOpposite()));
	}
}
