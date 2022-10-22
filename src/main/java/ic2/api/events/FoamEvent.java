package ic2.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public abstract class FoamEvent extends LevelEvent
{
	final BlockPos pos;
	public FoamEvent(LevelAccessor world, BlockPos pos)
	{
		super(world);
		this.pos = pos;
	}
	
	public BlockPos getPos()
	{
		return pos;
	}
	
	public BlockState getState()
	{
		return getLevel().getBlockState(getPos());
	}
	
	public BlockEntity getBlockEntity()
	{
		return getLevel().getBlockEntity(getPos());
	}
	
	@Cancelable
	public static class Check extends FoamEvent
	{
		TargetType type = TargetType.ANY;
		boolean isCustom = false;
		
		public Check(LevelAccessor world, BlockPos pos)
		{
			super(world, pos);
		}
		
		public void setCustomTarget(TargetType type)
		{
			this.type = type;
			isCustom = true;
		}
		
		public boolean isCustomPlacement()
		{
			return isCustom;
		}
		
		public TargetType getType()
		{
			return type;
		}
	}
	
	@Cancelable
	public static class Place extends FoamEvent
	{
		boolean placeFoam = false;
		
		public Place(LevelAccessor world, BlockPos pos)
		{
			super(world, pos);
		}
		
		public void requestFoamPlacement()
		{
			placeFoam = true;
		}
		
		public boolean shouldPlaceFoam()
		{
			return placeFoam;
		}
		
	}
	
	public enum TargetType
	{
		ANY,
		SCAFFOLD,
		CABLE,
		TUBE,
		PIPE,
		CUSTOM
	}
}
