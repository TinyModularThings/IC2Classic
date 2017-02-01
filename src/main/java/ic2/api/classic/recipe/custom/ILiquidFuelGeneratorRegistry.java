package ic2.api.classic.recipe.custom;

import java.util.Map;

import net.minecraftforge.fluids.Fluid;

public interface ILiquidFuelGeneratorRegistry
{
	/**
	 * Function to add a fluid that is burn-able in a liquidFuel generator. The
	 * registry will default for a fluid amount 1 Bucket if there is less fluid
	 * drained it will math the ticks down
	 * 
	 * @param fluid
	 *            the Fluid that is burn-able
	 * @param burnTicks
	 *            how many ticks the fluid will burn
	 * @param euPerTick
	 *            how much EU it produces per tick
	 */
	public void addEntry(Fluid fluid, int burnTicks, float euPerTick);
	
	/**
	 * Function to access the map that stores the burn types.
	 * @return the complete registry
	 */
	public Map<Fluid, BurnEntry> getBurnMap();
	
	/**
	 * Function to get the result for a fluid
	 * @param fluid the Fluid
	 * @return the burnEntry. Can be null
	 */
	public BurnEntry getBurnEntry(Fluid fluid);
	
	public static class BurnEntry
	{
		Fluid fluid;
		int ticksLast;
		float production;
		
		public BurnEntry(Fluid fluid, int ticksLast, float production)
		{
			this.fluid = fluid;
			this.ticksLast = ticksLast;
			this.production = production;
		}

		public Fluid getFluid()
		{
			return fluid;
		}
		
		public int getTicksLast()
		{
			return ticksLast;
		}
		
		public float getProduction()
		{
			return production;
		}
		
	}
}
