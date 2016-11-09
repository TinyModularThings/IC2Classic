package ic2.api.classic.trading.providers;

import net.minecraftforge.fluids.FluidStack;

public interface IFluidTradeProvider extends ITradeProvider
{
	/**
	 * Function to request a FluidStack from a FluidProvider for a trade
	 * @param requested the amount of Fluid you want.
	 * @param simulate if it is only for a test (if it is not for a test then it removes it)
	 * @return the amount of Fluid that is provided
	 */
	public FluidStack getNearbyFluidStack(FluidStack asked, boolean simulate);
}
