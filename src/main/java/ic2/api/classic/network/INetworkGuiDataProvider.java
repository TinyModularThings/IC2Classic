package ic2.api.classic.network;

import java.util.List;

/**
 * 
 * @author Speiger
 * 
 * This class is there to allow Gui data to get an initial sync
 * Which should be required anyway.
 * Note: This class & the functions using Gui sync will not call {@INetworkUpdateListener}
 * Its only Gui data which will be gathered when the Player is opening a Gui
 * Not required to sync single Gui fields just for initalStart
 * IC2Classic supports only TileEntities that uses this class
 */
public interface INetworkGuiDataProvider
{
	/**
	 * Note: Gui Fields need still to be sync by request. No Request = no Sync
	 * 
	 * @return all field names that need to be sync when a Gui is opened
	 */
	public List<String> getGuiFields();
}
