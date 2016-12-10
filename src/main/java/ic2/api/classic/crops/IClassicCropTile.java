package ic2.api.classic.crops;

import ic2.api.crops.ICropTile;
import net.minecraft.world.World;

public interface IClassicCropTile extends ICropTile, ICropProvider
{
	public IFarmland getFarmland();
}
