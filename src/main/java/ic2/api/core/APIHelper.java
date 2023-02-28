package ic2.api.core;

import java.util.List;

import ic2.api.network.INetworkManager;
import ic2.api.ticks.ITickScheduler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.FluidStack;

public interface APIHelper
{
	List<ItemStack> getFluidContainers(Fluid fluid);
	FluidStack createSteam(int amount);
	ITickScheduler getTickHelper();
	INetworkManager getNetworkManager();
	INetworkManager getNetworkManager(Dist side);
}
