package ic2.api.classic.tile;

public interface IElectrolyzerProvider
{
	/**
	 * @return Speed of Energy used or send back...
	 */
	public int getProcessRate();
	
	/**
	 * @return Energy Tier of the Provider
	 */
	public int getTier();
	
	/**
	 * @param amount of Energy that is been drawn.
	 * It validates if enough energy there.
	 */
	public void drawPower(int amount);
	
	/**
	 * @param amount of Energy that is been added
	 * It validates if there is enough space
	 */
	public void addPower(int amount);
	
	/**
	 * @return power stored in the Provider
	 */
	public int getStoredEnergy();
	
	/**
	 * @return maximum Energy Storage
	 */
	public int getMaxEnergyStorage();
}
