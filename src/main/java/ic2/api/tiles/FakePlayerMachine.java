package ic2.api.tiles;

import java.util.EnumSet;

import ic2.api.items.IUpgradeItem.UpgradeType;
import ic2.api.tiles.tubes.ITube;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;

public class FakePlayerMachine implements IMachine
{
	static final EnumSet<UpgradeType> DEFAULT = EnumSet.allOf(UpgradeType.class); 
	EnumSet<UpgradeType> type;
	Player player;
	
	public FakePlayerMachine(Player player)
	{
		this(player, DEFAULT);
	}
	
	public FakePlayerMachine(Player player, EnumSet<UpgradeType> type)
	{
		this.player = player;
		this.type = type;
	}
	
	@Override
	public int getValidRoom(ItemStack stack)
	{
		return 0;
	}
	
	@Override
	public EnumSet<UpgradeType> getSupportedUpgradeTypes()
	{
		return type;
	}
	
	@Override
	public int getAvailableEnergy()
	{
		return 0;
	}
	
	@Override
	public boolean useEnergy(int toUse, boolean doUse)
	{
		return false;
	}
	
	@Override
	public boolean isMachineWorking()
	{
		return false;
	}
	
	@Override
	public boolean isRedstoneSensitive()
	{
		return false;
	}
	
	@Override
	public void setRedstoneSensitive(boolean flag)
	{
		
	}
	
	@Override
	public void onUpgradesChanged()
	{
		
	}
	
	@Override
	public IItemHandler getConnectedInventory(Direction dir)
	{
		return null;
	}
	
	@Override
	public ITube getConnectedTube(Direction dir)
	{
		return null;
	}
	
	@Override
	public Level getWorldObj()
	{
		return player.getCommandSenderWorld();
	}

	@Override
	public BlockPos getPosition()
	{
		return player.blockPosition();
	}
	
	public static class FakeMachine implements IMachine
	{
		EnumSet<UpgradeType> type;
		Level world;
		BlockPos pos;
		
		public FakeMachine(Level world, BlockPos pos)
		{
			this(DEFAULT, world, pos);
		}

		public FakeMachine(EnumSet<UpgradeType> type, Level world, BlockPos pos)
		{
			this.type = type;
			this.world = world;
			this.pos = pos;
		}

		@Override
		public int getValidRoom(ItemStack stack)
		{
			return 0;
		}
		
		@Override
		public EnumSet<UpgradeType> getSupportedUpgradeTypes()
		{
			return type;
		}
		
		@Override
		public int getAvailableEnergy()
		{
			return 0;
		}
		
		@Override
		public boolean useEnergy(int toUse, boolean doUse)
		{
			return false;
		}
		
		@Override
		public boolean isMachineWorking()
		{
			return false;
		}
		
		@Override
		public boolean isRedstoneSensitive()
		{
			return false;
		}
		
		@Override
		public void setRedstoneSensitive(boolean flag)
		{
			
		}
		
		@Override
		public void onUpgradesChanged()
		{
			
		}
		
		@Override
		public IItemHandler getConnectedInventory(Direction dir)
		{
			return null;
		}
		
		@Override
		public ITube getConnectedTube(Direction dir)
		{
			return null;
		}
		
		@Override
		public Level getWorldObj()
		{
			return world;
		}

		@Override
		public BlockPos getPosition()
		{
			return pos;
		}
	}
}
