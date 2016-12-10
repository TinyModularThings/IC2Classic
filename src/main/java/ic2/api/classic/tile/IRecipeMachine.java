package ic2.api.classic.tile;

import ic2.api.classic.recipe.machine.IMachineRecipeList;

public interface IRecipeMachine
{
	/**
	 * @return RecipeList of that Machine. Can be Null
	 */
	public IMachineRecipeList getRecipeList();
	
	/**
	 * Function to create for slots a Event that can be posted
	 * @return return the Event. Can be Null
	 * Note: Parameters are not allowed to be null since its throwing events which are created by other mods too
	 */
	public MachineType getType();
}
