package ic2.api.recipe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeInputItemStack implements IRecipeInput {
	public RecipeInputItemStack(ItemStack aInput) {
		this(aInput, aInput.stackSize);
	}

	public RecipeInputItemStack(ItemStack aInput, int aAmount) {
		if (aInput.getItem() == null) throw new IllegalArgumentException("Invalid item stack specfied");

		input = aInput.copy(); // Never forget to copy.
		amount = aAmount;
	}

	@Override
	public boolean matches(ItemStack subject) {
		return subject.getItem() == input.getItem() && // Item matching
				(subject.getMetadata() == input.getMetadata() || input.getMetadata() == OreDictionary.WILDCARD_VALUE) && //meta matching
				(input.getMetadata() == OreDictionary.WILDCARD_VALUE || RecipeUtil.matchesNBT(subject.getTagCompound(), input.getTagCompound())); //nbt matching.
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public List<ItemStack> getInputs() {
		return Collections.unmodifiableList(Arrays.asList(RecipeUtil.setImmutableSize(input, getAmount())));
	}

	@Override
	public String toString() {
		return "RInputItemStack<"+RecipeUtil.setImmutableSize(input, amount)+">";
	}

	public final ItemStack input;
	public final int amount;
}
