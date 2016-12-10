package ic2.api.classic.audio;


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
	 * Sounds can be disabled if to many are get played at once near
	 * the player. This is a function if your sound is active or not
	 * While the sound is disabled you can only remove it or modify its pitch/volume
	 * All other actions are not getting processed
	 * @return if the sound is enabled
	 */
	public boolean isEnabled();
	
	/**
	 * Function to detect if the Sound isPlaying.
	 * @return true if playing. Pause = false
	 */
	public boolean isPlaying();
	
	/**
	 * @return is the sound is removed
	 */
	public boolean isRemoved();
	
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
	 * Function to detect the current pitch of a sound
	 * @return the pitch
	 */
	public float getPitch();
	
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
	 * function that allows you to get the direct position of the sound (4D)
	 * @return position of the sound
	 */
	public IAudioPosition getPosition();
	
	/**
	 * Manual function that allows you to update the position of the sound
	 * This function will be called automated
	 */
	public void updatePosition();
}
