package ic2.api.classic.trading.prefab;

import ic2.api.classic.trading.trades.ISubTrade;
import ic2.api.classic.trading.trades.ITrade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import net.minecraft.item.ItemStack;

public class BasicSubTrade implements ISubTrade
{
	ITrade trade;
	ItemStack needed;
	
	public BasicSubTrade(ITrade hoster, ItemStack wanted)
	{
		trade = hoster;
		needed = wanted;
	}
	
	@Override
	public ITrade getTrade()
	{
		return trade;
	}
	
	@Override
	public boolean canPerformTrade(UUID player)
	{
		return true;
	}
	
	@Override
	public List<ItemStack> getRequiredItems(UUID player)
	{
		return Arrays.asList(needed.copy());
	}

	@Override
	public List<String> getToolTip()
	{
		return EMPTY_LIST;
	}

	@Override
	public boolean isMoneyTrade()
	{
		return false;
	}

	@Override
	public int getNeededMoney()
	{
		return 0;
	}
}
