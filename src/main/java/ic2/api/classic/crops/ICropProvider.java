package ic2.api.classic.crops;

import ic2.api.crops.CropCard;

/**
 * 
 * @author Speiger
 * Interface just for Breeding with IC2Classic Crops
 * if you have your own Crop System but want that IC2Crops
 * can be used for Breeding this is the class you can use for it.
 * If you implement the class IC2Crops will automaticly see it as
 * valid Crop Breeding target.
 */
public interface ICropProvider
{
	/**
	 * Function to see which crop is at the moment growing there.
	 * Needed to calculate the Result Crop.
	 * @return the crop that is growing can be null
	 */
	public CropCard getCrop();
	
	/**
	 * Function if the crop that is growing is ready to cross-breed. 
	 * @return if it can cross-breed.
	 */
	public boolean canCrossBreed();
	
	/**
	 * Function that provides the GrowthStat for your crop.
	 * needed to calculate the new Crops GrowthStat.
	 * This effects growth of a crop.
	 * @return yourGrowthStat (between 0-31)
	 */
	public int getStatGrowth();
	
	/**
	 * Function that provides the GainStat for your crop.
	 * needed to calculate the new Crops GainStat.
	 * This effects how much yield a crop gives.
	 * @return yourGainhStat (between 0-31)
	 */
	public int getStatGain();
	
	/**
	 * Function that provides the ResistanceStat for your crop.
	 * needed to calculate the new Crops ResistanceStat.
	 * This effects how resistant the crop is against weed and breeding (if to high)
	 * @return yourResistanceStat (between 0-31)
	 */
	public int getStatResistance();
}
