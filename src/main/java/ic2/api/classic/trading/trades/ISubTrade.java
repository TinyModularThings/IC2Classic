package ic2.api.classic.trading.trades;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemStack;

public interface ISubTrade
{
	public static final List<String> EMPTY_LIST = ImmutableList.of();
	
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
	
	/**
	 * This function is only used in the gui for the Trade-O-Mats
	 * so you can easier see how much money is needed.
	 * In IC2C there is no other use by it
	 * @return the currentTrade is a money trade
	 */
	public boolean isMoneyTrade();
	
	/**
	 * @return how much Money is needed to do this trade
	 */
	public int getNeededMoney();
}
