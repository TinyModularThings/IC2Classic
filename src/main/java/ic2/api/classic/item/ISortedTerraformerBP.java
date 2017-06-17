package ic2.api.classic.item;

import ic2.api.item.ITerraformingBP;
import net.minecraft.item.ItemStack;

public interface ISortedTerraformerBP extends ITerraformingBP
{
	/**
	 * This function determens if the logic from the Terraformer
	 * should be random or a Final List that actually can end.
	 * The Biome BluePrints from classic need only to run once thats why it exists.
	 * @param stack yourBluePrint
	 * @return true = SortedLogic, false = defaultLogic
	 */
	public boolean needsSortedLogic(ItemStack stack);
}
