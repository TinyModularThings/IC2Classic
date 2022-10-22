package ic2.api.reactor.planner;

import net.minecraft.nbt.CompoundTag;

public class Tracker
{
	private int total = 0;
	private int count = 0;
	private int change = 0;
	
	public CompoundTag save(CompoundTag tag)
	{
		tag.putInt("total", total);
		tag.putInt("count", count);
		tag.putInt("change", change);
		return tag;
	}
	
	public void load(CompoundTag tag)
	{
		total = tag.getInt("total");
		count = tag.getInt("count");
		change = tag.getInt("change");
	}
	
	public void addChange(int value) {
		this.change += value;
	}
	
	public void commit() {
		total += change;
		change = 0;
		count++;
	}
	
	public void reset() {
		total = 0;
		count = 0;
		change = 0;
	}
	
	public float getAverage() {
		return count == 0 ? 0F : (float)total / (float)count;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getTotal() {
		return total;
	}
	
}
