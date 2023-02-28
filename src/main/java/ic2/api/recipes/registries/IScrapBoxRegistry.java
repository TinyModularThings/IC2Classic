package ic2.api.recipes.registries;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public interface IScrapBoxRegistry extends IListenableRegistry<IScrapBoxRegistry>
{
	default void addDrop(ItemLike prov, float chance)
	{
		if(prov == null)
		{
			return;
		}
		addDrop(new ItemStack(prov), chance);
	}
	
	void addDrop(ItemStack stack, float chance);
	
	IDrop getRandomDrop(ItemStack source, boolean consumeItem);
	
	void removeDrops(ItemStack stack);
	
	void removeDrop(IDrop drop);
	
	List<IDrop> getAllDrops();
	
	interface IDrop
	{
		ItemStack getDrop();
		
		float getChance();
		
		float getPoolChance();
	}
}
