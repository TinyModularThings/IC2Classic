package ic2.api.items.readers;

import net.minecraft.world.item.ItemStack;

public interface IEUReader
{
	boolean isEUReader(ItemStack stack);
	
	static boolean isEUReaderImpl(ItemStack stack)
	{
		return stack.getItem() instanceof IEUReader && ((IEUReader)stack.getItem()).isEUReader(stack);
	}
}
