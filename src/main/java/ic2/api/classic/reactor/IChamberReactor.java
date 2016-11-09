package ic2.api.classic.reactor;

import ic2.api.reactor.IReactor;

public interface IChamberReactor extends IReactor
{
	public void refreshChambers();
	
	public int getReactorSize();
}
