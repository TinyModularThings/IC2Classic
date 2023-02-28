package ic2.api.energy;

import java.util.List;

import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IEnergyNet
{
	IEnergyTile getTile(Level world, BlockPos pos);
	
	IEnergyTile getSubTile(Level world, BlockPos pos);
	GridTile getTiles(Level world, BlockPos pos);
	
	void addTile(IEnergyTile tile);
	void removeTile(IEnergyTile tile);
	void updateTile(IEnergyTile tile);
	
	int getPowerFromTier(int tier);
	int getTierFromPower(int power);
	String getDisplayTier(int tier);
	
	TransferStats getStats(IEnergyTile tile);
	
	List<PacketStats> getPacketStats(IEnergyTile tile);
	
	class GridTile
	{
		IEnergyTile mainTile;
		IEnergyTile subTile;
		
		public GridTile(IEnergyTile mainTile, IEnergyTile subTile)
		{
			this.mainTile = mainTile;
			this.subTile = subTile;
		}
		
		public IEnergyTile getMainTile()
		{
			return mainTile;
		}
		
		public IEnergyTile getSubTile()
		{
			return subTile;
		}
		
		public BlockPos getPos()
		{
			return subTile.getPosition();
		}
		
		public Level getWorld()
		{
			return subTile.getWorldObj();
		}
	}
}
