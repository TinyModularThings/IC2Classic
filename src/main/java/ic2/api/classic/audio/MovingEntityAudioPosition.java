package ic2.api.classic.audio;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MovingEntityAudioPosition implements IAudioPosition
{
	Entity entity;
	
	public MovingEntityAudioPosition(Entity entity)
	{
		this.entity = entity;
	}

	@Override
	public Vec3d getPosition()
	{
		return entity.getPositionVector();
	}

	@Override
	public World getWorld()
	{
		return entity.getEntityWorld();
	}
	

}
