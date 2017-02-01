package ic2.api.classic.audio;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 * 
 * This class allows you to make custom AudioPosition classes
 * which do not have to be static so you can make moving sounds with this class
 * Things to know: This class does not get saved and does not receive a update tick
 * so if you want to have a moving sound you have to update it yourself.
 * This class is cached in the IAudioSource and calls getPosition/getWorld every tick
 */
public interface IAudioPosition
{
	/**
	 * @return current position of the sound
	 */
	public Vec3d getPosition();
	
	/**
	 * @return currentWorld of the sound
	 */
	public World getWorld();
}
