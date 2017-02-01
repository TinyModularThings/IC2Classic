package ic2.api.classic.audio;

import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 * 
 * This class allows you to install custom AudioSources to IC2Classics
 * sound engine that it would not be able to handle otherwise.
 * It can only handle Entity & TileEntity sounds
 * But if you want to do anything except that it would not be able to
 * handle it.
 * This allows it to you to make it handle-able.
 * Note: You have to override equals and hashCode function and do a proper check
 */
public interface ISoundProvider
{
	/**
	 * function that validates that the soundProvider is still there to cause
	 * sound effects.
	 * Also it can be false if the dimension is not the same.
	 * @return If the sound provider is valid and if the dimension is the same.
	 */
	public boolean isValid(World world);
	
	/**
	 * function to get the SoundPosition of the provider
	 * This is called only once when the sound is created
	 * @return audioPosition
	 */
	public IAudioPosition getAudioPositon();
	
}
