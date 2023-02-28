package ic2.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 *
 * @author Speiger
 *
 * Wrapper interface for TileEntities to allow easy access to their Level and BlockPos
 */
public interface ILocation
{
	default Level getWorldObj()
	{
		if(this instanceof BlockEntity)
		{
			return ((BlockEntity)this).getLevel();
		}
		throw new RuntimeException("ILocation needs to be implemented explicitly");
	}
	
	default BlockPos getPosition()
	{
		if(this instanceof BlockEntity)
		{
			return ((BlockEntity)this).getBlockPos();
		}
		throw new RuntimeException("ILocation needs to be implemented explicitly");
	}
}
