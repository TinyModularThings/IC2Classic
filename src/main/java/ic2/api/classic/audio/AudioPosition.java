package ic2.api.classic.audio;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * 
 * @author IC2Team
 * @Modified Speiger
 * Static SoundPosition class
 * this allows you to get a None moving sound on Entities, Tiles or other things
 */

public class AudioPosition implements IAudioPosition
{
	public World world;
	public Vec3d pos;
	
	public static IAudioPosition getFrom(Object obj, PositionSpec spec)
	{
		if(obj instanceof ISoundProvider)
		{
			return ((ISoundProvider)obj).getAudioPositon();
		}
		if(obj instanceof IAudioPosition)
		{
			return (IAudioPosition)obj;
		}
		if(obj instanceof Entity)
		{
			Entity e = (Entity)obj;
			if(spec == PositionSpec.Center)
			{
				return new AudioPosition(e.getEntityWorld(), e.getPositionVector());
			}
			return new MovingEntityAudioPosition(e);
		}
		if(obj instanceof TileEntity)
		{
			TileEntity te = (TileEntity)obj;
			return new AudioPosition(te.getWorld(), te.getPos());
		}
		return null;
	}
	
	public AudioPosition(World world, BlockPos pos)
	{
		this(world, new Vec3d(pos).addVector(0.5D, 0.5D, 0.5D));
	}
	
	public AudioPosition(World world, float x, float y, float z)
	{
		this(world, new Vec3d(x, y, z));
	}

	public AudioPosition(World world, Vec3d pos)
	{
		this.world = world;
		this.pos = pos;
	}

	@Override
	public Vec3d getPosition()
	{
		return pos;
	}

	@Override
	public World getWorld()
	{
		return world;
	}
}
