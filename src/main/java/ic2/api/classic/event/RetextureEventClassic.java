package ic2.api.classic.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class RetextureEventClassic extends WorldEvent
{
	/**
	 * Classic has his own RetextureEvent simply because
	 * IC2 Experimental's RetextureEvent can not be supported.
	 * 
	 * When something is rendered it uses a ModelState to get a model
	 * and then applying the ExtendetState for TileEntityData.
	 * The original Texture Event supports only 1 BlockState
	 * The Reference String that is send over is simply
	 * the BlockState as String. I assume to load/Save the BlockState Easy
	 * But it is a unnecessary Parameter.
	 */
	
	//Target Stuff
	private final BlockPos targetPos; //Pos of the Target Block
	private final EnumFacing targetSide; //Side of the Target Block
	private final EntityPlayer applingPlayer; //The person who fires the event Can Be null
	
	//Stuff to be added
	private final EnumFacing refSide;   // Side of the Block that want to be copied
	private final IBlockState modelState; //The state you get the model from
	private final IBlockState renderState; //The state you use for getting the BakedQuads (Supporting TileEntity Data)
	private final int[] colorMultipliers; //ColorMultipliers of the Block that got copied
	private final Rotation[] textureRotations; // The rotations of each texture layer
	
	boolean isApplied;
	
	public RetextureEventClassic(World world, BlockPos pos, EnumFacing dir, EntityPlayer player, EnumFacing side, IBlockState model, IBlockState render, int[] colors, Rotation[] rotations)
	{
		super(world);
		if(world == null || world.isRemote || pos == null || dir == null || side == null || model == null || render == null || colors == null || rotations == null || colors.length != rotations.length) setCanceled(true);
		
		targetPos = pos;
		targetSide = dir;
		applingPlayer = player;
		refSide = side;
		modelState = model;
		renderState = render;
		colorMultipliers = colors;
		textureRotations = rotations;
	}
	
	public BlockPos getTargetPos()
	{
		return targetPos;
	}
	
	public IBlockState getTargetState()
	{
		return getWorld().getBlockState(targetPos);
	}
	
	public TileEntity getTargetTile()
	{
		return getWorld().getTileEntity(getTargetPos());
	}
	
	public EnumFacing getTargetSide()
	{
		return targetSide;
	}
	
	public EntityPlayer getApplingPlayer()
	{
		return applingPlayer;
	}
	
	
	public EnumFacing getRefSide()
	{
		return refSide;
	}
	
	public IBlockState getModelState()
	{
		return modelState;
	}
	
	public IBlockState getRenderState()
	{
		return renderState;
	}
	
	public int[] getColorMultipliers()
	{
		return colorMultipliers;
	}
	
	public Rotation[] getRotations()
	{
		return textureRotations;
	}
	
	public boolean isApplied()
	{
		return isApplied;
	}
	
	public void setApplied(boolean apply)
	{
		isApplied = apply;
	}
	
	public static enum Rotation
	{
		Rotation0(0),
		Rotation90(90),
		Rotation180(180),
		Rotation270(270);
		
		int rotation;
		
		private Rotation(int facing)
		{
			rotation = facing;
		}
		
		public int getRotation()
		{
			return rotation;
		}
		
		public static Rotation getRotation(byte index)
		{
			Rotation[] values = values();
			if(values.length > index)
			{
				return values[index];
			}
			return values[0];
		}
		
		public static Rotation[] convert(byte[] array)
		{
			Rotation[] result = new Rotation[array.length];
			Rotation[] values = values();
			for(int i = 0;i<array.length;i++)
			{
				result[i] = values[array[i]];
			}
			return result;
		}
		
		public static byte[] invertConverting(Rotation[] array)
		{
			byte[] result = new byte[array.length];
			for(int i = 0;i<array.length;i++)
			{
				result[i] = (byte)array[i].ordinal();
			}
			return result;
		}
	}
}
