package ic2.api.classic.tile.machine;

/**
 * 
 * @author Speiger
 * 
 * Read only class for accessing Machine Data.
 * Only used for comparators in classic itself.
 */
public interface IEUStorage
{
	/**
	 * @return How much EU is stored
	 */
	public int getStoredEU();
	
	/**
	 * @return how much EU can be stored max
	 */
	public int getMaxEU();
}
