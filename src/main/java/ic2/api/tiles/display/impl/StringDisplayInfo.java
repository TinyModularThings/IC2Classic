package ic2.api.tiles.display.impl;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.PoseStack;

import ic2.api.tiles.display.IDisplayInfo;
import ic2.api.tiles.display.IMonitorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StringDisplayInfo implements IDisplayInfo
{
	Component comp;
	Supplier<Component> textProvider;
	BooleanSupplier aliveProvider;
	
	public StringDisplayInfo(Supplier<Component> textProvider, BooleanSupplier aliveProvider)
	{
		this.textProvider = textProvider;
		this.aliveProvider = aliveProvider;
	}
	
	public StringDisplayInfo(FriendlyByteBuf buffer)
	{
		comp = buffer.readComponent();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void render(PoseStack stack, int x, int y, int width, int height, Alignment align, IMonitorRenderer helper)
	{
		Font font = helper.getFont();
		int heightConsumed = 0;
		for(FormattedCharSequence sub : font.split(comp, width))
		{
			if(height - heightConsumed < font.lineHeight) return;
		    font.drawInBatch(sub, x-align.getXOffset(font.width(sub)), y+heightConsumed, -1, false, stack.last().pose(), helper.getBatcher(), false, 0, 15728880);
		    heightConsumed += font.lineHeight;
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getHeight(int width, Alignment align)
	{
		Font font = Minecraft.getInstance().font;
		return font.getSplitter().splitLines(comp, width, Style.EMPTY).size() * font.lineHeight;
	}
	
	@Override
	public boolean isValid()
	{
		return aliveProvider.getAsBoolean();
	}
	
	@Override
	public Tag getServerData()
	{
		return StringTag.valueOf(textProvider.get().getString());
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeComponent(textProvider.get());
	}
}