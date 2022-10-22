package ic2.api.items;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import ic2.api.items.armor.IMetalArmor;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public final class ItemRegistries
{
	static final Int2ObjectMap<Item> COIN_MAP = Int2ObjectMaps.synchronize(new Int2ObjectLinkedOpenHashMap<>());
	static final IntList SORTED_COINS = IntLists.synchronize(new IntArrayList());
	static final Map<Item, IMetalArmor> METAL_ARMORS = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
	static final Multimap<Item, IBoxableItem> BOX_ITEMS = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());
	static final Map<ShieldItem, Set<String>> SHIELD_BLOCKABLE = Object2ObjectMaps.synchronize(new Object2ObjectLinkedOpenHashMap<>());
	static final Set<String> SHIELD_BLOCKABLE_GLOBAL = ObjectSets.synchronize(new ObjectLinkedOpenHashSet<>());
	
	public static void registerMetalArmor(IMetalArmor armor, Item... items)
	{
		for(Item item : items)
		{
			METAL_ARMORS.put(item, armor);
		}
	}

	public static boolean isMetalArmor(Player player, EquipmentSlot slot)
	{
		ItemStack stack = player.getItemBySlot(slot);
		if(stack.isEmpty())
		{
			return false;
		}
		IMetalArmor armor = METAL_ARMORS.get(stack.getItem());
		if(armor == null && stack.getItem() instanceof IMetalArmor)
		{
			armor = (IMetalArmor)stack.getItem();
		}
		return armor != null && armor.isMetalArmor(stack, player, slot);
	}
	
	public static void registerBoxableItems(IBoxableItem box, Item...items)
	{
		for(Item item : items)
		{
			BOX_ITEMS.put(item, box);
		}
	}
	
	public static boolean isBoxable(ItemStack stack)
	{
		Item item = stack.getItem();
		for(IBoxableItem box : BOX_ITEMS.get(item))
		{
			if(box.canStoreIntoBox(stack))
			{
				return true;
			}
		}
		return item instanceof IBoxableItem && ((IBoxableItem)item).canStoreIntoBox(stack);
	}
	
	public static void registerCoin(Item item)
	{
		if(item instanceof ICoinItem)
		{
			int value = ((ICoinItem)item).getMoneyValue(new ItemStack(item));
			if(value > 0)
			{
				COIN_MAP.put(value, item);
				SORTED_COINS.clear();
				SORTED_COINS.addAll(COIN_MAP.keySet());
				SORTED_COINS.sort(IntComparators.OPPOSITE_COMPARATOR);
			}
		}
	}
	
	public static NonNullList<ItemStack> generateCoins(int coinValue, NonNullList<ItemStack> result, boolean limit)
	{
		for(int i = 0,m=SORTED_COINS.size();i<m;i++)
		{
			int value = SORTED_COINS.getInt(i);
			if(coinValue < value) continue;
			Item item = COIN_MAP.get(value);
			if(item == null) continue;
			int amount = coinValue / value;
			if(limit && amount < 7 && i + 1 < m)
			{
				continue;
			}
			coinValue -= amount * value;			
			while(amount > 0)
			{
				ItemStack stack = new ItemStack(item);
				stack.setCount(Math.min(stack.getMaxStackSize(), amount));
				amount -= stack.getCount();
				result.add(stack);
			}
			if(coinValue <= 0)
			{
				return result;
			}
		}
		return result;
	}
	
	public static void registerBlockableDamageSource(ShieldItem shield, DamageSource source) {
		Set<String> set = SHIELD_BLOCKABLE.get(shield);
		if (set == null) {
			set = new ObjectLinkedOpenHashSet<>();
			SHIELD_BLOCKABLE.put(shield, set);
		}
		set.add(source.getMsgId());
	}

	public static void registerBlockableDamageSource(DamageSource source) {
		SHIELD_BLOCKABLE_GLOBAL.add(source.getMsgId());
	}

	public static boolean isDamageSourceBlockable(ShieldItem shield, DamageSource source) {
		return SHIELD_BLOCKABLE_GLOBAL.contains(source.getMsgId()) || SHIELD_BLOCKABLE.getOrDefault(shield, Collections.emptySet()).contains(source.getMsgId());
	}
}
