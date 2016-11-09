package ic2.api.classic.trading.trades;

public interface IModifyableTrade<T> extends ITrade<T>
{
	/**
	 * function to delete a subtrade from a trade provider.
	 * This does not have to be supported but if it is supported
	 * then make sure that people can delete sub trades if they do not like it
	 * @param sub the subTrade that wants to be deleted
	 */
	public void deleteSubTrade(ISubTrade sub);
}
