package ic2.api.recipes;

import ic2.api.recipes.registries.IAdvancedCraftingManager;
import ic2.api.recipes.registries.ICannerRecipeRegistry;
import ic2.api.recipes.registries.IElectrolyzerRecipeList;
import ic2.api.recipes.registries.IFluidFuelRegistry;
import ic2.api.recipes.registries.IFoodCanRegistry;
import ic2.api.recipes.registries.IFusionRecipeList;
import ic2.api.recipes.registries.IIngredientRegistry;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.api.recipes.registries.IPotionBrewRegistry;
import ic2.api.recipes.registries.IRareEarthRegistry;
import ic2.api.recipes.registries.IRecyclerRecipeList;
import ic2.api.recipes.registries.IScrapBoxRegistry;
import ic2.api.recipes.registries.IUUMatterRegistry;
import ic2.api.util.SidedObject;

public final class RecipeRegistry
{
	public static IIngredientRegistry INGREDIENTS;
	public static IAdvancedCraftingManager CRAFTING;
	public static final SidedObject<IUUMatterRegistry> UU_SHAPES = new SidedObject<>();
	
	public static final SidedObject<IMachineRecipeList> FURNACE = new SidedObject<>(); //Read Only
	public static final SidedObject<IMachineRecipeList> BLAST_FURNACE = new SidedObject<>(); //Read Only
	public static final SidedObject<IMachineRecipeList> SMOKER = new SidedObject<>(); //Read Only
	public static final SidedObject<IMachineRecipeList> MACERATOR = new SidedObject<>();
	public static final SidedObject<IMachineRecipeList> EXTRACTOR = new SidedObject<>();
	public static final SidedObject<IMachineRecipeList> COMPRESSOR = new SidedObject<>();
	public static final SidedObject<IRecyclerRecipeList> RECYCLER = new SidedObject<>();
	public static final SidedObject<IMachineRecipeList> SAWMILL = new SidedObject<>();
	public static final SidedObject<IRareEarthRegistry> RARE_EARTH = new SidedObject<>();
	public static final SidedObject<ICannerRecipeRegistry> CANNER = new SidedObject<>();
	public static final SidedObject<IElectrolyzerRecipeList> ELECTROLYZER = new SidedObject<>();
	public static final SidedObject<IMachineRecipeList> MIXING_FURNACE = new SidedObject<>();
	public static final SidedObject<IScrapBoxRegistry> SCRAP_BOX = new SidedObject<>();
	public static final SidedObject<IMachineRecipeList> MASS_FABRICATOR = new SidedObject<>();
	
	public static IFoodCanRegistry CAN_EFFECTS;
	public static final SidedObject<IPotionBrewRegistry> POTION_BREWING = new SidedObject<>();
	public static final SidedObject<IFluidFuelRegistry> FLUID_FUELS = new SidedObject<>();
	public static final SidedObject<IFusionRecipeList> FUSION_REACTOR = new SidedObject<>();
}
