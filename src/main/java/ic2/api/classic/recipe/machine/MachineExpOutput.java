package ic2.api.classic.recipe.machine;

import java.util.List;
import java.util.function.BiConsumer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.translation.I18n;

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
	public void onJEIInfo(BiConsumer<String, Vec3i> consumer)
	{
		if(getExperienceOutput() > 0F)
		{
			consumer.accept(I18n.translateToLocalFormatted("jeiInfo.xpProduction.name", exp), aboveOutput);
		}
	}
	
	@Override
	public MachineOutput copy()
	{
		return new MachineExpOutput(copyNBT(metadata), exp, copyItems(items));
	}

	@Override
	public MachineOutput overrideOutput(List<ItemStack> list)
	{
		if(!canOverride())
		{
			return copy();
		}
		return new MachineExpOutput(copyNBT(metadata), exp, copyItems(list));
	}
	
	
}
