package ic2.api.tiles.teleporter;

import com.google.common.base.Objects;

import ic2.api.network.buffer.IInputBuffer;
import ic2.api.network.buffer.INetworkDataBuffer;
import ic2.api.network.buffer.IOutputBuffer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.server.ServerLifecycleHooks;

public class TeleporterTarget implements INetworkDataBuffer
{
	ResourceKey<Level> dimension;
	BlockPos targetPosition;
	
	private TeleporterTarget() {}
	
	public TeleporterTarget(TeleporterTarget target)
	{
		dimension = target.getDimension();
		targetPosition = target.getTargetPosition().immutable();
	}
	
	public TeleporterTarget(BlockEntity tile)
	{
		this(tile.getLevel(), tile.getBlockPos());
	}
	
	public TeleporterTarget(Level world, BlockPos pos)
	{
		dimension = world.dimension();
		targetPosition = pos.immutable();
	}
	
	@Override
	public void write(IOutputBuffer buffer)
	{
		buffer.writeRegistryKey(dimension);
		buffer.writeLong(targetPosition.asLong());
	}

	@Override
	public void read(IInputBuffer buffer)
	{
		dimension = buffer.readRegistryKey(Registry.DIMENSION_REGISTRY);
		targetPosition = BlockPos.of(buffer.readLong());
	}
	
	public ResourceKey<Level> getDimension()
	{
		return dimension;
	}
	
	public BlockPos getTargetPosition()
	{
		return targetPosition;
	}
	
	public ServerLevel getWorld()
	{
		return ServerLifecycleHooks.getCurrentServer().getLevel(dimension);
	}
	
	public BlockEntity getTile()
	{
		return getWorld().getBlockEntity(getTargetPosition());
	}
	
	public boolean isSame(BlockEntity tile)
	{
		return isSame(tile.getLevel().dimension(), tile.getBlockPos());
	}
	
	public boolean isSame(ResourceKey<Level> type, BlockPos pos)
	{
		return dimension == type && targetPosition.equals(pos);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(dimension.location().hashCode(), targetPosition.hashCode());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof TeleporterTarget)
		{
			TeleporterTarget target = (TeleporterTarget)obj;
			return target.dimension == dimension && target.targetPosition.equals(targetPosition);
		}
		return false;
	}
	
	public static TeleporterTarget readFromBuffer(IInputBuffer buffer)
	{
		TeleporterTarget target = new TeleporterTarget();
		target.read(buffer);
		return target.dimension == null ? null : target;
	}
	
	public static TeleporterTarget read(CompoundTag nbt)
	{
		if(nbt.isEmpty())
		{
			return null;
		}
		TeleporterTarget target = new TeleporterTarget();
		target.dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(nbt.getString("id")));
		target.targetPosition = BlockPos.of(nbt.getLong("pos"));
		return target;
	}
	
	public CompoundTag write()
	{
		return write(new CompoundTag());
	}
	
	public CompoundTag write(CompoundTag nbt)
	{
		nbt.putString("id", dimension.location().toString());
		nbt.putLong("pos", targetPosition.asLong());
		return nbt;
	}
}
