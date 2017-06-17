package ic2.api.classic.tile;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.Objects;

import ic2.api.info.ILocatable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public interface ITeleporterTarget extends ILocatable
{
	/**
	 * Function which type of Teleporter it is.
	 * Item: Sending Items
	 * Fluid: Sending Fluids
	 * Energy: Sending EU
	 * Entity: Sending Entities
	 * @return which type it is that it is sending
	 */
	public TeleportType getSendingType();
	
	/**
	 * Function which type it canReceive.
	 * @param type the type it can receive
	 * @return if it can receive
	 */
	public boolean canReceiveType(TeleportType type);
	
	/**
	 * The facing which the Teleporter is Facing
	 * the facing defines the target
	 * @return the facing of the teleporter. null is not allowed
	 */
	public EnumFacing getTeleporterFacing();
	
	/**
	 * function to set a target to the Teleporter
	 * @param dimensionID the Dimension of the Target
	 * @param targetPos the Position of the Target
	 */
	public void setTarget(TeleporterTarget target);
	
	/**
	 * Function to check if the target is in the Teleporter
	 * @param target, Position & World of the target
	 * @return if the target is already set in the telporter
	 */
	public boolean hasTarget(TeleporterTarget target);
	
	public static enum TeleportType
	{
		Item,
		Fluid,
		Energy,
		Entity,
		None;
	}
	
	/**
	 * 
	 * @author Speiger
	 * Name registry for Teleporters
	 * This includes only Teleporters which have a custom name.
	 * if a Teleporters has no custom name then remove yourself from here
	 * also this is a backup solution if your Teleporter can not implement IWorldNameable.
	 * Please use that interface instead of this. The interface is more reliable.
	 */
	public static final class TeleporterNameRegistry
	{
		private static final Map<TeleporterTarget, String> nameRegistry = new LinkedHashMap<TeleporterTarget, String>();
		
		public static void addTeleporter(TeleporterTarget target, String targetName)
		{
			nameRegistry.put(target, targetName);
		}
		
		public static String getName(TeleporterTarget target)
		{
			return nameRegistry.get(target);
		}
		
		public static void removeTeleporter(TeleporterTarget target)
		{
			nameRegistry.remove(target);
		}
		
		public static Map<TeleporterTarget, String> getMap()
		{
			return nameRegistry;
		}
	}
	
	/**
	 * 
	 * @author Speiger
	 * 
	 * 4D Vec helper class for the Teleporter API which allows easier created.
	 * the isCustom parameter is just there for a autorename feature for the teleporter.
	 * Basicly that is false by default and is for 1 reason. If you return that to true
	 * it will not take the default name from the TeleporterNameRegistry
	 * if that is false it takes it from that registry.
	 */
	public static class TeleporterTarget
	{
		private final BlockPos pos;
		private final int dim;
		private final int hashCode;
		public boolean isCustom = false;
		
		protected TeleporterTarget(TeleporterTarget target)
		{
			pos = target.pos;
			dim = target.dim;
			hashCode = target.hashCode;
		}
		
		public TeleporterTarget(NBTTagCompound nbt)
		{
			this(new BlockPos(nbt.getInteger("xPos"), nbt.getInteger("yPos"), nbt.getInteger("zPos")), nbt.getInteger("DimID"));
			isCustom = nbt.getBoolean("IsCustom");
		}
		
		public TeleporterTarget(ITeleporterTarget target)
		{
			this(target.getPosition(), target.getWorldObj());
		}
		
		public TeleporterTarget(BlockPos pos, World world)
		{
			this(pos, world.provider.getDimensionType().getId());
		}
		
		public TeleporterTarget(BlockPos pos, DimensionType dim)
		{
			this(pos, dim.getId());
		}
		
		public TeleporterTarget(BlockPos pos, int id)
		{
			this.pos = pos;
			this.dim = id;
			this.hashCode = Objects.hashCode(pos, id);
		}
		
		@Override
		public int hashCode()
		{
			return hashCode;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if(obj instanceof TeleporterTarget)
			{
				TeleporterTarget other = (TeleporterTarget)obj;
				return other.dim == dim && Objects.equal(other.pos, pos);
			}
			return false;
		}
		
		public NBTTagCompound writeToNBT()
		{
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("DimID", dim);
			nbt.setInteger("xPos", pos.getX());
			nbt.setInteger("yPos", pos.getY());
			nbt.setInteger("zPos", pos.getZ());
			nbt.setBoolean("IsCustom", isCustom);
			return nbt;
		}
		
		public BlockPos getPos()
		{
			return pos;
		}
		
		public int getDimID()
		{
			return dim;
		}
		
		public TileEntity getTile()
		{
			return getWorld().getTileEntity(pos);
		}
		
		public World getWorld()
		{
			return FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(dim);
		}
	}
}
