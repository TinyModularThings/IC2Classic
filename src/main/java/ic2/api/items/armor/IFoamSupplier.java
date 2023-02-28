package ic2.api.items.armor;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IFoamSupplier
{
	boolean canProvideFoam(Player player, ItemStack stack, InventoryType inv, int amount);
	
	void useFoam(Player player, ItemStack stack, int amount);
	
	int getFreeFoamSpace(ItemStack stack);
	
	void fillFoam(ItemStack stack, int amount);
	
	enum InventoryType
	{
		HOTBAR,
		OFFHAND,
		ARMOR,
		CURIO
	}
}
