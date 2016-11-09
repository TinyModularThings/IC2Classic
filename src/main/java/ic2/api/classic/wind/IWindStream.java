package ic2.api.classic.wind;

import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;

public interface IWindStream
{
	/**
	 * Function to detect which Direction (xyz) is supported.
	 * Supports means that it does not cover the whole world at once
	 * Like having Wind Chunks moving around.
	 * @param axis Axis you want to check
	 * @return if it is supported.
	 */
	public boolean isAxisSupported(Axis axis);
	
	/**
	 * Bounding Box of the wind Stream.
	 * If it is not supported axis it will return 0 on minimum and maximum
	 * @return box of the wind stream
	 */
	public AxisAlignedBB getBoundingBox();
	
	/**
	 * @return if the Wind Stream has a Direction (x, z)
	 */
	public boolean hasDirection();
	
	/**
	 * @return gets the Direction of the Wind Stream
	 */
	public float getDirection();
	
	/**
	 * @return if the Wind Stream has a Angle
	 */
	public boolean hasAngle();
	
	/**
	 * @return the Angle of the Stream
	 */
	public float getAngle();
	
	/**
	 * @return the current Wind Speed of the Stream
	 */
	public double getWindSpeed();
}
