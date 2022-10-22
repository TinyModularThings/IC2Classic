package ic2.api.tiles;

import ic2.api.util.ILocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

public interface ITerraformer extends ILocation
{
	default Holder<Biome> getBiome(Level world, BlockPos pos)
	{
		return world.getBiome(pos);
	}
	
	boolean setBiome(Level world, BlockPos pos, ResourceLocation biomeKey);
	
	default BlockPos getFirstSolidBlockFrom(Level world, BlockPos pos)
	{
		MutableBlockPos mutable = new MutableBlockPos().set(pos);
		while(mutable.getY() > -1)
		{
			if(world.getBlockState(mutable).isRedstoneConductor(world, mutable)) return mutable.immutable();
			mutable.move(Direction.DOWN);
		}
		return mutable.immutable();
	}
	
	default BlockPos getFirstBlockFrom(Level world, BlockPos pos)
	{
		MutableBlockPos mutable = new MutableBlockPos().set(pos);
		while(mutable.getY() > 0)
		{
			if(!world.isEmptyBlock(mutable)) return mutable.immutable();
			mutable.move(Direction.DOWN);
		}
		mutable.setY(-1);
		return mutable.immutable();
	}
	
	default boolean switchGround(Level world, BlockPos pos, BlockState from, BlockState to, boolean upwards, boolean stateCompare)
	{
		if(upwards)
		{
			MutableBlockPos targetPos = new MutableBlockPos().setWithOffset(pos, Direction.UP);
			MutableBlockPos checkPos = new MutableBlockPos().set(pos);
			BlockPos up = targetPos.immutable();
			while(true)
			{
				if(world.isEmptyBlock(checkPos))
				{
					break;
				}
				BlockState state = world.getBlockState(checkPos);
				if(state.getBlock() != from.getBlock() || (stateCompare && state != from))
				{
					break;
				}
				targetPos.move(Direction.DOWN);
				checkPos.move(Direction.DOWN);
			}
			if(targetPos.getY() == up.getY())
			{
				return false;
			}
			world.setBlockAndUpdate(targetPos.immutable(), to);
			return true;
		}
		MutableBlockPos mutable = new MutableBlockPos().set(pos);
		while(true)
		{
			if(world.isEmptyBlock(mutable))
			{
				break;
			}
			BlockState state = world.getBlockState(mutable);
			if(state.getBlock() != to.getBlock() || (stateCompare && state != from))
			{
				break;
			}
			mutable.move(Direction.DOWN);
		}
		if(mutable.getY() < 0 || world.isEmptyBlock(mutable))
		{
			return false;
		}
		BlockState state = world.getBlockState(mutable);
		if(state.getBlock() != from.getBlock() || (stateCompare && state != from))
		{
			return false;
		}
		world.setBlockAndUpdate(mutable.immutable(), to);
		return true;
	}
}
