package ic2.api.tiles;

import net.minecraft.core.Direction;

public interface IAnchorTile
{
	boolean hasAnchor(Direction side);
	boolean addAnchor(Direction side);
	boolean removeAnchor(Direction side);
}