package ic2.api.classic.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * 
 * @author Speiger
 * 
 * a Event that gets fired every time someone uses EU in an item.
 * This counts only for useFunction in the ElectricItemManager.
 * This is only a reading function if you want to track this.
 *
 */
public class EnergyUsedEvent extends PlayerEvent
{
	final int used;
	final ItemStack item;
	
	public EnergyUsedEvent(EntityPlayer player, ItemStack stack, int usedEU)
	{
		super(player);
		item = stack;
		used = usedEU;
	}
	
	public ItemStack getItem()
	{
		return item;
	}
	
	public int getUsedEU()
	{
		return used;
	}
	
}
