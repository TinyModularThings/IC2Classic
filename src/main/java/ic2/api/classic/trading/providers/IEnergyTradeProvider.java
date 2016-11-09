package ic2.api.classic.trading.providers;

public interface IEnergyTradeProvider extends ITradeProvider
{
	/**
	 * Function to request energy from a EnergyProvider for a trade
	 * @param requested the amount of Energy you want.
	 * @param simulate if it is only for a test (if it is not for a test then it removes it)
	 * @return the amount of Energy that is provided
	 */
	public int getNearbyEnergy(int requested, boolean simulate);
}
