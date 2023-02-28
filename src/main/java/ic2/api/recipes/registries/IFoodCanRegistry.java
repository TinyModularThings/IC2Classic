package ic2.api.recipes.registries;

import java.util.List;

import ic2.api.recipes.misc.ICanEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IFoodCanRegistry
{
	Item registerEffect(ICanEffect effect);
	
	ICanEffect getEffect(ResourceLocation location);
	
	List<ResourceLocation> getAllEffects();
	
	Item getEffectItem(ResourceLocation item);
	
	void registerItemForEffect(ItemStack stack, ResourceLocation location);
	
	ResourceLocation getEffectForFood(ItemStack stack);
	
	default Item getItemForFood(ItemStack food)
	{
		ResourceLocation location = getEffectForFood(food);
		return location == null ? null : getEffectItem(location);
	}
}
