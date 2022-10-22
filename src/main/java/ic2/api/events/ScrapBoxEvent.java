package ic2.api.events;

import java.util.List;

import net.minecraft.core.BlockSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class ScrapBoxEvent extends Event
{
	List<ItemStack> drops;
	ItemStack scrapBox;
	
	public ScrapBoxEvent(List<ItemStack> drops, ItemStack scrapBox)
	{
		this.drops = drops;
		this.scrapBox = scrapBox;
	}
	
	public List<ItemStack> getDrops()
	{
		return drops;
	}
	
	public ItemStack getScrapBox()
	{
		return scrapBox;
	}
	
	public static class ScrapBoxPlayerUseEvent extends ScrapBoxEvent
	{
		Player player;
		InteractionHand hand;
		
		public ScrapBoxPlayerUseEvent(List<ItemStack> drops, ItemStack scrapBox, Player player, InteractionHand hand)
		{
			super(drops, scrapBox);
			this.player = player;
			this.hand = hand;
		}
		
		public Player getPlayer()
		{
			return player;
		}
		
		public InteractionHand getHand()
		{
			return hand;
		}
	}
	
	public static class ScrapBoxDispenseEvent extends ScrapBoxEvent
	{
		BlockSource source;
		
		public ScrapBoxDispenseEvent(List<ItemStack> drops, ItemStack scrapBox, BlockSource source)
		{
			super(drops, scrapBox);
			this.source = source;
		}
		
		public BlockSource getSource()
		{
			return source;
		}
	}
}
