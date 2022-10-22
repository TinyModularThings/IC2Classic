package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ItemStackInput implements IInput
{
	ItemStack input;
	int size;
	
	public ItemStackInput(JsonObject obj)
	{
		input = CraftingHelper.getItemStack(obj.getAsJsonObject("item"), true);
		size = obj.get("size").getAsInt();
	}
	
	public ItemStackInput(FriendlyByteBuf buffer)
	{
		input = buffer.readItem();
		size = buffer.readByte();
	}
	
	public ItemStackInput(ItemStack input, int size)
	{
		this.input = input.copy();
		this.size = size;
	}
	
	public ItemStackInput(ItemStack input)
	{
		this(input, input.getCount());
	}
	
	@Override
	public List<ItemStack> getComponents()
	{
		return ObjectLists.singleton(IInput.copyWithSize(input, size));
	}
	
	@Override
	public int getInputSize()
	{
		return size;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		return IInput.isStackEqual(input, stack);
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeItem(input);
		buffer.writeByte(size);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.add("item", IInput.writeItemStack(input));
		obj.addProperty("size", size);
		return obj;
	}
}
