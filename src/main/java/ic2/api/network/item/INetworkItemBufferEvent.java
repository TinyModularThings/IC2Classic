package ic2.api.network.item;

import ic2.api.network.IPlayerPacket;
import ic2.api.network.buffer.INetworkDataBuffer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;

public interface INetworkItemBufferEvent<T extends INetworkDataBuffer> extends IPlayerPacket
{
	void onDataBufferReceived(ItemStack stack, Player player, String id, T buffer, Dist targetSide);
}
