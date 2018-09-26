package ic2.api.classic.addon;

import java.util.Map;

import ic2.api.classic.addon.misc.IOverrideObject;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PluginBase implements IModul
{
	@Override
	public void preInit(FMLPreInitializationEvent preinit, Map<String, IOverrideObject> map)
	{
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent preinit)
	{
	}
	
	@Override
	public void init(FMLInitializationEvent init)
	{
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent postinit)
	{
	}
	
	@Override
	public void gameLoaded(FMLLoadCompleteEvent loaded)
	{
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onTextureLoad()
	{
	}
	
	@Override
	public void onServerStarting(MinecraftServer server)
	{
	}
	
	@Override
	public void onServerStopped()
	{
	}
}
