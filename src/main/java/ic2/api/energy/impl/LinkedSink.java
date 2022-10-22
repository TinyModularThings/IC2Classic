package ic2.api.energy.impl;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LinkedSink implements IEnergyAcceptor
{
	Level world;
	BlockPos pos;
	int directions;
	
	public LinkedSink(Level world, BlockPos pos, Iterable<Direction> directions)
	{
		this.world = world;
		this.pos = pos;
		for(Direction dir : directions)
		{
			this.directions |= 1 << dir.get3DDataValue();
		}
	}
	
	public LinkedSink(Level world, BlockPos pos, Direction...directions)
	{
		this.world = world;
		this.pos = pos;
		for(int i = 0;i<directions.length;i++)
		{
			this.directions |= 1 << directions[i].get3DDataValue();
		}
	}
	
	public LinkedSink(Level world, BlockPos pos, int directions)
	{
		this.world = world;
		this.pos = pos;
		this.directions = directions;
	}
	
	public LinkedSink(Level world, BlockPos pos)
	{
		this.world = world;
		this.pos = pos;
		directions = 63;
	}
	
	public LinkedSink(BlockEntity tile, int directions)
	{
		world = tile.getLevel();
		pos = tile.getBlockPos();
		this.directions = directions;
	}
	
	public LinkedSink(BlockEntity tile)
	{
		world = tile.getLevel();
		pos = tile.getBlockPos();
		directions = 63;
	}
	
	@Override
	public Level getWorldObj()
	{
		return world;
	}
	
	@Override
	public BlockPos getPosition()
	{
		return pos;
	}
	
	@Override
	public boolean canAcceptEnergy(IEnergyEmitter emitter, Direction side)
	{
		return (directions & 1 << side.get3DDataValue()) != 0;
	}
	
}
