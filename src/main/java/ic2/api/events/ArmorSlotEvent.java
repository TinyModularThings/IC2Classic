package ic2.api.events;

import ic2.api.items.armor.IArmorModule.ModuleType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.Event;

public class ArmorSlotEvent extends Event
{
	Item item;
	String id;
	EquipmentSlot slot;
	Object2IntMap<ModuleType> slots;
	
	public ArmorSlotEvent(Item item, String id, EquipmentSlot slot, Object2IntMap<ModuleType> slots)
	{
		this.item = item;
		this.id = id;
		this.slot = slot;
		this.slots = slots;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public String getId()
	{
		return id;
	}
	
	public EquipmentSlot getEquipmentSlot()
	{
		return slot;
	}
	
	public Object2IntMap<ModuleType> getSlots()
	{
		return slots;
	}
	
	public void addSlots(ModuleType type, int amount)
	{
		slots.computeInt(type, (T, V) -> Math.min(9, (V == null ? 0 : V) + amount));
	}
}
