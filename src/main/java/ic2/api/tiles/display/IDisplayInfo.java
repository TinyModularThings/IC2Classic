package ic2.api.tiles.display;

import com.mojang.blaze3d.vertex.PoseStack;

import ic2.api.tiles.display.IDisplayRegistry.DelegateRegistry;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IDisplayInfo
{
	IDisplayRegistry REGISTRY = new DelegateRegistry();
	
	@OnlyIn(Dist.CLIENT)
	void render(PoseStack stack, int x, int y, int width, int height, Alignment align, IMonitorRenderer helper);
	@OnlyIn(Dist.CLIENT)
	int getHeight(int width, Alignment align);
	
	boolean isValid();
	void serialize(FriendlyByteBuf buffer);
	
	Tag getServerData();
	
	public static enum Alignment
	{
		LEFT,
		CENTER,
		RIGHT;
		
		public int getXOffset(int width)
		{
			return this == Alignment.CENTER ? width >> 1 : (this == Alignment.RIGHT ? width : 0); 
		}
	}
}