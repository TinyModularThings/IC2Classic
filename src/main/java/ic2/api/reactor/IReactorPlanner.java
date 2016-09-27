package ic2.api.reactor;

import ic2.api.reactor.IReactor;

public interface IReactorPlanner extends IReactor
{
	/**
	 * A check if the ReactorPlanner is collecting Pulses
	 * @return if it collects pulses
	 */
	public boolean isCollecting();
	
	/**
	 * If your FuelRod receives a Uranium Pulse please call this function
	 * When collecting
	 */
	public void addFuelPulse();
	
	/**
	 * If your Isotopic cell receives a Uranium Pulse please call this function.
	 * When collecting
	 */
	public void addReEnrichPulse();
	
}
