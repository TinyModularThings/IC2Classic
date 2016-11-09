package ic2.api.classic.trading.trades;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.google.common.collect.ImmutableList;

public interface IItemTrade extends ITrade<ItemStack>
{
	public static final List<ItemStack> EMPTY_LIST = ImmutableList.of();
}
