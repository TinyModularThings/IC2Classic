package ic2.api.classic.recipe.machine;

import java.util.List;
import java.util.Objects;

import net.minecraft.item.ItemStack;

public interface IElectrolyzerRecipeList
{
	/**
	 * Recipe Method for charging & discharging
	 * @param input Input Stack
	 * @param output Output Stack
	 * @param energy Energy used to produce the output
	 * @Info Input & Output do not support NBT unless it is used in Meta-data
	 * Output is used for reverse and will throw the input item out.
	 * NBT is supported for the Output not for the Input
	 * 
	 * This function will set the stack-size of the side (charge = input, discharge = output) to 1
	 * But keeps the other side as it was
	 */
	public void addBothRecipe(ItemStack input, ItemStack output, int energy);
	
	/**
	 * Recipe Method for Charging.
	 * @param input Input Stack (Stack-size max = 1)
	 * @param output Output Stack (No Stack-size limit)
	 * @param energy needed in the process
	 */
	public void addChargeRecipe(ItemStack input, ItemStack output, int energy);
	
	/**
	 * Recipe Method for discharging
	 * @param input Input Stack (Stack-size max = 1)
	 * @param output Output Stack (No Stack-size limit)
	 * @param energy that will be gained. Note: 60% of the charge will be gained back.
	 * This is hard-coded
	 */
	public void addDischargeRecipe(ItemStack input, ItemStack output, int energy);
	
	/**
	 * Function to read the Recipe List. Read only function
	 * You can delete stuff here but it will not be effecting the Machine RecipeList
	 * Use Remove Recipe
	 * @return the Recipe List
	 */
	public List<RecipeEntry> getRecipeList();
	
	/**
	 * Function to delete recipes
	 * @param stack Item that should be compared with
	 * @param input Input defines if it should check input or output
	 * @param both if delete both sides
	 * Note If it deletes both sides it will start on the side you check and the result will delete the other side
	 */
	public void removeRecipe(ItemStack stack, boolean input, boolean both);
	
	/**
	 * Function to get the Recipe Entry of the Item
	 * @param stack Input Stack
	 * @param input if it check the input or output list
	 * @param consumeInput if it should consume the input stack
	 * @return the Recipe Entry
	 */
	public RecipeEntry getOutput(ItemStack stack, boolean input);
	
	public static class RecipeEntry
	{
		ItemStack input;
		ItemStack output;
		int energy;
		boolean both;
		boolean charge;
		int hashCode;
		
		public RecipeEntry(ItemStack in, ItemStack out, int energy, boolean both, boolean charge)
		{
			if(in == null || out == null)
			{
				//Needs to be not null
				return;
			}
			input = in.copy();
			output = out.copy();
			this.energy = energy;
			this.both = both;
			this.charge = charge;
			hashCode = Objects.hash(in.getItem(), in.getMetadata(), out.getItem(), out.getMetadata(), energy, both, charge);
		}
		
		@Override
		public int hashCode()
		{
			return hashCode;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if(obj instanceof RecipeEntry)
			{
				RecipeEntry entry = (RecipeEntry)obj;
				if(isInputEqual(entry.input) && isOutputEqual(entry.output) && energy == entry.energy)
				{
					if(both == entry.both)
					{
						if(both)
						{
							return true;
						}
						return charge == entry.charge;
					}
				}
			}
			return false;
		}
		
		public boolean isInputEqual(ItemStack other)
		{
			if(input == null || other == null)
			{
				return false;
			}
			if(input.getItem() != other.getItem())
			{
				return false;
			}
			if(input.getMetadata() != other.getMetadata())
			{
				return false;
			}
			return true;
		}
		
		public boolean isOutputEqual(ItemStack other)
		{
			if(output == null || other == null)
			{
				return false;
			}
			if(output.getItem() != other.getItem())
			{
				return false;
			}
			if(output.getMetadata() != other.getMetadata())
			{
				return false;
			}
			return true;
		}
		
		public ItemStack getInput()
		{
			return input;
		}
		
		public ItemStack getOutput()
		{
			return output;
		}
		
		public int getEnergy()
		{
			return energy;
		}
		
		public boolean isDualRecipe()
		{
			return both;
		}
		
		public boolean isChargeRecipe()
		{
			return charge;
		}
	}
}
