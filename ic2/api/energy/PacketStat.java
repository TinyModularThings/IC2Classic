package ic2.api.energy;

public class PacketStat implements Comparable<PacketStat>
{
	public final int energy;
	public final long count;
	
	public PacketStat(int par1, long par2)
	{
		energy = par1;
		count = par2;
	}
	
	public long getPacketCount()
	{
		return count;
	}
	
	public int getPacketEnergy()
	{
		return energy;
	}

	@Override
	public int compareTo(PacketStat o)
	{
		if(o.energy < energy)
		{
			return 1;
		}
		if(o.energy > energy)
		{
			return -1;
		}
		return 0;
	}

}
