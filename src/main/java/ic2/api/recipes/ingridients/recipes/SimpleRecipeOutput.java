package ic2.api.recipes.ingridients.recipes;

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

public class SimpleRecipeOutput implements IRecipeOutput
{
	protected List<ItemStack> outputs = new ObjectArrayList<>();
	protected CompoundTag nbt;
	protected float xp;
	
	public SimpleRecipeOutput(JsonObject obj)
	{
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
	
	public SimpleRecipeOutput(FriendlyByteBuf buffer)
	{
		int expected = buffer.readByte();
		for(int i = 0;i<expected;i++)
		{
			outputs.add(buffer.readItem());
		}
		nbt = buffer.readNbt();
		xp = buffer.readFloat();
	}
	
	public SimpleRecipeOutput(List<ItemStack> outputs)
	{
		this.outputs.addAll(outputs);
	}
	
	public SimpleRecipeOutput(List<ItemStack> outputs, CompoundTag nbt)
	{
		this.outputs.addAll(outputs);
		this.nbt = nbt;
	}
	
	public SimpleRecipeOutput(List<ItemStack> outputs, float xp)
	{
		this.outputs.addAll(outputs);
		this.xp = xp;
	}
	
	public SimpleRecipeOutput(List<ItemStack> outputs, CompoundTag nbt, float xp)
	{
		this.outputs.addAll(outputs);
		this.nbt = nbt;
		this.xp = xp;
	}
	
	@Override
	public List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags)
	{
		return IRecipeOutput.copyItems(outputs);
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
		buffer.writeNbt(nbt);
		buffer.writeFloat(xp);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
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
