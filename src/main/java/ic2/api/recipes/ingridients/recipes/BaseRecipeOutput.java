package ic2.api.recipes.ingridients.recipes;

import java.util.List;

import ic2.api.recipes.ingridients.generators.IOutputGenerator;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;

public abstract class BaseRecipeOutput implements IRecipeOutput {
	protected List<IOutputGenerator> generators = new ObjectArrayList<>();
	
	protected void handleGenerators(List<IOutputGenerator> generators, List<ItemStack> outputs) {
		this.generators.addAll(generators);
		for(IOutputGenerator generator : generators) {
			generator.addItems(outputs::add);
		}
	}
}
