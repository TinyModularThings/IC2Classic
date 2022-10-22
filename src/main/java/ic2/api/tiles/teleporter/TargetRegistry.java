package ic2.api.tiles.teleporter;

import java.util.Map;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;

public class TargetRegistry
{
	public static final TargetRegistry INSTANCE = new TargetRegistry();
	Map<TeleporterTarget, String> names = new Object2ObjectLinkedOpenHashMap<>();
	
	public void addTarget(TeleporterTarget target, String name)
	{
		names.put(target, name);
	}
	
	public void removeTarget(TeleporterTarget target)
	{
		names.remove(target);
	}
	
	public String getTargetName(TeleporterTarget target)
	{
		return names.get(target);
	}
}
