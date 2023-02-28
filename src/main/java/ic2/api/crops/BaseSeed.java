package ic2.api.crops;

import net.minecraft.world.item.ItemStack;

/**
 * 
 * @author Speiger
 * 
 * A Helper class that allows to define a crop with its Stats and required stacksize
 *
 */
public class BaseSeed
{
	public final ICrop crop;
	public int stage;
	public int growth;
	public int gain;
	public int resistance;
	public int stack_size;
	
	public BaseSeed(ICrop crop, int stage, int growth, int gain, int resistance, int stack_size)
	{
		this.crop = crop;
		this.stage = stage;
		this.growth = growth;
		this.gain = gain;
		this.resistance = resistance;
		this.stack_size = stack_size;
	}
	
	public BaseSeed(ItemStack stack)
	{
		ICropSeed seed = (ICropSeed)stack.getItem();
		crop = seed.getCrop(stack);
		stage = 1;
		growth = seed.getGrowth(stack);
		gain = seed.getGain(stack);
		resistance = seed.getResistance(stack);
		stack_size = 1;
	}
	
}
