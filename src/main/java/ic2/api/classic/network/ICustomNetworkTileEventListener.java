package ic2.api.classic.network;

import net.minecraft.entity.player.EntityPlayer;

/**
 * 
 * @author Speiger
 * Advanced Sync for sync data from Server to Client
 */
public interface ICustomNetworkTileEventListener
{
	/**
	 * Function which get called when a TileEntity receives this kind of event.
	 * Its Server to client Only.
	 * Note: You have to register the NetworkFieldData class to allow the packet to get send over.
	 * @param player Player Who send the Packet Over
	 * @param data Packet Data
	 */
	public void onNetworkEvent(EntityPlayer player, INetworkFieldData data);
}
