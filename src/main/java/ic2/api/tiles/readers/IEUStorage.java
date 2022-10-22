package ic2.api.tiles.readers;

public interface IEUStorage
{
	int getStoredEU();
	
	int getMaxEU();
	
	int getTier();
	
	default double getChargeLevel()
	{
		return (double)getStoredEU() / (double)getMaxEU();
	}
}
