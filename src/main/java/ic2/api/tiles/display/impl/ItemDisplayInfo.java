package ic2.api.tiles.display.impl;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix3f;

import ic2.api.tiles.display.IDisplayInfo;
import ic2.api.tiles.display.IMonitorRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemDisplayInfo implements IDisplayInfo
{
	ItemStack item;
	boolean showCount;
	Supplier<ItemStack> itemProvider;
	BooleanSupplier aliveProvider;
	
	public ItemDisplayInfo(FriendlyByteBuf buffer)
	{
		showCount = buffer.readBoolean();
		item = buffer.readItem();
		if(showCount) item.setCount(buffer.readVarInt());
	}
	
	public ItemDisplayInfo(boolean showCount, Supplier<ItemStack> itemProvider, BooleanSupplier aliveProvider)
	{
		this.showCount = showCount;
		this.itemProvider = itemProvider;
		this.aliveProvider = aliveProvider;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void render(PoseStack stack, int x, int y, int width, int height, Alignment align, IMonitorRenderer helper)
	{
		stack.pushPose();
		stack.last().normal().load(Matrix3f.createScaleMatrix(1.0F, -1.0F, 1.0F));
		helper.renderGuiItems(stack, item, x, y);
		if(showCount)
		{
			helper.renderGuiItemText(stack, item, x, y, null);
		}
		stack.popPose();
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getHeight(int width, Alignment align)
	{
		return 18;
	}
	
	@Override
	public boolean isValid()
	{
		return aliveProvider.getAsBoolean();
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		ItemStack stack = itemProvider.get();
		buffer.writeBoolean(showCount);
		buffer.writeItem(stack);
		if(showCount) buffer.writeVarInt(stack.getCount());
	}
	
	@Override
	public Tag getServerData()
	{
		ItemStack stack = itemProvider.get();
		CompoundTag data = stack.save(new CompoundTag());
		if(showCount) data.putInt("Count", stack.getCount());
		else data.remove("Count");
		return data;
	}
	
}
