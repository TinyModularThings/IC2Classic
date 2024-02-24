package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ItemListInput implements IInput
{
	List<ItemStack> list = new ObjectArrayList<>();
	int size;
	boolean or;

	private ItemListInput()
	{
	}
	
	public ItemListInput(JsonObject obj)
	{
		size = obj.get("size").getAsInt();
		or = obj.get("or").getAsBoolean();
		for(JsonElement el : obj.getAsJsonArray("items"))
		{	
			ItemStack stack = CraftingHelper.getItemStack(el.getAsJsonObject(), true);
			if(stack.isEmpty()) continue;
			list.add(stack);
		}
	}
	
	public ItemListInput(FriendlyByteBuf buffer)
	{
		int size = buffer.readVarInt();
		for(int i = 0;i<size;i++)
		{
			ItemStack stack = buffer.readItem();
			if(!stack.isEmpty())
			{
				list.add(stack);
			}
		}
		this.size = buffer.readVarInt();
		or = buffer.readBoolean();
	}

	public ItemListInput(List<ItemStack> list, int size, boolean or)
	{
		this.list.addAll(list);
		this.size = size;
		this.or = or;
	}
	
	public static IInput createItemList(ItemLike...prov)
	{
		return createItemList(1, true, prov);
	}
	
	public static IInput createItemList(int size, ItemLike...prov)
	{
		return createItemList(size, true, prov);
	}
	
	public static IInput createItemList(int size, boolean or, ItemLike...prov)
	{
		ItemListInput inv = new ItemListInput();
		inv.size = size;
		inv.or = or;
		for(int i = 0;i<prov.length;i++)
		{
			ItemStack stack = new ItemStack(prov[i]);
			if(stack.isEmpty())
			{
				continue;
			}
			inv.list.add(stack);
		}
		return inv;
	}
	
	public static IInput createItemList(ItemStack...prov)
	{
		return createItemList(1, true, prov);
	}
	
	public static IInput createItemList(int size, ItemStack...prov)
	{
		return createItemList(size, true, prov);
	}
	
	public static IInput createItemList(int size, boolean or, ItemStack...stacks)
	{
		ItemListInput inv = new ItemListInput();
		inv.size = size;
		inv.or = or;
		for(int i = 0;i<stacks.length;i++)
		{
			ItemStack stack = stacks[i];
			if(stack.isEmpty())
			{
				continue;
			}
			inv.list.add(stack.copy());
		}
		return inv;
	}
	
	@Override
	public List<ItemStack> getComponents()
	{
		List<ItemStack> result = new ObjectArrayList<>();
		for(int i = 0,m=list.size();i<m;i++)
		{
			result.add(IInput.copyWithSize(list.get(i), size));
		}
		return result;
	}
	
	@Override
	public int getInputSize()
	{
		return size;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		if(or)
		{
			for(int i = 0,m=list.size();i<m;i++)
			{
				if(IInput.isStackEqual(list.get(i), stack))
				{
					return true;
				}
			}
			return false;
		}
		for(int i = 0,m=list.size();i<m;i++)
		{
			if(!IInput.isStackEqual(list.get(i), stack))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeVarInt(list.size());
		for(int i = 0,m=list.size();i<m;i++)
		{
			buffer.writeItem(list.get(i));
		}
		buffer.writeVarInt(size);
		buffer.writeBoolean(or);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonArray array = new JsonArray();
		for(ItemStack stack : list)
		{
			array.add(IInput.writeItemStack(stack, false));
		}
		JsonObject obj = new JsonObject();
		obj.add("items", array);
		obj.addProperty("or", or);
		obj.addProperty("size", size);
		return obj;
	}
}