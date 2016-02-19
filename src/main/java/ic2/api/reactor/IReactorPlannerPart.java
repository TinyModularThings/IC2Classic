package ic2.api.reactor;

import net.minecraft.item.ItemStack;

public interface IReactorPlannerPart
{
	
	/**
	 * This function is there if a item has more then 1 ReactorPart
	 * return all parts here if hasSubParts is true.
	 * @return all Reactor Parts (creative/new Crafted)
	 */
	public ItemStack[] getSubParts();
	
	/**
	 * @return true if the Item has more then one part.
	 * If true then it skips the getReactorPart so if true return all,
	 * on the getSubParts function
	 */
	public boolean hasSubParts();
	
	/**
	 * @return the Reactor part created Creatively.
	 */
	public ItemStack getReactorPart();
	
	/**
	 * @return the Reactor Part type so the Reactor Planner knows how to handle it. 
	 */
	public ReactorPartType getType(ItemStack par1);
	
	/**
	 * Based on the ReactorPart Type you return the Planner wants to know some stats,
	 * which are important for the calculation.
	 * @return the Stat based on the Item and the Stat it requests.
	 */
	public int getReactorStat(ReactorPartStat par1, ItemStack par2);

	
	
	public static enum ReactorPartType
	{
		FuelRod(ReactorPartStat.HeatProduction, ReactorPartStat.EnergyProduction, ReactorPartStat.MaxDurability),
		CoolantCell(ReactorPartStat.HeatStorage),
		Conensator(ReactorPartStat.HeatStorage),
		Vent(ReactorPartStat.SelfCooling, ReactorPartStat.ReactorCooling),
		VentSpread(ReactorPartStat.PartCooling, ReactorPartStat.ReactorCooling),
		HeatSwitch(ReactorPartStat.ReactorChange, ReactorPartStat.PartChange),
		Plating(ReactorPartStat.ReactorMaxHeat, ReactorPartStat.ReactorEEM),
		Reflection(ReactorPartStat.EnergyProduction, ReactorPartStat.MaxDurability),
		IsotopeCell(ReactorPartStat.MaxDurability);
		
		ReactorPartStat[] parts;
		
		private ReactorPartType(ReactorPartStat...par1)
		{
			parts = par1;
		}
	}
	
	public static enum ReactorPartStat
	{
		HeatProduction,
		EnergyProduction,
		SelfCooling,
		PartCooling,
		ReactorCooling,
		PartChange,
		ReactorChange,
		HeatStorage,
		ReactorMaxHeat,
		ReactorEEM,
		MaxDurability;
		
	}
}
