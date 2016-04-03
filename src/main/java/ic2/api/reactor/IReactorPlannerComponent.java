package ic2.api.reactor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;

public interface IReactorPlannerComponent extends IReactorComponent
{
	/**
	 * Null Tag for states so you do not need to create something differed
	 */
	public static NBTPrimitive nulltag = new NBTNull();
	/**
	 * This function is there if a item has more then 1 ReactorPart
	 * return all parts here if hasSubParts is true.
	 * @return all Reactor Parts (creative/new Crafted)
	 * Info: NO NBT Support on the Comparing.
	 * The Reactor States get Cached as Item&Meta so NBT is not for SubTypes
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
	 * This function is for a simple ID system which reduce the data amount in the NBTData
	 * Its only used for Coping&Pasting Setups for clip board.
	 * Please use static numbers that never change.
	 * ID0-200 Is bound to IC2 Classic. 
	 * As same as the ComponentStates these do not support NBTData for Types
	 * @param stack YourItem
	 * @return the ID
	 */
	public short getID(ItemStack stack);
	
	/**
	 * @return the Reactor Part type so the Reactor Planner knows how to handle it. 
	 */
	public ReactorComponentType getType(ItemStack par1);
	
	/**
	 * Based on the ReactorPart Type you return the Planner wants to know some stats,
	 * which are important for the calculation.
	 * @return the State based on the Item and the State it requests.
	 */
	public NBTPrimitive getReactorStat(ReactorComponentStat par1, ItemStack par2);
	
	/**
	 * This function is there to say if a state needs a reactor for more detailed info.
	 * Note: This function is only called if the item is in the grid of the reactorPlanner.
	 * @param par1 The state which is requested
	 * @param par2 The Item.
	 * @return If the state has differed when in a grid.
	 */
	public boolean isAdvancedStat(ReactorComponentStat par1, ItemStack par2);
	
	/**
	 * This function is for advanced/grid information about the state.
	 * Note: This function will be called separate from the original getReactorStat.
	 * So support both when you create the support.
	 * @param par1: The Reactor
	 * @param x: xCoord of your Item in the Reactor
	 * @param y: yCoord of your Item in the Reactor
	 * @param item: yourItem
	 * @param stat: The Requested State.
	 * @return the result of the state
	 */
	public NBTPrimitive getReactorStat(IReactor par1, int x, int y, ItemStack item, ReactorComponentStat stat);
	
	/**
	 * Important Info. If a Type has a MaxDurabilty State
	 * it has to show the durability in the getDamage function
	 * simply because its used in the ReactorPlanner
	 */
	
	
	public static enum ReactorComponentType
	{
		FuelRod(ReactorComponentStat.HeatProduction, ReactorComponentStat.EnergyProduction, ReactorComponentStat.MaxDurability, ReactorComponentStat.ReactorEEM),
		CoolantCell(ReactorComponentStat.HeatStorage),
		Conensator(ReactorComponentStat.HeatStorage),
		HeatPack(ReactorComponentStat.HeatProduction),
		Vent(ReactorComponentStat.SelfCooling, ReactorComponentStat.ReactorCooling),
		VentSpread(ReactorComponentStat.PartCooling, ReactorComponentStat.ReactorCooling),
		HeatSwitch(ReactorComponentStat.ReactorChange, ReactorComponentStat.PartChange),
		Plating(ReactorComponentStat.ReactorMaxHeat, ReactorComponentStat.ReactorEEM),
		Reflection(ReactorComponentStat.EnergyProduction, ReactorComponentStat.MaxDurability),
		IsotopeCell(ReactorComponentStat.MaxDurability, ReactorComponentStat.ReactorEEM);
		
		ReactorComponentStat[] parts;
		
		private ReactorComponentType(ReactorComponentStat...par1)
		{
			parts = par1;
		}
		
		public ReactorComponentStat[] getStats()
		{
			return parts;
		}
	}
	
	public static enum ReactorComponentStat
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
	
	public static class NBTNull extends NBTTagInt
	{
		public NBTNull()
		{
			super(0);
		}
	}
}
