package ic2.api.classic.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICutterItem
{
	/**
	 * Function to call the cutter to remove the Insulation.
	 * Its only a compat function
	 * @param stack the cutter
	 * @param world World that the cutter would be used
	 * @param pos the position where it should cut
	 * @param player the Player who is cutting
	 * You should use this function on BlockClicked.
	 * Thats what it is made for
	 */
	public void cutInsulationFrom(ItemStack stack, World world, BlockPos pos, EntityPlayer player);
}
