package ic2.api.energy.tile;

public interface IEnergyConductorModifiable extends IEnergyConductor
{
	boolean tryAddInsulation();
	
	boolean tryRemoveInsulation();
}
