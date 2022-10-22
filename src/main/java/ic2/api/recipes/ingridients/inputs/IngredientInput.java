package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class IngredientInput implements IInput
{
	Ingredient entry;
	int size;
	
	public IngredientInput(JsonObject obj)
	{
		this(Ingredient.fromJson(obj.get("ingredient")), obj.get("size").getAsInt());
	}
	
	public IngredientInput(FriendlyByteBuf buffer)
	{
		entry = Ingredient.fromNetwork(buffer);
		size = buffer.readByte();
	}
	
	public IngredientInput(Ingredient entry, int size)
	{
		this.entry = entry;
		this.size = size;
	}
	
	@Override
	public List<ItemStack> getComponents()
	{
		return ObjectArrayList.wrap(entry.getItems());
	}
	
	@Override
	public Ingredient asIngredient()
	{
		return entry;
	}
	
	@Override
	public int getInputSize()
	{
		return size;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		return entry.test(stack);
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		entry.toNetwork(buffer);
		buffer.writeByte(size);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.add("ingredient", entry.toJson());
		obj.addProperty("size", size);
		return obj;
	}
}
