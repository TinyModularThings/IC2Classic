package ic2.api.tiles.display;

import java.util.function.Function;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public interface IDisplayRegistry
{
	void register(ResourceLocation id, Class<? extends IDisplayInfo> info, Function<FriendlyByteBuf, IDisplayInfo> creator);
	void serialize(IDisplayInfo info, FriendlyByteBuf buffer);
	IDisplayInfo deserialize(FriendlyByteBuf buffer);
	
	public static class DelegateRegistry implements IDisplayRegistry
	{
		IDisplayRegistry registry;
		
		public void setRegistry(IDisplayRegistry registry)
		{
			this.registry = registry;
		}
		
		@Override
		public void register(ResourceLocation id, Class<? extends IDisplayInfo> info, Function<FriendlyByteBuf, IDisplayInfo> creator)
		{
			if(registry == null) throw new UnsupportedOperationException();
			registry.register(id, info, creator);
		}
		
		@Override
		public void serialize(IDisplayInfo info, FriendlyByteBuf buffer)
		{
			if(registry == null) throw new UnsupportedOperationException();
			registry.serialize(info, buffer);
		}
		
		@Override
		public IDisplayInfo deserialize(FriendlyByteBuf buffer)
		{
			if(registry == null) throw new UnsupportedOperationException();
			return registry.deserialize(buffer);
		}
	}
}