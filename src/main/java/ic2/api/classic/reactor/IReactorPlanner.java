package ic2.api.classic.reactor;

import ic2.api.reactor.IReactor;

/**
 * 
 * @author Speiger
 * 
 * Interface which lets you detect if the Reactor that is
 * using your Components is actually a ReactorPlanner.
 * In certain cases you want to know that.
 * 
 */
public interface IReactorPlanner extends IReactor
{
	/**
	 * Function if the reactor is collecting data for a prediction
	 * or something like that. That means no usual behavior is needed.
	 * This is mostly for Uranium-Cells or Isotopic cells which
	 * basically tell the reactor if they have a fuel pulse or
	 * a reEnrich pulse.
	 * @return the reactorPlanner makes a prediction
	 */
	public boolean isCollecting();
	
	/**
	 * Function called by the Uranium Cells whenever
	 * they would have a fuel pulse. But only if the planner is collecting.
	 */
	public void addFuelPulse();
	
	/**
	 * Function called by Isotopic Cells whenerver
	 * they would receive a pulse that would progress them.
	 * But only if the collecting is online.
	 */
	public void addReEnrichPulse();
	
}
