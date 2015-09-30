package ic2.api.event;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.Cancelable;

public class FoamEvent extends WorldEvent
{
	public int x;
	public int y;
	public int z;
	
	public FoamEvent(World world, int xCoord, int yCoord, int zCoord)
	{
		super(world);
		x = xCoord;
		y = yCoord;
		z = zCoord;
	}
	/**
	 * 
	 * @author Speiger
	 * This Event is checking if your block can be foamed
	 * You have to cancle it if you want to foam your block.
	 * Else you create a big mess!
	 */
	@Cancelable
	public static class Check extends FoamEvent
	{
		public Check(World world, int xCoord, int yCoord, int zCoord)
		{
			super(world, xCoord, yCoord, zCoord);
		}
	}
	/**
	 * 
	 * @author Speiger
	 * You have to cancel it to preven that your block get Overriden.
	 * So After this Event is get called and this event is not Canceled
	 * It call the setBlock Command
	 */
	@Cancelable
	public static class Foam extends FoamEvent
	{
		public Foam(World world, int xCoord, int yCoord, int zCoord)
		{
			super(world, xCoord, yCoord, zCoord);
		}
		
	}
	
}
