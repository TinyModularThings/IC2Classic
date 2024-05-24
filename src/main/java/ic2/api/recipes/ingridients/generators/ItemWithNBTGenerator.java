package ic2.api.recipes.ingridients.generators;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import ic2.api.recipes.ingridients.inputs.IInput;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ItemWithNBTGenerator implements IOutputGenerator {
	ItemStack stack;
	int count;
	
	public ItemWithNBTGenerator(JsonObject obj) {
		this(CraftingHelper.getItemStack(obj, true), obj.get("size").getAsInt());
	}
	
	public ItemWithNBTGenerator(ItemStack stack) {
		this(stack, stack.getCount());
	}
	
	public ItemWithNBTGenerator(ItemStack stack, int count) {
		this.stack = stack;
		this.count = count;
	}
	
	@Override
	public void addItems(Consumer<ItemStack> output) {
		output.accept(IInput.copyWithSize(stack, count));
	}
	
	@Override
	public JsonObject serialize() {
		JsonObject obj = IInput.writeItemStack(stack, false);
		obj.addProperty("size", count);
		return obj;
	}
}
