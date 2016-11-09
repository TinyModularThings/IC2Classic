package ic2.api.classic.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Speiger
 * Client => Server
 * Server => Client
 *
 */
public interface ICustomNetworkItemEventListener
{
	/**
	 * Function that get called when a event Received this Item.
	 * Simply the more complex function to send data over.
	 * Note: You have to register the class else the packet will not be send over.
	 * @param stack Item that should receive that event
	 * @param player Player Who made that event
	 * @param data Data you wanted to have transfered over
	 */
	public void onNetworkEvent(ItemStack stack, EntityPlayer player, INetworkFieldData data);
}
