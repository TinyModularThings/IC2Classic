package ic2.api.classic.tile;

/**
 * 
 * @author Speiger
 * 
 * A Class for a Machine that can be notified by other machines.
 * The only case where this is used is that all machines give a notify
 * when they have done something
 */
public interface INotifyMachine
{
	/**
	 * a function to notify hte machine
	 */
	public void onNotify();
	
	/**
	 * Opposite function of TileEntity isInvalid
	 * @return if the machine is Valid
	 */
	public boolean isValid();
}
