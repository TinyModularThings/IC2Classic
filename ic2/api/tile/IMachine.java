package ic2.api.tile;

import net.minecraft.item.ItemStack;

public interface IMachine
{
	public double getEnergy();
	
	public boolean useEnergy(double amount, boolean simulate);
	
	public void setRedstoneSensitive(boolean active);
	
	public boolean isRedstoneSensitive();
	
	public boolean getActive();
	
	public boolean isValidInput(ItemStack par1);
}
