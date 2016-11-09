package ic2.api.classic.event;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class FoamEvent extends WorldEvent
{
	final BlockPos pos;
	
	public FoamEvent(World world, BlockPos pos)
	{
		super(world);
		this.pos = pos;
	}
	
	public BlockPos getPos()
	{
		return pos;
	}
	
	public IBlockState getBlockState()
	{
		return getWorld().getBlockState(getPos());
	}
	
	public Block getBlock()
	{
		return getBlockState().getBlock();
	}
	
	public TileEntity getTileEntity()
	{
		return getWorld().getTileEntity(getPos());
	}
	
	
	/**
	 * 
	 * @author Speiger
	 * Custom Foam Event to allow you to apply Foam
	 * If you want to apply a custom Foaming on this position
	 * you can do that.
	 * Cancel the event prevents the foaming. No matter if it has
	 * already a target set.
	 * Also always check if the event has already a target
	 */
	@Cancelable
	public static class Check extends FoamEvent
	{
		FoamResult foamResult = FoamResult.Any;
		boolean hasCustomTarget;
		
		public Check(World world, BlockPos pos)
		{
			super(world, pos);
		}
		
		public void setResult(FoamResult result)
		{
			foamResult = result;
			hasCustomTarget = true;
		}
		
		public FoamResult getFoamResult()
		{
			return hasCustomTarget ? foamResult : FoamResult.Any;
		}
		
		public void setHasTarget(boolean par1)
		{
			hasCustomTarget = par1;
		}
		
		public boolean hasCustomTarget()
		{
			return hasCustomTarget;
		}
	}
	
	
	/**
	 * 
	 * @author Speiger
	 * Placement call. Apply your foam here
	 * and Cancel it when you are done
	 * Else it will set the Foam Block
	 * If you only making a custom mode but still only
	 * use the API enable foam place request and
	 * the Sprayer will place the foam Block For you.
	 * if(!event.isCanceld() || event.hasFoamPlaceRequest())
	 */
	@Cancelable
	public static class Place extends FoamEvent
	{
		boolean placeFoam = false;
		
		public Place(World world, BlockPos pos)
		{
			super(world, pos);
		}
		
		public void requestFoamPlacement()
		{
			placeFoam = true;
		}
		
		public boolean hasFoamPlaceRequest()
		{
			return placeFoam;
		}
	}
	
	public static enum FoamResult
	{
		Any,
		Scaffold,
		Cable,
		Custom;
	}
	
}
