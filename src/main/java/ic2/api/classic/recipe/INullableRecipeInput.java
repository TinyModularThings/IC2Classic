package ic2.api.classic.recipe;

import ic2.api.recipe.IRecipeInput;

/**
 * 
 * @author Speiger
 * 
 * If your Recipe Class is implementing this interface
 * that means that your matches(ItemStack) function has to support a null
 * parameter. Since the default implementations do not support it i had to
 * make this class to be a thing
 */
public interface INullableRecipeInput extends IRecipeInput
{
}
