package ic2.api.blocks.wrench;

import ic2.api.blocks.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DispenserWrenchHandler extends BaseWrenchHandler
{
	public static final IWrenchable INSTANCE = new DispenserWrenchHandler();
	
	@Override
	public Direction getFacing(BlockState state, Level world, BlockPos pos)
	{
		return state.getValue(DispenserBlock.FACING);
	}
	
	@Override
	public boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side)
	{
		return state.getValue(DispenserBlock.FACING) != side;
	}
	
	@Override
	public boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side)
	{
		return world.setBlockAndUpdate(pos, state.setValue(DispenserBlock.FACING, side));
	}
	
}
