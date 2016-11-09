package ic2.api.classic.recipe.custom;

import java.util.List;

import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Speiger
 * 
 * Teleporter Validator registry
 * This class allows you to decide which Interfaces can be drained from.
 */
public interface ITeleporterRegistry
{
	/**
	 * Function if the TileEntity is a Valid Inventory class.
	 * That it can pick from. It does not have to be validated that it is a IInventory or IItemHandler class
	 * It has to be validated that this class can be used to teleport stuff..
	 * @param tile The tile that needs to be checkt
	 * @return the result
	 */
	public boolean isValidInventory(TileEntity tile);
	
	/**
	 * Function if the TileEntity is a Valid FluidTank class.
	 * That it can pick from. It does not have to be validated that it is a IFluidHandler class
	 * It has to be validated that this class can be used to teleport stuff..
	 * @param tile The tile that needs to be checkt
	 * @return the result
	 */
	public boolean isValidTank(TileEntity tile);
	
	/**
	 * Function to register a Class that can be used as Teleporter Provider
	 * The function isInstance will be used for those classes
	 * @param tile the Interface or class
	 */
	public void registerInventory(Class tile);
	
	/**
	 * Function to register a Class that can be used as Teleporter Provider
	 * The function isInstance will be used for those classes
	 * @param tile the Interface or class
	 */
	public void registerTank(Class tile);
	
	/**
	 * @return Access List for InventoryClasses. You can remove them if you want or add them from here
	 * but i would not suggest that.
	 */
	public List<Class> getInventoryRegistry();
	
	/**
	 * @return Access List for TankClassesClasses. You can remove them if you want or add them from here
	 * but i would not suggest that.
	 */
	public List<Class> getFluidRegistry();
}
