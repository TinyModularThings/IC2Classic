package ic2.api.blocks.wrench;

import ic2.api.blocks.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HopperWrenchHandler extends BaseWrenchHandler
{
	public static final IWrenchable INSTANCE = new HopperWrenchHandler();

	@Override
	public Direction getFacing(BlockState state, Level world, BlockPos pos)
	{
		return state.getValue(HopperBlock.FACING);
	}
	
	@Override
	public boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side)
	{
		return side != Direction.UP && getFacing(state, world, pos) != side;
	}
	
	@Override
	public boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side)
	{
		return side != Direction.UP && world.setBlockAndUpdate(pos, state.setValue(HopperBlock.FACING, side));
	}
}
