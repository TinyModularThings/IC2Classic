package ic2.api.classic.tile.machine;

/**
 * 
 * @author Speiger
 * 
 * Read only class for accessing Machine Data.
 * Only used for comparators in classic itself.
 */
public interface ISpeedMachine
{
	/**
	 * @return the current speed of the machine
	 */
	public float getSpeed();
	
	/**
	 * @return the max speed of that machine
	 */
	public float getMaxSpeed();
}
