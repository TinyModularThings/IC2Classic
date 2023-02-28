package ic2.api.items;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public interface ITagBlock
{
	boolean matches(ItemStack self, Block block);
	List<Block> getBlocks(ItemStack self);
}
