package ic2.api.classic.crops;

import ic2.api.crops.BaseSeed;
import ic2.api.crops.CropCard;

public class ClassicBaseSeed extends BaseSeed
{
	public final int stackSize;
	
	public ClassicBaseSeed(BaseSeed seed)
	{
		this(seed.crop, seed.size, seed.statGrowth, seed.statGain, seed.statResistance, 1);
	}
	
	public ClassicBaseSeed(CropCard crop, int size, int statGrowth, int statGain, int statResistance)
	{
		this(crop, size, statGrowth, statGain, statResistance, 1);
	}
	
	public ClassicBaseSeed(CropCard crop, int size, int statGrowth, int statGain, int statResistance, int stackSize)
	{
		super(crop, size, statGrowth, statGain, statResistance);
		this.stackSize = stackSize;
	}
	

	
	
}
