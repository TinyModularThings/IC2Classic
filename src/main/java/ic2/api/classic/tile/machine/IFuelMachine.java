package ic2.api.classic.tile.machine;

/**
 * 
 * @author Speiger
 * 
 * Read only class for accessing Machine Data.
 * Only used for comparators in classic itself.
 */
public interface IFuelMachine
{
	/**
	 * @return the currentFuel of the machine
	 */
	public float getFuel();
	
	/**
	 * @return the max Fuel for that machine
	 */
	public float getMaxFuel();
}
