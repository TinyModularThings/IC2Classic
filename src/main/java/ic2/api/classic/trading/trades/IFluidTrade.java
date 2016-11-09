package ic2.api.classic.trading.trades;

import java.util.List;

import net.minecraftforge.fluids.FluidStack;

import com.google.common.collect.ImmutableList;


public interface IFluidTrade extends ITrade<FluidStack>
{
	public static final List<FluidStack> EMPTY_LIST = ImmutableList.of();
}
