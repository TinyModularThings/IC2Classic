package ic2.api.recipes.ingridients.queue;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class SimpleStackOutput implements IStackOutput
{
	ItemStack output;
	int slot;
	
	public SimpleStackOutput(CompoundTag nbt)
	{
		output = ItemStack.of(nbt.getCompound("stack"));
		slot = nbt.getInt("slot");
	}
	
	public SimpleStackOutput(ItemStack output, int slot)
	{
		this.output = output;
		this.slot = slot;
	}

	@Override
	public boolean addToInventory(IInputter inventory)
	{
		inventory.addItemIntoSlot(slot, output);
		return output.isEmpty();
	}
	
	@Override
	public ItemStack getStack()
	{
		return output;
	}
	
	@Override
	public void save(CompoundTag save)
	{
		save.putByte("slot", (byte)slot);
		save.put("stack", output.save(new CompoundTag()));
	}
	
}
