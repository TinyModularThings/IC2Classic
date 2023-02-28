package ic2.api.tiles.tubes;

import java.util.UUID;
import java.util.function.ObjIntConsumer;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public interface IProviderTube extends ITube
{
	int provideItems(ItemStack stack, int amount, DyeColor color, UUID requestId);
	void getItemsProvided(ObjIntConsumer<ItemStack> listener);
	long getProviderSource();
}
