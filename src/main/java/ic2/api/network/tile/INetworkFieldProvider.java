package ic2.api.network.tile;

import java.util.List;

public interface INetworkFieldProvider
{
	List<String> getNetworkFields();
	List<String> getGuiFields();
	default boolean isDefaultData(String fieldName) { return false; }
}
