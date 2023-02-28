package ic2.api.energy;

public class TransferStats
{
	final long energyIn;
	final long energyOut;
	final long energyLossIn;
	final long energyLossOut;
	
	public TransferStats(long energyIn, long energyOut, long energyLossIn, long energyLossOut)
	{
		this.energyIn = energyIn;
		this.energyOut = energyOut;
		this.energyLossIn = energyLossIn;
		this.energyLossOut = energyLossOut;
	}

	public long getEnergyIn()
	{
		return energyIn;
	}
	
	public long getEnergyOut()
	{
		return energyOut;
	}
	
	public long getEnergyLossIn()
	{
		return energyLossIn;
	}
	
	public long getEnergyLossOut()
	{
		return energyLossOut;
	}
	
	@Override
	public String toString()
	{
		return "TransferStats[In="+energyIn+", Out="+energyOut+", LossIn="+energyLossIn+", LossOut="+energyLossOut+"]";
	}
}
