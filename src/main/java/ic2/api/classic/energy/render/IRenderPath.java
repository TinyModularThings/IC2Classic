package ic2.api.classic.energy.render;

import net.minecraft.util.math.BlockPos;

public interface IRenderPath
{
	/**
	 * Function to reset the animation and it.
	 */
	public void resetPaths();
	
	/**
	 * A function to let you know where the animation starts
	 * @return Position where the path starts
	 */
	public BlockPos getStartingPos();
	
}
