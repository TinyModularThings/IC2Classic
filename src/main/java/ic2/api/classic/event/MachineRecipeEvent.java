package ic2.api.classic.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * 
 * @author Speiger
 * 
 * A Event that allows you to detect who did use which machine.
 * you can not modify the result its a read only function.
 * But you can detect if someone has used a automated or manual way.
 * Automated way is via upgrades. Other Mod Extraction does not count.
 */

public abstract class MachineRecipeEvent extends PlayerEvent
{
	final boolean automated;
	final ItemStack output;
	
	public MachineRecipeEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
	{
		super(player);
		automated = isAutoamted;
		output = automated ? ItemStack.copyItemStack(stack) : stack;
	}
	
	/**
	 * If it is a automated extraction out of the machine.
	 * Note: If automated the item can not be modified since it can be delayed.
	 */
	public boolean isAutomated()
	{
		return automated;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	public static class ItemSmeltedEvent extends MachineRecipeEvent
	{
		public ItemSmeltedEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}	
	}
	
	public static class ItemMaceratedEvent extends MachineRecipeEvent
	{
		public ItemMaceratedEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}
	}
	
	public static class ItemExtractedEvent extends MachineRecipeEvent
	{
		public ItemExtractedEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}
	}
	
	public static class ItemCompressedEvent extends MachineRecipeEvent
	{
		public ItemCompressedEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}
	}
	
	public static class ItemRecycledEvent extends MachineRecipeEvent
	{
		public ItemRecycledEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}
	}
	
	public static class MatterCreatedEvent extends MachineRecipeEvent
	{
		public MatterCreatedEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}	
	}
	
	public static class ItemSawCuttedEvent extends MachineRecipeEvent
	{
		public ItemSawCuttedEvent(EntityPlayer player, ItemStack stack, boolean isAutoamted)
		{
			super(player, stack, isAutoamted);
		}		
	}
}
