package ic2.api.items.electric;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public interface IMiningDrill
{
	boolean canMineBlock(ItemStack drill, BlockState state, LevelReader access, BlockPos pos);
	
	default int getMiningBoost(ItemStack stack, BlockState state)
	{
		return 0;
	}
	
	default int getExtraEnergyCost(ItemStack stack)
	{
		return 0;
	}
	
	boolean canDrillBeUsed(ItemStack stack);

	void onDrillUsed(ItemStack stack);
}
