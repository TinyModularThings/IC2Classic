package ic2.api.classic.network;

import ic2.api.network.INetworkManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface INetworkManagerExt extends INetworkManager
{
	
	/**
	 * Simply a extended call for the same function but classic support only.
	 * Does require the Class INetworkClientTileEventListener
	 */
	public void initiateClientTileEntityEvent(TileEntity te, int event, int value);
	
	/**
	 * Function to send CustomFieldDataNetworkEvents. 
	 * This is required so that the packet handler know how to use that class
	 * Note: Class require a empty constructor
	 * @param clz FieldData class
	 * @param id the ID. (Will be bound to your modid)
	 */
	public void registerEventFieldData(Class<? extends INetworkFieldData> clz, String id);
	
	/**
	 * Function to send Custom Data from client to server
	 * Note: You need to register the class before calling this. Else it will not get send over
	 * @param te TileEntity who will receive the Event
	 * @param data The Data you want to send over
	 */
	public void initiateCustomClientTileEntityEvent(TileEntity te, INetworkFieldData data);
	
	/**
	 * Function to send custom Data from server to client
	 * Note: You need to register the class before calling this. Else it will not get send over
	 * @param te TileEntity who will receive the Event
	 * @param data The Data you want to send over
	 */
	public void initiateCustomTileEntityEvent(TileEntity te, EntityPlayer player, INetworkFieldData data);
	
	/**
	 * Function to send Custom Data from client to server
	 * Note: You need to register the class before calling this. Else it will not get send over
	 * @param te TileEntity who will receive the Event
	 * @param data The Data you want to send over
	 */
	public void initiateCustomClientItemEvent(ItemStack stack, INetworkFieldData data);
	
	/**
	 * Function to send Custom Data from Server to Client
	 * Note: You need to register the class before calling this. Else it will not get send over
	 * @param player The Player Who will receive that packet
	 * @param stack Stack who will receive that event
	 * @param data The Data which will get send over
	 */
	public void initiateCustomItemEvent(EntityPlayer player, ItemStack stack, INetworkFieldData data);

	/**
	 * Function to mark gui fields to be updated.
	 * Difference to TileEntity fields is: They do only get sync on request or when a Player is tracking that Tile
	 * Also requests are going to be reseted every tick. So that only updates get handled when needed and are not marked all the time
	 * @param te That wants a update
	 * @param field that needs to be updated
	 */
	public void updateTileEntityGuiField(TileEntity te, String field);
	
	/**
	 * Function to send the initial gui field packet.
	 * This ignores the updateTileEntityGuiField requests
	 * its checking if the TileEntity is instance of INetworkGuiDataProvider
	 * and sync the fields that are provided by that
	 * @param player The player that should receive the Data
	 * @param te that needs to be sync
	 */
	public void sendInitialGuiData(EntityPlayer player, TileEntity te);
	
	/**
	 * Function to update the gui changes that a TileEntity did make
	 * Note this is a manual update call. It has to be done before the ServerTickEvent (Post) has bin called
	 * Because after that the GuiFields are reseted
	 * Also if the Tile has no request made it will send no packet for it
	 * @param player Player that needs to receive the data
	 * @param te The tile that should be updated
	 */
	public void updateGuiChanges(EntityPlayer player, TileEntity te);
	
	/**
	 * Function to automatically track gui changes.
	 * Note: This function automatically calls updateGuiChanges.
	 * Also another Note: EveryPlayer can only track 1 TileEntity
	 * and as soon a player closes a gui the Tracking gets automatically disabled.
	 * Its meant to be used when you have gui data that you want to be sync automatically
	 * Here use it. But do not use it for something else.
	 * @param player Player that needs to receive the packet
	 * @param te That needs to be updated
	 */
	public void startTracking(EntityPlayer player, TileEntity te);
}
