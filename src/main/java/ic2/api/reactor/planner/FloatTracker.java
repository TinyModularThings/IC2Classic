package ic2.api.reactor.planner;

public class FloatTracker
{
	private float total = 0;
	private int count = 0;
	private float change = 0;
	
	public void addChange(float value) {
		this.change += value;
	}
	
	public void commit() {
		total += change;
		change = 0;
		count++;
	}
	
	public void reset() {
		total = 0F;
		count = 0;
		change = 0F;
	}
	
	public float getAverage() {
		return count == 0 ? 0F : total / count;
	}
	
}
