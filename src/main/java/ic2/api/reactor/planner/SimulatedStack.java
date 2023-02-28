package ic2.api.reactor.planner;

import java.util.List;

import ic2.api.reactor.IReactorPlannerComponent.ComponentType;
import ic2.api.reactor.IReactorPlannerComponent.ReactorStat;
import ic2.api.reactor.IReactorPlannerComponent.ReactorType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.world.item.ItemStack;

public interface SimulatedStack
{
	NumericTag NULL_VALUE = IntTag.valueOf(0);

	public ItemStack syncStack(ItemStack original);
	public CompoundTag save();
	public void load(CompoundTag data);
	public void commitState();
	public void reset();
	
	public void simulate(ISimulatedReactor reactor, int x, int y, boolean heatTick, boolean damageTick);
	public boolean acceptUraniumPulse(ISimulatedReactor reactor, int x, int y, SimulatedStack source, int sourceX, int sourceY, boolean heatRun, boolean damageTick);
	public boolean canStoreHeat(ISimulatedReactor reactor, int x, int y);
	public int getStoredHeat(ISimulatedReactor reactor, int x, int y);
	public int getMaxStoredHeat(ISimulatedReactor reactor, int x, int y);
	public int storeHeat(ISimulatedReactor reactor, int x, int y, int heatChange);
	public boolean canViewHeat(ISimulatedReactor reactor, int x, int y);
	public float getExplosionInfluence(ISimulatedReactor reactor);

	public short getId();
	public List<ReactorStat> getStats();
	public ReactorType getValidType();
	public ComponentType getComponentType();
	public NumericTag getStat(ReactorStat stat);
	public default NumericTag getStat(ReactorStat stat, ISimulatedReactor reactor, int x, int y) { return getStat(stat); }
}
