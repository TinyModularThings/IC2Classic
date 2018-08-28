package ic2.api.classic.trading.prefab;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import ic2.api.classic.trading.ITradeInventory;
import ic2.api.classic.trading.providers.IItemTradeProvider;
import ic2.api.classic.trading.providers.ITradeProvider;
import ic2.api.classic.trading.trades.IItemTrade;
import ic2.api.classic.trading.trades.ISubTrade;
import ic2.api.classic.trading.trades.ITrade;
import ic2.api.classic.trading.trades.TradeType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;

public class BasicItemTrade implements IItemTrade
{
	ItemStack required;
	ItemStack gain;
	ISubTrade subTrade;
	
	public BasicItemTrade()
	{
		/*
		 * This class does not need to be registered in the registry since its
		 * Since its auto-registered
		 */
	}
	
	public BasicItemTrade(ItemStack wanted, ItemStack give)
	{
		required = wanted.copy();
		gain = give.copy();
		subTrade = new BasicSubTrade(this, wanted);
	}
	
	@Override
	public boolean canAccessTrade(UUID player)
	{
		return true;
	}
	
	@Override
	public TradeType getType()
	{
		return TradeType.Item;
	}
	
	@Override
	public List<ISubTrade> getSubTrades(UUID player, boolean ignoreOwner)
	{
		return Arrays.asList(subTrade);
	}
	
	@Override
	public ISubTrade getSubTradeFromSlot(int slot, UUID player, boolean ignoreOwner)
	{
		return slot == 0 ? subTrade : null;
	}
	
	@Override
	public int getTradeSlot(ISubTrade sub)
	{
		return subTrade.equals(sub) ? 0 : -1;
	}
	
	@Override
	public ActionResult<List<ItemStack>> trade(UUID player, ITradeProvider prov, ISubTrade trade, ITradeInventory inv, int limit)
	{
		if(!subTrade.equals(trade) || !(prov instanceof IItemTradeProvider))
		{
			return new ActionResult(EnumActionResult.FAIL, EMPTY_LIST); 
		}
		IRecipeInput input = new RecipeInputItemStack(required);
		int total = inv.getItemCount(input); //Getting total count
		int count = Math.min(limit, total / required.getCount());
		if(count <= 0)
		{
			return new ActionResult(EnumActionResult.PASS, EMPTY_LIST);
		}
		List<ItemStack> list = ((IItemTradeProvider)prov).getNearbyItemStack(new RecipeInputItemStack(gain), gain.getCount() * count, true); 
		if(list.isEmpty())
		{
			return new ActionResult(EnumActionResult.PASS, EMPTY_LIST);
		}
		float multiplier = ((float)getTotalStackSize(list) / (float)gain.getCount()); 
		if(multiplier <= 0)
		{
			return new ActionResult(EnumActionResult.PASS, EMPTY_LIST);
		}
		((IItemTradeProvider)prov).getNearbyItemStack(new RecipeInputItemStack(gain), (int)(gain.getCount() * count), false);
		List<ItemStack> removed = inv.removeItems(input, Math.min(total, (int)(required.getCount() * multiplier)));
		prov.onTradePerformed(player, trade, removed); 
		return ActionResult.newResult(EnumActionResult.SUCCESS, list); 
	}
	
	public int getTotalStackSize(List<ItemStack> list)
	{
		int total = 0;
		for(ItemStack stack : list)
		{
			total += stack.getCount();
		}
		return total;
	}
	
	
	@Override
	public List<ItemStack> getTradeResults(ISubTrade sub)
	{
		return Arrays.asList(gain.copy());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) throws Exception
	{
		required = new ItemStack(nbt.getCompoundTag("wanted"));
		gain = new ItemStack(nbt.getCompoundTag("give"));
		if(gain.isEmpty() || required.isEmpty())
		{
			throw new Exception("Items got removed. Trade is now invalid");
		}
		subTrade = new BasicSubTrade(this, required);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagCompound data = new NBTTagCompound();
		required.writeToNBT(data);
		nbt.setTag("wanted", data);
		data = new NBTTagCompound();
		gain.writeToNBT(data);
		nbt.setTag("give", data);
	}

	@Override
	public ITrade<ItemStack> newInstance(UUID owner)
	{
		return new BasicItemTrade(required, gain);
	}

	@Override
	public int getStockCount(ISubTrade sub, ITradeProvider prov, UUID player)
	{
		if(!subTrade.equals(sub) || !(prov instanceof IItemTradeProvider))
		{
			return 0;
		}
		List<ItemStack> count = ((IItemTradeProvider)prov).getNearbyItemStack(new RecipeInputItemStack(gain), gain.getCount() * Short.MAX_VALUE, false);
		return getTotalStackSize(count) / gain.getCount();
	}
	
}
