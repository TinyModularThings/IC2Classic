package ic2.api.classic.addon.misc;

import net.minecraftforge.fml.common.FMLCommonHandler;

public final class SideGateway<T>
{
	private final T clientInstance;
	private final T serverInstance;
	
	public SideGateway(String server, String client)
	{
		try
		{
			if(FMLCommonHandler.instance().getSide().isClient())
			{
				this.clientInstance = (T)Class.forName(client).newInstance();
			}
			else
			{
				this.clientInstance = null;
			}
			
			this.serverInstance = (T)Class.forName(server).newInstance();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public T get()
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			return this.clientInstance;
		}
		return this.serverInstance;
	}
	
	public T get(boolean simulating)
	{
		if(simulating)
		{
			return this.serverInstance;
		}
		return this.clientInstance;
	}
}
