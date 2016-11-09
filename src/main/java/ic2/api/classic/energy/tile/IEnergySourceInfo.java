package ic2.api.classic.energy.tile;

import ic2.api.energy.tile.IEnergySource;

public interface IEnergySourceInfo extends IEnergySource
{
	/**
	 * Function to make the energyNet way more efficient.
	 * As soon you make a EnergySource paths get created based of a Energy the
	 * machine will be ever able to create. And to decrease lag it should be only
	 * able to scan for the range it can deliver. So if you want to increase performance
	 * then support this feature
	 * @return you return the maximum energy the source is able to produce
	 */
	public int getMaxSendingEnergy();
}
