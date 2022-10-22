package ic2.api.energy.tile;

public interface IMultiEnergySource extends IEnergySource
{
	boolean hasMultiplePackets();
	
	int getPacketCount();
}
