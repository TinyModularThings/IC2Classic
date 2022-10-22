package ic2.api.recipes.registries;

import java.util.List;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IElectrolyzerRecipeList extends IListenableRegistry<IElectrolyzerRecipeList>
{
	int CHARGE = 1;
	int DISCHARGE = 2;
	
	default void addChargeRecipe(ResourceLocation id, ItemStack input, ItemStack output, int energy)
	{
		addRecipe(id, input, output, CHARGE, energy);
	}
	
	default void addDischargeRecipe(ResourceLocation id, ItemStack input, ItemStack output, int energy)
	{
		addRecipe(id, input, output, DISCHARGE, energy);
	}
	
	default void addDualRecipe(ResourceLocation id, ItemStack input, ItemStack output, int energy)
	{
		addRecipe(id, input, output, CHARGE | DISCHARGE, energy);
	}
	
	void addRecipe(ResourceLocation id, ItemStack input, ItemStack output, int flags, int energy);
	
	void removeRecipe(ItemStack input, boolean charge);
	void removeRecipe(ResourceLocation id);
	
	List<ElectrolyzerRecipe> getRecipes();
	
	ElectrolyzerRecipe getRecipe(ItemStack input, boolean charge, boolean compareSize);
	
	public static class ElectrolyzerRecipe
	{
		ResourceLocation id;
		ItemStack input;
		ItemStack output;
		int flags;
		int energy;
		
		public ElectrolyzerRecipe(FriendlyByteBuf buffer)
		{
			id = buffer.readResourceLocation();
			input = buffer.readItem();
			output = buffer.readItem();
			flags = buffer.readByte();
			energy = buffer.readInt();
		}
		
		public ElectrolyzerRecipe(ResourceLocation id, ItemStack input, ItemStack output, int flags, int energy)
		{
			this.id = id;
			this.input = input;
			this.output = output;
			this.flags = flags;
			this.energy = energy;
		}
		
		public ResourceLocation getId()
		{
			return id;
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
		
		public boolean isChargeRecipe()
		{
			return (flags & CHARGE) != 0;
		}
		
		public boolean isDischargeRecipe()
		{
			return (flags & DISCHARGE) != 0;
		}
		
		public boolean isDual()
		{
			return (flags & (CHARGE | DISCHARGE)) != 0;

		}
		
		public boolean matches(ItemStack input, boolean charge)
		{
			return charge ? input.getCount() >= this.input.getCount() : input.getCount() >= output.getCount();
		}
		
		public void serialize(FriendlyByteBuf buffer)
		{
			buffer.writeResourceLocation(id);
			buffer.writeItem(input);
			buffer.writeItem(output);
			buffer.writeByte(flags);
			buffer.writeInt(energy);
		}
	}
}
