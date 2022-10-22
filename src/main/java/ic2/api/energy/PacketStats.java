package ic2.api.energy;

import ic2.api.energy.tile.IEnergyTile;

public class PacketStats
{
	final IEnergyTile tile;
	final long packets;
	final long power;
	final boolean accepting;
	
	public PacketStats(IEnergyTile tile, long packets, long power, boolean accepting)
	{
		this.tile = tile;
		this.packets = packets;
		this.power = power;
		this.accepting = accepting;
	}
	
	public IEnergyTile getTile()
	{
		return tile;
	}
	
	public long getPackets()
	{
		return packets;
	}
	
	public long getPower()
	{
		return power;
	}
	
	public boolean isAccepting()
	{
		return accepting;
	}
}