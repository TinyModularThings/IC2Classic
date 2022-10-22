package ic2.api.tiles;

import ic2.api.recipes.registries.IMachineRecipeList;

public interface IRecipeMachine extends IInputMachine
{
	IMachineRecipeList getRecipeList();
}
