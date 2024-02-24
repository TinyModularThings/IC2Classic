package ic2.api.recipes.ingridients.generators;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import net.minecraft.world.item.ItemStack;

public class EmptyGenerator implements IOutputGenerator {
	public static final IOutputGenerator EMPTY = new EmptyGenerator();
	
	
	public EmptyGenerator() {}
	public EmptyGenerator(JsonObject obj) {}
	
	@Override
	public void addItems(Consumer<ItemStack> output) {
		output.accept(ItemStack.EMPTY);
	}
	
	@Override
	public JsonObject serialize() {
		return new JsonObject();
	}
	
}
