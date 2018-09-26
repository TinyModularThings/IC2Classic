package ic2.api.classic.recipe.crafting;

import java.util.List;

import ic2.api.recipe.IRecipeInput;

public interface IAdvRecipe
{
	
	/**
	 * @return the type of recipe
	 */
	public RecipeType getRecipeType();
	
	/** 
	 * @return if the recipe is a secret recipe
	 */
	public boolean isInvisible();
	
	/**
	 * @return Input of the Recipe
	 */
	public List<IRecipeObject> getRecipeInput();
	
	public IRecipeInput getTrueInput(int index);
	
	/**
	 * It will return -1 on Shapeless
	 * @return Recipe Grid Height
	 */
	public int getRecipeHeight();
	
	/**
	 * It will return -1 on Shapeless
	 * @return Recipe Grid Length
	 */
	public int getRecipeLength();
	
	/**
	 * Returns the recipe id of this recipe
	 * @return Recipe id
	 */
	public String getRecipeID();
	
	
	public static enum RecipeType
	{
		Shaped,
		Shapeless,
		Repair;
		
		public boolean isShaped()
		{
			return this == Shaped;
		}
		
		public boolean isShapeless()
		{
			return this != Shaped;
		}
		
		public boolean isRepair()
		{
			return this == Repair;
		}
	}
}
