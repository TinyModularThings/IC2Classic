package ic2.api.classic.crops;

import ic2.api.crops.ICropTile;

public interface IClassicCropTile extends ICropTile, ICropProvider
{
	/**
	 * Function to get the Farmland being used on the Crop
	 * so you know what it is.
	 * @return the Farmland. If null that means the sticks can't stay there.
	 */
	public IFarmland getFarmland();
}
