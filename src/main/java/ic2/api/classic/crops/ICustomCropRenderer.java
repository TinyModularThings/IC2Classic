package ic2.api.classic.crops;

import java.util.List;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author Speiger
 * 
 * Custom Renderer class. 2 Ways to use it:
 * 1: Implement it to the CropCard class that you use.
 * 2: Register this renderer with a CropCard to the ClassicCrops Registry.
 * Note: 2 overrides 1
 */
public interface ICustomCropRenderer
{
	/**
	 * Function to create custom renders to the Crop
	 * So if you want to make custom rendering (Crop sticks not included)
	 * go ahead.
	 * @param stage which CropStage the Crop is in
	 * @param fancy if its requesting the Fancy or Fast renderer. Note: Both have to be supported!
	 * Else it get skipped. And it loads the Standard Renderer.
	 * @param bake Helper function so you do not have to overcreate this class
	 * @return the list of Quads. Null not supported. Same as Empty lists...
	 */
	@SideOnly(Side.CLIENT)
	public List<BakedQuad> createQuads(int stage, boolean fancy, FaceBakery bake);
}
