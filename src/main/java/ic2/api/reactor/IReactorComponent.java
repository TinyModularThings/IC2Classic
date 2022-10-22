package ic2.api.reactor;

import net.minecraft.world.item.ItemStack;

public interface IReactorComponent extends IReactorProduct
{
	@Override
	default boolean isValidForReactor(ItemStack stack, IReactor reactor)
	{
		return true;
	}
	
	void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatCalculation, boolean damageTick);
	
	boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack source, int myX, int myY, int sourceX, int sourceY, boolean heatRun, boolean damageTick);
	
	boolean canStoreHeat(ItemStack stack, IReactor reactor, int x, int y);
	
	int getStoredHeat(ItemStack stack, IReactor reactor, int x, int y);
	
	int getMaxStoredHeat(ItemStack stack, IReactor reactor, int x, int y);
	
	int storeHeat(ItemStack stack, IReactor reactor, int x, int y, int heatChange);
	
	float getExplosionInfluence(ItemStack stack, IReactor reactor);
}
