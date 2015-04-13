package ic2.api.info;

public class IC2Classic
{
	public static Object ic2Classic;
	public static IWindTicker windNetwork;
	
	public static boolean isIc2ClassicLoaded()
	{
		return ic2Classic != null;
	}
	
	public static IWindTicker getWindNetwork()
	{
		return windNetwork;
	}
	
	public static boolean enabledCustoWindNetwork()
	{
		return windNetwork != null;
	}
	
}
