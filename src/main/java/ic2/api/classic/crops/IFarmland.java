package ic2.api.classic.crops;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 * A Class which allows you to register custom Farm-lands for
 * IC2 Crops. You have to register it in the Crops registry
 */
public interface IFarmland
{
	/**
	 * Function to return the Water level of your Farm-land
	 * @param world World the Block is in
	 * @param pos Position of the Farm-land
	 * @return Water level of your Farm-land (Default: Max Vanilla Farm-land applies 2)
	 * @Note Supports negative Effects
	 */
	public int getHumidity(World world, BlockPos pos);
	
	/**
	 * Function to return the Nutrients from the farmland. Farmland (block) adds 1 nutrient
	 * @param world The Farmland World
	 * @param pos The FarmLand Position
	 * @return the Value of the Nutrients
	 */
	public int getNutrients(World world, BlockPos pos);
}
