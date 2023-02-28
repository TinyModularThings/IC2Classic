package ic2.api.reactor.planner;

public interface ISimulatedReactor
{
	public SimulatedStack getItem(int x, int y);
	public void markBroken(int x, int y);
	public void addBreedingPulse();
	public void addFuelPulse();
	
	public int getHeat();
	public void setHeat(int newHeat);
	public void addHeat(int heat);
	
	public int getMaxHeat();
	public void setMaxHeat(int heat);
	
	public float getHeatEffectModifier();
	public void setHeatEffectModifier(float hem);
	
	public void addOutput(float output);
	public float getEnergyOutput();
	public void addSteam(int amount);
	public int consumeWater(int amount);
	
	public boolean isProducingEnergy();
	public boolean isSteamReactor();
	public boolean isSimulatingPulses();
	
	public long getGameTime();
}