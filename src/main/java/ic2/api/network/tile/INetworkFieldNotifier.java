package ic2.api.network.tile;

import java.util.Set;

import ic2.api.network.IPlayerPacket;
import net.minecraft.world.entity.player.Player;

public interface INetworkFieldNotifier extends IPlayerPacket
{
	void onNetworkFieldChanged(Set<String> fields, Player player);
	
	void onGuiFieldChanged(Set<String> fields, Player player);
}
