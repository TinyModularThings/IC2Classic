package ic2.api.blocks;

import java.util.List;
import java.util.Map;

import ic2.api.blocks.wrench.BaseWrenchHandler;
import ic2.api.blocks.wrench.ChestWrenchHandler;
import ic2.api.blocks.wrench.DispenserWrenchHandler;
import ic2.api.blocks.wrench.HopperWrenchHandler;
import ic2.api.blocks.wrench.HorizontalWrenchHandler;
import ic2.api.blocks.wrench.InvertedHorizontalWrenchHandler;
import ic2.api.blocks.wrench.ObserverBlockWrenchHandler;
import ic2.api.blocks.wrench.PillarWrenchHandler;
import ic2.api.blocks.wrench.PistonWrenchHandler;
import ic2.api.blocks.wrench.StairWrenchHandler;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public interface IWrenchable
{
	/**
	 * Returns the current facing of the Block. If this returns null then the wrench does not try to rotate things but the Overlay still checks for it.
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @return the current facing of the Block or Null
	 */
	Direction getFacing(BlockState state, Level world, BlockPos pos);
	
	/**
	 * Returns if the facing could be set. This is only called through the Wrench Overlay but can be called by any means too, but isn't by ic2c by default
	 * Treat it as it should be vital in its implementation
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @param side the side that is tested for
	 * @return true if the facing can be set or false if it can not
	 */
	boolean canSetFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side);
	
	/**
	 * Used by the wrench to set the facing. It does not check if it can. You have to test if the facing is valid.
	 * If it is not valid return false otherwise do your actions and return true
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @param side that the block should be set to
	 * @return true if it was successful and the Block was modified
	 */
	boolean setFacing(BlockState state, Level world, BlockPos pos, Player player, Direction side);
	
	/**
	 * If your wrench has a special action that does not fit the normal setfacing/remove scheme then this is your function
	 * it does not test if it can do something it just tries to do something
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @param dir the side the block is clicked from
	 * @param hit the exact pixel position where it was hit from
	 * @return true if you did something and false if you didn't
	 */
	boolean doSpecialAction(BlockState state, Level world, BlockPos pos, Direction dir, Player player, Vec3 hit);
	
	/**
	 * This is an Overlay only function but could "maybe" be used for other things so treat it as slightly important on client and server
	 * This function purely exists to give an overlay of what is going to be modified. For example: Anchors removed from a Cable/Tube
	 * It just shows the user what is being done
	 * The AxisAlignedBB is only in BlockSpace that means you are returning a box within 0-1 range.
	 * You can exceed it if you want, and it will be rendered, but you have to deal with the alignment, etc.
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @param dir the side the block is clicked from
	 * @param hit the exact pixel position where it was hit from
	 * @return null if no action would be done otherwise return the hitbox that would be affected by it
	 */
	AABB hasSpecialAction(BlockState state, Level world, BlockPos pos, Direction dir, Player player, Vec3 hit);
	
	/**
	 * Function that tests if Block can be removed by a wrench. If not then it will not try to do so.
	 * This is also used by the wrench overlay
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @return true if the block can be removed by the player with its wrench
	 */
	boolean canRemoveBlock(BlockState state, Level world, BlockPos pos, Player player);
	
	/**
	 * Function that determines how likely it is that a block is wrenched successfully and drops all its contents.
	 * The expected value range is between 0-1 which 0 is not dropping stuff and 1 dropping everything.
	 * Read {@link #getDrops(BlockState, Level, BlockPos, Player)}
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @return the likelyhood of successful wrench drop
	 */
	double getDropRate(BlockState state, Level world, BlockPos pos, Player player);
	
	/**
	 * Function that gets the successful drops of the Block. If it was not successful then it would call the normal Block.getDrops function
	 * This function takes {@link #getDropRate(BlockState, Level, BlockPos, Player)} into account.
	 * But modifiers like "Lossless mode" or "Luck increasing modifiers" are applied too before it is decided if this function is called or the failure one
	 * @param state current BlockState
	 * @param world the World the BlockState is in
	 * @param pos The position the BlockState is at
	 * @param player the player who attempts the action
	 * @return list of drops that would happen if the block was wrenched successfully
	 */
	List<ItemStack> getDrops(BlockState state, Level world, BlockPos pos, Player player);
	
	/**
	 * 
	 * @author Speiger
	 * 
	 * Registry for custom Wrenchable Blocks that can not implement the said interface.
	 * So custom support can be easily added.
	 * {@link BaseWrenchHandler} is the default implementation and various implementations can be found in the same directory.
	 * This can be also used to override existing blocks that implement the interface directly.
	 */
	public static final class WrenchRegistry
	{
		public static final WrenchRegistry INSTANCE = new WrenchRegistry();
		Map<Block, IWrenchable> wrenches = new Object2ObjectOpenHashMap<>();
		
		public void init()
		{
			registerWrenchHandler(PistonWrenchHandler.INSTANCE, Blocks.PISTON, Blocks.STICKY_PISTON);
			registerWrenchHandler(ObserverBlockWrenchHandler.INSTANCE, Blocks.OBSERVER);
			registerWrenchHandler(ChestWrenchHandler.INSTANCE, Blocks.CHEST, Blocks.ENDER_CHEST, Blocks.TRAPPED_CHEST);
			registerWrenchHandler(HorizontalWrenchHandler.INSTANCE, Blocks.FURNACE, Blocks.BLAST_FURNACE, Blocks.SMOKER);
			registerWrenchHandler(InvertedHorizontalWrenchHandler.INSTANCE, Blocks.REPEATER, Blocks.COMPARATOR);
			registerWrenchHandler(PillarWrenchHandler.INSTANCE, 
					Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG, Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG,
					Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_SPRUCE_LOG
			);
			registerWrenchHandler(DispenserWrenchHandler.INSTANCE, Blocks.DISPENSER, Blocks.DROPPER);
			registerWrenchHandler(HopperWrenchHandler.INSTANCE, Blocks.HOPPER);
			for(Block block : ForgeRegistries.BLOCKS)
			{
				if(block instanceof StairBlock)
				{
					registerWrenchHandler(StairWrenchHandler.INSTANCE, block);
				}
				else if(block instanceof GlazedTerracottaBlock)
				{
					registerWrenchHandler(InvertedHorizontalWrenchHandler.INSTANCE, block);
				}
			}
		}
		
		/**
		 * Function to register Blocks with custom Wrench-handlers
		 * @param wrench the handler for the blocks
		 * @param blocks that should apply for this Wrench-handlers
		 */
		public void registerWrenchHandler(IWrenchable wrench, Block...blocks)
		{
			for(Block block : blocks)
			{
				wrenches.put(block, wrench);
			}
		}
		
		/**
		 * Returns the wrench handler for the state provided
		 * @param state that the Wrench-handler is requested for.
		 * @return either null or the Wrench-handler of said state
		 */
		public IWrenchable getWrenchable(BlockState state)
		{
			IWrenchable wrench = wrenches.get(state.getBlock());
			return wrench != null ? wrench : (state.getBlock() instanceof IWrenchable result ? result : null);
		}
	}
}