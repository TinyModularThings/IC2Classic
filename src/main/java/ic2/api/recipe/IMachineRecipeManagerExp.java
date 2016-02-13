package ic2.api.recipe;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IMachineRecipeManagerExp extends IMachineRecipeManagerExt
{
	public void addRecipe(IRecipeInput input, NBTTagCompound metadata, float exp, ItemStack...outputs);
	
	public float getExpResult(ItemStack output);
	
	public Map<IRecipeInput, Float> getExpMap();
}
