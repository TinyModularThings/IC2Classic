package ic2.api.classic.trading;

import java.util.UUID;

import ic2.api.classic.trading.providers.ITradeProvider;
import ic2.api.classic.trading.trades.ISubTrade;

public interface ITradeListener
{
	/**
	 * When a trade-o-mat has done a trade then he notify this class that he done one (Also which one but not how often)
	 * @param trade The trade which was done
	 * @param player Who did the trade
	 * @param prov Which trade-o-Mat was used
	 */
	public void onTradePerformed(ISubTrade sub, UUID player, ITradeProvider prov);
	
	/**
	 * When a Trade-O-Mat shuts down (Chunk-unload/broken/Killed) this function will be called on every Notify
	 * @param prov who is canceling the connection
	 */
	public void onListenBreakDown(ITradeProvider prov);
	
	/**
	 * When a Trade-O-Mat has created a link to your listener
	 * @param prov Who created the link
	 * Note: This is not called on customAdded once. It will be only called from the Global Loader
	 */
	public void onListenCreated(ITradeProvider prov);
}
