package ic2.api.classic.audio;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AudioPosition
{
	public World world;
	public float x;
	public float y;
	public float z;
	
	public static AudioPosition getFrom(Object obj, PositionSpec positionSpec)
	{
		if(obj instanceof AudioPosition)
		{
			return (AudioPosition)obj;
		}
		if(obj instanceof Entity)
		{
			Entity e = (Entity)obj;
			return new AudioPosition(e.worldObj, (float)e.posX, (float)e.posY, (float)e.posZ);
		}
		if(obj instanceof TileEntity)
		{
			TileEntity te = (TileEntity)obj;
			return new AudioPosition(te.getWorld(), te.getPos());
		}
		return null;
	}
	
	public AudioPosition(World world, BlockPos par1)
	{
		this(world, par1.getX() + 0.5F, par1.getY() + 0.5F, par1.getZ() + 0.5F);
	}
	
	public AudioPosition(World world, float x, float y, float z)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BlockPos asBlockPos()
	{
		return new BlockPos(x, y, z);
	}
}
