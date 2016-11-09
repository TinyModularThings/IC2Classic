package ic2.api.classic.crops;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
	 * 
	 * @param soil
	 * @param state
	 */
	public abstract void registerCropSoil(ICropSoil soil, IBlockState... state);
	
	public abstract void registerCropSoil(ICropSoil soil, Block... block);
	
	public abstract ICropSoil getSoil(IBlockState state);
	
	public abstract Map<IBlockState, ICropSoil> getSoilMap();
	
	public abstract CropCard getCropCard(ResourceLocation card);
	
	public abstract void registerCustomRenderer(CropCard card, ICustomCropRenderer renderer);
}
