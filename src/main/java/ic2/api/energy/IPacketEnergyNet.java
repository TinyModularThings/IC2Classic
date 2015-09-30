package ic2.api.energy;

import java.util.List;

import net.minecraft.tileentity.TileEntity;

public interface IPacketEnergyNet extends IEnergyNet
{
	public List<PacketStat> getSendedPackets(TileEntity par1);
	
	public List<PacketStat> getTotalSendedPackets(TileEntity par1);
}
