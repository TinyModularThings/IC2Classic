package ic2.api.items.armor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import ic2.api.network.buffer.INetworkDataBuffer;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public interface IArmorModule
{
	ModuleType getType(ItemStack stack);
	
	boolean canInstallInArmor(ItemStack stack, ItemStack armor, EquipmentSlot type);
	
	void onInstall(ItemStack stack, ItemStack armor, IArmorModuleHolder holder);
	void onUninstall(ItemStack stack, ItemStack armor, IArmorModuleHolder holder);
	void transferToArmor(ItemStack stack, ItemStack oldArmor, ItemStack newArmor);
	
	void onTick(ItemStack stack, ItemStack armor, Level world, Player player);
	
	void onEquipped(ItemStack stack, ItemStack armor, Player entity);
	void onUnequipped(ItemStack stack, ItemStack armor, Player entity);

	void provideCapabilities(ItemStack stack, ItemStack armor);
		
	boolean handlePacket(Player player, ItemStack module, ItemStack armor, String id, INetworkDataBuffer buffer, Dist targetSide);
	
	public default void handleToolTip(ItemStack stack, Consumer<MutableComponent> results)
	{
		results.accept(Component.translatable("misc.ic2.module.slot_type", getType(stack).getName()));
		MutableComponent adding = Component.literal("[");
		Component next = null;
		boolean first = true;
		for(EquipmentSlot slot : EquipmentSlot.values())
		{
			if(slot.getType() == Type.ARMOR && canInstallInArmor(stack, stack, slot))
			{
				if(next != null) 
				{
					if(!first) adding.append(", ");
					adding.append(next);
					first = false;
				}
				next = Component.translatable("misc.ic2.module.valid_armor."+slot.getName());
			}
		}
		
		if(next != null)
		{
			if(!first) adding.append(", ");
			results.accept(Component.translatable("misc.ic2.module.valid_armor", adding.append(next).append("]")));
		}
	}
	
	public static interface IArmorModuleHolder
	{
		String DISABLE_MODULES = "no_modules";

		List<ItemStack> getInstalledModules(ItemStack stack);
		
		Object2IntMap<ModuleType> getModuleLimits(ItemStack stack);
		
		void addAddModifier(ItemStack stack, ArmorMod type, int amount);
		void addMulModifier(ItemStack stack, ArmorMod type, float amount);
		
		void removeAddModifier(ItemStack stack, ArmorMod type, int amount);
		void removeMulModifier(ItemStack stack, ArmorMod type, float amount);
		
		void onEquipmentStateChanged(ItemStack stack, boolean equip, Player player);
		
		static boolean isValidModularArmor(ItemStack stack)
		{
			if(!(stack.getItem() instanceof IArmorModuleHolder)) return false;
			CompoundTag tag = stack.getTag();
			return tag == null || !tag.getBoolean(DISABLE_MODULES);
		}
	}
	
	public static class ArmorMod
	{
		//EU
		public static final ArmorMod ENERGY_STORAGE = new ArmorMod("storage_add", "storage_mul");
		public static final ArmorMod ENERGY_TIER = new ArmorMod("tier");
		public static final ArmorMod ENERGY_TRANSFER = new ArmorMod("transfer_add", "transfer_mul");
		public static final ArmorMod ENERGY_PROVIDER = new ArmorMod("provider");

		public static final ArmorMod NO_LAVA_DAMAGE = new ArmorMod("lava");
		
		public static final ArmorMod ENERGY_SHIELD = new ArmorMod("shield");
		public static final ArmorMod ENERGY_SHIELD_ALWAYS = new ArmorMod("shield_always");
		
		public static final ArmorMod EU_READER = new ArmorMod("eu_reader");
		public static final ArmorMod THERMOMETER = new ArmorMod("thermo");
		public static final ArmorMod HAZMAT_PROTECTION = new ArmorMod("hazmat");
		public static final ArmorMod CROP_SCANNER = new ArmorMod("crop");
		public static final ArmorMod HUD_ENERGY = new ArmorMod("armor_hud");

		public static final ArmorMod GOLDEN = new ArmorMod("golden");
		
		String add_mod;
		String mul_mod;
		
		public ArmorMod(String add_mod)
		{
			this(add_mod, null);
		}
		
		public ArmorMod(String add_mod, String mul_mod)
		{
			this.add_mod = add_mod;
			this.mul_mod = mul_mod;
		}
		
		public String getAdd()
		{
			return add_mod;
		}
		
		public String getMul()
		{
			return mul_mod;
		}
	}
	
	public static interface IArmorCapability
	{
		<T> void registerArmorCapability(Capability<T> cap, ICapabilityProvider value);
		
		<T> ICapabilityProvider removeArmorCapability(Capability<T> cap);
		
		<T> LazyOptional<T> getArmorCapability(Capability<T> cap);
	}
	
	public static final class ModuleType implements Comparable<ModuleType>
	{
		private static final Map<String, ModuleType> TYPES = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
		private static final AtomicInteger IDS = new AtomicInteger(1);
		public static final ModuleType BATTERY = getOrCreateType("battery", "misc.ic2.module.battery");
		public static final ModuleType HUD = getOrCreateType("hud", "misc.ic2.module.hud");
		public static final ModuleType ANY = getOrCreateType("any", "misc.ic2.module.any");
		public static final ModuleType GENERIC = getOrCreateType("generic", "misc.ic2.module.generic");
		public static final ModuleType CHARGER = getOrCreateType("charge", "misc.ic2.module.charger");
		public static final ModuleType STORAGE = getOrCreateType("storage", "misc.ic2.module.storage");
		public static final ModuleType BACK_SLOT = getOrCreateType("back_slot", "misc.ic2.module.back_slot");
		public static final ModuleType MOVEMENT = getOrCreateType("movement", "misc.ic2.module.movement");
		
		final int index;
		final String id;
		final String name;
		
		ModuleType(String id, String name)
		{
			index = IDS.getAndIncrement();
			this.id = id;
			this.name = name;
		}
		
		public Component getName()
		{
			return Component.translatable(name);
		}
		
		public String getId()
		{
			return id;
		}
		
		public static ModuleType getType(String id)
		{
			return TYPES.get(id);
		}
		
		public static synchronized ModuleType getOrCreateType(String id, String name)
		{
			ModuleType type = TYPES.get(id);
			if(type == null)
			{
				type = new ModuleType(id, name);
				TYPES.put(id, type);
			}
			return type;
		}
		
		public static List<ModuleType> getAllTypes()
		{
			return new ObjectArrayList<>(TYPES.values());
		}
		
		@Override
		public int compareTo(ModuleType o)
		{
			return Integer.compareUnsigned(index, o.index);
		}
	}
}
