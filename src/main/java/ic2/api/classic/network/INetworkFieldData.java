package ic2.api.classic.network;

import ic2.api.classic.network.adv.IInputBuffer;
import ic2.api.classic.network.adv.IOutputBuffer;

/**
 * 
 * @author Speiger
 * IC2 Classic only
 */
public interface INetworkFieldData
{
	/**
	 * Instead of being limited by a System that IC2 Provide i thought how could
	 * i expand it modular without much work and also not much by way of
	 * complexity. This is the Result. INetworkFieldData
	 * This class is implemented by any classField that you want to sync.
	 * What this will cause then is simple. It will call these functions and allow you to sync indirectly stuff.
	 * Note: When a field class implements this it will be not sync normally.
	 * And it will be not replaced. Field has to be always there and not a Null!
	 * But it will still be calling the Function in the INetworkUpdateListener with the field name
	 */
	
	/**
	 * Read function. Please make sure you do not read more data then necessary.
	 */
	public void read(IInputBuffer buffer);
	
	/**
	 * Write Function.
	 */
	public void write(IOutputBuffer buffer);
}
