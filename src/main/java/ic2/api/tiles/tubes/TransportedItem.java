package ic2.api.tiles.tubes;

import java.util.UUID;
import java.util.function.Supplier;

import ic2.api.network.buffer.IInputBuffer;
import ic2.api.network.buffer.INetworkDataBuffer;
import ic2.api.network.buffer.IOutputBuffer;
import ic2.api.util.DirectionList;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public final class TransportedItem implements INetworkDataBuffer
{
	/**
	* Write essential Compression
	* Bit 1-6: Tried directions,
	* Bit 7-9: Direction,
	* Bit 10-14: Color,
	* Bit 15: isCentering
	*/
	static int GLOBAL_COUNTER = 0;
	public static final int MIN_SPEED = 1;
	public static final int MAX_SPEED = 100;
	
	int id;
	
	UUID logistics = null;
	ItemStack serverStack = ItemStack.EMPTY;
	Supplier<ItemStack> clientStack;
	byte color = 0;
	
	byte speed = 5;
	byte progress;
	byte lastProgress;
	
	boolean centering;
	byte currentDirection;
	byte triedDirections;
	
	protected byte timeOut = 0;
	protected boolean invalid = false;
	
	private TransportedItem()
	{
	}
	
	public TransportedItem(ItemStack stack)
	{
		serverStack = stack.copy();
		id = GLOBAL_COUNTER++; //Yes this counter isn't the best idea, but keeping track of all instances is a bad idea. And its unlikely that you reach 2 billion entries and the low numbers still being active... So a loop is fine!
		if(GLOBAL_COUNTER < 0) GLOBAL_COUNTER = 0;
	}
	
	public static TransportedItem readFromNetwork(IInputBuffer buffer)
	{
		TransportedItem item = new TransportedItem();
		item.read(buffer);
		return item;
	}
	
	public static TransportedItem read(CompoundTag nbt)
	{
		TransportedItem item = new TransportedItem();
		item.readFromNBT(nbt);
		return item.isInvalid() ? null : item;
	}
	
	public void readFromNBT(CompoundTag nbt)
	{
		id = nbt.getInt("id");
		serverStack = ItemStack.of(nbt.getCompound("item"));
		speed = nbt.getByte("speed");
		progress = nbt.getByte("progress");
		centering = nbt.getBoolean("centering");
		color = nbt.getByte("color");
		currentDirection = nbt.getByte("dir");
		triedDirections = nbt.getByte("tried");
		if(nbt.contains("logistics")) logistics = nbt.getUUID("logistics");
	}
	
	public CompoundTag writeToNBT(CompoundTag nbt)
	{
		nbt.putInt("id", id);
		nbt.put("item", serverStack.save(new CompoundTag()));
		nbt.putByte("speed", speed);
		nbt.putByte("progress", progress);
		nbt.putBoolean("centering", centering);
		nbt.putByte("color", color);
		nbt.putByte("dir", currentDirection);
		nbt.putByte("tried", triedDirections);
		if(logistics != null) nbt.putUUID("logistics", logistics);
		return nbt;
	}
	
	@Override
	public void read(IInputBuffer buffer)
	{
		id = buffer.readVarInt();
		serverStack = ItemStack.EMPTY;
		clientStack = IItemCache.getCache().getItem(buffer.readVarInt());
		speed = buffer.readByte();
		progress = buffer.readByte();
		deserializeEssentials(buffer.readShort());
	}
	
	@Override
	public void write(IOutputBuffer buffer)
	{
		buffer.writeVarInt(id);
		buffer.writeVarInt(IItemCache.getCache().registerItem(serverStack));
		buffer.writeByte(speed);
		buffer.writeByte(progress);
		buffer.writeShort(serializeEssentials());
	}
	
	public short serializeEssentials()
	{
		return (short)((centering ? 1 : 0) << 14 | (color & 31) << 9 | (currentDirection & 7) << 6 | (triedDirections & 63));
	}
	
	public void deserializeEssentials(short data)
	{
		triedDirections = (byte)(data & 63);
		currentDirection = (byte)((data >> 6) & 7);
		color = (byte)((data >> 9) & 31);
		centering = (data >> 14) > 0;
	}
	
	public int getId()
	{
		return id;
	}
	
	public UUID getRequestId()
	{
		return logistics;
	}
	
	public boolean isCentering()
	{
		return centering;
	}
	
	public ItemStack getServerStack()
	{
		return serverStack;
	}
	
	public ItemStack getStack()
	{
		return clientStack != null ? clientStack.get() : serverStack;
	}
	
	public byte getProgress()
	{
		return progress;
	}
	
	public boolean isInvalid()
	{
		return invalid || (clientStack == null && serverStack.isEmpty());
	}
	
	public DyeColor getColor()
	{
		return color == 0 ? null : DyeColor.byId(color - 1);
	}
	
	public TransportedItem setColor(DyeColor color)
	{
		this.color = (byte)(color == null ? 0 : color.getId() + 1);
		return this;
	}
	
	public void setAttemptedDirections(DirectionList list)
	{
		this.triedDirections = (byte)list.getCode();
	}
	
	public TransportedItem setRequestId(UUID id)
	{
		this.logistics = id;
		return this;
	}
	
	public void invalidate()
	{
		invalid = true;
	}
	
	public boolean hasReachedCenter()
	{
		return centering && progress >= 0;
	}
	
	public boolean hasReachedEnd()
	{
		return !centering && progress >= 100;
	}
	
	public Direction getTransferDirection()
	{
		return Direction.from3DDataValue(currentDirection);
	}
	
	public Direction getTravelingDirection()
	{
		return isCentering() ? getTransferDirection().getOpposite() : getTransferDirection();
	}
	
	public DirectionList getAttemptedDirections()
	{
		return DirectionList.ofNumber(triedDirections);
	}
	
	public void clearDirections()
	{
		triedDirections = 0;
	}
	
	public void setCentering()
	{
		centering = true;
	}
	
	public void setInsertionDirection(Direction dir)
	{
		if(dir == null)
		{
			progress = 0;
			centering = false;
			return;
		}
		centering = true;
		progress = -100;
		currentDirection = (byte)dir.get3DDataValue();
		triedDirections |= 1 << dir.get3DDataValue();
	}
	
	public void setExportDirection(Direction dir)
	{
		centering = false;
		currentDirection = (byte)dir.get3DDataValue();
	}
	
	public void update()
	{
		progress = (byte)Math.min(centering ? 0 : 100, progress+speed);
		if(progress == lastProgress)
		{
			if(++timeOut > 4)
			{
				invalid = true;
				return;
			}
		}
		else
		{
			timeOut = 0;
		}
		lastProgress = progress;
	}
	
	public TransportedItem setStartSpeed(int speed)
	{
		this.speed = (byte)Mth.clamp(speed, MIN_SPEED, MAX_SPEED);
		return this;
	}
	
	public boolean updateSpeed(int newSpeed, int change)
	{
		if(newSpeed == speed || change == 0) return false;
		speed = (byte)Mth.clamp(newSpeed < speed ? speed-change : speed+change, MIN_SPEED, MAX_SPEED);
		return true;
	}
}
