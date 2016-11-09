package ic2.api.classic.recipe.crafting;

import ic2.api.item.ICustomDamageItem;
import ic2.api.recipe.ICraftingRecipeManager;
import ic2.api.recipe.IRecipeInput;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ICraftingRecipeList
{
	/**
	 * All Created recipes
	 * @return List with only IC2 Recipes. All created
	 * @Note: You can not delete these recipes or Modify them.
	 * This is more a backup storage so you can get Recipes Back if someone deleted them
	 */
	public List<IAdvRecipe> getRecipes();
	
	/**
	 * Adds a shaped crafting recipe.
	 * 
	 * @param output Recipe output
	 * @param input Recipe input format
	 */
	public void addRecipe(ItemStack output, Object... input);
	
	/**
	 * Adds a shapeless crafting recipe.
	 * 
	 * @param output Recipe output
	 * @param input Recipe input
	 */
	public void addShapelessRecipe(ItemStack output, Object... input);
	
	/**
	 * Function to add a repair Recipe really easy.
	 * Its only for CustomDamageItems and meta-data should be split from ItemDamage
	 * @param toRepair ItemType the recipe Should check for
	 * @param reapirMaterial Item that is needed to repair the Item And also how much is needed (StacksizeCheck)
	 * @param repairEffect how much effect the repair Material has (both number sides work. Positive & Negative)
	 * Only difference is: It ignores meta-data directly
	 */
	public void addRepairRecipe(ICustomDamageItem toRepair, IRecipeInput repairMaterial, int repairEffect);
	
	/**
	 * Function to add a repair Recipe really easy.
	 * Its only for CustomDamageItems and meta-data should be split from ItemDamage
	 * @param toRepair ItemType the recipe Should check for
	 * @param itemMeta meta-data of that itemType. Short.MaxValue == ignoreMeta
	 * @param reapirMaterial Item that is needed to repair the Item And also how much is needed (StacksizeCheck)
	 * @param repairEffect how much effect the repair Material has (both number sides work. Positive & Negative)
	 */
	public void addRepairRecipe(ICustomDamageItem toRepair, int itemMeta, IRecipeInput repairMaterial, int repairEffect);
	
	/**
	 * @return Helper function for IC2Exp stuff...
	 */
	public ICraftingRecipeManager toIC2Exp();
}
