package ic2.api.blocks.wrench;

import java.util.List;

import ic2.api.blocks.IWrenchable;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public abstract class BaseWrenchHandler implements IWrenchable
{

	@Override
	public boolean doSpecialAction(BlockState state, Level world, BlockPos pos, Direction dir, Player player, Vec3 hit) { return false; }
	@Override
	public AABB hasSpecialAction(BlockState state, Level world, BlockPos pos, Direction dir, Player player, Vec3 hit) { return null; }
	@Override
	public boolean canRemoveBlock(BlockState state, Level world, BlockPos pos, Player player) { return false; }
	@Override
	public double getDropRate(BlockState state, Level world, BlockPos pos, Player player) { return 0; }
	@Override
	public List<ItemStack> getDrops(BlockState state, Level world, BlockPos pos, Player player) {
		if(!(world instanceof ServerLevel)) return ObjectLists.emptyList();
		return Block.getDrops(state, (ServerLevel)world, pos, null);
	}
	
}
