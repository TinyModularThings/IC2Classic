package ic2.api.energy.tile;

public interface IMultiEnergySource extends IEnergySource
{
	
	/**
	 * If you have a Transformer that lows energy down
	 * It sends the same amount of energy in more packets.
	 * This function is mostly used in transformers.
	 */
	public boolean sendMultibleEnergyPackets();
	
	/**
	 * returns the amount of Packets it sends.
	 * Note: You have to provide More than 0 EU for each packet.
	 * Else it breaks the Multible packets.
	 */
	public int getMultibleEnergyPacketAmount();
	
}
