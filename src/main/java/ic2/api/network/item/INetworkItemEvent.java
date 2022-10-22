package ic2.api.network.item;

import ic2.api.network.IPlayerPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;

public interface INetworkItemEvent extends IPlayerPacket
{
	void onEventReceived(ItemStack stack, Player player, int key, int value, Dist target);
}
