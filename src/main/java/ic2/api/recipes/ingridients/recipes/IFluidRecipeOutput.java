package ic2.api.recipes.ingridients.recipes;

import java.util.List;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidRecipeOutput extends IRecipeOutput
{
	List<FluidStack> onFluidRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags, ItemStack input);
	default List<FluidStack> onFluidRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags, ItemStack input, IRecipeOverride overrides)
	{ 
		return onFluidRecipeProcessed(rand, persistentData, recipeFlags, input);
	}
	
	List<FluidStack> getAllFluidOutputs();
	
	static List<FluidStack> copyFluids(List<FluidStack> copy)
	{
		List<FluidStack> list = new ObjectArrayList<>();
		for(FluidStack stack : copy)
		{
			list.add(stack.copy());
		}
		return list;
	}
}
