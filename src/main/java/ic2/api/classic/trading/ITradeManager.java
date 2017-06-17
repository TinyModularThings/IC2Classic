package ic2.api.classic.trading;

import java.util.List;
import java.util.Map;

import ic2.api.classic.trading.providers.ITradeProvider;
import ic2.api.classic.trading.trades.ITrade;
import ic2.api.classic.trading.trades.TradeType;
import net.minecraft.nbt.NBTTagCompound;

public interface ITradeManager
{
	/**
	 * Access to trade Class for the right id
	 * @param id you provide
	 * @return Class of the trade but can be null if not registered
	 */
	public Class<? extends ITrade> getClassForID(String id);
	
	/**
	 * Function to get the ID from a Class
	 * @param clz Class you want to have the ID from
	 * @return the id. Can be null if not registered
	 */
	public String getIDFromClass(Class<? extends ITrade> clz);
	
	/**
	 * Function to register a ITradeClass with the right id
	 * @param trade Class you want to register
	 * @param id the id for it.
	 * @Note: Can throw a runtimeException if already used
	 */
	public void registerTrade(Class<? extends ITrade> trade, String id);
	
	/**
	 * Helper function to save a Trade to NBT
	 * @param trade the trade you want to save
	 * @return the NBTTagCompound of it (If nothing was saved its returning a empty nbttag)
	 */
	public NBTTagCompound saveTradeToNBT(ITrade trade);
	
	/**
	 * Helper function to create a ITrade from NBT
	 * @param nbt NBT That contains Trade
	 * @return the trade can be null. Throws no runtime exception
	 */
	public ITrade createTradeFromNBT(NBTTagCompound nbt);
	
	/**
	 * No Use Yet But its a little spoiler
	 * @param trade
	 */
	public void addWorldGenTrade(ITrade trade);
	
	/**
	 * Access to all world-generation trades
	 * @return the list. Unsorted by types
	 */
	public List<ITrade> getWorldGenTrades();
	
	/**
	 * Register Custom Trades that can be loaded into the TradeMaker.
	 * Note: These have to be registered Server & Client. 
	 * If the client misses it it will not be listed
	 * @param trade Trade Itself
	 * @param id The ID so the syncing can be happening.
	 */
	public void registerCustomTrade(ITrade trade, String id);
	
	/**
	 * Getting all Trades from the type you are asking for
	 * @param type The Type you want to have
	 * @return the fitting map.
	 */
	public Map<String, ITrade> getCustomTrades(TradeType type);
	
	/**
	 * Global listeners are simply there if you want to watch all made trades
	 * @param listener you want to register
	 */
	public void addGlobalListener(ITradeListener listener);
	
	/**
	 * Function that have to be called on Load or later when the TileEntity is loaded to world (Or Entity or anything else)
	 * What it does it load all the global listeners to the Trade-O-Mat so you do not have to take care of that
	 * @param prov the Trade-O-Mat
	 */
	public void onTraderCreated(ITradeProvider prov);
	
}
