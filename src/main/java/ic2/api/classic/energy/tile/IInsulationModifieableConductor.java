package ic2.api.classic.energy.tile;

import ic2.api.energy.tile.IEnergyConductor;

public interface IInsulationModifieableConductor extends IEnergyConductor
{
	/**
	 * Function to add insulation to your wire. It will use Rubber and nothing else.
	 * @return if the insulation was added
	 */
	public boolean tryAddInsulation();
	
	/**
	 * Function to remove insulation from your wire. It will spawn rubber and nothing else.
	 * @return if the insulation was removed
	 */
	public boolean tryRemoveInsulation();
	
	
	
}
