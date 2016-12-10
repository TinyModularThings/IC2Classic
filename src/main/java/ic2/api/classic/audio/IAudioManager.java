package ic2.api.classic.audio;

import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author Speiger
 *
 *
 * ReadOnly IC2 SoundAPI. It allows you to create SoundSources,
 * but you can only use the system and not add on to it.
 */
public interface IAudioManager
{
	/**
	 * Basic Single Sound Play function.
	 * @param obj Sound Source Producer. Valid Cases: TileEntity, Entity, IAudioPosition, ISoundProvider
	 * @param soundFile Path of the SoundFile (supports also Vanilla Sounds)
	 */
	public void playOnce(Object obj, ResourceLocation soundFile);
	
	/**
	 * More Advanced Single Sound Playing
	 * @param obj Sound Source Producer. Valid Cases: TileEntity, Entity, IAudioPosition, ISoundProvider
	 * @param positionSpec Which Type of Sound It is. (Block, Item, Backpack)
	 * @param soundFile The Sound File (supports also Vanilla Sounds)
	 * @param priorized if the SoundSource is a Priority
	 * @param volume Sound Volume
	 */
	public void playOnce(Object obj, PositionSpec positionSpec, ResourceLocation soundFile, boolean priorized, float volume);
	
	/**
	 * Function to remove all Sounds attached to the Object
	 * @param obj Sound Producer. Valid Cases: TileEntity, Entity, ISoundProvider
	 */
	public void removeSources(Object obj);
	
	/**
	 * This function creates a AudioSource which you can control.
	 * It will be not auto started or autoStopped.
	 * @param obj Sound Source Producer. Valid Cases: TileEntity, Entity, ISoundProvider
	 * @param soundFile Path of the SoundFile (supports also Vanilla Sounds)
	 * @return the instance of the Source. CAN BE NULL
	 */
	public IAudioSource createSource(Object obj, ResourceLocation soundFile);
	
	/**
	 * The more advanced version of the AudioSource Creation which you can control
	 * It will be not auto started or autoStopped.
	 * @param obj Sound Source Producer. Valid Cases: TileEntity, Entity, ISoundProvider
	 * @param positionSpec Which Type of Sound It is. (Block, Item, Backpack)
	 * @param soundFile The Sound File (supports also Vanilla Sounds)
	 * @param loop if the SoundFile should loop
	 * @param priorized if the SoundSource is a Priority
	 * @param volume Sound Volume
	 * @return the instance of the Source. CAN BE NULL
	 */
	public IAudioSource createSource(Object obj, PositionSpec positionSpec, ResourceLocation soundFile, boolean loop, boolean priorized, float volume);
	
	/**
	 * The Sound System has a configuration where you can decide how loud it is
	 * this function will tell you how loud it is.
	 * @return Master Volume of the SoundSystem
	 */
	public float getMasterVolume();
	
	/**
	 * This function will tell you the Default Value for sounds is.
	 * @return default SoundVolume
	 */
	public float getDefaultVolume();
	
	/**
	 * @return function that returns a default pitch if you do not know which pitch to use
	 */
	public float getDefaultPitch();
	
	/**
	 * This function allows you to check how loud the Part of the System is.
	 * @param type Which Type of Sound It is. (Block, Item, Backpack)
	 * @return Sound Volume for that type
	 */
	public float getVolumeForType(PositionSpec type);
}
