package ic2.api.energy.tile;

import net.minecraft.core.Direction;

public interface IEnergyEmitter extends IEnergyTile
{
	boolean canEmitEnergy(IEnergyAcceptor acceptor, Direction side);
}
