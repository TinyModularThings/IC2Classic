package ic2.api.classic.recipe.crafting;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ic2.api.classic.recipe.ICustomRecipeInput;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class RecipeInputFluid implements ICustomRecipeInput
{
	private static final Map<Fluid, List<ItemStack>> map = new LinkedHashMap<Fluid, List<ItemStack>>();
	private static boolean init = false;
	public final FluidStack fluid;
	public final int stacksize;
	
	
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
		return getList(fluid.getFluid());
	}
	
	@Override
	public boolean isSpecial()
	{
		return true;
	}

	@Override
	public boolean isOreDict()
	{
		return false;
	}

	@Override
	public String getOreDictEntry()
	{
		return null;
	}

//	private List<ItemStack> getFluids()
//	{
//		List<ItemStack> list = new ArrayList<ItemStack>();
//		for(ItemStack stack : getList(fluid.getFluid()))
//		{
//			list.add(stack.copy());
//		}
//		return list;
//	}
	
	static List<ItemStack> getList(Fluid fluid)
	{
		if(!init)
		{
			init();
		}
		return map.getOrDefault(fluid, new ArrayList<ItemStack>());
	}
	
	static void init()
	{
		for(Item item : Item.REGISTRY)
		{
			NonNullList<ItemStack> list = NonNullList.create();
			item.getSubItems(CreativeTabs.SEARCH, list);
			for(ItemStack stack : list)
			{
				FluidStack fluid = FluidUtil.getFluidContained(stack);
				if(fluid != null)
				{
					map.computeIfAbsent(fluid.getFluid(), T -> new ArrayList<>()).add(stack);
				}
			}
		}
	}
	
}
