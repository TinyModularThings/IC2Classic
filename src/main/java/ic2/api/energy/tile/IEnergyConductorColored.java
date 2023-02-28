package ic2.api.energy.tile;

import net.minecraft.world.item.DyeColor;

public interface IEnergyConductorColored extends IEnergyConductor
{
	DyeColor getColor();
}
