package ic2.api.energy.impl;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LinkedSource implements IEnergyEmitter
{
	Level world;
	BlockPos pos;
	int directions;
	
	public LinkedSource(Level world, BlockPos pos, Iterable<Direction> directions)
	{
		this.world = world;
		this.pos = pos;
		for(Direction dir : directions)
		{
			this.directions |= 1 << dir.get3DDataValue();
		}
	}
	
	public LinkedSource(Level world, BlockPos pos, Direction...directions)
	{
		this.world = world;
		this.pos = pos;
		for(int i = 0;i<directions.length;i++)
		{
			this.directions |= 1 << directions[i].get3DDataValue();
		}
	}
	
	public LinkedSource(Level world, BlockPos pos, int directions)
	{
		this.world = world;
		this.pos = pos;
		this.directions = directions;
	}
	
	public LinkedSource(Level world, BlockPos pos)
	{
		this.world = world;
		this.pos = pos;
		directions = 63;
	}
	
	public LinkedSource(BlockEntity tile, int directions)
	{
		world = tile.getLevel();
		pos = tile.getBlockPos();
		this.directions = directions;
	}
	
	public LinkedSource(BlockEntity tile)
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
	public boolean canEmitEnergy(IEnergyAcceptor acceptor, Direction side)
	{
		return (directions & 1 << side.get3DDataValue()) != 0;
	}
}
