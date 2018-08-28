package ic2.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;

public interface IRecipeInputFactory {
	/**
	 * If the passed stack as a meta of {@link OreDictionary#WILDCARD_VALUE},<br/>
	 * the returned {@link IRecipeInput#matches(ItemStack)} will check if a stack
	 * <ul>
	 * 	<li>Matches the item</li>
	 * </ul>
	 * Otherwise checks for if the stack
	 * <ul>
	 * 	<li>Matches the item</li>
	 * 	<li>Matches the metadata</li>
	 * 	<li>Matches the present NBT tags of the given stack</li>
	 * </ul>
	 * And the {@link IRecipeInput#getAmount()} returns the size of the given stack
	 */
	IRecipeInput forStack(ItemStack stack);
	/**
	 * If the passed stack as a meta of {@link OreDictionary#WILDCARD_VALUE},<br/>
	 * the returned {@link IRecipeInput#matches(ItemStack)} will check if a stack
	 * <ul>
	 * 	<li>Matches the item</li>
	 * </ul>
	 * Otherwise checks for if the stack
	 * <ul>
	 * 	<li>Matches the item</li>
	 * 	<li>Matches the metadata</li>
	 * 	<li>Matches the present NBT tags of the given stack</li>
	 * </ul>
	 * And the {@link IRecipeInput#getAmount()} returns the given amount
	 */
	IRecipeInput forStack(ItemStack stack, int amount);
	/**
	 * If the passed stack as a meta of {@link OreDictionary#WILDCARD_VALUE},<br/>
	 * the returned {@link IRecipeInput#matches(ItemStack)} will act identically to {@link #forStack(ItemStack)}
	 * Otherwise checks for if the stack
	 * <ul>
	 * 	<li>Matches the item</li>
	 * 	<li>Matches the metadata</li>
	 * 	<li>Has the same NBT tags as the given stack</li>
	 * </ul>
	 * And the {@link IRecipeInput#getAmount()} returns the size of the given stack
	 */
	IRecipeInput forExactStack(ItemStack stack);
	/**
	 * If the passed stack as a meta of {@link OreDictionary#WILDCARD_VALUE},<br/>
	 * the returned {@link IRecipeInput#matches(ItemStack)} will act identically to {@link #forStack(ItemStack, int)}
	 * Otherwise checks for if the stack
	 * <ul>
	 * 	<li>Matches the item</li>
	 * 	<li>Matches the metadata</li>
	 * 	<li>Has the same NBT tags as the given stack</li>
	 * </ul>
	 * And the {@link IRecipeInput#getAmount()} returns the given amount
	 */
	IRecipeInput forExactStack(ItemStack stack, int amount);
	IRecipeInput forOreDict(String name);
	IRecipeInput forOreDict(String name, int amount);
	IRecipeInput forOreDict(String name, int amount, int metaOverride);
	IRecipeInput forFluidContainer(Fluid fluid);
	IRecipeInput forFluidContainer(Fluid fluid, int amount);
	IRecipeInput forAny(IRecipeInput... options);
	IRecipeInput forAny(Iterable<IRecipeInput> options);
	IRecipeInput forIngredient(Ingredient ingredient);
	Ingredient getIngredient(IRecipeInput input);
}
