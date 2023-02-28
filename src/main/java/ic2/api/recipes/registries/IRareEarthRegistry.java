package ic2.api.recipes.registries;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IRareEarthRegistry extends IListenableRegistry<IRareEarthRegistry>
{	
	void registerOutput(ResourceLocation id, ItemStack output);
	void registerInput(ResourceLocation id, float value, ItemStack... input);
		
	RareEntry getOutputFor(ItemStack input);
	ItemStack getOutputFor(ResourceLocation id);
	
	RareEntry removeInput(ItemStack input);
	void removeOutput(ItemStack output);
	void remove(ResourceLocation id);
	List<RareEntry> getAllRecipes();
	
	public static class RareEntry
	{
		ResourceLocation id;
		ItemStack input;
		float value;
		ItemStack output;
		
		public RareEntry(ResourceLocation id, ItemStack input, float value, ItemStack output)
		{
			this.id = id;
			this.input = input;
			this.value = value;
			this.output = output;
		}
		
		public ResourceLocation getID()
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
		
		public float getValue()
		{
			return value;
		}
		
		public boolean matches(ResourceLocation location)
		{
			return id.equals(location);
		}
	}
}
