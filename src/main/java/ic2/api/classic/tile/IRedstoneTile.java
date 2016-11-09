package ic2.api.classic.tile;

import net.minecraft.util.EnumFacing;

public interface IRedstoneTile
{
	/**
	 * TileEntity interface for classic core TileEntities
	 * if you are using IC2Classics base Blocks this class will allow you to return which side it can connect to
	 * @param side the side its asked for
	 * @return if it can connect
	 */
	public boolean canConnectToRedstone(EnumFacing side);
}
