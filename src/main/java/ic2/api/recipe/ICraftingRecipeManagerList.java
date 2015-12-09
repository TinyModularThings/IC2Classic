package ic2.api.recipe;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public interface ICraftingRecipeManagerList extends ICraftingRecipeManager
{
	
	public List<IAdvRecipe> getRecipeList();
	
	
	public interface IAdvRecipe
	{
		/**
		 * @return if the recipe is a Shaped or not
		 */
		public boolean isShaped();
		
		/** 
		 * @return if the recipe is a secret recipe
		 */
		public boolean isHidden();
		
		/**
		 * @return Input of the Recipe
		 */
		public List<RecipeObject> getRecipeInput();
		
		/**
		 * It will return -1 on Shapeless
		 * @return Recipe Grid Height
		 */
		public int getRecipeHeight();
		
		/**
		 * It will return -1 on Shapeless
		 * @return Recipe Grid Length
		 */
		public int getRecipeLength();
	}
	
	public class RecipeObject
	{
		int slot;
		List<ItemStack> items;
		
		public RecipeObject(int slot, ItemStack item)
		{
			this(slot, Arrays.asList(item));
		}
		
		public RecipeObject(int slot, List<ItemStack> items)
		{
			this.slot = slot;
			this.items = items;
		}
		
		/**
		 * @return if Shapeless it will be always -1
		 */
		public int getSlot()
		{
			return slot;
		}
		
		public List<ItemStack> getItems()
		{
			return items;
		}
	}
}
