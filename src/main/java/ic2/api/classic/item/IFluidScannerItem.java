package ic2.api.classic.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidScannerItem
{
	/**
	 * MetaSensitive Check for a FluidScanner
	 * @param stack yourStack
	 * @return if it is a Fluid Scanner
	 */
	public boolean isFluidScanner(ItemStack stack);
	
	/**
	 * Function that drains Energy out of the scanner and
	 * returns the max range of the scanner
	 * @param stack yourStack
	 * @return the max Radius of the scanner
	 */
	public int startScanningFluidLayer(ItemStack stack);
	
	/**
	 * Simple check if the Scanner searches for that fluid.
	 * @param stack YourScanner
	 * @param fluid the fluid it wants to check
	 * @return if the fluid is a valid target
	 */
	public boolean isValidFluid(ItemStack stack, FluidStack fluid);
}
