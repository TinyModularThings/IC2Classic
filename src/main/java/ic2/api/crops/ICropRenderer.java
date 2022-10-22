package ic2.api.crops;

import java.util.List;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * 
 * @author Speiger
 *
 * A Interface to register a Custom Crop Renderer for your own Crop.
 * The function is called when the models are being baked.
 * You need to take care of the loading of the Textures yourself.
 */
public interface ICropRenderer
{
	/**
	 * Function to bake all required quads for a Crop Growth Stage.
	 * @param stage the crop is right now in. 0-MaxGrowthStage
	 * @param fancy if your crops are really FPS lagging only load if so requested. If not ignore this.
	 * @param baker helper to a existing face bakery.
	 * @return a List of quads that can be used for the crop sticks.
	 */
	@OnlyIn(Dist.CLIENT)
	List<BakedQuad> createQuadsForStage(int stage, boolean fancy, FaceBakery baker);
}
