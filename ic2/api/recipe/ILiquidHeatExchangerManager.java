package ic2.api.recipe;

import java.util.Map;

import net.minecraftforge.fluids.Fluid;

public interface ILiquidHeatExchangerManager extends ILiquidAcceptManager {

	void addFluid(String fluidName, String fluidOutput, int huPerMB);

	HeatExchangeProperty getHeatExchangeProperty(Fluid fluid);

	Map<String, HeatExchangeProperty> getHeatExchangeProperties();

	public static class HeatExchangeProperty {
		public HeatExchangeProperty(Fluid outputFluid, int huPerMB) {
			this.outputFluid = outputFluid;
			this.huPerMB = huPerMB;
		}

		public final Fluid outputFluid;
		public final int huPerMB;
	}

}
