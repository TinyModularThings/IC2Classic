package ic2.api.items;

import ic2.api.tiles.ITerraformer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ITerraformerBP
{
	boolean canInsert(ItemStack stack, Player player, Level world, BlockPos pos);
	
	void onInsert(ItemStack stack, Player player, Level world, BlockPos pos);
	
	boolean isRandomized(ItemStack stack);
	
	int getEnergyUsage(ItemStack stack);
	
	int getRadius(ItemStack stack);
	
	boolean terraform(ItemStack stack, Level world, BlockPos position, ITerraformer terraformer);
}
