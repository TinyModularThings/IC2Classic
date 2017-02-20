package ic2.api.classic.recipe.machine;

import ic2.api.item.ICustomDamageItem;
import ic2.api.recipe.IRecipeInput;

import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public interface ICannerRegistry
{
	/**
	 * Register your custom Food Can effects.
	 * @param effect Your customEffect 
	 * @return the id of your Effect
	 */
	public int registerCanEffect(IFoodCanEffect effect);
	
	/**
	 * Function to access the Effect from the id
	 * @param id you provide
	 * @return the FoodEffect you get for it
	 */
	public IFoodCanEffect getEffectFromID(int id);
	
	/**
	 * Access to all Effects
	 * @return Effect Map
	 */
	public Map<Integer, IFoodCanEffect> getEffectMap();
	
	/**
	 * Register Items for the Effect you want.
	 * 
	 * 1: Hunger (Rotten Flesh) (80% chance)
	 * 2: Poison (Spider Eye)
	 * 3: Hunger (Raw Chicken) (30% chance)
	 * 4: Golden Apple
	 * 5: Notch Apple
	 * 6: Corus Fruit
	 * 
	 * @param effectID you want to apply
	 * @param items you want to apply the Effects
	 * @Note: Its saves the Item as Item & Meta-data no NBT support
	 */
	public void registerItemsForEffect(int effectID, ItemStack...items);
	
	/**
	 * Get your Effect id from the item
	 * @param item the Item you provide
	 * @return the ID you get. 0 = no Effect
	 */
	public int getEffectForItem(ItemStack item);
	
	/**
	 * If you want to delete a effect because you do not want to have that applied
	 * then here you can do that.
	 * @param effect Effect id you want to delete.
	 * @param if you want to delete the items with it. or just want to replace the id
	 * @Note Delete items will be auto applied if there is no replacement found when the machine runs
	 * @Note Intended use is for Modpack that want to change effects a bit
	 */
	public void deleteEffectID(int effect, boolean deleteItems);
	
	/**
	 * register a custom Fuel Value for your Item in the canning machine.
	 * Its the fuel can. Note: Fuel overrides Multiplier at the canning machine
	 * @param item you want to register
	 * @param fuel Fuel amount they provide
	 * @Note: Its saves the Item as Item & Meta-data no NBT support 
	 * @Note: Override supported
	 */
	public void registerFuelValue(ItemStack item, int fuel);
	
	/**
	 * Register fuel multiplier for a Item that is used in the canning machine
	 * @param item you want to register
	 * @param mutiplier fuel multiplier you want to provide
	 * @Note: Its saves the Item as Item & Meta-data no NBT support
	 * @Note: Override supported
	 */
	public void registerFuelMultiplier(ItemStack item, float mutiplier);
	
	/**
	 * Access to the FuelInfo
	 * @param stack you want to get the Info with.
	 * @return the FuelInfo. Null if there is no fuel info
	 */
	public FuelInfo getFuelInfo(ItemStack stack);
	
	/**
	 * Access of all Types of items that are fuels
	 * @return list of fuels
	 */
	public List<FuelInfo> getAllFuelTypes();
	
	/**
	 * Only way of deleting fuel Values in the registry.
	 * Even accessing the list does not work. And modifying the list
	 * or the getter will not work. (Not because i want to prevent it,
	 * its because of the way the registry works)
	 * @param fuel you want to delete
	 */
	public void deleteItemFuel(ItemStack fuel);
	
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
	 * Function to access all registered Repair Recipes
	 * @return repair Recipe Map
	 */
	public Map<ICustomDamageItem, List<Tuple<Integer, Tuple<IRecipeInput, Integer>>>> getRepairMap();
	
	/**
	 * Function to get the Result of the Recipe
	 * @param input yourItem that needs to be repaired
	 * @param material the Material that is needed
	 * @return the result. Can be null
	 */
	public Tuple<IRecipeInput, Integer> getRepairResult(ItemStack input, ItemStack material, boolean ignoreStacksize);
	
	/**
	 * function to remove the RepairRecipes for a CustomDamageItem
	 * @param item your Item
	 * Note this removes all recipes
	 */
	public void removeRepairItem(ICustomDamageItem item);
	
	/**
	 * Function to remove 1 Repair Recipe
	 * @param item theItem
	 * @param meta and the metadata of the item (The Entry)
	 */
	public void removeRepairItem(ICustomDamageItem item, int meta);
	
	/**
	 * Function to add a Canning Recipe
	 * @param container The Container that is needed. (No NBTSupport)
	 * @param toFill The Input into the Container
	 * @param output the Result the of the Canning
	 */
	public void registerCannerItem(ItemStack container, IRecipeInput toFill, ItemStack output);
	
	/**
	 * Function to access the recipe Map.
	 * Do not remove Recipes here that will not work.
	 * @return the recipeMap
	 */
	public Map<ItemStack, List<Tuple<IRecipeInput, ItemStack>>> getCanningMap();
	
	/**
	 * Function to check if the container is present in the RecipeList.
	 * @param container the Container
	 * @return if the container has Recipes
	 */
	public boolean hasCanningContainer(ItemStack container);
	
	/**
	 * Function to get the Filling Result
	 * @param container the Container that is provided
	 * @param toFill the Input that is needed
	 * @return the result. can be null
	 */
	public Tuple<IRecipeInput, ItemStack> getCanningResult(ItemStack container, ItemStack toFill, boolean ignoreStacksize);
	
	/**
	 * Function to remove all Items for a Container
	 * @param container yourContainer
	 */
	public void removeCanningRecipe(ItemStack container);
	
	/**
	 * Function to remove the Recipe that matches the filling component
	 * @param container yourContainer
	 * @param toFill what is needed to fill
	 * it will remove All Recipes that matches
	 */
	public void removeCanningRecipe(ItemStack container, ItemStack toFill);
	
	
	
	public static class FuelInfo
	{
		int amount;
		float multiplier;
		ItemStack item;
		
		public FuelInfo(int amount, float multiplier, ItemStack item)
		{
			this.amount = amount;
			this.multiplier = multiplier;
			this.item = item;
		}
		
		public int getAmount()
		{
			return amount;
		}
		
		public float getMultiplier()
		{
			return multiplier;
		}
		
		public ItemStack getItem()
		{
			return item;
		}
	}
}
