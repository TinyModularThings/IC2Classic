package ic2.api.classic.wind;

import java.util.List;

public interface IWindHandlerInfo extends IWindHandler
{
	/**
	 * Function for accessing the Wind Streams...
	 * @return all WindStreams that are currently there
	 */
	public List<IWindStream> getStreams();
	
	/**
	 * Function only for the IWindManager just so it can call unload
	 * and garbage can happen.
	 */
	public void onUnloaded();
}
