package ic2.api.classic.wind;

import net.minecraft.world.World;

public interface IWindFactory
{
	/**
	 * Wind Factory API.
	 * It allows you to create a custom Wind Simulator if you want to.
	 * You can go very Basic or extremely complicated, how you wish
	 */
	
	/**
	 * Function to create the WindSimlator (new Instance) for the world
	 * It has to be IWindHandlerInfo simply so that people can access
	 * important data if they want to do detection systems or co.
	 * @param world The World which is the Simulator Needed for.
	 * @return The WindSimulator. 
	 * If null it checks the next one and returns default if it can not find any
	 */
	public IWindHandlerInfo createWindHander(World world);
	
	/**
	 * Function if your Factory can handle that type of world
	 * @param world The World that is requested
	 * @return if it can handle the world
	 */
	public boolean canHandleWorld(World world);
}
