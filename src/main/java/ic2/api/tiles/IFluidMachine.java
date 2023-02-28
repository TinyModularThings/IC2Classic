package ic2.api.tiles;

import net.minecraft.core.Direction;
import net.minecraftforge.fluids.capability.IFluidHandler;

public interface IFluidMachine
{
	IFluidHandler getConnectedTank(Direction dir);
}
