package ic2.api.recipes.registries;

import java.util.List;
import java.util.Map;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public interface IPotionBrewRegistry extends IListenableRegistry<IPotionBrewRegistry>
{
	default void registerBrew(Item item, MobEffect potion){registerBrew(item, null, potion);}
	void registerBrew(Item item, MobEffect fromEffect, MobEffect toEffect);
	void removeBrew(Item item);
	MobEffect getEffect(Item item, MobEffect original);
	Map<Item, Map<MobEffect, MobEffect>> getEffects();
	List<Item> getRequiredItems(MobEffect desiredEffect);
	
	void registerName(MobEffect effect, String name);
	String getName(MobEffect effect);
	
	void registerPotionContainer(Item input, PotionContainer output);
	PotionContainer getPotionContainer(Item input);
	Map<Item, PotionContainer> getContainers();
	
	final class PotionContainer
	{
		final Item output;
		final int fluidUsage;
		final float durationEffectiveness;
		
		public PotionContainer(Item output, int fluidUsage, float durationEffectiveness)
		{
			if(output == Items.AIR || output == null || fluidUsage <= 0 || durationEffectiveness <= 0F)
			{
				throw new IllegalStateException("Potion Brew Result is invalid");
			}
			this.output = output;
			this.fluidUsage = fluidUsage;
			this.durationEffectiveness = durationEffectiveness;
		}
		
		public float getDurationEffectiveness()
		{
			return durationEffectiveness;
		}
		
		public int getFluidUsage()
		{
			return fluidUsage;
		}
		
		public Item getOutput()
		{
			return output;
		}
	}
}
