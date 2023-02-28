package ic2.api.energy.tile;

import java.util.List;

public interface IMultiEnergyTile extends IEnergyTile
{
	List<IEnergyTile> getTiles();
}
