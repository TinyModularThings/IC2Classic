package ic2.api.recipes.ingridients.queue;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class MultiStackOutput implements IStackOutput
{
	ItemStack output;
	int[] slots;
	
	public MultiStackOutput(CompoundTag nbt)
	{
		output = ItemStack.of(nbt.getCompound("stack"));
		slots = nbt.getIntArray("slots");
	}
	
	public MultiStackOutput(ItemStack output, int... slots)
	{
		this.output = output;
		this.slots = slots;
	}

	@Override
	public boolean addToInventory(IInputter inventory)
	{
		for(int slot : slots)
		{
			inventory.addItemIntoSlot(slot, output);
			if(output.isEmpty())
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ItemStack getStack()
	{
		return output;
	}
	
	@Override
	public void save(CompoundTag save)
	{
		save.putIntArray("slots", slots);
		save.put("stack", output.save(new CompoundTag()));
	}
	
}
