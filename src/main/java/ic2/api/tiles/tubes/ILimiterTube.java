package ic2.api.tiles.tubes;

import java.util.EnumSet;

import net.minecraft.world.item.DyeColor;

public interface ILimiterTube extends ITube
{
	public EnumSet<DyeColor> getValidColors();
}
