package ic2.api.blocks;

import ic2.api.items.readers.IWrenchTool;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class WrenchHelper
{
	/**
	 * Helper function to detect if a player holds a Wrench in his hand for the overlay
	 * @param player who might hold a wrench
	 * @return true if it holds a wrench that wants to show the overlay
	 */
	public static boolean hasWrench(Player player)
	{
		return isWrench(player.getMainHandItem()) || isWrench(player.getOffhandItem());
	}
	
	/**
	 * Function to detect if the currently held wrench wants to render the wrench overlay
	 * @param stack that may or not contain a valid wrench
	 * @return true if the held item is a wrench that should show the overlay
	 */
	public static boolean isWrench(ItemStack stack)
	{
		return stack.getItem() instanceof IWrenchTool tool && tool.shouldRenderOverlay(stack);
	}
	
	/**
	 * Helper function that gets the correct side of the overlay.
	 * This is function is relative to the facing you are looking at
	 * 0 -> Nothing
	 * 1 -> Center
	 * 2 -> Positive Z
	 * 4 -> Negative Z
	 * 8 -> Negative X
	 * 16 -> Positive X
	 * @param result the BlockHit result itself, which provides info about the player itself to know how he is looking
	 * @return result of the index
	 */
	public static int getDirectionIndex(BlockHitResult result)
	{
		return getDirectionIndex(result.getDirection(), result.getLocation().subtract(Vec3.atLowerCornerOf(result.getBlockPos())));
	}
	
	/**
	 * Helper function that gets the correct side of the overlay.
	 * This is function is relative to the facing you are looking at
	 * 0 -> Nothing
	 * 1 -> Center
	 * 2 -> Positive Z
	 * 4 -> Negative Z
	 * 8 -> Negative X
	 * 16 -> Positive X
	 * @param context the UseContext result itself, which provides info about the player itself to know how he is looking
	 * @return result of the index
	 */
	public static int getDirectionIndex(UseOnContext context)
	{
		return getDirectionIndex(context.getClickedFace(), context.getClickLocation().subtract(Vec3.atLowerCornerOf(context.getClickedPos())));
	}
	
	/**
	 * Helper function that gets the correct side of the overlay.
	 * This is function is relative to the facing you are looking at
	 * 0 -> Nothing
	 * 1 -> Center
	 * 2 -> Positive Z
	 * 4 -> Negative Z
	 * 8 -> Negative X
	 * 16 -> Positive X
	 * @param direction which side is looked at.
	 * @param sideHit is the exact location on the block that is clicked on. Range 0-1
	 * @return result of the index
	 */
	public static int getDirectionIndex(Direction direction, Vec3 sideHit)
	{
		switch(direction.getAxis())
		{
			case X: return calculateIndex(direction == Direction.EAST ? 1D-sideHit.z() : sideHit.z(), 1D-sideHit.y());
			case Y: return calculateIndex(sideHit.x(), direction == Direction.DOWN ? 1D-sideHit.z() : sideHit.z());
			case Z: return calculateIndex(direction == Direction.NORTH ? 1D-sideHit.x() : sideHit.x(), 1D-sideHit.y());
			default: return 0;
		}
	}
	
	/**
	 * Function that translates the {@link WrenchHelper#getDirectionIndex(Direction, Vec3)} into an actual Direction to be set.
	 * @param base the direction the block is looked at from
	 * @param index which the player is looking at.
	 * @param player the player itself, which is only for the centerpiece so "inverted" can be used. (So if the result is != 1 player can be null)
	 * @return the Direction the Block would be set at, in an Impossible case it will return null.
	 */
	public static Direction getFacingFromIndex(Direction base, int index, Player player)
	{
		if(base.getAxis().isHorizontal())
		{
			return switch (index) {
				case 1 -> player.isShiftKeyDown() ? base.getOpposite() : base;
				case 2 -> Direction.DOWN;
				case 4 -> Direction.UP;
				case 8 -> base.getClockWise();
				case 16 -> base.getCounterClockWise();
				default -> null;
			};
		}
		return switch (index) {
			case 1 -> player.isShiftKeyDown() ? base.getOpposite() : base;
			case 2 -> base == Direction.DOWN ? Direction.NORTH : Direction.SOUTH;
			case 4 -> base == Direction.DOWN ? Direction.SOUTH : Direction.NORTH;
			case 8 -> Direction.WEST;
			case 16 -> Direction.EAST;
			default -> null;
		};
	}
	
	private static int calculateIndex(double x, double z)
	{
		if(x >= 0.2D && x <= 0.8D && z >= 0.2D && z <= 0.8D) return 1;
		if((x < 0.2D || x > 0.8D) && ((z >= 0.2D && z <= 0.8D) || (x > 0.8D && (z > 0.5 ? z <= x : z >= 1-x)) || (x < 0.2D && (z > 0.5 ? z <= 1-x : z >= x))))
		{
			return x < 0.5 ? 8 : 16;
		}
		if((z < 0.2D || z > 0.8D) && ((x >= 0.2D && x <= 0.8D) || (z > 0.8D && (x > 0.5 ? x <= z : x >= 1-z)) || (z < 0.2D && (x > 0.5 ? x <= 1-z : x >= z))))
		{
			return z < 0.5 ? 4 : 2;
		}
		return 0;
	}
}