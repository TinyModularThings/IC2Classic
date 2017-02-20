package ic2.api.classic.reactor;

import ic2.api.reactor.IReactor;

public interface IChamberReactor extends IReactor
{
	/**
	 * Function to update the Reactor Chambers
	 * Connected.
	 */
	public void refreshChambers();
	
	/**
	 * Function to get the Information how many
	 * chambers are loaded. This auto-calls refreshChambers();
	 * @return the ReactorSize. Between 3-9 (in ic2c)
	 */
	public int getReactorSize();
}
