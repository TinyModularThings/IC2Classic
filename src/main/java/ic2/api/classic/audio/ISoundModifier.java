package ic2.api.classic.audio;

import net.minecraft.world.World;

public interface ISoundModifier
{
	/**
	 * Validates that the modifier is in the right world.
	 * And if it is valid for being used.
	 * @param world The world that the player is in
	 * @return if it is valid.
	 */
	public boolean isModifierValid(World world);
	
	/**
	 * Client tick if that is needed. Could be that you need that.
	 * @param world The player is in
	 */
	public void onAudioTick(World world);
	
	/**
	 * Function for checking if it has a effect for that type.
	 * If not its getting skipped.
	 * @param type The sound type it checks.
	 * @return if it has a effect
	 */
	public boolean hasEffect(PositionSpec type);
	
	/**
	 * the modifier that the SoundModifier has.
	 * @param type the Type of sound it checks for.
	 * @return the Modifier of the sound
	 */
	public float getAudioEffect(PositionSpec type);
	
	/**
	 * The Range of the modifier. only for is in range check
	 * @param type The type it is in
	 * @return the max radius into each direction
	 */
	public int getEffectRange(PositionSpec type);
	
	/**
	 * Position where the modifier is.
	 * Get requested every tick
	 * @return the position
	 */
	public IAudioPosition getPosition();
}
