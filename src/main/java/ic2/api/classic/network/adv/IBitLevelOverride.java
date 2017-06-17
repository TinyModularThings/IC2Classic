package ic2.api.classic.network.adv;

import ic2.api.classic.network.adv.NetworkField.BitLevel;

public interface IBitLevelOverride
{
	public BitLevel getOverride(int fieldID, String fieldName);
	
	public boolean hasOverride(int fieldID, String fieldName);
}
