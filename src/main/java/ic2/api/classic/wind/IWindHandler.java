package ic2.api.classic.wind;

import net.minecraft.util.math.BlockPos;

public interface IWindHandler
{
	/**
	 * Main Tick of the Simulator
	 * Whatever you want to do you can do it here.
	 * It will be called on WorldTick End.
	 */
	public void onTick();
	
	/**
	 * Current Wind Strength Access function.
	 * @param pos Position of the Wind Strength
	 * @param rotation Rotation of the Requester (using EnumFacing as a base)
	 * @param angle Angle of the Requester (90 & 270 are Horizontal) (0 == Down, 180 Up)
	 * @return the WindSpeed of Position & Rotation & Angle (can be negative)
	 * @Note: Both variables have to between 0-360
	 */
	public double getWindStrenght(BlockPos pos, float rotation, float angle);
	
	/**
	 * The Wind that it can reach maximum in that world.
	 * @return Max Wind of the World
	 */
	public double getMaxWind();
	

}
