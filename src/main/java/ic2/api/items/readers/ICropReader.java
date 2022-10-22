package ic2.api.items.readers;

import net.minecraft.world.item.ItemStack;

public interface ICropReader
{
	boolean isCropReader(ItemStack stack);
	
	static boolean isCropReaderImpl(ItemStack stack)
	{
		return stack.getItem() instanceof ICropReader && ((ICropReader)stack.getItem()).isCropReader(stack);
	}
}
