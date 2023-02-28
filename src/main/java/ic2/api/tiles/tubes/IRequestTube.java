package ic2.api.tiles.tubes;

import java.util.UUID;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public interface IRequestTube extends ITube
{
	void provideRequests(ITubeRequester requested);
	void onRequestsReset();
	void onRequestFulfilled(ItemStack stack, int amount);
	UUID getRequestId();
	void onRequestLost(ItemStack stack, int amount);
	long getRequestSource();
	
	interface ITubeRequester
	{
		void requestItems(ItemStack stack, int amount, DyeColor color, UUID requestId);
		int validateRequest(ItemStack stack, int originalRequest, DyeColor color);
	}
}
