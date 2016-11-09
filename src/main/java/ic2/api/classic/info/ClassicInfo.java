package ic2.api.classic.info;

import ic2.api.classic.wind.IWindManager;
import ic2.api.info.IIC2Classic;

public class ClassicInfo
{
	private static IIC2Classic ic2Instance;
	private static IWindManager customManager;
	
	public static IIC2Classic getIC2Instance()
	{
		return ic2Instance;
	}
	
	public static IWindManager getWindManager()
	{
		if(ic2Instance != null)
		{
			return ic2Instance.getWindManager();
		}
		return customManager;
	}
	
	public static boolean isIC2ClassicLoaded()
	{
		return ic2Instance != null;
	}
	
	public static void setCustomWindManager(IWindManager manager)
	{
		if(customManager == null)
		{
			customManager = manager;
		}
	}
	
	public static void setIC2Instance(IIC2Classic mod)
	{
		ic2Instance = mod;
	}
	
}
