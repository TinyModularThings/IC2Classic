package ic2.api.recipes.registries;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ic2.api.recipes.ingridients.inputs.EmptyInput;
import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.inputs.ItemInput;
import ic2.api.recipes.ingridients.inputs.ItemStackInput;
import ic2.api.recipes.ingridients.inputs.ItemTagInput;
import ic2.api.recipes.ingridients.recipes.IFluidRecipeOutput;
import ic2.api.recipes.ingridients.recipes.RangeFluidOutput;
import ic2.api.recipes.ingridients.recipes.SimpleFluidOutput;
import ic2.api.recipes.misc.RecipeFlags;
import ic2.api.recipes.misc.RecipeMods;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;

public interface IRefiningRecipeList extends IListenableRegistry<IRefiningRecipeList>
{
	default Builder addIC2Recipe(String id){ return new Builder(this, new ResourceLocation("ic2", id)); }
	default Builder addRecipe(ResourceLocation id) { return new Builder(this, id); }
	void addRecipe(ResourceLocation location, FluidStack input, FluidStack secondInput, IInput catalyst, IFluidRecipeOutput output);
	
	FluidRecipe getRecipe(ResourceLocation location);
	FluidRecipe getRecipe(ItemStack item, FluidStack firstTank, FluidStack secondTank, boolean testAmounts);
	FluidRecipe getRecipe(Predicate<FluidRecipe> checker);
	
	FluidRecipe removeRecipe(ResourceLocation location, boolean includeInverted);
	
	List<FluidRecipe> getAllRecipes();
	
	boolean isValidCatalyst(ItemStack stack);
	boolean isValidFirstFluid(FluidStack fluid);
	boolean isValidSecondFluid(FluidStack fluid);
	
	class Builder
	{
		IRefiningRecipeList registry;
		ResourceLocation id;
		FluidStack firstInput = FluidStack.EMPTY;
		FluidStack secondInput = FluidStack.EMPTY;
		IInput catalyst = EmptyInput.INSTANCE;
		CompoundTag data;
		
		public Builder(IRefiningRecipeList registry, ResourceLocation id)
		{
			this.registry = registry;
			this.id = id;
		}
		
		public Builder catalyst(ItemLike provider)
		{
			catalyst = new ItemInput(provider);
			return this;
		}
		
		public Builder catalyst(ItemStack stack)
		{
			catalyst = new ItemStackInput(stack);
			return this;
		}
		
		public Builder catalyst(TagKey<Item> tag)
		{
			catalyst = new ItemTagInput(tag);
			return this;
		}
		
		public Builder catalyst(RegistryObject<Item> provider)
		{
			return catalyst(provider.get());
		}
		
		public Builder catalyst(IInput input)
		{
			catalyst = input;
			return this;
		}
		
		public Builder mainInput(RegistryObject<Fluid> fluid, int amount)
		{
			return mainInput(fluid.get(), amount);
		}
		
		public Builder mainInput(Fluid fluid, int amount)
		{
			firstInput = new FluidStack(fluid, amount);
			return this;
		}
		
		public Builder mainInput(FluidStack fluid)
		{
			firstInput = fluid;
			return this;
		}
		
		public Builder secondInput(RegistryObject<Fluid> fluid, int amount)
		{
			return secondInput(fluid.get(), amount);
		}
		
		public Builder secondInput(Fluid fluid, int amount)
		{
			secondInput = new FluidStack(fluid, amount);
			return this;
		}
		
		public Builder secondInput(FluidStack fluid)
		{
			secondInput = fluid;
			return this;
		}
		
		private void checkNBT()
		{
			if(data == null) data = new CompoundTag();
		}
		
		public Builder addNBT(String id, Tag nbt)
		{
			checkNBT();
			data.put(id, nbt);
			return this;
		}
		
		public Builder addNBT(CompoundTag data)
		{
			checkNBT();
			data.merge(data);
			return this;
		}
		
		public Builder setNBT(CompoundTag data)
		{
			this.data = data;
			return this;
		}
		
		public Builder addFlag(RecipeFlags flag, boolean value)
		{
			checkNBT();
			flag.applyFlag(data, value);
			return this;
		}
		
		public Builder addMod(RecipeMods mod, int value)
		{
			checkNBT();
			mod.create(data, value);
			return this;
		}
		
		public Builder addMod(RecipeMods mod, double value)
		{
			checkNBT();
			mod.create(data, value);
			return this;
		}
		
		public Builder addMod(RecipeMods mod, int exactValue, double modValue)
		{
			checkNBT();
			mod.create(data, modValue, exactValue);
			return this;
		}
		
		public void buildSimple(ItemStack output)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new SimpleFluidOutput(ObjectLists.singleton(output), data));			
		}
		
		public void buildSimple(ItemStack output, FluidStack...fluidOutputs)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new SimpleFluidOutput(ObjectLists.singleton(output), ObjectArrayList.wrap(fluidOutputs), data));			
		}
		
		public void buildSimple(ItemStack...itemOutputs)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new SimpleFluidOutput(ObjectArrayList.wrap(itemOutputs), data));			
		}
		
		public void buildSimple(FluidStack...fluidOutputs)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new SimpleFluidOutput(Collections.emptyList(), ObjectArrayList.wrap(fluidOutputs), data));			
		}
		
		public void buildSimple(List<ItemStack> itemOutputs, List<FluidStack> fluidOutputs)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new SimpleFluidOutput(itemOutputs, fluidOutputs, data));
		}
		
		public void buildRange(ItemStack itemOutput, int minItem, int maxItem)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new RangeFluidOutput(itemOutput, minItem, maxItem, data));
		}
		
		public void buildRange(FluidStack fluidOutput, int minFluid, int maxFluid)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new RangeFluidOutput(fluidOutput, minFluid, maxFluid, data));			
		}
		
		public void buildRange(ItemStack itemOutput, int minItem, int maxItem, FluidStack fluidOutput, int minFluid, int maxFluid)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, new RangeFluidOutput(itemOutput, minItem, maxItem, fluidOutput, minFluid, maxFluid, data));
		}
		
		public void buildCustom(Function<CompoundTag, IFluidRecipeOutput> mapper)
		{
			registry.addRecipe(id, firstInput, secondInput, catalyst, mapper.apply(data));
		}
	}
	
	public static class FluidRecipe
	{
		ResourceLocation location;
		FluidStack firstTank;
		FluidStack secondTank;
		IInput itemInput;
		IFluidRecipeOutput output;
		
		public FluidRecipe(ResourceLocation location, FluidStack firstTank, FluidStack secondTank, IInput itemInput, IFluidRecipeOutput output)
		{
			this.location = location;
			this.firstTank = firstTank;
			this.secondTank = secondTank;
			this.itemInput = itemInput;
			this.output = output;
		}
		
		public ResourceLocation getLocation()
		{
			return location;
		}

		public FluidStack getFirstTank()
		{
			return firstTank;
		}

		public FluidStack getSecondTank()
		{
			return secondTank;
		}

		public IInput getItemInput()
		{
			return itemInput;
		}

		public IFluidRecipeOutput getOutput()
		{
			return output;
		}
		
		public FluidRecipe invert()
		{
			return new FluidRecipe(new ResourceLocation(location.getNamespace(), location.getNamespace()+"_inverted"), secondTank, firstTank, itemInput, output);
		}
	}
}
