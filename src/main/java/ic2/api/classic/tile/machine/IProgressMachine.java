package ic2.api.classic.tile.machine;

/**
 * 
 * @author Speiger
 * 
 * Read only class for accessing Machine Data.
 * Only used for comparators in classic itself.
 */
public interface IProgressMachine
{
	/**
	 * @return current progress of a Machine
	 */
	public float getProgress();
	
	/**
	 * @return the max Progress of a machine
	 */
	public float getMaxProgress();
}
