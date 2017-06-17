package ic2.api.classic.recipe.crafting;

import java.util.ArrayList;
import java.util.List;

import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class RecipeInputFluid implements IRecipeInput
{
	public final FluidStack fluid;
	public final int stacksize;
	private List<ItemStack> list = null;
	
	
	public RecipeInputFluid(Fluid fluid)
	{
		this(new FluidStack(fluid, Fluid.BUCKET_VOLUME), 1);
	}
	
	public RecipeInputFluid(Fluid fluid, int stacksize)
	{
		this(new FluidStack(fluid, Fluid.BUCKET_VOLUME), stacksize);
	}
	
	public RecipeInputFluid(FluidStack fluid)
	{
		this(fluid, 1);
	} 
	
	public RecipeInputFluid(FluidStack fluid, int stacksize)
	{
		this.fluid = fluid.copy();
		this.stacksize = stacksize;
	}
	
	@Override
	public boolean matches(ItemStack subject)
	{
		FluidStack otherFluid = FluidUtil.getFluidContained(subject);
		if(otherFluid != null && otherFluid.containsFluid(fluid))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int getAmount()
	{
		return stacksize;
	}
	
	@Override
	public List<ItemStack> getInputs()
	{
		if(list == null)
		{
			list = getFluids();
		}
		return list;
	}

	private List<ItemStack> getFluids()
	{
		List<ItemStack> list = new ArrayList<ItemStack>();
		for(FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
		{
			if(data.fluid.containsFluid(fluid))
			{
				list.add(data.filledContainer.copy());
			}
		}
		return list;
	}
	
	
}
