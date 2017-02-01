package ic2.api.classic.recipe;

import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.custom.IClassicScrapBoxManager;
import ic2.api.classic.recipe.custom.ILiquidFuelGeneratorRegistry;
import ic2.api.classic.recipe.custom.IOreRegistry;
import ic2.api.classic.recipe.custom.ITeleporterRegistry;
import ic2.api.classic.recipe.machine.ICannerRegistry;
import ic2.api.classic.recipe.machine.IElectrolyzerRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.IRareEarthExtractorRecipeList;
import ic2.api.classic.recipe.machine.IRecyclerRecipeList;

public class ClassicRecipes
{
	public static ICraftingRecipeList advCrafting;
	public static IMachineRecipeList furnace;
	public static IMachineRecipeList macerator;
	public static IMachineRecipeList compressor;
	public static IMachineRecipeList extractor;
	public static IRecyclerRecipeList recycler;
	public static ICannerRegistry canningMachine;
	public static IElectrolyzerRecipeList electrolyzer;
	public static IMachineRecipeList massfabAmplifier;
	public static IClassicScrapBoxManager scrapboxDrops;
	public static IRareEarthExtractorRecipeList earthExtractor;
	
	public static ILiquidFuelGeneratorRegistry fluidGenerator;
	
	public static IOreRegistry oreRegistry;
	
	public static ITeleporterRegistry teleRegistry;
}
