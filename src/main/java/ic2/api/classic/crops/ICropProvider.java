package ic2.api.classic.crops;

import ic2.api.crops.CropCard;

public interface ICropProvider
{
	public CropCard getCrop();
	
	public boolean canCrossBreed();
	
	public int getStatGrowth();
	
	public int getStatGain();
	
	public int getStatResistance();
}
