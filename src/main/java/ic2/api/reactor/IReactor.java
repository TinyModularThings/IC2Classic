package ic2.api.reactor;

import ic2.api.util.ILocation;
import net.minecraft.world.item.ItemStack;

public interface IReactor extends ILocation
{
	int getHeat();
	
	void setHeat(int newHeat);
	
	void addHeat(int heat);
	
	int getMaxHeat();
	
	void setMaxHeat(int heat);
	
	float getHeatEffectModifier();
	
	void setHeatEffectModifier(float hem);
	
	double getEnergyOutput();
	
	void addOutput(float output);
	
	ItemStack getStackInReactor(int x, int y);
	
	void setStackInReactor(int x, int y, ItemStack stack);
	
	void explode();
	
	int getTickRate();
	
	boolean isProducingEnergy();
}
