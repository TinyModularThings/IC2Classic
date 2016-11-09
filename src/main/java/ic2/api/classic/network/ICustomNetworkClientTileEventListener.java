package ic2.api.classic.network;

import net.minecraft.entity.player.EntityPlayer;

/**
 * 
 * @author Speiger
 * Advanced Sync for sync data from Client to server
 */
public interface ICustomNetworkClientTileEventListener
{
	/**
	 * Function which get called when a TileEntity receives this kind of event.
	 * Its client to server Only. Other way around you have something different to use.
	 * Note: You have to register the NetworkFieldData class to allow the packet to get send over.
	 * @param player Player Who send the Packet Over
	 * @param data Packet Data
	 */
	public void onNetworkEvent(EntityPlayer player, INetworkFieldData data);
}
