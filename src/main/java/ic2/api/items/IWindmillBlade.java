package ic2.api.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IWindmillBlade
{
	int getRadius(ItemStack stack);
	float getEffectiveness(ItemStack stack);
	ResourceLocation getTexture(ItemStack stack);
}