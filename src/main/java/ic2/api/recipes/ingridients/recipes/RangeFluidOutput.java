package ic2.api.recipes.ingridients.recipes;

import java.util.List;

import com.google.gson.JsonObject;

import ic2.api.recipes.ingridients.inputs.IInput;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;

public class RangeFluidOutput implements IRecipeOutput, IFluidRecipeOutput
{
	CompoundTag nbt;
	ItemStack output;
	int minValue;
	int maxValue;
	FluidStack fluidOutput;
	int minFluid;
	int maxFluid;
	
	public RangeFluidOutput(JsonObject obj)
	{
		output = CraftingHelper.getItemStack(obj.getAsJsonObject("output"), true);
		minValue = obj.get("minOut").getAsInt();
		maxValue = obj.get("maxOut").getAsInt();
		fluidOutput = IInput.readFluidStack(obj.getAsJsonObject("fluidOutput"));
		minFluid = obj.get("minFluid").getAsInt();
		maxFluid = obj.get("maxFluid").getAsInt();
		if(obj.has("nbt")) nbt = IInput.readNBT(obj.get("nbt").getAsString());
	}
	
	public RangeFluidOutput(FriendlyByteBuf buffer)
	{
		output = buffer.readItem();
		minValue = buffer.readByte();
		maxValue = buffer.readByte();
		fluidOutput = buffer.readFluidStack();
		minFluid = buffer.readVarInt();
		maxFluid = buffer.readVarInt();
	}
	
	public RangeFluidOutput(ItemStack output, int minValue, int maxValue)
	{
		this.output = output.copy();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.fluidOutput = FluidStack.EMPTY;
	}
	
	public RangeFluidOutput(ItemStack output, int minValue, int maxValue, CompoundTag nbt)
	{
		this.output = output.copy();
		this.nbt = nbt;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.fluidOutput = FluidStack.EMPTY;
	}
	
	public RangeFluidOutput(FluidStack fluidOutput, int minFluid, int maxFluid)
	{
		this.output = ItemStack.EMPTY;
		this.fluidOutput = fluidOutput.copy();
		this.minFluid = minFluid;
		this.maxFluid = maxFluid;
	}
	
	public RangeFluidOutput(FluidStack fluidOutput, int minFluid, int maxFluid, CompoundTag nbt)
	{
		this.output = ItemStack.EMPTY;
		this.nbt = nbt;
		this.fluidOutput = fluidOutput.copy();
		this.minFluid = minFluid;
		this.maxFluid = maxFluid;
	}
	
	public RangeFluidOutput(ItemStack output, int minValue, int maxValue, FluidStack fluidOutput, int minFluid, int maxFluid)
	{
		this.output = output.copy();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.fluidOutput = fluidOutput.copy();
		this.minFluid = minFluid;
		this.maxFluid = maxFluid;
	}
	
	public RangeFluidOutput(ItemStack output, int minValue, int maxValue, FluidStack fluidOutput, int minFluid, int maxFluid, CompoundTag nbt)
	{
		this.output = output.copy();
		this.nbt = nbt;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.fluidOutput = fluidOutput.copy();
		this.minFluid = minFluid;
		this.maxFluid = maxFluid;
	}
	
	@Override
	public List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags)
	{
		if(output.isEmpty()) return ObjectLists.emptyList();
		int value = minValue + rand.nextInt((maxValue - minValue) + 1);
		if(value <= 0) return ObjectLists.emptyList();
		ItemStack stack = output.copy();
		stack.setCount(value);
		return ObjectLists.singleton(stack);
	}
	
	@Override
	public List<ItemStack> getAllOutputs()
	{
		if(output.isEmpty()) return ObjectLists.emptyList();
		List<ItemStack> result = new ObjectArrayList<>();
		int diff = maxValue - minValue;
		if(diff > 5)
		{
			ItemStack copy = output.copy();
			copy.setCount(Math.max(1, minValue));
			result.add(copy);
			copy = output.copy();
			copy.setCount(maxValue);
			result.add(copy);
		}
		else
		{
			for(int i = minValue;i<=maxValue;i++)
			{
				if(i == 0) continue;
				ItemStack stack = output.copy();
				stack.setCount(i);
				result.add(stack);
			}
		}
		return result;
	}
	
	@Override
	public List<FluidStack> onFluidRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags, ItemStack input)
	{
		if(fluidOutput.isEmpty()) return ObjectLists.emptyList();
		int value = minValue + rand.nextInt((maxValue - minValue) + 1);
		if(value <= 0) return ObjectLists.emptyList();
		return ObjectLists.singleton(new FluidStack(fluidOutput, value));
	}

	@Override
	public List<FluidStack> getAllFluidOutputs()
	{
		if(fluidOutput.isEmpty()) return ObjectLists.emptyList();
		int step = Math.min(1, (maxFluid - minFluid) / 10);
		List<FluidStack> fluids = new ObjectArrayList<>();
		for(int value = minFluid;value<=maxFluid;)
		{
			fluids.add(new FluidStack(fluidOutput, value));
			if(value == maxFluid) break;
			value = Math.min(maxFluid, value + step);
		}
		return fluids;
	}
	
	@Override
	public CompoundTag getMetadata()
	{
		return nbt == null ? EMPTY_COMPOUND : nbt;
	}
	
	@Override
	public float getExperience()
	{
		return 0;
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeItem(output);
		buffer.writeByte(minValue);
		buffer.writeByte(maxValue);
		buffer.writeFluidStack(fluidOutput);
		buffer.writeVarInt(minFluid);
		buffer.writeVarInt(maxFluid);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.add("output", IInput.writeItemStack(output));
		obj.addProperty("minOut", minValue);
		obj.addProperty("maxOut", maxValue);
		obj.add("fluidOutput", IInput.writeFluidStack(fluidOutput));
		obj.addProperty("minFluid", minFluid);
		obj.addProperty("maxFluid", maxFluid);
		if(nbt != null && nbt != EMPTY_COMPOUND)
		{
			obj.addProperty("nbt", nbt.toString());
		}
		return obj;
	}
}
