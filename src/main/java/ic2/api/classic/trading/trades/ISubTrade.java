package ic2.api.classic.trading.trades;

import java.util.List;
import java.util.UUID;

import net.minecraft.item.ItemStack;

public interface ISubTrade
{
	/**
	 * @return the trade Which it is bound to
	 */
	public ITrade getTrade();
	
	/**
	 * Check if the player can perform this trade
	 * @param player the player wants to use this trade
	 * @return if it can be performed
	 */
	public boolean canPerformTrade(UUID player);
	
	/**
	 * Function to show which items are required for the trade
	 * @param player the player who want to do the trade
	 * @return the list of required items
	 */
	public List<ItemStack> getRequiredItems(UUID player);
	
	/**
	 * If a trade has a toolTip here you can return it
	 * @return the list of text you want to display
	 */
	public List<String> getToolTip();
}
