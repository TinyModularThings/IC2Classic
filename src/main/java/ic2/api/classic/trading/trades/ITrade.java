package ic2.api.classic.trading.trades;

import java.util.List;
import java.util.UUID;

import ic2.api.classic.trading.ITradeInventory;
import ic2.api.classic.trading.providers.ITradeProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;

public interface ITrade<T>
{
	/**
	 * Check if the player can access the trade
	 * @param player the player that wants to get the trade
	 * @return true if it can be accessed
	 */
	public boolean canAccessTrade(UUID player);
	
	/**
	 * @return which type of trade it is (Item, Fluid, Energy)
	 */
	public TradeType getType();
	
	/**
	 * functions to get all subtrades for the player
	 * @param player the player who wants to get subtrade
	 * @return if empty means he can not access any trade
	 */
	public List<ISubTrade> getSubTrades(UUID player, boolean ignoreOwner);
	
	/**
	 * function to get a special sub Trade
	 * @param slot the subTradeID
	 * @param player the player who should get it.
	 * @return the sub Trade for the player. can be null if the player is no longer able to get the sub Trade
	 */
	public ISubTrade getSubTradeFromSlot(int slot, UUID player, boolean ignoreOwner);
	
	/**
	 * Function to get the ID for the subTrade
	 * @param sub the sub Trade
	 * @return the id. -1 means is not existent
	 */
	public int getTradeSlot(ISubTrade sub);
	
	/**
	 * Function to perform the trade. 
	 * @param player Player who wants to perform the trade
	 * @param prov the TradeProvider that is used.
	 * @param trade The Trade you want to perform
	 * @param inv the inventory that is used for in & output
	 * @param limit how many t rades you want to do at once
	 * @return the result. Based on the Trade Type it can be either (items, Fluids, Energy). 
	 * Fail = Can no longer perform the trade. If it is a trade automate delete it then.
	 * Pass = Trade can not be done at the moment. Just wait a bit...
	 * Success = Trade was performed.
	 */
	public ActionResult<List<T>> trade(UUID player, ITradeProvider prov, ISubTrade trade, ITradeInventory inv, int limit);
	
	/**
	 * Function for just reading what you would get out of the trade
	 * @param sub The trade you want to check.
	 * @return the list of possible result
	 */
	public List<T> getTradeResults(ISubTrade sub);
	
	/**
	 * Basic read function.
	 * @param nbt NBTData of the tade
	 * @throws Exception if the trade is invalid or corrupted it will throw that exception and you then are not allowed to add it.
	 */
	public void readFromNBT(NBTTagCompound nbt) throws Exception;
	
	/**
	 * Saving function for the trade
	 * @param nbt the DataStorage for the trade
	 */
	public void writeToNBT(NBTTagCompound nbt);
	
	/**
	 * Function which allows you to recreate a trade (copy it) with a differend owner
	 * this is used for mod registered trades and someone wants to provide these trades.
	 * The owner ship means just that the player can read it without issues
	 * @param newOwner the new Owner
	 * @return the trade that is copied.
	 */
	public ITrade<T> newInstance(UUID newOwner);
	
	/**
	 * function to tell how often it can be performed at once at the provider and by the player
	 * @param sub the trade who needs to be checkt
	 * @param prov the provider who provides the items
	 * @param player the player who wants to use the trade
	 * @return the amount how often it can be used
	 */
	public int getStockCount(ISubTrade sub, ITradeProvider prov, UUID player);
	
}
