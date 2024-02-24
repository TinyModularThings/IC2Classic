package ic2.api.recipes.ingridients.recipes;

import java.util.List;

import com.google.gson.JsonObject;

import ic2.api.recipes.RecipeRegistry;
import ic2.api.recipes.ingridients.generators.IOutputGenerator;
import ic2.api.recipes.ingridients.inputs.IInput;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public class RangeRecipeOutput extends BaseRecipeOutput
{
	CompoundTag nbt;
	ItemStack output;
	int minValue;
	int maxValue;
	
	public RangeRecipeOutput(JsonObject obj)
	{
		IOutputGenerator generator = RecipeRegistry.INGREDIENTS.readOutputGenerator(obj.getAsJsonObject("output"));
		generator.addItems(T -> output = T);
		generators.add(generator);
		minValue = obj.get("minOut").getAsInt();
		maxValue = obj.get("maxOut").getAsInt();
		if(obj.has("nbt")) nbt = IInput.readNBT(obj.get("nbt").getAsString());
	}
	
	public RangeRecipeOutput(FriendlyByteBuf buffer)
	{
		output = buffer.readItem();
		minValue = buffer.readByte();
		maxValue = buffer.readByte();
	}
	
	public RangeRecipeOutput(IOutputGenerator output, int minValue, int maxValue)
	{
		output.addItems(T -> this.output = T);
		generators.add(output);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public RangeRecipeOutput(IOutputGenerator output, CompoundTag nbt, int minValue, int maxValue)
	{
		output.addItems(T -> this.output = T);
		generators.add(output);
		this.nbt = nbt;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	@Override
	public List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags)
	{
		ItemStack stack = output.copy();
		stack.setCount(minValue + rand.nextInt((maxValue - minValue) + 1));
		return stack.getCount() <= 0 ? ObjectLists.emptyList() : ObjectLists.singleton(stack);
	}
	
	@Override
	public List<ItemStack> getAllOutputs()
	{
		List<ItemStack> result = new ObjectArrayList<>();
		for(int i = minValue;i<=maxValue;i++)
		{
			if(i == 0) continue;
			ItemStack stack = output.copy();
			stack.setCount(i);
			result.add(stack);
		}
		return result;
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
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.add("output", RecipeRegistry.INGREDIENTS.serializeOutputGenerator(generators.get(0)));
		obj.addProperty("minOut", minValue);
		obj.addProperty("maxOut", maxValue);
		if(nbt != null && nbt != EMPTY_COMPOUND)
		{
			obj.addProperty("nbt", nbt.toString());
		}
		return obj;
	}
}
