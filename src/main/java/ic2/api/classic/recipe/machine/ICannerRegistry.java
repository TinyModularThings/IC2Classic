package ic2.api.classic.recipe.machine;

import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

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
