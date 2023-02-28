package ic2.api.events;

import java.util.Arrays;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.LevelEvent;

public class RetextureEvent extends LevelEvent
{
	BlockPos pos;
	Direction side;
	Player applyingPlayer;
	
	TextureContainer container;
	
	boolean applied = false;
	
	public RetextureEvent(LevelAccessor world, BlockPos pos, Direction side, Player applyingPlayer, TextureContainer container)
	{
		super(world);
		this.pos = pos;
		this.side = side;
		this.applyingPlayer = applyingPlayer;
		this.container = container;
	}

	public BlockPos getPos()
	{
		return pos;
	}
	
	public Direction getSide()
	{
		return side;
	}
	
	public BlockState getBlockState()
	{
		return getLevel().getBlockState(pos);
	}
	
	public BlockEntity getBlockEntity()
	{
		return getLevel().getBlockEntity(pos);
	}
	
	public Player getApplyingPlayer()
	{
		return applyingPlayer;
	}
	
	public TextureContainer getContainer()
	{
		return container;
	}
	
	public boolean isApplied()
	{
		return applied;
	}
	
	public void setApplied(boolean apply)
	{
		applied = apply;
	}
	
	public static class TextureContainer
	{
		BlockState state;
		Direction side;
		Rotation[] rotations;
		int[] colors;
		
		public TextureContainer(CompoundTag nbt)
		{
			this(NbtUtils.readBlockState(nbt.getCompound("block")), Direction.from3DDataValue(nbt.getInt("side")), decode(nbt.getByteArray("rotations")), nbt.getIntArray("colors"));
		}
		
		public TextureContainer(BlockState state, Direction side, Rotation[] rotations, int[] colors)
		{
			this.state = state;
			this.side = side;
			this.rotations = rotations;
			this.colors = colors;
		}
		
		public BlockState getState()
		{
			return state;
		}
		
		public Direction getSide()
		{
			return side;
		}
		
		public int[] getColors()
		{
			return colors;
		}
		
		public Rotation[] getRotations()
		{
			return rotations;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if(obj instanceof TextureContainer)
			{
				TextureContainer other = (TextureContainer)obj;
				return other.state.equals(state) && other.side == side && Arrays.equals(other.colors, colors);
			}
			return false;
		}
		
		public CompoundTag save()
		{
			CompoundTag nbt = new CompoundTag();
			nbt.put("block", NbtUtils.writeBlockState(state));
			nbt.putByte("side", (byte)side.get3DDataValue());
			nbt.putByteArray("rotations", encode(rotations));
			nbt.putIntArray("colors", colors);
			return nbt.copy();//Ensure no corruption on temp changes
		}
		
		private static Rotation[] decode(byte[] data)
		{
			Rotation[] rotation = new Rotation[data.length];
			for(int i = 0;i<data.length;i++)
			{
				rotation[i] = Rotation.byIndex(data[i]);
			}
			return rotation;
		}
		
		private static byte[] encode(Rotation[] rotations)
		{
			byte[] data = new byte[rotations.length];
			for(int i = 0;i<data.length;i++)
			{
				data[i] = (byte)rotations[i].getIndex();
			}
			return data;
		}
	}
	
	public enum Rotation
	{
		ROTATION_0(0),
		ROTATION_90(1),
		ROTATION_180(2),
		ROTATION_270(3);
		
		public static final Rotation[] ROTATIONS;
		
		int rotation;

		Rotation(int rotation)
		{
			this.rotation = rotation;
		}
		
		public static Rotation byIndex(int index)
		{
			return ROTATIONS[index % ROTATIONS.length];
		}
		
		public Rotation getNext()
		{
			return byIndex(rotation + 1);
		}
		
		public int getIndex()
		{
			return rotation;
		}
		
		public int getRotation()
		{
			return rotation * 90;
		}
		
		static
		{
			Rotation[] values = values();
			ROTATIONS = new Rotation[values.length];
			for(Rotation rotation : values)
			{
				ROTATIONS[rotation.getIndex()] = rotation;
			}
		}
	}
}
