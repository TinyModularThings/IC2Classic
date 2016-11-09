package ic2.api.classic.reactor;

import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;

public interface ISteamReactorComponent extends IReactorComponent
{
	
	/**
	 * 
	 * @param reactor Reference to the Reactor
	 * @param yourStack Reference to the specific instance of iterated ItemStack
	 * @param x X-coordinate of the stack in the grid
	 * @param y Y-coordinate of the stack in the grid
	 * @param heatrun every Stack will cycle 2 time (true, false) first run for heat, sec for Eu calculation
	 * @param damageTick is the tick when damage gets Applied, Steam reactor has a Faster Tickrate so that
	 */
	public void processTick(ISteamReactor reactor, ItemStack yourStack, int x, int y, boolean heatrun, boolean damageTick);
}
