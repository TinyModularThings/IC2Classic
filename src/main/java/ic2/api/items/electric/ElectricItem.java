package ic2.api.items.electric;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.items.IItemHandler;

public final class ElectricItem
{
	static final Map<Item, IElectricItemManager> BACKUPS = new Object2ObjectOpenHashMap<>();
	static Function<Player, IItemHandler> CURIO_ACCESSOR;
	public static IElectricItemManager MANAGER;
	public static IElectricItemManager DIRECT_MANAGER;
	
	public static void registerBackupManager(IElectricItemManager manager, ItemLike...items)
	{
		for(ItemLike prov : items)
		{
			BACKUPS.put(prov.asItem(), manager);
		}
	}
	
	public static void setCurioSupport(Function<Player, IItemHandler> curio)
	{
		CURIO_ACCESSOR = curio;
	}
	
	public static IElectricItemManager getBackupManager(Item item)
	{
		return BACKUPS.get(item);
	}

	public static int chargeArmor(Player player, int provided) {
		return chargeArmor(player, provided, true, EquipmentSlot.values());
	}
	
	public static int chargeArmor(Player player, int provided, boolean whitelist, EquipmentSlot... slots) {
		return chargeArmor(player, provided, whitelist, true, slots);
	}
	
	public static int chargeArmor(Player player, int provided, boolean whitelist, boolean allowCurio, EquipmentSlot... slots) {
		if(whitelist)
		{
			for (EquipmentSlot slot : slots) {
				if (provided <= 0)
					break;
				if (slot.getType() == EquipmentSlot.Type.HAND)
					continue;
				provided -= ElectricItem.MANAGER.charge(player.getItemBySlot(slot), provided, Integer.MAX_VALUE, false, false);
			}
			if(provided > 0 && allowCurio && CURIO_ACCESSOR != null)
			{
				provided -= chargeCurio(CURIO_ACCESSOR.apply(player), provided);
			}
			return provided;
		}
		Set<EquipmentSlot> notAllowed = EnumSet.copyOf(ObjectArrayList.wrap(slots));
		for (EquipmentSlot slot : EquipmentSlot.values()) {
			if (provided <= 0)
				break;
			if (slot.getType() == EquipmentSlot.Type.HAND || notAllowed.contains(slot))
				continue;
			provided -= ElectricItem.MANAGER.charge(player.getItemBySlot(slot), provided, Integer.MAX_VALUE, false, false);
		}
		if(provided > 0 && allowCurio && CURIO_ACCESSOR != null)
		{
			provided -= chargeCurio(CURIO_ACCESSOR.apply(player), provided);
		}
		return provided;
	}
	
	public static int chargeCurio(IItemHandler handler, int energyProvided)
	{
		if(handler == null) return 0;
		int used = 0;
		for(int i = 0,m=handler.getSlots();i<m;i++)
		{
			if(used >= energyProvided) break;
			used += ElectricItem.MANAGER.charge(handler.getStackInSlot(i), energyProvided - used, Integer.MAX_VALUE, false, false);
		}
		return used;
	}
	
	public static int applyEnchantmentEffect(ItemStack item, int amount)
	{
		amount *= 1D + (0.15D * EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, item));
		amount *= 1D - (0.1D * EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, item));
		return amount;
	}
}

