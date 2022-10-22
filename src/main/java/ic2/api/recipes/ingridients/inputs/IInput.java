package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public interface IInput
{
	default Ingredient asIngredient()
	{
		List<ItemStack> stacks = getComponents();
		return Ingredient.of(stacks.toArray(new ItemStack[stacks.size()]));
	}
	
	List<ItemStack> getComponents();
	
	int getInputSize();
	
	boolean matches(ItemStack stack);
	
	default boolean matchesAll(ItemStack... stacks)
	{
		for(ItemStack stack : stacks)
		{
			if(!matches(stack))
			{
				return false;
			}
		}
		return true;
	}
	
	default boolean matchesAny(ItemStack... stacks)
	{
		for(ItemStack stack : stacks)
		{
			if(matches(stack))
			{
				return true;
			}
		}
		return false;
	}
	
	void serialize(FriendlyByteBuf buffer);
	JsonObject serialize();
	
	static JsonObject writeItemStack(ItemStack stack)
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
		obj.addProperty("count", stack.getCount());
		if(stack.hasTag()) {
			obj.addProperty("nbt", stack.getTag().toString());
		}
		return obj;
	}
	
	static JsonObject writeFluidStack(FluidStack stack)
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(stack.getFluid()).toString());
		obj.addProperty("amount", stack.getAmount());
		if(stack.hasTag()) {
			obj.addProperty("nbt", stack.getTag().toString());
		}
		return obj;		
	}
	
	static FluidStack readFluidStack(JsonObject obj)
	{
		return new FluidStack(ForgeRegistries.FLUIDS.getValue(ResourceLocation.tryParse(obj.get("fluid").getAsString())), obj.get("amount").getAsInt(), obj.has("nbt") ? readNBT(obj.get("nb").getAsString()) : null);
	}
	
	static CompoundTag readNBT(String s)
	{
		try
		{
			return TagParser.parseTag(s);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	static ItemStack copyWithSize(ItemStack stack, int newSize)
	{
		ItemStack copy = stack.copy();
		copy.setCount(newSize);
		return copy;
	}
	
	static boolean isStackEqual(ItemStack key, ItemStack other)
	{
		return key.sameItem(other) && isNBTExact(key, other);
	}
	
	static boolean isNBTExact(ItemStack subject, ItemStack target)
	{
		boolean key = subject.hasTag();
		boolean value = target.hasTag();
		if(!key && !value)
		{
			return true;
		}
		if(!key || !value)
		{
			return false;
		}
		CompoundTag keyTag = subject.getTag();
		CompoundTag valueTag = target.getTag();
		if(keyTag.size() != valueTag.size())
		{
			return false;
		}
		for(String entry : keyTag.getAllKeys())
		{
			Tag subjectNBT = keyTag.get(entry);
			Tag targetNBT = valueTag.get(entry);
			if(targetNBT == null || subjectNBT.getId() != targetNBT.getId() || !subjectNBT.equals(targetNBT))
			{
				return false;
			}
		}
		return false;
	}
}
