package ic2.api.classic.addon.misc;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public interface IOverrideObject
{
	public Item getItem();
	
	public Block getBlock();
	
	public Class<? extends ItemBlock> getItemBlock();
}
