package ic2.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Speiger
 *
 * This class is my custom Support for other Mod wrenches in IC2 Classic
 * and partly IC2 Exp. There is a really good mod called WrenchPlugin on Curse
 * which will use this API and add it to Exp
 * This class is simply there where you can register your wrench to support IC2 machines.
 */
public interface IWrenchHandler
{
	/**
	 * Basic Check function
	 * @param possibleWrench possible Wrench Item
	 * @return if this wrench get by your Plugin Supportet. (I did use a instance Check)
	 */
	public boolean supportsItem(ItemStack possibleWrench);
	
	/**
	 * Function which checks if the Wrench can be used at this postion from this player
	 * @param wrench item representation
	 * @param x coord representation
	 * @param y coord representation
	 * @param z coord representation
	 * @param player Representation of the Player
	 * @return true for that you can use the wrench and false if not.
	 */
	public boolean canWrench(ItemStack wrench, int x, int y, int z, EntityPlayer player);
	
	/**
	 * Simply called after the wrench handler did his work.
	 * Apply damage here if it is a damage based Wrench.
	 * @param wrench ItemRepresentation
	 * @param x coord representation
	 * @param y coord representation
	 * @param z coord representation
	 * @param player Representation of the Player
	 */
	public void useWrench(ItemStack wrench, int x, int y, int z, EntityPlayer player);
	
	public static interface IWrenchRegistry
	{
		/**
		 * You find the Registry in the Class IC2Classic in the info Package
		 * just call the function in there and do not use the Field. 
		 * (its optional for addons that want to add their own registry for Exp)
		 */
		public void registerWrenchSupporter(IWrenchHandler par1);
	}
	
}
