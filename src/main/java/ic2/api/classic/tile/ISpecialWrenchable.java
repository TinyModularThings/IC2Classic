package ic2.api.classic.tile;

import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ISpecialWrenchable extends IWrenchable
{
	/**
	 * A function that defines your wrench drop rate. 
	 * if this interface is not used it defaults to 85% chance
	 * @param world The world
	 * @param pos of the block
	 * @return the chance
	 */
	public double getWrenchSuccessRate(World world, BlockPos pos);
	
	/**
	 * Custom function for special actions which are not Rotation or Remove
	 * return true to init the call.
	 * @param world The World the block is in
	 * @param pos The position of the block
	 * @param dir The side the click is happening
	 * @param player the Player who does the click
	 * @return if it has a special action
	 */
	public boolean hasSpecialAction(World world, BlockPos pos, EnumFacing dir, EntityPlayer player);
	
	/**
	 * Function to apply the Special Action.
	 * @param world The World the block is in
	 * @param pos The position of the block
	 * @param dir The side the click is happening
	 * @param player the Player who does the click
	 * @return If it did work of nothing happend or failed
	 */
	public EnumActionResult onSpecialAction(World world, BlockPos pos, EnumFacing dir, EntityPlayer player);
}
