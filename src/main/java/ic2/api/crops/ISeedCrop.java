package ic2.api.crops;

import net.minecraft.world.item.ItemStack;

/**
 * 
 * @author Speiger
 * 
 * Helper interface for Crops if they have altering Drops.
 * This is in combination to {@link ICropModifier#canChangeSeedMode(ItemStack)} is used for.
 * When enabled this interface is usually in play.
 * 
 * This interface is used by ICrop implementation
 *
 */
public interface ISeedCrop
{
	/**
	 * Function that defines if this alternative drop function should be called.
	 * @param tile the crop holder
	 * @return true if it should use this alternative drop function
	 */
	boolean isDroppingSeeds(ICropTile tile);
	
	/**
	 * Returns a freshly created ItemStack array with all the drops that can come out of the Crop in the seed Mode enabled.
	 * @param tile the crop holder
	 * @return a ItemStack array of all the drops. If empty just return a new ItemStack[0];
	 */
	ItemStack[] getSeedDrops(ICropTile tile);
}
