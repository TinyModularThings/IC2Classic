package ic2.api.classic.addon;

import java.util.Map;

import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.api.classic.addon.misc.SideGateway;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Classes implementing IModul are required to be marked with the {@link IC2Plugin} annotation.
 * For mod-like modules, use {@link SideGateway} instead of the {@link SidedProxy} annotation.
 * <br>{@link SidedProxy} example:
 * <pre>
 * {@code @}SidedProxy(clientSide = "clientside.proxyclass.name", serverSide = "serverside.proxyclass.name")
 * public static CommonProxy proxy;
 * 
 * public void init(FMLInitializationEvent event) {
 * 	proxy.init();
 * }
 * </pre>
 * {@link SideGateway} equivalent:
 * <pre>
 * public static SideGateway<CommonProxy> proxy = new SideGateway("serverside.proxyclass.name", "clientside.proxyclass.name");
 * 
 * public void init(FMLInitializationEvent event) {
 * 	proxy.get().init();
 * }
 * </pre>
 */
public interface IModul
{
	/**
	 * Specifies wether this module should be loaded or not.
	 * This allows making server or client only modules and you should check for required mods here.
	 * @param par1 {@link Side} on which the code is executed right now
	 * @return {@code true} if the module should be loaded, {@code false} otherwise
	 */
	public boolean canLoad(Side par1);
	
	/**
	 * Load your config, items and blocks here (and everything else which belongs to the {@link FMLPreInitializationEvent}).
	 * The {@code map} can be used to override ic2c blocks and items.
	 * This is executed before IC2C registers its items and blocks!
	 * @param preinit {@link FMLPreInitializationEvent} object
	 * @param map Override map
	 */
	public void preInit(FMLPreInitializationEvent preinit, Map<String, IOverrideObject> map);
	
	/**
	 * Load your config, items and blocks here (and everything else which belongs to the {@link FMLPreInitializationEvent}).
	 * This is executed after IC2C registers its items and blocks!
	 * @param preinit {@link FMLPreInitializationEvent} object
	 */
	public void preInit(FMLPreInitializationEvent preinit);
	/**
	 * Register your recipes here (and everything else which belongs to the {@link FMLInitializationEvent}).
	 * This is executed after IC2Cs recipes are registered!
	 * @param preinit {@link FMLInitializationEvent} object
	 */
	public void init(FMLInitializationEvent init);
	
	/**
	 * Register your TileEntities and other {@link FMLPostInitializationEvent} stuff here.
	 * This is executed before IC2C registers its TileEntities!
	 * @param preinit {@link FMLPostInitializationEvent} object
	 */
	public void postInit(FMLPostInitializationEvent postinit);
	
	/**
	 * @param loaded {@link FMLLoadCompleteEvent} object
	 */
	public void gameLoaded(FMLLoadCompleteEvent loaded);
	
	/**
	 * Use this function to register your own textures if you want to use the IC2C spritesheets instead of writing json models for your items and blocks.
	 */
	@SideOnly(Side.CLIENT)
	public void onTextureLoad();
	
	/**
	 * Called on the {@link net.minecraftforge.fml.common.event.FMLServerStartingEvent} to allow registering stuff.
	 * @param server The current server instance
	 */
	public void onServerStarting(MinecraftServer server);
	
	/**
	 * Called on the {@link net.minecraftforge.fml.common.event.FMLServerStoppedEvent} to allow cleaning up stuff.
	 */
	public void onServerStopped();
}
