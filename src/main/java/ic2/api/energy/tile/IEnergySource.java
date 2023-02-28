package ic2.api.energy.tile;

public interface IEnergySource extends IEnergyEmitter
{
	int getSourceTier();
	
	int getMaxEnergyOutput();
	
	int getProvidedEnergy();
	
	void consumeEnergy(int consumed);
	
	//When the energyNet couldn't send energy
	default void onPacketFailed()
	{
		
	}
}
