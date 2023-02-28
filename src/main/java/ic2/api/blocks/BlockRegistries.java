package ic2.api.blocks;

import java.util.List;
import java.util.Set;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

/** 
 * 
 * @author Speiger
 * 
 * This Registry is for the Miner and OreScanner.
 * Allowing to register Blocks and provide info how valuable they are.
 * Any Value above 0 will be recognized as valuable
 */
public class BlockRegistries
{
	static final Object2IntMap<Block> ORE_VALUES = new Object2IntOpenHashMap<>();
	static final Int2ObjectMap<ObjectSet<Block>> VALUES_TO_ORES = new Int2ObjectOpenHashMap<>();
	static final List<Runnable> LISTENERS = ObjectLists.synchronize(new ObjectArrayList<>());
	
	/**
	 * register a callback for ore value loading.
	 * The registered callbacks are called on datapack load to support changes done using those.
	 * @param run callback that notifies you when should register ore values
	 */
	public static void registerListener(Runnable run)
	{
		LISTENERS.add(run);
	}
	
	public static void reload()
	{
		ORE_VALUES.clear();
		VALUES_TO_ORES.clear();
		LISTENERS.forEach(Runnable::run); //Lambda is normally slower, but in this case this makes the code thread-save and faster then a for-loop.
		for(Entry<Block> entry : ORE_VALUES.object2IntEntrySet())
		{
			ObjectSet<Block> sets = VALUES_TO_ORES.get(entry.getIntValue());
			if(sets == null)
			{
				sets = new ObjectLinkedOpenHashSet<>();
				VALUES_TO_ORES.put(entry.getIntValue(), sets);
			}
			sets.add(entry.getKey());
		}
	}
	
	/**
	 * Function to register a valuable ore as a Tag
	 * @param value how valuable it should be
	 * @param tag The list of blocks that should be added
	 * @apiNote this directly resolves the tags, this is fine since it is called after tags were already resolved
	 */
	public static void registerOre(int value, TagKey<Block> tag)
	{
		for(Block entry : ForgeRegistries.BLOCKS.tags().getTag(tag))
		{
			ORE_VALUES.put(entry, value);
		}
	}
	
	/**
	 * Function to register the value of a list of Blocks
	 * @param value how valuable it should be
	 * @param blocks The list of blocks that should be added
	 */
	public static void registerOre(int value, Block...blocks)
	{
		for(Block block : blocks)
		{
			ORE_VALUES.put(block, value);
		}
	}
	
	/**
	 * @param value of the blocks you want.
	 * @return a set of Blocks with the requested value
	 */
	public static Set<Block> getBlocksForOreValue(int value)
	{
		return ObjectSets.unmodifiable(VALUES_TO_ORES.getOrDefault(value, ObjectSets.emptySet()));
	}
	
	/**
	 * @param state that you want to know the value of.
	 * @return the value of the BlockState provided
	 */
	public static int getOreValue(BlockState state)
	{
		return ORE_VALUES.getInt(state.getBlock());
	}
	
	/**
	 * @param block that you want to know the value of.
	 * @return the value of the Block Provided
	 */
	public static int getOreValue(Block block)
	{
		return ORE_VALUES.getInt(block);
	}
	
	/**
	 * Function to get all registered Blocks
	 * @return the blocks that were registered
	 */
	public static List<Block> getAllValueBlocks()
	{
		return new ObjectArrayList<>(ORE_VALUES.keySet());
	}
}