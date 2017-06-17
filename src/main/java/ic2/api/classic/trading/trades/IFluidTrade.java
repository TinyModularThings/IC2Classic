package ic2.api.classic.trading.trades;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraftforge.fluids.FluidStack;


public interface IFluidTrade extends ITrade<FluidStack>
{
	public static final List<FluidStack> EMPTY_LIST = ImmutableList.of();
}
