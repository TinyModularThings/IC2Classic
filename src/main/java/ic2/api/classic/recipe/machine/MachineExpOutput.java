package ic2.api.classic.recipe.machine;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MachineExpOutput extends MachineOutput
{
	float exp;
	
	public MachineExpOutput(NBTTagCompound meta, float exp, ItemStack... items)
	{
		super(meta, items);
		this.exp = exp;
	}
	
	public MachineExpOutput(NBTTagCompound meta, float exp, List<ItemStack> items)
	{
		super(meta, items);
		this.exp = exp;
	}
	
	@Override
	public float getExperienceOutput()
	{
		return exp;
	}
	
	@Override
	public MachineOutput copy()
	{
		return new MachineExpOutput(copyNBT(metadata), exp, copyItems(items));
	}
}
