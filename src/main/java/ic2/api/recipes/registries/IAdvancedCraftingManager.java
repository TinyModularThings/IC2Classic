package ic2.api.recipes.registries;

import ic2.api.items.IRepairable;
import ic2.api.recipes.ingridients.inputs.IInput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IAdvancedCraftingManager extends IListenableRegistry<IAdvancedCraftingManager>
{	
	default void addShapedIC2Recipe(String location, ItemStack output, Object... args) {addShapedRecipe(new ResourceLocation("ic2", location), output, args);}
	default void addShapelessIC2Recipe(String location, ItemStack output, Object... args) {addShapelessRecipe(new ResourceLocation("ic2", location), output, args);}
	default void addRepairIC2Recipe(String location, IRepairable repair, IInput repairMaterial, int effect, Object... args) {addRepairRecipe(new ResourceLocation("ic2", location), repair, repairMaterial, effect, args);}
	
	void addShapedRecipe(ResourceLocation location, ItemStack output, Object... args);
	void addShapelessRecipe(ResourceLocation location, ItemStack output, Object... args);
	void addRepairRecipe(ResourceLocation id, IRepairable repair, IInput repairMaterial, int effect, Object... args);
	
	default void addIC2SmeltingRecipe(String location, ItemStack output, Object input, SmeltingType... types) {addIC2SmeltingRecipe(location, output, input, 0F, types);}
	default void addIC2SmeltingRecipe(String location, ItemStack output, Object input, float xp, SmeltingType... types) {addIC2SmeltingRecipe(location, output, input, 0F, 1F, types);}
	default void addIC2SmeltingRecipe(String location, ItemStack output, Object input, float xp, float extraTime, SmeltingType... types) {addSmeltingRecipe(new ResourceLocation("ic2", location), output, input, xp, extraTime, types);}
	
	default void addSmeltingRecipe(ResourceLocation location, ItemStack output, Object input, SmeltingType... types) {addSmeltingRecipe(location, output, input, 0F, types);}
	
	default void addSmeltingRecipe(ResourceLocation location, ItemStack output, Object input, float xp, SmeltingType... types) {addSmeltingRecipe(location, output, input, xp, 1F, types);}
	void addSmeltingRecipe(ResourceLocation location, ItemStack output, Object input, float xp, float extraTime, SmeltingType... types);
	
	void addStoneCutterRecipe(ResourceLocation location, ItemStack output, Object input);
	default void addStoneCutterIC2Recipe(String location, ItemStack output, Object input) {addStoneCutterRecipe(new ResourceLocation("ic2", location), output, input);}
	
	void addSmithingRecipe(ResourceLocation location, Object main, Object secondary, ItemStack output);
	default void addSmithingIC2Recipe(String location, Object main, Object secondary, ItemStack output) { addSmithingRecipe(new ResourceLocation("ic2", location), main, secondary, output);}
	
	enum SmeltingType
	{
		FURNACE(200, "furnace"),
		SMOKER(100, "smelter"),
		BLASTFURNACE(100, "blast"),
		CAMPFIRE(100, "camp");
		
		String suffix;
		int baseTime;
		
		SmeltingType(int baseTime, String suffix)
		{
			this.baseTime = baseTime;
			this.suffix = suffix;
		}
		
		public int getBaseTime()
		{
			return baseTime;
		}
		
		public ResourceLocation convert(ResourceLocation location)
		{
			return new ResourceLocation(location.getNamespace(), location.getPath() + "_" + suffix);
		}
	}
}
