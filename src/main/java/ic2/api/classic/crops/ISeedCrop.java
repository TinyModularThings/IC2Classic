package ic2.api.classic.crops;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Speiger
 * 
 * Support for the feature that Crops can drop Seeds
 * optionally when you rightclick it with the hoe
 * to toggle its mode. Its not the best item to use for but i had no
 * better idea.
 * This does not mean IC2 Seeds i mean the original crop seeds.
 */
public interface ISeedCrop
{
	/**
	 * If the Crop drops seeds instead of
	 * @param tile the Crop it is planted on.
	 * @return if it should drop seeds instead of the usual Gain
	 */
	public boolean doDropSeeds(ICropTile tile);
	
	/**
	 * Function to gain the CropSeeds.
	 * Note this is not about IC2Seeds this is about the base crop seeds.
	 * @param tile the Crop it is planted on.
	 * @return the Seed that it should drop
	 */
	public ItemStack getSeed(ICropTile tile);
}
