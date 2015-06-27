package ic2.api.info;

import cpw.mods.fml.common.Loader;

public class IC2Classic
{
	public static IWindTicker windNetwork;
	private static IC2Type ic2 = IC2Type.NeedLoad;
	
	


	
	public static IC2Type getLoadedIC2Type()
	{
		if(ic2 == IC2Type.NeedLoad)
		{
			updateState();
		}
		return ic2;
	}
	
	public static boolean isIc2ExpLoaded()
	{
		if(ic2 == IC2Type.NeedLoad)
		{
			updateState();
		}
		return ic2 == IC2Type.Experimental;
	}
	
	public static boolean isIc2ClassicLoaded()
	{
		if(ic2 == IC2Type.NeedLoad)
		{
			updateState();
		}
		return ic2 == IC2Type.SpeigersClassic;
	}
	
	public static IWindTicker getWindNetwork()
	{
		return windNetwork;
	}
	
	public static boolean enabledCustoWindNetwork()
	{
		return windNetwork != null;
	}
	
	
	
	
	static void updateState()
	{
		if(Loader.isModLoaded("IC2-Classic"))
		{
			ic2 = IC2Type.ImmibisClassic;
			return;
		}
		if(Loader.isModLoaded("IC2"))
		{
			try
			{
				Class clz = Class.forName("ic2.core.IC2");
				clz.getField("ironName").get(null);
				clz.getField("displayNoUseItems").get(null);
				ic2 = IC2Type.SpeigersClassic;
			}
			catch(Exception e)
			{
				ic2 = IC2Type.Experimental;
			}
		}
		else
		{
			ic2 = IC2Type.None;
		}
	}
	
	public static enum IC2Type
	{
		NeedLoad,
		Experimental,
		SpeigersClassic,
		ImmibisClassic,
		None;
	}
	
}
