package ic2.api.classic.addon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;

/**
 * 
 * @author Speiger
 *
 * Loader Interface for the IModul addons for IC2Classic.
 * This interface API allows you to load IC2 Classic addons easier and saver.
 * Which a couple ups and downs.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.TYPE})
public @interface IC2Plugin
{
	/**
	 * @return name of the Plugin/Addon
	 */
	public String name();
	
	/**
	 * @return Plugin / Addon id thats unique and has to be lowercased.
	 * also used for cross access between plugins
	 */
	public String id();
	
	/**
	 * @return version of the Plugin / Addon
	 */
	public String version();
	
	/**
	 * If your Plugin / Addon has textures, lang or any file that it needs to be loaded from the jar then this flag makes sure that it gets loaded
	 * @return true if jar resources should be loaded
	 */
	public boolean hasResourcePack() default false;
	
	/**
	 * Flag for adding the Plugin / Addon class to MinecraftForges Event bus as soon its being loaded.
	 * Reduces extra manual call if that is needed.
	 * @return if the Plugin / Addon class should be auto-added to MCForges Event bus.
	 */
	public boolean eventListener() default false;
	
    /**
     * An optional GUI factory for this mod. This is the name of a class implementing {@link IModGuiFactory} that will be instantiated
     * on the client side, and will have certain configuration/options guis requested from it.
     *
     * @return The name of a class implementing {@link IModGuiFactory}
     */
	public String guiConfig() default "";
}
