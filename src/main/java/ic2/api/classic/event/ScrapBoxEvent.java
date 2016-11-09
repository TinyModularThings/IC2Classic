package ic2.api.classic.event;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * 
 * @author Speiger
 * 
 * Custom event that tells you whenever a ScrapBox is opened.
 * You can modify the list how you wish. You can add/remove drops.
 * Also you can detect which item is used. By default it will be IC2s scrapbox
 */
public class ScrapBoxEvent extends Event
{
	List<ItemStack> drops = new LinkedList<ItemStack>();
	final ItemStack scrapBoxItem;
	
	public ScrapBoxEvent(ItemStack item, List<ItemStack> items)
	{
		drops.addAll(items);
		scrapBoxItem = item;
	}
	
	public List<ItemStack> getDrops()
	{
		return drops;
	}
	
	public ItemStack getProvider()
	{
		return scrapBoxItem;
	}
	
	/**
	 * 
	 * @author Speiger
	 * 
	 * Player Version of that event.
	 * Called when ever the Scrapbox is opened by a player
	 */
	public static class ScrapBoxPlayerUseEvent extends ScrapBoxEvent
	{
		EntityPlayer user;
		EnumHand usedHand;
		
		public ScrapBoxPlayerUseEvent(ItemStack item, List<ItemStack> items, EntityPlayer player, EnumHand hand)
		{
			super(item, items);
			user = player;
			usedHand = hand;
		}
		
		public EnumHand getUsedHand()
		{
			return usedHand;
		}
		
		public EntityPlayer getUser()
		{
			return user;
		}
	}
	
	/**
	 * 
	 * @author Speiger
	 * 
	 * Automated Version of Scrapbox opening.
	 * Always fired when a Dispenser opens it.
	 */
	public static class ScrapBoxDispenseEvent extends ScrapBoxEvent
	{
		IBlockSource source;
		
		public ScrapBoxDispenseEvent(ItemStack item, List<ItemStack> items, IBlockSource thrower)
		{
			super(item, items);
			source = thrower;
		}
		
		public IBlockSource getSource()
		{
			return source;
		}
		
	}
}
