package ic2.api.classic.tile;

import ic2.api.classic.event.MachineRecipeEvent;
import ic2.api.classic.event.MachineRecipeEvent.ItemCompressedEvent;
import ic2.api.classic.event.MachineRecipeEvent.ItemExtractedEvent;
import ic2.api.classic.event.MachineRecipeEvent.ItemMaceratedEvent;
import ic2.api.classic.event.MachineRecipeEvent.ItemRecycledEvent;
import ic2.api.classic.event.MachineRecipeEvent.ItemSawCuttedEvent;
import ic2.api.classic.event.MachineRecipeEvent.ItemSmeltedEvent;
import ic2.api.classic.event.MachineRecipeEvent.MatterCreatedEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class MachineType
{
	private static MachineType[] storage = new MachineType[256];//Byte LVL
	public static final MachineType furnace = new IC2MachineType(0, 0);
	public static final MachineType macerator = new IC2MachineType(1, 1);
	public static final MachineType extractor = new IC2MachineType(2, 2);
	public static final MachineType compressed = new IC2MachineType(3, 3);
	public static final MachineType recylced = new IC2MachineType(4, 4);
	public static final MachineType massFabricated = new IC2MachineType(5, 5);
	public static final MachineType sawMill = new IC2MachineType(6, 6);
	
	int recipeID;
	
	public MachineType(int typeID)
	{
		if(storage[typeID] != null)
		{
			throw new RuntimeException("AlreadyUsedID");
		}
		recipeID = typeID;
		storage[typeID] = this;
	}
	
	@Override
	public int hashCode()
	{
		return getID();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof MachineType)
		{
			return ((MachineType)obj).getID() == getID();
		}
		return false;
	}
	
	public final int getID()
	{
		return recipeID;
	}
	
	/**
	 * Function to create a custom event when you have a machine.
	 * @param player The player who did it
	 * @param stack the stack that got produced
	 * @param automated if the stack was taken out manually
	 * @return your Event. Can be null
	 */
	public abstract MachineRecipeEvent createEvent(EntityPlayer player, ItemStack stack, boolean automated);
	
	
	public static boolean hasID(int id)
	{
		return storage[id] != null;
	}
	
	public static MachineType getFromID(int id)
	{
		return storage[id];
	}
	
	public static class IC2MachineType extends MachineType
	{
		int subType;
		public IC2MachineType(int typeID, int subID)
		{
			super(typeID);
			subType = subID;
		}
		
		@Override
		public MachineRecipeEvent createEvent(EntityPlayer player, ItemStack stack, boolean automated)
		{
			switch(subType)
			{
				case 0: return new ItemSmeltedEvent(player, stack, automated);
				case 1: return new ItemMaceratedEvent(player, stack, automated);
				case 2: return new ItemExtractedEvent(player, stack, automated);
				case 3: return new ItemCompressedEvent(player, stack, automated);
				case 4: return new ItemRecycledEvent(player, stack, automated);
				case 5: return new MatterCreatedEvent(player, stack, automated);
				case 6: return new ItemSawCuttedEvent(player, stack, automated);
			}
			return null;
		}
	}
}
