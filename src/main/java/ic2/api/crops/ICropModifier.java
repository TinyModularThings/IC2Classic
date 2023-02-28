package ic2.api.crops;

import net.minecraft.world.item.ItemStack;

/**
 * 
 * @author Speiger
 *
 * Some crops have a "Seed" mode where they drop seeds instead of their normal Yield.
 * This ICropModifier interface allows seeds to detect if someone wants to change the seeds "Seed" mode.
 * One example: Wheat drops seeds instead of Wheat.
 * Or Oak Saplings will be dropped instead of Logs.
 */
public interface ICropModifier
{
	/**
	 * Function to detect if the item can toggle the seeds internal switch mode
	 * @param input myItem
	 * @return if it can do special actions for the crop.
	 */
	boolean canChangeSeedMode(ItemStack input);
	
	static boolean canToggleSeedMode(ItemStack stack)
	{
		return stack.getItem() instanceof ICropModifier mod && mod.canChangeSeedMode(stack);
	}
}
