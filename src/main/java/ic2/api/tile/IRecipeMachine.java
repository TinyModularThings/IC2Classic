package ic2.api.tile;

import ic2.api.recipe.IMachineRecipeManager;

public interface IRecipeMachine extends IMachine
{
	public IMachineRecipeManager getRecipeList();
}
