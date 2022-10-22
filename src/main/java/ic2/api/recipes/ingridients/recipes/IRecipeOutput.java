package ic2.api.recipes.ingridients.recipes;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public interface IRecipeOutput
{
	CompoundTag EMPTY_COMPOUND = new CompoundTag();
	
	List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags);
	
	default List<ItemStack> onRecipeProcessed(RandomSource rand, CompoundTag persistentData, CompoundTag recipeFlags, IRecipeOverride overrides)
	{
		return onRecipeProcessed(rand, persistentData, recipeFlags);
	}
	
	List<ItemStack> getAllOutputs();
	
	@Nonnull
	CompoundTag getMetadata();
	
	float getExperience();
	
	void serialize(FriendlyByteBuf buffer);
	JsonObject serialize();
	
	static List<ItemStack> copyItems(List<ItemStack> copy)
	{
		List<ItemStack> list = new ObjectArrayList<>();
		for(ItemStack stack : copy)
		{
			list.add(stack.copy());
		}
		return list;
	}
	
	public static interface IRecipeOverride
	{
		float getChance(float original);
	}
}
