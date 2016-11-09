package ic2.api.classic.energy.render;

import net.minecraft.util.math.Vec3d;

public interface IRenderEntry
{
	/**
	 * Current position of that render Entry
	 * @return a 3D vec pos of the position
	 */
	public Vec3d getPosition();
}
