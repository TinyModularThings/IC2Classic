package ic2.api.classic.audio;

import net.minecraft.entity.player.EntityPlayer;

public interface IAudioSource
{
	/**
	 * Removes the Sound completely
	 * If you call something after that it will cause problems
	 */
	public void remove();
	
	/**
	 * Starts the Sound
	 */
	public void play();
	
	/**
	 * Pauses the Sound
	 * Can be resumed with Play
	 */
	public void pause();
	
	/**
	 * Stops the Sound
	 * Can be Resumed with Play
	 */
	public void stop();
	
	/**
	 * Function to detect if the Sound isPlaying.
	 * @return true if playing. Pause = false
	 */
	public boolean isPlaying();
	
	/**
	 * Function to detect how loud the sound is in the SoundEngine
	 * @return SoundVolume
	 */
	public float getVolume();
	
	/**
	 * Function to change the Volume of the Sound
	 * @param volume Next SoundVolume
	 */
	public void setVolume(float volume);
	
	/**
	 * Function to to set the Pitch of the Sound
	 * @param pitch new Pitch of the Sound
	 */
	public void setPitch(float pitch);
	
	/**
	 * Function to detect how loud the Sound is.
	 * @return
	 */
	public float getRealVolume();

	/**
	 * Function to detect if the Sound is in the same Dimension as the Player
	 * @param player The Player
	 * @return true if it is in the same Dimension
	 */
	public boolean matchDimension(EntityPlayer player);
	

}
