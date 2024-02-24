package ic2.api.recipes.ingridients.generators;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemGenerator implements IOutputGenerator {
	
	Item item;
	int count;
	
	public ItemGenerator(JsonObject obj) {
		this(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(obj.get("item").getAsString())), obj.get("size").getAsInt());
	}
	
	public ItemGenerator(ItemLike item) {
		this(item, 1);
	}
	
	public ItemGenerator(ItemLike item, int count) {
		this.item = item.asItem();
		this.count = count;
	}
	
	@Override
	public void addItems(Consumer<ItemStack> output) {
		output.accept(new ItemStack(item, count));
	}
	
	@Override
	public JsonObject serialize() {
		JsonObject obj = new JsonObject();
		obj.addProperty("item", ForgeRegistries.ITEMS.getKey(item).toString());
		obj.addProperty("size", count);
		return obj;
	}
	
}
