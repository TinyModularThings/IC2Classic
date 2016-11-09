package ic2.api.classic.energy;

import ic2.api.classic.energy.render.IRenderValidator;
import ic2.api.energy.IEnergyNet;
import ic2.api.energy.tile.IEnergyTile;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPacketEnergyNet extends IEnergyNet
{
	/**
	 * Function to receive the Packet Infos for a EnergyTile for 1 Tick.
	 * @param tile The Tile you want to scan.
	 * @param type What packet information you want
	 * @return list of packet information. Can be empty
	 */
	public List<PacketStat> getPackets(IEnergyTile tile, PacketType type);	

	/**
	 * Function to receive the Packet Infos for a EnergyTile for All ticks since it was loaded
	 * @param tile The Tile you want to scan.
	 * @param type What packet information you want
	 * @return list of packet information. Can be empty
	 */
	public List<PacketStat> getTotalPackets(IEnergyTile tile, PacketType type);
	
	/**
	 * Function to send a rendering packet to the client. It will show all paths bound to this packet
	 * This will get sended to all Players.
	 * @param world The World it is in.
	 * @param pos The Position the of the Tile
	 * @param validator The validator that handles the rendering
	 * @return If it worked, failed or nothing did happen at all
	 */
	public EnumActionResult drawTile(World world, BlockPos pos, IRenderValidator validator);
	
	/**
	 * Ranged function to send a rendering packet to the client.
	 * Difference to the other function is: Its sending only to 1 Player
	 * and it draws all paths that are in a Area of Effect that you define here.
	 * @param world World the Rendering should happen
	 * @param min The min Position of the AOE
	 * @param max The Max Position of the AOE
	 * @param player The player that should receive the render event
	 * @param validator The Validator for the rendering.
	 * @return If it worked, failed or nothing did happen at all
	 */
	public EnumActionResult drawAreaInTiles(World world, BlockPos min, BlockPos max, EntityPlayer player, IRenderValidator validator);
	
	public static enum PacketType
	{
		Sended,
		Received,
		Both;
	}
}
