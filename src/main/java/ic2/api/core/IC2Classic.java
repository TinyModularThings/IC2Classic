package ic2.api.core;

import ic2.api.items.armor.IArmorModule.IArmorCapability;
import ic2.api.tiles.INotifiableMachine;
import ic2.api.tiles.tubes.ITube;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.fml.ModLoadingContext;

public class IC2Classic
{
    public static final Capability<ITube> TUBE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<INotifiableMachine> NOTIFY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IArmorCapability> ARMOR_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    
    private static APIHelper HELPER;
    
    public static APIHelper getHelper()
	{
		return HELPER;
	}
    
    public static void setHelper(APIHelper helper)
	{
    	if(!ModLoadingContext.get().getActiveNamespace().equalsIgnoreCase("ic2") || HELPER != null)
    	{
    		throw new IllegalStateException("IC2 is the only mod that can set these variables");
    	}
		HELPER = helper;
	}
}
