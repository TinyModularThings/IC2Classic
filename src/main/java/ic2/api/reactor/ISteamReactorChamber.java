package ic2.api.reactor;

public interface ISteamReactorChamber extends IReactorChamber
{
	/**
	 * Accessing the SteamReactor from Chambers
	 */
	public ISteamReactor getReactor();
}
