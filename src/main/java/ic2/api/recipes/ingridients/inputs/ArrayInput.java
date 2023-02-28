package ic2.api.recipes.ingridients.inputs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ic2.api.recipes.RecipeRegistry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class ArrayInput implements IInput
{
	List<IInput> inputs = new ObjectArrayList<>();
	boolean and;
	int stacksize;
	
	public ArrayInput(JsonObject obj)
	{
		stacksize = obj.get("size").getAsInt();
		and = obj.get("and").getAsBoolean();
		for(JsonElement element : obj.getAsJsonArray("elements"))
		{
			IInput input = RecipeRegistry.INGREDIENTS.readInput(element.getAsJsonObject());
			if(input != null) inputs.add(input);
		}
	}
	
	public ArrayInput(FriendlyByteBuf buffer)
	{
		stacksize = buffer.readByte();
		and = buffer.readBoolean();
		int size = buffer.readByte();
		for(int i = 0;i<size;i++)
		{
			IInput input = RecipeRegistry.INGREDIENTS.readInput(buffer);
			if(input == null)
			{
				continue;
			}
			inputs.add(input);
		}
	}
	
	public ArrayInput(boolean and, int size, IInput... inputs)
	{
		this.inputs.addAll(ObjectArrayList.wrap(inputs));
		this.and = and;
		stacksize = size;
	}
	
	public ArrayInput(Collection<IInput> inputs, int size, boolean and)
	{
		this.inputs.addAll(inputs);
		stacksize = size;
		this.and = and;
	}
	
	public ArrayInput(int size, boolean and, Object...values)
	{
		stacksize = size;
		this.and = and;
		for(Object obj : values)
		{
			IInput input = RecipeRegistry.INGREDIENTS.createInputFrom(obj);
			if(input == null)
			{
				throw new IllegalStateException("Couldn't create Input from Object: "+obj);
			}
			inputs.add(input);
		}
	}
	
	@Override
	public List<ItemStack> getComponents()
	{
		List<ItemStack> stacks = new ArrayList<>();
		for(IInput input : inputs)
		{
			stacks.addAll(input.getComponents());
		}
		return stacks;
	}
	
	@Override
	public int getInputSize()
	{
		return stacksize;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		for(IInput input : inputs)
		{
			if(input.matches(stack))
			{
				if(!and)
				{
					return true;
				}
			}
			else if(and)
			{
				return false;
			}
		}
		return and;
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeByte(stacksize);
		buffer.writeBoolean(and);
		buffer.writeByte(inputs.size());
		for(IInput input : inputs)
		{
			RecipeRegistry.INGREDIENTS.writeInput(input, buffer);
		}
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonArray array = new JsonArray();
		for(IInput input : inputs)
		{
			array.add(input.serialize());
		}
		JsonObject obj = new JsonObject();
		obj.addProperty("size", stacksize);
		obj.addProperty("and", and);
		obj.add("elements", array);
		return obj;
	}
}
