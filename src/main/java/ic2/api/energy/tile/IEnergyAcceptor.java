package ic2.api.energy.tile;

import net.minecraft.core.Direction;

public interface IEnergyAcceptor extends IEnergyTile
{
	boolean canAcceptEnergy(IEnergyEmitter emitter, Direction side);
}
