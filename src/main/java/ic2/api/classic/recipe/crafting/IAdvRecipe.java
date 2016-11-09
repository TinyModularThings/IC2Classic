package ic2.api.classic.recipe.crafting;

import java.util.List;

public interface IAdvRecipe
{
	
	/**
	 * @return the type of recipe
	 */
	public RecipeType getRecipeType();
	
	/** 
	 * @return if the recipe is a secret recipe
	 */
	public boolean isHidden();
	
	/**
	 * @return Input of the Recipe
	 */
	public List<IRecipeObject> getRecipeInput();
	
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
