package ic2.api.classic.util;

import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public interface IWorldTickCallback
{
	/**
	 * Class to get a Tick after the World Tick was happening
	 * @param world World which it should apply to
	 * @return the Result of the Callback
	 * Fail = Stop
	 * Pass = Continue
	 * Success = Finished & Stop
	 * @param Integer delay of until the next Tick (in ticks).
	 */
	public ActionResult<Integer> tickCallback(World world);
}
