package ic2.api.classic.energy;

public class PacketStat implements Comparable<PacketStat>
{
	public boolean out;
	public int energy;
	public long count;
	
	public PacketStat(boolean sending, int packetEnergy, long packetCount)
	{
		out = sending;
		energy = packetEnergy;
		count = packetCount;
	}
	
	/**
	 * @return If the packet is a Emit Packet. (If it got send or received)
	 */
	public boolean isEmitting()
	{
		return out;
	}
	
	/**
	 * @return How many packets got into this Energy Level.
	 */
	public long getPacketCount()
	{
		return count;
	}
	
	/**
	 * @return How much Energy is inside these packets
	 */
	public int getPacketEnergy()
	{
		return energy;
	}
	
	/**
	 * Sorter if you want to sort these packets by energy
	 */
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
