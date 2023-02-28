package ic2.api.addons;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * 
 * @author Speiger
 * The Module Interface that is used together with the {@link IC2Plugin} Annotation.
 * This Interface is used to load Plugins.
 */
public interface IModule
{
	/** @return if the Plugin is able to load. Use this method to check for mod dependencies etc. */
	boolean canLoad(Dist side);
	
	/**
	 * Function to load the configs.
	 * This is called directly after a plugin got loaded, and before the config gets saved.
	 * IC2Classics internal config system is designed to load configs even if the plugin may not be loaded or if it is loaded late.
	 * Which Forges Config does no longer support. So when this function is called Configs are accessible to you.
	 * Note that: This code should only load config specific stuff and not any code that has to do with other mods.
	 * That should be in {{@link #preInit(IEventBus)}}.
	 */
	default void loadConfigs(){}
	
	/**
	 * Function that loads directly after the config has been saved.
	 * This is called at the end of IC2Classics preInit phase.
	 * @param modBus is access to the Mod Specific EventBus of IC2Classic.
	 */
	default void preInit(IEventBus modBus){}
	
	/** Called after IC2Classic has finished the FMLCommonSetupEvent */
	default void postInit(){}
}
