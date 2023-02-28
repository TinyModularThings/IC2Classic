package ic2.api.crops;

import net.minecraft.world.item.ItemStack;

/**
 * 
 * @author Speiger
 * 
 * API to the CropSeedItem implementation
 */
public interface ICropSeed
{
	/** Cost Values how much each scan tier would cost */
	int[] SCAN_COST = new int[]{10, 90, 900, 9000};
	/** Tier values for each crop tier */
	String[] TIERS = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI"};
	
	/**
	 * Get's the Crop in the seed.
	 * @param stack of the seed
	 * @return the Crop that is in said item
	 */
	ICrop getCrop(ItemStack stack);
	
	/**
	 * Get's the Scan Level of the seed
	 * @param stack the seed
	 * @return the scan level of said seed.
	 */
	int getScanLevel(ItemStack stack);
	
	/**
	 * Set's the Scan Level of the seed
	 * @param stack the seed
	 * @param level that it should be set to.
	 */
	void setScanLevel(ItemStack stack, int level);
	
	/**
	 * Increments the Scan Level of the seed
	 * @param stack the seed itself
	 */
	default void increaseScanLevel(ItemStack stack)
	{
		setScanLevel(stack, getScanLevel(stack) + 1);
	}
	
	/**
	 * Get's the Growth Stat of the Seed
	 * @param stack the seed
	 * @return the Growth stat of the Seed. Between 0-31 (inclusive)
	 */
	int getGrowth(ItemStack stack);
	
	/**
	 * Set's the Growth Stat of the Seed
	 * @param stack the seed
	 * @param growth what the stat should be. Clamped between 0-31 (inclusive)
	 */
	void setGrowth(ItemStack stack, int growth);
	
	/**
	 * Get's the Gain Stat of the Seed
	 * @param stack the seed
	 * @return the Gain stat of the Seed. Between 0-31 (inclusive)
	 */
	int getGain(ItemStack stack);
	
	/**
	 * Set's the Gain Stat of the Seed
	 * @param stack the seed
	 * @param gain what the stat should be. Clamped between 0-31 (inclusive)
	 */
	void setGain(ItemStack stack, int gain);
	
	/**
	 * Get's the Resistance Stat of the Seed
	 * @param stack the seed
	 * @return the Resistance stat of the Seed. Between 0-31 (inclusive)
	 */
	int getResistance(ItemStack stack);
	
	/**
	 * Set's the Resistance Stat of the Seed
	 * @param stack the seed
	 * @param resistance what the stat should be. Clamped between 0-31 (inclusive)
	 */
	void setResistance(ItemStack stack, int resistance);
}
