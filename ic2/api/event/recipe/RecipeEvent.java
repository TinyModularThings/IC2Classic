package ic2.api.event.recipe;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * 
 * @author Speiger
 * recipe injection base class. If you want to modify a recipe just cancel it.
 * try not to override the internal Recipe Item. Just cancel it...
 */

@Cancelable
public class RecipeEvent extends Event
{
	final ItemStack input;
	final ItemStack output;
	
	public RecipeEvent(ItemStack input, ItemStack output)
	{
		this.input = input;
		this.output = output;
	}
	
	public final ItemStack getInput()
	{
		return input.copy();
	}
	
	public final ItemStack getOutput()
	{
		return output.copy();
	}
}
