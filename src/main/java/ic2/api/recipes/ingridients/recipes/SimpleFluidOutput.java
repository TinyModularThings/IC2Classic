package ic2.api.recipes.ingridients.recipes;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ic2.api.recipes.ingridients.generators.IOutputGenerator;
import ic2.api.recipes.ingridients.inputs.IInput;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class SimpleFluidOutput extends SimpleRecipeOutput implements IFluidRecipeOutput
{
	List<FluidStack> fluidOutputs = new ObjectArrayList<>();
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs)
	{
		super(outputs);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, List<FluidStack> fluidOutputs)
	{
		super(outputs);
		this.fluidOutputs.addAll(fluidOutputs);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, CompoundTag nbt)
	{
		super(outputs, nbt);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, List<FluidStack> fluidOutputs, CompoundTag nbt)
	{
		super(outputs, nbt);
		this.fluidOutputs.addAll(fluidOutputs);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, float xp)
	{
		super(outputs, xp);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, List<FluidStack> fluidOutputs, float xp)
	{
		super(outputs, xp);
		this.fluidOutputs.addAll(fluidOutputs);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, CompoundTag nbt, float xp)
	{
		super(outputs, nbt, xp);
	}
	
	public SimpleFluidOutput(List<IOutputGenerator> outputs, List<FluidStack> fluidOutputs, CompoundTag nbt, float xp)
	{
		super(outputs, nbt, xp);
		this.fluidOutputs.addAll(fluidOutputs);
	}
	
	public SimpleFluidOutput(JsonObject obj)
	{
		super(obj);
		for(JsonElement el : obj.getAsJsonArray("fluidOutputs"))
		{
			fluidOutputs.add(IInput.readFluidStack(el.getAsJsonObject()));
		}
	}
	
	public SimpleFluidOutput(FriendlyByteBuf buffer)
	{
		super(buffer);
		for(int i = 0,m=buffer.readVarInt();i<m;i++)
		{
			fluidOutputs.add(buffer.readFluidStack());
		}
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		super.serialize(buffer);
		buffer.writeVarInt(fluidOutputs.size());
		for(int i = 0,m=fluidOutputs.size();i<m;i++)
		{
			buffer.writeFluidStack(fluidOutputs.get(i));
		}
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = super.serialize();
		JsonArray array = new JsonArray();
		for(FluidStack stack : fluidOutputs) {
			array.add(IInput.writeFluidStack(stack));
		}
		obj.add("fluidOutputs", array);
		return obj;
	}
	
	@Override
	public List<FluidStack> onFluidRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags, ItemStack input)
	{
		return IFluidRecipeOutput.copyFluids(fluidOutputs);
	}

	@Override
	public List<FluidStack> getAllFluidOutputs()
	{
		return IFluidRecipeOutput.copyFluids(fluidOutputs);
	}
}