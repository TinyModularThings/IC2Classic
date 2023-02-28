package ic2.api.reactor;

import net.minecraftforge.fluids.capability.templates.FluidTank;

public interface ISteamReactor extends IReactor
{
	FluidTank getWaterTank();
	
	FluidTank getSteamTank();
}
