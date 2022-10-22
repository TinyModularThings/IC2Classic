package ic2.api.blocks;

import java.util.Map;
import java.util.Map.Entry;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

public class DyeableMap
{
	Map<Block, DyeColor> blockToColor = new Object2ObjectLinkedOpenHashMap<>();
	Map<DyeColor, Block> colorToBlock = new Object2ObjectLinkedOpenHashMap<>();
	ObjectSet<Block> blocks = new ObjectLinkedOpenHashSet<>();
	
	public void addBlock(Block block, DyeColor color)
	{
		blocks.add(block);
		blockToColor.put(block, color);
		colorToBlock.put(color, block);
	}
	
	public void addBlocks(Map<Block, DyeColor> maps)
	{
		blocks.addAll(maps.keySet());
		blockToColor.putAll(maps);
		for(Entry<Block, DyeColor> entry : maps.entrySet())
		{
			colorToBlock.put(entry.getValue(), entry.getKey());
		}
	}
	
	public void addReverseBlocks(Map<DyeColor, Block> maps)
	{
		blocks.addAll(maps.values());
		colorToBlock.putAll(maps);
		for(Entry<DyeColor, Block> entry : maps.entrySet())
		{
			blockToColor.put(entry.getValue(), entry.getKey());
		}
	}
	
	public Block getBlock(DyeColor color)
	{
		return colorToBlock.get(color);
	}
	
	public Block getBlock(DyeColor color, Block defaultValue)
	{
		return colorToBlock.getOrDefault(color, defaultValue);
	}
	
	public DyeColor getColor(Block block)
	{
		return blockToColor.get(block);
	}
	
	public ObjectSet<Block> getBlocks()
	{
		return ObjectSets.unmodifiable(blocks);
	}
	
	public Block[] getBlockArray()
	{
		return blocks.toArray(Block[]::new);
	}
	
	public boolean contains(Block block)
	{
		return blocks.contains(block);
	}
}