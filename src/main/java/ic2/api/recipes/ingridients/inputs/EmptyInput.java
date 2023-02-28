package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class EmptyInput implements IInput
{
	public static final IInput INSTANCE = new EmptyInput();
	
	private EmptyInput() {}
	@Override
	public List<ItemStack> getComponents() { return ObjectLists.singleton(ItemStack.EMPTY); }
	@Override
	public Ingredient asIngredient() { return Ingredient.EMPTY; }
	@Override
	public int getInputSize() { return 0; }
	@Override
	public boolean matches(ItemStack stack) { return stack.isEmpty(); }
	@Override
	public void serialize(FriendlyByteBuf buffer) {}
	@Override
	public JsonObject serialize() { return new JsonObject(); }
	
	public static class InternalInput extends EmptyInput
	{
		public static final IInput INSTANCE = new InternalInput();
	}
}
