package ic2.api.classic.trading.trades;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemStack;

public interface IItemTrade extends ITrade<ItemStack>
{
	public static final List<ItemStack> EMPTY_LIST = ImmutableList.of();
}
