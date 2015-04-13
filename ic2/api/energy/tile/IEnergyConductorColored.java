package ic2.api.energy.tile;

public interface IEnergyConductorColored extends IEnergyConductor
{
	/**
	 * @Return the color of the Conductor.
	 * 0 means none Color.
	 * That helps with Addon Compatiblity.
	 * IC2 Cables uses that also!
	 */
	public int getConductorColor();
}
