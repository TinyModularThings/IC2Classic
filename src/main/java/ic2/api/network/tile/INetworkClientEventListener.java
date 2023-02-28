package ic2.api.network.tile;

import ic2.api.network.IPlayerPacket;
import net.minecraft.world.entity.player.Player;

public interface INetworkClientEventListener extends IPlayerPacket
{
	void onClientDataReceived(Player entity, int key, int value);
}
