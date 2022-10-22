package ic2.api.items;

import java.util.function.Consumer;

import ic2.api.tiles.display.IDisplayInfo;
import net.minecraft.world.item.ItemStack;

public interface IDisplayProvider
{
	void provideInfo(ItemStack stack, Consumer<IDisplayInfo> infos);
}
