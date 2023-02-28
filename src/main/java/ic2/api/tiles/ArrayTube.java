package ic2.api.tiles;

import java.util.List;

import ic2.api.tiles.tubes.ITube;
import ic2.api.tiles.tubes.TransportedItem;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public class ArrayTube implements ITube
{
	ITube[] array;
	int index;
	boolean loop;
	
	public ArrayTube(List<ITube> tubes)
	{
		this(tubes.toArray(new ITube[tubes.size()]));
	}
	
	public ArrayTube(ITube[] array)
	{
		this(array, true);
	}
	
	public ArrayTube(ITube[] array, boolean loop)
	{
		this.array = array;
		this.loop = loop;
	}
	
	@Override
	public TubeType getTubeType()
	{
		return TubeType.SIMPLE;
	}
	
	@Override
	public void addItem(ItemStack item, Direction side, DyeColor color)
	{
		index = loop ? ++index % array.length : 0;
		array[index].addItem(item, side, color);
	}
	
	@Override
	public void addItem(TransportedItem item, Direction side)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean canAddItem(TransportedItem item, Direction side)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean canConnect(ITube other, Direction dir)
	{
		throw new UnsupportedOperationException();
	}
}
