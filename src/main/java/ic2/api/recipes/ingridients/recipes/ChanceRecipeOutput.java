package ic2.api.recipes.ingridients.recipes;

import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ic2.api.recipes.ingridients.inputs.IInput;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ChanceRecipeOutput implements IRecipeOutputChance
{
	List<ItemStack> outputs = new ObjectArrayList<>();
	float chance;
	CompoundTag nbt;
	float xp;
	
	public ChanceRecipeOutput(JsonObject obj)
	{
		chance = obj.get("chance").getAsFloat();
		xp = obj.get("xp").getAsFloat();
		if(obj.has("nbt"))
		{
			nbt = IInput.readNBT(obj.get("nbt").getAsString());
		}
		for(JsonElement el : obj.getAsJsonArray("outputs"))
		{
			outputs.add(CraftingHelper.getItemStack(el.getAsJsonObject(), true));
		}
	}
	
	public ChanceRecipeOutput(FriendlyByteBuf buffer)
	{
		int expected = buffer.readByte();
		for(int i = 0;i<expected;i++)
		{
			outputs.add(buffer.readItem());
		}
		chance = buffer.readFloat();
		nbt = buffer.readNbt();
		xp = buffer.readFloat();
	}

	public ChanceRecipeOutput(List<ItemStack> outputs, float xp, CompoundTag nbt, float chance)
	{
		this.outputs.addAll(outputs);
		this.chance = chance;
		this.nbt = nbt;
		this.xp = xp;
	}
	
	public ChanceRecipeOutput(List<ItemStack> outputs, float xp, float chance)
	{
		this.outputs = outputs;
		this.chance = chance;
		this.xp = xp;
	}
	
	@Override
	public List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags)
	{
		return rand.nextFloat() < chance ? IRecipeOutput.copyItems(outputs) : Collections.emptyList();
	}
	
	@Override
	public List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags, IRecipeOverride overrides)
	{
		return rand.nextFloat() < overrides.getChance(chance) ? IRecipeOutput.copyItems(outputs) : Collections.emptyList();
	}
	
	@Override
	public float getChance() 
	{
		return chance; 
	}

	@Override
	public List<ItemStack> getAllOutputs()
	{
		return IRecipeOutput.copyItems(outputs);
	}

	@Override
	public CompoundTag getMetadata()
	{
		return nbt == null ? EMPTY_COMPOUND : nbt;
	}

	@Override
	public float getExperience()
	{
		return xp;
	}

	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeByte(outputs.size());
		for(ItemStack out : outputs)
		{
			buffer.writeItem(out);
		}
		buffer.writeFloat(chance);
		buffer.writeNbt(nbt);
		buffer.writeFloat(xp);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("chance", chance);
		obj.addProperty("xp", xp);
		if(nbt != null && nbt != EMPTY_COMPOUND) obj.addProperty("nbt", nbt.toString());
		JsonArray array = new JsonArray();
		for(ItemStack stack : outputs) {
			array.add(IInput.writeItemStack(stack));
		}
		obj.add("outputs", array);
		return obj;
	}
}
