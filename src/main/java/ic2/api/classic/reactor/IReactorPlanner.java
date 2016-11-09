package ic2.api.classic.reactor;

import ic2.api.reactor.IReactor;

public interface IReactorPlanner extends IReactor
{
	public boolean isCollecting();
	
	public void addFuelPulse();
	
	public void addReEnrichPulse();
	
}
