package ic2.api.classic.tile;

import java.util.List;

import net.minecraft.util.text.translation.I18n;

/**
 * 
 * @author Speiger
 * 
 * Waila & the One Probe Info class
 * It delivers the info to these mods.
 * Note TOP is going to have a separate class
 * which will be not in the API. its a core class
 * Else the API will rely on the one Probe.
 */
public interface IInfoTile
{
	/**
	 * @return List of Info Components.
	 */
	public List<InfoComponent> getComponents();
	
	public static interface InfoComponent
	{
		/**
		 * @return displayText that is shown in Waila & The One Probe
		 */
		public String getDisplayText();
		
		/**
		 * What type of info it is.
		 * used for Waila & The One Probe
		 */
		public InfoType getType();
	}
	
	public static enum InfoType
	{
		Fuel("ic2.fuel"),
		EUStorage("ic2.euStorage"),
		EUUsage("ic2.euUsage"),
		EUProduction("ic2.euProduction"),
		EUTier("ic2.euTier"),
		CableData("ic2.cableData"),
		ReceiveData("ic2.receiveData"),
		EmitData("ic2.emitData"),
		MachineProgress("ic2.machineProg"),
		Custom("ic2.custom");
		
		String configID;
		
		private InfoType(String id)
		{
			configID = id;
		}
		
		public String getConfigID()
		{
			return configID;
		}
		
		public String getName()
		{
			return I18n.translateToLocal(configID+".name");
		}
	}
}
