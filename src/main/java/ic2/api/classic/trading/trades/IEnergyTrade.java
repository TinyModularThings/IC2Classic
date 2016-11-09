package ic2.api.classic.trading.trades;

import java.util.List;

import com.google.common.collect.ImmutableList;


public interface IEnergyTrade extends ITrade<Integer>
{
	public static final List<Integer> EMPTY_LIST = ImmutableList.of();
}
