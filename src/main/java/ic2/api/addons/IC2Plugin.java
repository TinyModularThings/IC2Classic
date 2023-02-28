package ic2.api.addons;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 
 * @author Speiger
 * This Annotation is used to load Plugins of IC2Classic.
 * Just by having the Annotation added to the class it will automatically be loaded.
 * It is expected that {@link IModule} is also implemented by said class.
 * Plugins are designed in a way, where they can be turned off by the user in the ic2 config.
 * Therefore, the plugin must work as an optional module and should not be required to load.
 * Enabling/disabling a module using the config requires a game restart.
 *
 * Current API Version: 2
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface IC2Plugin
{
	/** @return display Name of the Plugin */
	String name();
	/** @return identifier of the Plugin when it needs to be looked up */
	String id();
	/** @return version of the Plugin */
	String version();
	
	/** @return the minimum API version required to run this Plugin. If a plugin requires a newer version then the one provided it won't load. */
	int requiredAPIVersion() default 0;
}
