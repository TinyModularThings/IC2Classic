package ic2.api.energy.tile;

public interface IEnergySource extends IEnergyEmitter
{
	int getSourceTier();
	
	int getMaxEnergyOutput();
	
	int getProvidedEnergy();
	
	void consumeEnergy(int consumed);
	
	//When the energyNet couldn't send energy
	default void onPacketFailed() {}
	
	default SourceType getSourceType() { return SourceType.UNKNOWN; }
	
	public static enum SourceType {
		ALWAYS_CONSUMING,
		PASSIVE_PRODUCING,
		INDIRECT_PASSIVE,
		INTELIGENT_CONSUMING,
		DUMB_CONSUMING,
		UNKNOWN;
		
		public SourceType merge(SourceType other) {
			return ordinal() < other.ordinal() ? this : other;
		}
		
		public static SourceType required(SourceType required, SourceType other) {
			return other.ordinal() < required.ordinal() ? required : other;
		}
	}
}
