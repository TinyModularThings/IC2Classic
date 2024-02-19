package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInput implements IInput
{
	Item item;
	int size;
	Ingredient ingredient;
	
	public ItemInput(JsonObject obj)
	{
		this(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(obj.get("item").getAsString())), obj.get("size").getAsInt());
	}
	
	public ItemInput(FriendlyByteBuf buffer)
	{
		this(Item.byId(buffer.readVarInt()), buffer.readByte());
	}
	
	public ItemInput(ItemLike prov, int size)
	{
		this(prov.asItem(), size);
	}
	
	public ItemInput(Item item, int size)
	{
		this.item = item;
		this.size = size;
		ingredient = Ingredient.of(new ItemStack(item, size));
	}
	
	public ItemInput(ItemLike prov)
	{
		this(prov.asItem());
	}
	
	public ItemInput(Item item)
	{
		this(item, 1);
	}
	
	@Override
	public Ingredient asIngredient()
	{
		return ingredient;
	}
	
	@Override
	public List<ItemStack> getComponents()
	{
		return ObjectLists.singleton(new ItemStack(item, size));
	}
	
	@Override
	public int getInputSize()
	{
		return size;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		return stack.getItem() == item;
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeVarInt(Item.getId(item));
		buffer.writeByte(size);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("item", ForgeRegistries.ITEMS.getKey(item).toString());
		obj.addProperty("size", size);
		return obj;
	}
}
