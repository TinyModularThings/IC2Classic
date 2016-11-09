package ic2.api.classic.network;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Allows a tile entity to receive network events received from clients.
 * Client => Server
 */
public interface INetworkClientTileEventListener {
	/**
	 * Called when a network event is received.
	 * 
	 * @param player client which sent the event
	 * @param event event ID
	 * @param value event value
	 */
	void onNetworkEvent(EntityPlayer player, int event, int value);
}

