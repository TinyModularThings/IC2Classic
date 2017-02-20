package ic2.api.classic.crops;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ClassicCrops extends Crops
{
	public static ClassicCrops instance;
	
	/**
	 * function to register a new Farmland for cropSticks.
	 * This is the meta based function
	 * @param land The instance for the farmland that provide.
	 * @param state the block + meta the farmland is added.
	 */
	public abstract void registerFarmland(IFarmland land, IBlockState... state);
	
	/**
	 * Basic register functions to add Blocks. It adds all BlockStates from the block automaticly
	 * @param land The instance for the farmland that provide.
	 * @param block the block
	 */
	public abstract void registerFarmland(IFarmland land, Block...block);
	
	/**
	 * Function to get a FarmLand for the BlockState
	 * @param state the farmland state
	 * @return return the Farmland instance, can be null
	 */
	public abstract IFarmland getFarmland(IBlockState state);
	
	/**
	 * Access map for all Farmlands.
	 * @return a map for the farmlands.
	 */
	public abstract Map<IBlockState, IFarmland> getFarmlandMap();
	
	/**
	 * Function to register a Soil that can be under the Farmland
	 * and can have special Properties to the Crop
	 * @param soil the Handler for the BlockState.
	 * @param state the State which is the soil.
	 */
	public abstract void registerCropSoil(ICropSoil soil, IBlockState... state);
	
	/**
	 * Function to register a Soil that can be under the Farmland
	 * and can have special Properties to the Crop
	 * @param soil the Handler for the Block.
	 * @param block the Block that is the Soil
	 */
	public abstract void registerCropSoil(ICropSoil soil, Block... block);
	
	/**
	 * Function to get the Soil for a BlockState
	 * @param state the State which is provided
	 * @return the Soil that is registered. Can be null
	 */
	public abstract ICropSoil getSoil(IBlockState state);

	/**
	 * Function to get the State/Soil Map.
	 * @return the map
	 */
	public abstract Map<IBlockState, ICropSoil> getSoilMap();
	
	/**
	 * Special Function to get a CropCard from a ResourceLocation
	 * which is basicly new ResourceLocation(CropCard.getOwner(), CropCard.getID()) combo
	 * but its easier to save.
	 * @param card The CropResourceLcoation
	 * @return the CropCard. If not registered can be null
	 */
	public abstract CropCard getCropCard(ResourceLocation card);
	
	/**
	 * Function to register a DisplayItem for a Crop.
	 * That will be shown when the crop is scanned and people decide to
	 * look via these new display Items
	 * @param card the Card that should get a item
	 * @param displayItem the item that should be shown.
	 */
	public abstract void registerCropDisplayItem(CropCard card, ItemStack displayItem);
	
	/**
	 * Function to get the DisplayItem for a Crop
	 * @param card the crop you want the DisplayItem for.
	 * @return the DisplayItem, can be null if none registered
	 */
	public abstract ItemStack getDisplayItem(CropCard card);
	
	/**
	 * Function for all Registered DisplayItems
	 * @return the map
	 */
	public abstract Map<ResourceLocation, ItemStack> getDisplayItems();
	
	/**
	 * Function to register a Custom Renderer to the IC2Crops.
	 * It is basically the last override you can do but it allows you to do
	 * anything render-wise you want.
	 * @param card the Crop you want. If card == null it will put that as the Crop Base Renderer
	 * @param renderer the Renderer. None Null Required
	 */
	public abstract void registerCustomRenderer(CropCard card, ICustomCropRenderer renderer);
}
