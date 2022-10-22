package ic2.api.tiles.teleporter;

import ic2.api.util.ILocation;
import net.minecraft.core.Direction;

public interface ITeleporterTarget extends ILocation
{
	Direction getFacing();
	TeleportType getSendType();
	boolean canReceive(TeleportType type);
	
	boolean setTarget(TeleporterTarget target);
	boolean hasTarget(TeleporterTarget target);
	
	enum TeleportType
	{
		ENTITY,
		ITEM,
		FLUID,
		ENERGY,
		NOTHING
	}
}
