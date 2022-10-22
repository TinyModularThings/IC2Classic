package ic2.api.energy.tile;

import net.minecraft.core.Direction;

public interface IEnergySink extends IEnergyAcceptor
{
	int getSinkTier();
	
	int getRequestedEnergy();
		
	int acceptEnergy(Direction side, int amount, int voltage);
}
