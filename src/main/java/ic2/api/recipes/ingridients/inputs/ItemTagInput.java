package ic2.api.recipes.ingridients.inputs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTagInput implements IInput
{
	TagKey<Item> tag;
	int size;
	
	public ItemTagInput(JsonObject obj)
	{
		tag = ItemTags.create(ResourceLocation.tryParse(obj.get("tag").getAsString()));
		size = obj.get("size").getAsInt();
	}
	
	public ItemTagInput(FriendlyByteBuf buffer)
	{
		tag = ItemTags.create(buffer.readResourceLocation());
		size = buffer.readByte();
	}
	
	public ItemTagInput(TagKey<Item> tag, int size)
	{
		this.tag = tag;
		this.size = size;
	}
	
	public ItemTagInput(TagKey<Item> tag)
	{
		this(tag, 1);
	}
	
	@Override
	public List<ItemStack> getComponents()
	{
		List<ItemStack> list = new ArrayList<>();
		for(Item item : ForgeRegistries.ITEMS.tags().getTag(tag))
		{
			list.add(new ItemStack(item, size));
		}
		return Collections.unmodifiableList(list);
	}
	
	@Override
	public Ingredient asIngredient()
	{
		if (size == 1) {
			return Ingredient.of(tag);
		} else {
			return IInput.super.asIngredient();
		}
	}
	
	@Override
	public int getInputSize()
	{
		return size;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		return stack.is(tag);
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeResourceLocation(tag.location());
		buffer.writeByte(size);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("tag", tag.location().toString());
		obj.addProperty("size", size);
		return obj;
	}
}
