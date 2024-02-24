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
		SPAWNER,
		ITEM,
		FLUID,
		ENERGY,
		NOTHING;
		
		public boolean matches(TeleportType other)
		{
			if(other == SPAWNER) return this == ENTITY;
			if(this == SPAWNER) return other == ENTITY;
			return this == other;
		}
	}
}
