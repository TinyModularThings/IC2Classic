package ic2.api.network.tile;

import ic2.api.network.IPlayerPacket;
import ic2.api.network.buffer.INetworkDataBuffer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;

public interface INetworkDataEventListener extends IPlayerPacket
{
	void onDataBufferReceived(Player player, String id, INetworkDataBuffer data, Dist target);
}
