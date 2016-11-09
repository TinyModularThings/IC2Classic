package ic2.api.classic.reactor;

import ic2.api.reactor.IReactorChamber;

public interface ISteamReactorChamber extends IReactorChamber
{
	/**
	 * Accessing the SteamReactor from Chambers
	 */
	@Override
	public ISteamReactor getReactorInstance();
}
