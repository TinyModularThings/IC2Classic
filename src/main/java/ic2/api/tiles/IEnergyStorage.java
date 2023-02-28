package ic2.api.tiles;

import ic2.api.tiles.readers.IEUStorage;

public interface IEnergyStorage extends IEUStorage
{
	int addEnergy(int power);
	
	int drawEnergy(int power);
}
