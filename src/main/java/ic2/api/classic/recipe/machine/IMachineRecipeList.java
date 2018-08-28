package ic2.api.classic.recipe.machine;

import java.util.List;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public interface IMachineRecipeList
{	
	/**
	 * Basic Function to add a Recipe to a Machine
	 * @param input The Input Items
	 * @param output What you want to have a Output
	 * @param id the id for the validation config. Null or empty = crash
	 */
	public void addRecipe(IRecipeInput input, ItemStack output, String id);
	
	/**
	 * Extra Function to add Exp For Outputs
	 * @param exp how much Exp the Recipe Produces
	 */
	public void addRecipe(IRecipeInput input, ItemStack output, float exp, String id);
	
	/**
	 * Extra Function to add Flags
	 * @param nbt Custom Flags/Mods
	 * @param output Recipe Result
	 * @param exp XP drop
	 * Possible Recipe Mods/Flags:
	 * RecipeEnergyModifier: Double Value which allows you to give a Multiplier to the Recipe Energy Needed.
	 * RecipeEnergy: Integer Value which allows you to give a Direct Energy Increase/Decrease to the Recipe. (Supports negatives)
	 * RecipeTimeModifier: Double Value which allows you to give a Multiplier on the Time that the Machine needs to process this Recipe.
	 * RecipeTime: Integer Value which allows you to give a Direct time value that the machine needs extra/less to process this machine (Supports negatives)
	 * These modifiers have to be in that NBTCompound you provide and no SubCompounds supported.
	 */
	public void addRecipe(IRecipeInput input, NBTTagCompound nbt, ItemStack output, float exp, String id);
	
	/**
	 * Advanced Recipe Adding Function. Allowing Random & Fortune control
	 * @param output Your Recipe Output
	 * Modifiers can be applied here in the output see Function above this one to see the modifiers
	 */
	public void addRecipe(IRecipeInput input, MachineOutput output, String id);
	
	/**
	 * Function to get a Recipe output of a Item
	 * @param input The item you want to process
	 * @param if stacksize should not matter
	 * @return the result. Can be null 
	 * @Note: Input could be null to keep that in mind
	 * @Note: If stackSize is less then the Recipe requested counts not as a match
	 */
	public RecipeEntry getRecipeInAndOutput(ItemStack input, boolean ignoreStackSize);
	
	/**
	 * Access to the Recipe List. You can delete here but you do not have to..
	 * @return recipeList
	 */
	public List<RecipeEntry> getRecipeMap();
	
	/**
	 * Function to delete a Recipe Based round the input
	 * @param input Input you want to delete with
	 * @return the Deleted Entires. Can be empty if nothing found
	 * Note it takes StackSize into account
	 */
	public List<RecipeEntry> removeRecipe(IRecipeInput input);
	
	/**
	 * Function to delete an Exact Entry that was added
	 * to the recipe List. It will not delete copied instances
	 * (If used wrong the recipe does not get deleted)
	 * @param entry the entry you want to deleted
	 */
	public void removeRecipe(RecipeEntry entry);
	
	/**
	 * Function to deleted a Recipe based on a Stack
	 * @param stack the stack you want to use
	 * @return the entry of the Deleted Recipe
	 */
	public RecipeEntry removeRecipe(ItemStack stack);
	
	/**
	 * Just a compatibility to IC2Exps recipe Manager
	 * If you want to have a custom Instance of IC2Exps version
	 * of the recipe list that is linked to this then use it
	 * but for api users it is useless. Its just there
	 * if you want to override RecipeManagers this is required
	 * (also you have to manually replace Exps managers..)
	 * @return the Classic Recipe Manager in Exps Formatting
	 */
	public IMachineRecipeManager toIC2Exp();
	
	public static class RecipeEntry
	{
		IRecipeInput input;
		MachineOutput output;
		
		public RecipeEntry(IRecipeInput in, MachineOutput out)
		{
			input = in;
			output = out;
		}
		
		public IRecipeInput getInput()
		{
			return input;
		}
		
		public MachineOutput getOutput()
		{
			return output;
		}
	}
}
