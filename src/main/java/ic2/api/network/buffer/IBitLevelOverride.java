package ic2.api.network.buffer;

import ic2.api.network.buffer.NetworkInfo.BitLevel;

public interface IBitLevelOverride
{
	BitLevel getOverride(int fieldID, String fieldName);
	
	boolean hasOverride(int fieldID, String fieldName);
}
