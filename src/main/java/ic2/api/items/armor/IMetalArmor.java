package ic2.api.items.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IMetalArmor
{
	boolean isMetalArmor(ItemStack stack, Player player, EquipmentSlot targetSlot);
}
