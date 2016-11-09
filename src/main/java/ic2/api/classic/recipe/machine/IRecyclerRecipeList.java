package ic2.api.classic.recipe.machine;

public interface IRecyclerRecipeList extends IMachineRecipeList
{
	/**
	 * function to get the Filter of the RecipeManager
	 * so you can access it.
	 * @return the filter of the machine
	 */
	public IMachineFilter getFilter();
}
