package ic2.api.classic.energy.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author Speiger
 * This class requires a Empty Constructor else the client will crash.
 * 
 */
public interface IRenderValidator 
{
	/**
	 * Function if the Renderer is Valid on the Player.
	 */
	public boolean isValid(EntityPlayer player);
	
	/**
	 * All Data you want to have from the server & send to client
	 * Once its on a client you no longer can update it
	 */
	public void writeServerData(NBTTagCompound nbt);
	
	/**
	 * All data you sended from Server.
	 */
	public void readClientData(NBTTagCompound nbt);
	
	/**
	 * @return the Speed that the Renderer should move. Default: 0.02
	 */
	public double getFlowSpeed();
	
	/**
	 * Function to define if you want SourceToSink or SinkToSource
	 * @return
	 */
	public boolean isReverse();
	
	/**
	 * Function that will ask you if you still can render this entry
	 * If false it will be disabled.
	 * @param player That it gets rendered on
	 * @param entry The Entry that gets rendered right now
	 * @return if it can render
	 */
	public boolean canRender(EntityPlayer player, IRenderEntry entry);
	
	/**
	 * Function that will be called whenever the Renderer is done.
	 * You have exactly 1 Tick to do what you want if the RenderPath is not
	 * reseted or modified it will be called invalid and disabled
	 * @param player That its get Rendered on
	 * @param path A list of all paths that can get rendered
	 */
	public void onRenderFinished(EntityPlayer player, IRenderPath path);
}
