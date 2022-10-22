package ic2.api.blocks;

import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.level.block.Block;

/**
 * 
 * @author Speiger
 * This Registry keeps track of blocks that have extremely high explosion resistances.
 * Any Block with an explosion Resistance above 3.400 explosion resistance will be automatically trimmed down to 1.6 resistance if not whitelisted.
 * This is to ensure nukes do actual damage.
 * 
 * Vanilla Blocks are also nerfed by IC2Classic to be TNT safe but not block entire nukes single-handedly.
 */
public class ExplosionWhitelist
{
	static final Set<Block> WHITELIST = new ObjectOpenHashSet<>();
	
	public static void addWhitelist(Block... blocks)
	{
		WHITELIST.addAll(ObjectArrayList.wrap(blocks));
	}
	
	public static void removeWhitelist(Block...blocks)
	{
		WHITELIST.removeAll(ObjectArrayList.wrap(blocks));
	}
	
	public static boolean isWhitelisted(Block block)
	{
		return WHITELIST.contains(block);
	}
}
