package ic2.api.classic.trading;

import ic2.api.classic.trading.providers.ITradeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ITradeMarket
{
	/**
	 * Money value of the requested stack. Does not have to be a coin.
	 * @param stack The Item that it asks with
	 * @return the money value of it
	 */
	public int getMoneyValue(ItemStack stack);
	
	/**
	 * @param prov if a provider gets unloaded it will notify you.
	 */
	public void onProviderStops(ITradeProvider prov);
	
	/**
	 * @return world of the TradeMarket
	 */
	public World getWorld();
	
	/**
	 * @return Position of the TradeMarket
	 */
	public BlockPos getPos();
	
	public static interface ITradeMarketManager
	{
		/**
		 * Money value of the requested stack. Does not have to be a coin.
		 * @param stack The Item that it asks with
		 * @return the money value of it
		 */
		public int getMoneyValue(ItemStack stack);
		
		/**
		 * function to add a market to a TradeMarket Manager
		 * @param marked you want to add
		 */
		public void addMarket(ITradeMarket marked);
		
		/**
		 * function to remove a market from a manager
		 * @param marked you want to remove
		 */
		public void removeMarket(ITradeMarket marked);
	}
}
