package ic2.api.classic.recipe;

import ic2.api.recipe.IRecipeInput;

public interface ICustomRecipeInput extends IRecipeInput
{
	/**
	 * If the recipe is special that means it use something else than ItemStacks oredict entries.
	 * These could be CropCards, FluidContainers, or other things. So avoid overriding those
	 * @return if the recipeInput isnt a form of a itemstack
	 */
	public boolean isSpecial();
	
	/**
	 * @return if the input is a oredict
	 */
	public boolean isOreDict();
	
	/**
	 * @return if the input is a oredict entry it will return the id of this else a null will be returned
	 */
	public String getOreDictEntry();
}
