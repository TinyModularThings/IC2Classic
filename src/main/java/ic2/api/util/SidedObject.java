package ic2.api.util;

import net.minecraftforge.fml.util.thread.EffectiveSide;

/**
 *
 * @author Speiger
 *
 * Similar to what forges {@link net.minecraftforge.fml.DistExecutor} does, but this allows to access both implementations if present and needed.
 *
 * @param <T>
 */
public class SidedObject<T>
{
	T client;
	T server;

	/**
	 *
	 * @param value an implementation of a sided component
	 * @param serverSide true for server thread implementations, false for client thread implementations
	 */
	public void set(T value, boolean serverSide)
	{
		if(serverSide)
		{
			server = value;
			return;
		}
		client = value;
	}

	/**
	 *
	 * @return the implementation for the current thread
	 */
	public T get()
	{
		return get(isSimulating());
	}

	/**
	 *
	 * @param serverSide true for server thread implementations, false for client thread implementations
	 * @return the implementation for the requested thread
	 */
	public T get(boolean serverSide)
	{
		return serverSide ? server : client;
	}
	
	protected boolean isSimulating()
	{
		return EffectiveSide.get().isServer();
	}
}
