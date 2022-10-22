package ic2.api.recipes.registries;

import java.util.List;

import net.minecraft.world.item.Item;

public interface IFusionRecipeList extends IListenableRegistry<IFusionRecipeList>
{
	public void addFuel(Item fuel, int fuelValue, float productionRate, boolean consumed);
	
	public FusionFuel getFuel(Item item);
	public void removeFuel(Item item);
	public List<FusionFuel> getFuels();
	
	public static record FusionFuel(Item fuel, int fuelValue, float productionRate, boolean consumed) {}
}
