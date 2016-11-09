package ic2.api.classic.wind;

import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 *
 * Wind Manager Class from IC2 Classic
 * You can get the WindSpeed from a world from this one
 * You can register a Custom Wind Factory to create your own wind simulation
 * if you want to.
 * If no Wind Factory found it uses the default from classic
 */
public interface IWindManager
{
	/**
	 * Class to register your own Wind Factory. Only on Game load since the
	 * factory will be used for every world it can support
	 * @param factory You want to register. Null Will be caught
	 */
	public void registerWindHandlerFactory(IWindFactory factory);
	
	/**
	 * Function to get the WindHandler from the world.
	 * If you really want to you can cast it to IWindHandlerInfo
	 * to get detailed information about the wind Simulation.
	 * @param world The world you need it for
	 * @return Wind Simulator of that world
	 */
	public IWindHandler getHanlderFromWorld(World world);
}
