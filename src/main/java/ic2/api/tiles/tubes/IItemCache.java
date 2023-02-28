package ic2.api.tiles.tubes;

import java.util.function.Supplier;

import ic2.api.util.SidedObject;
import net.minecraft.world.item.ItemStack;

/**
 * 
 * @author Speiger
 * A Network Manager that syncs and keeps tracks of Item instances.
 * Simply Register server sided the Stack and a ID is allocated/provided to you that you can send to the client.
 * The client just gets the Item with the Provided Id, it is a supplier because it might take a while for it to arrive, so it can return a ItemStack.EMPTY or the actual instance.
 * This is to reduce network traffic.
 */
public interface IItemCache
{
	SidedObject<IItemCache> CACHE = new SidedObject<>();
	
	static IItemCache getCache()
	{
		return CACHE.get();
	}
	
	/**
	 * Returns the requested Stack from the given ID.
	 * @param id that was provided from the registering
	 * @return ItemStack.EMPTY or the stack registered. This is due to network delay.
	 * @throws IllegalStateException if this is called on the server
	 */
	Supplier<ItemStack> getItem(int id);
	
	/**
	 * Registers a item + NBT Data and get a id that the client will have.
	 * @param stack that should be synced to the client
	 * @return the id for that specific stack
	 * @throws IllegalStateException when called on the client.
	 */
	int registerItem(ItemStack stack);
	
	/**
	 * Internally called automatically, this is just here for easier access.
	 * Sends a sync request to the server from all the new ids that asked for on the client.
	 * @throws IllegalStateException when called server sided.
	 */
	void updateCache();
	
	/**
	 * Clears the cache. Internal called automatically. Here for easier access.
	 * Can be called both sides
	 */
	void clearCache();
}
