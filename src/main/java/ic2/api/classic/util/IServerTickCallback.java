package ic2.api.classic.util;

import net.minecraft.util.ActionResult;

public interface IServerTickCallback
{
	/**
	 * Class to receive a callback when the server tick is already over
	 * @return the Result of the Callback
	 * Fail = Stop
	 * Pass = Continue
	 * Success = Finished & Stop
	 * @param Integer delay of until the next Tick (in ticks).
	 */
	public ActionResult<Integer> tickCallback();
}
