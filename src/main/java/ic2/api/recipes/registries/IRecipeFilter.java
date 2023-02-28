package ic2.api.recipes.registries;

import java.util.List;

import com.google.gson.JsonObject;

import ic2.api.recipes.ingridients.inputs.IInput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public interface IRecipeFilter
{
	int SIMPLE = 1;
	int FILTER = 2;
	int BOTH = SIMPLE | FILTER;
	
	void add(ItemLike... items);
	
	void add(IInput... inputs);
	
	void remove(ItemStack stack, int flags);
	
	boolean contains(ItemStack stack);
	
	List<ItemStack> getFilteredItems();
	
	void clear();
	
	JsonObject serialize();
	void read(JsonObject obj);
}
