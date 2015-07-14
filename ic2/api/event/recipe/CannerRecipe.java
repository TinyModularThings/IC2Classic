package ic2.api.event.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * 
 * @author Speiger
 * This or all classes related to this is not a Recipe List helper
 * it is just a Information helper for the Canner.
 * The Canner Itself has no RecipeList it is a static thing,
 * i just wanted to give a Static thing a little bit more dynamic,
 * so that it is no longer bind to the IC2 itself and other mods can
 * add to that.
 */
public abstract class CannerRecipe extends RecipeEvent
{
	int fuel;
	
	public CannerRecipe(ItemStack input, int fuel)
	{
		super(input, input);
		this.fuel = fuel;
	}
	
	public int getFuelValue()
	{
		return fuel;
	}
	
	/**
	 * 
	 * @author Speiger
	 * Simply add your own fuel Values to the Canning machine.
	 * This is not a RecipeList its just a information List for the canner.
	 */
	public static class FuelValue extends CannerRecipe
	{
		public FuelValue(ItemStack input, int fuel)
		{
			super(input, fuel);
		}
	}
	
	
	/**
	 * 
	 * @author Speiger
	 * add a Fuel Multiplier to your item instead a raw FuelValue.
	 * Note the integer means simply percent. So if it is adding 10% you say 10
	 * else i would have todo cast float to Integer to float. We leave 1 step away
	 * easier for everyone.
	 */
	public static class FuelMultiplier extends CannerRecipe
	{
		public FuelMultiplier(ItemStack input, int percentage)
		{
			super(input, percentage);
		}
	}
	
	/**
	 * 
	 * @author Speiger
	 * Sets the metadata to the right effect based on your food.
	 * @Meta:
	 * 0: Standard Food can. No effect. Default...
	 * 1: Hunger. (Rotten Flesh effect) (80% chance)
	 * 2: Poison. (Spider Eye effect)
	 * 3: Hunger. (Chicken Raw Effect) (30% chance)
	 * 4: Regeneration. (Golden Apple Effect)
	 * 5: Regeneration, Fire&Resistance. (Notch Apple Effect)
	 * 
	 * @Important:
	 * No NBTSupport... only Item & Metadata
	 *
	 */
	public static class FoodEffect extends CannerRecipe
	{

		public FoodEffect(ItemStack input, int meta)
		{
			super(input, meta);
		}
		
	}
	
}
