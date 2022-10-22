package ic2.api.network;

import java.util.Optional;
import java.util.function.Consumer;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("unchecked")
public interface IPlayerPacket
{
	default <T extends AbstractContainerMenu> T getContainer(Player player, Class<T> clz)
	{
		return clz.isInstance(player.containerMenu) ? (T)player.containerMenu : null;
	}
	
	default <T extends AbstractContainerMenu> void getContainer(Player player, Class<T> clz, Consumer<T> listener)
	{
		if(clz.isInstance(player.containerMenu))
		{
			listener.accept((T)player.containerMenu);
		}
	}
	
	default <T extends AbstractContainerMenu> Optional<T> getOptionalContainer(Player player, Class<T> clz)
	{
		return clz.isInstance(player.containerMenu) ? Optional.of((T)player.containerMenu) : Optional.empty();
	}
	
	default ItemStack findPlayerStack(Player player, ItemStack send)
	{
		for(EquipmentSlot slot : EquipmentSlot.values()) if(ItemStack.matches(send, player.getItemBySlot(slot))) return player.getItemBySlot(slot);
		return ItemStack.EMPTY;
	}
}
