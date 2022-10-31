package ic2.api.tiles.display.impl;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import ic2.api.tiles.display.IDisplayInfo;
import ic2.api.tiles.display.IMonitorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ProgressDisplayInfo implements IDisplayInfo
{
	Progress progress;
	Supplier<Progress> progressProvider;
	BooleanSupplier aliveProvider;
	
	public ProgressDisplayInfo(FriendlyByteBuf buffer)
	{
		progress = new Progress(buffer);
	}
	
	public ProgressDisplayInfo(Supplier<Progress> progressProvider, BooleanSupplier aliveProvider)
	{
		this.progressProvider = progressProvider;
		this.aliveProvider = aliveProvider;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void render(PoseStack stack, int x, int y, int width, int height, Alignment align, IMonitorRenderer helper)
	{
		Font font = helper.getFont();
		if(height <= font.lineHeight+2) return;
		Tesselator tes = Tesselator.getInstance();
		BufferBuilder builder = tes.getBuilder();
		builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		int fontHeight = Math.min(font.lineHeight+2, height);
		int color = progress.mainColor;
		x -= align.getXOffset(width);
		drawColorFrame(stack, builder, x, y, 1, fontHeight, color);
		drawColorFrame(stack, builder, x+width-2, y, 1, fontHeight, color);
		drawColorFrame(stack, builder, x, y, width-1, 1, color);
		drawColorFrame(stack, builder, x, y+fontHeight, width-1, 1, color);
		int size = (int)((width - 3) * progress.progress);
		for(int i = 0;i<size;i++)
		{
			drawColorFrame(stack, builder, x+i+1, y+1, 1F, fontHeight-1, i % 2 == 0 ? color : progress.secondaryColor);
		}
		RenderSystem.enableDepthTest();
		RenderSystem.disableTexture();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		tes.end();
	}
	
	@OnlyIn(Dist.CLIENT)
	public void drawColorFrame(PoseStack stack, VertexConsumer builder, float x, float y, float width, float height, int color)
	{
		Matrix4f matrix = stack.last().pose();
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = color & 0xFF;
		int a = (color >> 24) & 0xFF;
		builder.vertex(matrix, x, y + height, 0F).color(r, g, b, a).endVertex();
		builder.vertex(matrix, x + width, y + height, 0F).color(r, g, b, a).endVertex();
		builder.vertex(matrix, x + width, y, 0F).color(r, g, b, a).endVertex();
		builder.vertex(matrix, x, y, 0F).color(r, g, b, a).endVertex();

	}
	
	public static int darker(int color, float factor)
	{
		int r = Math.max(0, (int)(((color >> 16) & 0xFF) * factor));
		int g = Math.max(0, (int)(((color >> 8) & 0xFF) * factor));
		int b = Math.max(0, (int)((color & 0xFF) * factor));
		return (color & 0xFF000000) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getHeight(int width, Alignment align)
	{
		return Minecraft.getInstance().font.lineHeight+5;
	}
	
	@Override
	public boolean isValid()
	{
		return aliveProvider.getAsBoolean();
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		progressProvider.get().serialize(buffer);
	}
	
	@Override
	public Tag getServerData()
	{
		Progress progress = progressProvider.get();
		return new LongArrayTag(new long[]{Double.doubleToLongBits(progress.progress), progress.mainColor, progress.secondaryColor});
	}
	
	public static class Progress
	{
		double progress;
		int mainColor;
		int secondaryColor;
		
		public Progress(FriendlyByteBuf buffer)
		{
			progress = buffer.readDouble();
			mainColor = buffer.readInt();
			secondaryColor = buffer.readInt();
		}
		
		public Progress(double progress, int mainColor)
		{
			this(progress, mainColor, mainColor);
		}
		
		public Progress(double progress, int mainColor, int secondaryColor)
		{
			this.progress = progress;
			this.mainColor = mainColor;
			this.secondaryColor = secondaryColor;
		}
		
		public void serialize(FriendlyByteBuf buffer)
		{
			buffer.writeDouble(progress);
			buffer.writeInt(mainColor);
			buffer.writeInt(secondaryColor);
		}
	}
}