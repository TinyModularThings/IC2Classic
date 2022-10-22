package ic2.api.tiles.display;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface IMonitorRenderer
{
	public MultiBufferSource.BufferSource getBatcher();
	public Font getFont();
	public ItemRenderer getItemRenderer();
	public void renderGuiItems(PoseStack matrix, ItemStack stack, float x, float y);
	public default void renderGuiItemText(PoseStack matrixstack, ItemStack stack, float x, float y, String text) {
		renderGuiItemText(matrixstack, getFont(), stack, x, y, text);
	}
	public void renderGuiItemText(PoseStack matrixstack, Font font, ItemStack stack, float x, float y, String text);
}