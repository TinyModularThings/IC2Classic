package ic2.api.classic.tile.machine;

public interface ISecondaryProgressMachine
{
	/**
	 * @return current Secondary-progress of a Machine
	 */
	public float getSecondaryProgress();
	
	/**
	 * @return the max Secondary-Progress of a machine
	 */
	public float getMaxSecondaryProgress();
}
