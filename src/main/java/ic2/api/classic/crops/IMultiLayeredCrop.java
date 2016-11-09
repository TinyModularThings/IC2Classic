package ic2.api.classic.crops;

import java.util.List;

import net.minecraft.util.ResourceLocation;

public interface IMultiLayeredCrop
{
	/**
	 * Function to allow you Layered Textures
	 * It will completely replace the getModelLocaltion function of the CropCard.
	 * Note: It will start at stage 1 and end at the getMaxSize()
	 * @param stage The Stage that the Crop is in (growth stage)
	 * @return the list of Textures (order is supported)
	 */
	public List<ResourceLocation> getLayerModelLocation(int stage);
}
