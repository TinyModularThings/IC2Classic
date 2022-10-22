package ic2.api.energy.tile;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface IEnergyConductor extends IEnergyAcceptor, IEnergyEmitter
{
	default boolean isWaterlogged()
	{
		BlockState state = ((BlockEntity)this).getBlockState();
		return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
	}
	
	boolean isLavaLogged();
	
	double getConductionLoss();
	
	int getInsulationEnergyAbsorption();

	int getInsulationBreakdownEnergy();

	int getConductorBreakdownEnergy();

	void removeInsulation();

	void removeConductor();
}
