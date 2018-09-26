package ic2.api.classic.recipe.machine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import ic2.api.recipe.RecipeOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3i;

public class MachineOutput
{
	public static final int defaultColor = Color.gray.getRGB();
	public static final Vec3i aboveOutput = new Vec3i(60, 5, defaultColor);
	public static final Vec3i aboveOutputLeft = new Vec3i(30, 5, defaultColor);
	public static final Vec3i belowOutput = new Vec3i(60, 43, defaultColor);
	public static final Vec3i belowOutputLeft = new Vec3i(30, 43, defaultColor);
	
	protected NBTTagCompound metadata;
	protected List<ItemStack> items;
	
	public MachineOutput(NBTTagCompound meta, List<ItemStack> items)
	{
		assert !items.contains(null);
		this.metadata = meta;
		this.items = new ArrayList<ItemStack>(items);
	}
	
	public MachineOutput(NBTTagCompound meta, ItemStack... items)
	{
		this(meta, Arrays.asList(items));
	}
	
	/**
	 * Function which get called by the machine.
	 * The Items can be random but they have to be 1 of the cases
	 * of getAllOutputs
	 * @return outputs of that process
	 */
	public List<ItemStack> getRecipeOutput(Random rand)
	{
		return items;
	}
	
	/**
	 * Functions that show all items (fortune excluded)
	 * of that Recipe Outputs.
	 * Its used to validate the output and for RecipeHandlers
	 */
	public List<ItemStack> getAllOutputs()
	{
		return items;
	}
	
	/**
	 * NBTTagMetadata It allows you to add special
	 * properties if the machine supports it
	 */
	public NBTTagCompound getMetadata()
	{
		return metadata;
	}
	
	/**
	 * IC2 Classic Machines support now the Processing
	 * Experience. If you want a Experience return a number greater then 0.
	 * @return Produced Experience (Can support fortune if you want)
	 */
	public float getExperienceOutput()
	{
		return 0;
	}
	
	/**
	 * if someone wants to add efficiency Upgrades like fortune
	 * or processing efficiency what kind you want.
	 * @param type the kind of upgrade it is
	 * @param effect what it effects
	 * @return if the recipe supports it
	 */
	public boolean canApplyEfficiency(EfficiencyType type, EfficiencyEffect effect)
	{
		return false;
	}
	
	/**
	 * function to apply the Efficiency effects
	 * Bad Effects should be also supported
	 * @param type which typeEffect
	 * @param effect what the target is
	 * @param amount how strong the effect is
	 */
	public void applyEfficiency(EfficiencyType type, EfficiencyEffect effect, int amount)
	{
	}
	
	/**
	 * Function to add Information into the JEI RecipeTab.
	 * The String is your Text that you want to display.
	 * The Vec3i is the position where you display it.
	 * And the Color you want to have
	 * @param consumer just a helper
	 */
	public void onJEIInfo(BiConsumer<String, Vec3i> consumer)
	{
		
	}
	
	/**
	 * Function to copy the Recipe. Fortune will be not copied
	 * It get reseted
	 * @return copy of the RecipeInstance
	 */
	public MachineOutput copy()
	{
		return new MachineOutput(copyNBT(metadata), copyItems(items));
	}
	
	public boolean canOverride()
	{
		return true;
	}
	
	public MachineOutput overrideOutput(List<ItemStack> list)
	{
		if(!canOverride())
		{
			return copy();
		}
		return new MachineOutput(copyNBT(metadata), copyItems(list));
	}
	
	/**
	 * I still need to support IC2 Exp.
	 * So provide that function to make it compatible to IC2Exp
	 * But you still can decide that.
	 */
	public RecipeOutput toIC2Exp()
	{
		return new RecipeOutput(metadata, items);
	}
	
	/**
	 * Helper function just to help out with NBT functions
	 */
	protected NBTTagCompound copyNBT(NBTTagCompound nbt)
	{
		if(nbt == null)
		{
			return null;
		}
		return nbt.copy();
	}
	
	/**
	 * Helper function just to help out with item copying
	 */
	protected List<ItemStack> copyItems(List<ItemStack> list)
	{
		List<ItemStack> newList = new ArrayList<ItemStack>(list.size());
		for(ItemStack stack : list)
		{
			newList.add(stack.copy());
		}
		return newList;
	}
	
	public static enum EfficiencyType
	{
		Enchantment,
		Technology,
		Magic;
		
		public boolean isMagicBased()
		{
			return this != Technology;
		}
		
		public boolean isTechnicBased()
		{
			return this == Technology;
		}
	}
	
	public static enum EfficiencyEffect
	{
		MainProducts,
		SubProducts,
		Experience;
	}
}
