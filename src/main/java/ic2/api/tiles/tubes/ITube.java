package ic2.api.tiles.tubes;

import ic2.api.util.ILocation;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public interface ITube extends ILocation
{
	default void addItem(ItemStack item, Direction side) { addItem(item, side, null); }
	void addItem(ItemStack item, Direction side, DyeColor color);
	void addItem(TransportedItem item, Direction side);
	boolean canAddItem(TransportedItem item, Direction side);
	
	boolean canConnect(ITube other, Direction dir);
	TubeType getTubeType();
	
	enum TubeType
	{
		SIMPLE,
		EXTRACTION
	}
}
