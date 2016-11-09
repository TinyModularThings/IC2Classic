package ic2.api.classic.crops;

import ic2.api.crops.ICropTile;

public interface IClassicCropTile extends ICropTile, ICropProvider
{
	public IFarmland getFarmland();
}
