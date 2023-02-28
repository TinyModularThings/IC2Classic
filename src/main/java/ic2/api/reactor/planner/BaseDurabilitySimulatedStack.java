package ic2.api.reactor.planner;

import net.minecraft.nbt.CompoundTag;

public abstract class BaseDurabilitySimulatedStack implements SimulatedStack
{
	protected int maxDamage;
	protected int damage;
	protected short id;
	
	public BaseDurabilitySimulatedStack(short id, int maxDamage)
	{
		this.id = id;
		this.maxDamage = maxDamage;
	}

	@Override
	public CompoundTag save() {
		CompoundTag data = new CompoundTag();
		data.putInt("damage", damage);
		return data;
	}
	
	@Override
	public void load(CompoundTag data) {
		damage = data.getInt("damage");
	}
	
	@Override
	public void commitState() {
	}
	
	@Override
	public void reset() {
		damage = 0;
	}
	
	@Override
	public boolean acceptUraniumPulse(ISimulatedReactor reactor, int x, int y, SimulatedStack source, int sourceX, int sourceY, boolean heatRun, boolean damageTick) { return false; }
	@Override
	public boolean canStoreHeat(ISimulatedReactor reactor, int x, int y) { return false; }
	@Override
	public int getStoredHeat(ISimulatedReactor reactor, int x, int y) { return 0; }
	@Override
	public int getMaxStoredHeat(ISimulatedReactor reactor, int x, int y) { return 0; }
	@Override
	public int storeHeat(ISimulatedReactor reactor, int x, int y, int heatChange) { return heatChange; }
	
	@Override
	public boolean canViewHeat(ISimulatedReactor reactor, int x, int y) { return true; }
	@Override
	public float getExplosionInfluence(ISimulatedReactor reactor) { return 0F; }
	@Override
	public short getId() { return id; }

}
