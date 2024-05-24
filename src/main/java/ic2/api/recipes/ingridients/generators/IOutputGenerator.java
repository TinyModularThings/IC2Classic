package ic2.api.recipes.ingridients.generators;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import net.minecraft.world.item.ItemStack;

public interface IOutputGenerator {
	public void addItems(Consumer<ItemStack> output);
	
	public JsonObject serialize();
}
