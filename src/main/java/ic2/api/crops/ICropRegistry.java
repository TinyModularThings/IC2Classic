package ic2.api.crops;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.Event;

public abstract class ICropRegistry
{
	/** Default on Ground Weed Reference */
	public static ICrop WEED = null;
	/** Default underwater Weed Reference */
	public static ICrop SEA_WEED = null;
	/** Registry INstance */
	public static ICropRegistry INSTANCE;
	
	/**
	 * Function to register a custom renderer for the Crop Sticks
	 * @param location the Crop Id it should affect.
	 * @param render that should be applied.
	 */
	public abstract void registerCustomCropRenderer(ResourceLocation location, ICropRenderer render);
	
	/**
	 * Function to register a custom crop stick renderer
	 * @param render that should be applied
	 */
	public abstract void registerCustomStickRenderer(ICropRenderer render);
	
	/**
	 * Function to register a Crop
	 * @param <T> generic type.
	 * @param crop that should be registered
	 * @return the registered crop
	 */
	public abstract <T extends ICrop> T registerCrop(T crop);
	
	/**
	 * Function to get a desired crop
	 * @param id the mod the crop comes from
	 * @param name the id of the crop
	 * @return the crop if it exists. Otherwise it returns null
	 */
	public ICrop getCrop(String id, String name)
	{
		return getCrop(new ResourceLocation(id, name));
	}
	
	/**
	 * Function to get a desired crop
	 * @param location the id of the crop
	 * @return the crop if it exists. Otherwise it returns null
	 */
	public abstract ICrop getCrop(ResourceLocation location);
	
	/**
	 * Get's the Crop of a Seed.
	 * @param stack the seed
	 * @return the Crop if there is one in the seed. Otherwise null.
	 */
	public abstract ICrop getCrop(ItemStack stack);
	
	/**
	 * A list of all crops registered.
	 * @return A list of all registered Blocks
	 */
	public abstract List<ICrop> getCrops();
	
	/**
	 * Function to register a custom Display Item to easier identify the Crop from its seed.
	 * @param cropID the crop that should get the display Item
	 * @param stack the display Item
	 */
	public abstract void registerDisplayItem(ResourceLocation cropID, ItemStack stack);
	
	/**
	 * Get's the display Item of a Crop.
	 * @param cropID of the crop.
	 * @return either ItemStack.EMPTY if there is no display Item, otherwise returns the Display Item.
	 */
	public abstract ItemStack getDisplayItem(ResourceLocation cropID);
	
	/**
	 * Function that allows you to define a custom Crop Seed.
	 * This allows that for example if you right click with 64 dirt Blocks on a Crop it will spawn a Adamantium Crop (Diamond)
	 * @param item that should place the Crop
	 * @param seed the crop that should be placed, with what stats and how many items required to do so.
	 */
	public abstract void registerBaseSeed(Item item, BaseSeed seed);
	
	/**
	 * Get's the BaseSeed for a given item
	 * @param stack myStack
	 * @return the BaseSeed if there is one for the given item. Otherwise returns null.
	 */
	public abstract BaseSeed getSeedForStack(ItemStack stack);
	
	/**
	 * Creates a Seedbag for the given crop.
	 * @param crop that you want in Item form.
	 * @return a new Seedbag.
	 */
	public abstract ItemStack getSeedForCrop(ICrop crop);
	
	/**
	 * Adds a crop humidity biome bonus.
	 *
	 * +10/-10 0 indicates no bonus and negative values indicate a penalty.
	 *
	 * @param type biome type to apply the bonus in.
	 * @param bonus Humidity stat bonus
	 */
	public abstract void addBiomeHumidityBonus(TagKey<Biome> type, int bonus);
	
	/**
	 * Gets the humidity bonus for a biome.
	 *
	 * @param biome Biome to check
	 * @return Humidity bonus or 0 if none
	 */
	public abstract int getHumidityBonus(Holder<Biome> biome);

	/**
	 * Adds a crop nutrient biome bonus.
	 *
	 * +10/-10  0 indicates no bonus and negative values indicate a penalty.
	 *
	 * @param type biome type to apply the bonus in
	 * @param bonus Nutrient stat bonus
	 */
	public abstract void addBiomeNutrientBonus(TagKey<Biome> type, int bonus);
	
	/**
	 * Gets the nutrient bonus for a biome.
	 *
	 * @param biome Biome to check
	 * @return Nutrient bonus or 0 if none
	 */
	public abstract int getNutrientBonus(Holder<Biome> biome);
	
	/**
	 * Adds a crop water quality biome bonus.
	 *
	 * +10/-10  0 indicates no bonus and negative values indicate a penalty.
	 *
	 * @param type biome type to apply the bonus in
	 * @param bonus Environment stat bonus for underwater crops
	 */
	public abstract void addBiomeWaterQualityBonus(TagKey<Biome> type, int bonus);
	
	/**
	 * Gets the nutrient bonus for a biome.
	 *
	 * @param biome Biome to check
	 * @return Environment stat bonus for underwater crops or 0 if none
	 */
	public abstract int getWaterQualityBonus(Holder<Biome> biome);
	
	/**
	 * Function to register Custom Farmland Blocks.
	 * Farmlands are the Blocks directly underneath the Crop itself
	 * @param land the farmland instance.
	 * @param blocks that represent said Farmland
	 */
	public abstract void registerFarmland(IFarmland land, Block...blocks);
	
	/**
	 * Get's the Farmland for a given Block
	 * @param block that you want the farmland of.
	 * @return if present the farmland, otherwise null
	 */
	public abstract IFarmland getFarmland(Block block);
	
	/**
	 * Function to register custom SubSoil Blocks
	 * The SubSoil are the 3 Blocks underneath the Farmland
	 * @param soil the SubSoil Instance
	 * @param blocks that represent said SubSoil
	 */
	public abstract void registerSubSoil(ISubSoil soil, Block...blocks);
	
	/**
	 * Get's a SubSoil for a given Block
	 * @param block that you want the SubSoil of.
	 * @return if present the SubSoil, otherwise null 
	 */
	public abstract ISubSoil getSubSoil(Block block);
	
	public static class CropRegisterEvent extends Event
	{
		public CropRegisterEvent()
		{
		}
		
		public void registerCrops(ICrop...crops)
		{
			for(ICrop crop : crops)
			{
				INSTANCE.registerCrop(crop);
			}
		}
		
		public ICropRegistry getRegistry()
		{
			return INSTANCE;
		}
	}
}
