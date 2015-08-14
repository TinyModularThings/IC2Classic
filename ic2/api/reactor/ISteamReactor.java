package ic2.api.reactor;

import net.minecraftforge.fluids.FluidTank;

/**
 * 
 * @author Speiger
 *
 * Steam reactors work differed then Electric Reactors,
 * Instead of producing EU directly it creates Steam which,
 * you can create to Energy. The good thing is that you are not limited
 * to EU itself.
 * Steam Reactors will have WaterTank and a Steam Tank.
 *
 */
public interface ISteamReactor extends IReactor
{
	/**
	 * Water Tank that provides water for the Components.
	 * Simple Access to the tank for Components
	 */
	public FluidTank getWaterTank();
	
	/**
	 * Steam that is produced by the Components.
	 * Simple Access to the Tank for Components
	 */
	public FluidTank getSteamTank();
}
