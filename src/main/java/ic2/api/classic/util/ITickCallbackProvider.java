package ic2.api.classic.util;

import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 * 
 * Ic2Classic tick listener helper. Its using a WorldTick & Server Tick event
 * but supports delays between ticks. 
 * But note it will not save Ticks when the game unloads.
 */
public interface ITickCallbackProvider
{
	/**
	 * function to add a world tick listener for 1 tick delay
	 * @param world the world you are in
	 * @param par1 the listener
	 */
	public void addCallback(World world, IWorldTickCallback par1);
	
	/**
	 * function to add a world tick listener for defined amount of ticks with delay
	 * @param world the world you are in
	 * @param par1 the listener
	 * @param delay the amount of ticks it should wait.
	 */
	public void addCallback(World world, IWorldTickCallback par1, int delay);
	
	/**
	 * function to add a server tick listener for 1 tick delay
	 * @param par1 the listener
	 */
	public void addCallback(IServerTickCallback par1);
	
	/**
	 * function to add a server tick listener for defined amount of ticks with delay
	 * @param par1 the listener
	 * @param delay the amount of ticks it should wait.
	 */
	public void addCallback(IServerTickCallback par1, int delay);
}
