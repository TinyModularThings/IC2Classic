package ic2.api.tiles;

import java.util.EnumSet;

import ic2.api.items.IUpgradeItem.UpgradeType;
import ic2.api.tiles.tubes.ITube;
import ic2.api.util.ILocation;
import net.minecraft.core.Direction;
import net.minecraftforge.items.IItemHandler;

public interface IMachine extends ILocation, IInputMachine
{
	EnumSet<UpgradeType> getSupportedUpgradeTypes();
	void onUpgradesChanged();
	
	int getAvailableEnergy();
	boolean useEnergy(int toUse, boolean doUse);
	
	boolean isMachineWorking();
	
	void setRedstoneSensitive(boolean flag);	
	boolean isRedstoneSensitive();
	
	IItemHandler getConnectedInventory(Direction dir);
	ITube getConnectedTube(Direction dir);
}
