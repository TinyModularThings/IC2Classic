package ic2.api.network;

import java.util.function.Supplier;

import ic2.api.network.buffer.INetworkDataBuffer;
import ic2.api.network.tile.INetworkFieldProvider;
import ic2.api.network.tile.PacketRange;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface INetworkManager
{
	void registerDataBuffer(ResourceLocation location, Class<? extends INetworkDataBuffer> clz, Supplier<INetworkDataBuffer> creator);
	
	
	// Auto Sync
	
	void startGuiTracking(BlockEntity tile, Player player);
	
	void sendInitialGuiData(INetworkFieldProvider tile, Player player);
	
	void updateGuiData(BlockEntity tile, Player player);
	
	void updateTileField(BlockEntity tile, String field);
	
	void updateTileFields(BlockEntity tile, String...fields);
	
	void updateGuiField(BlockEntity tile, String field);
	
	void updateGuiFields(BlockEntity tile, String...fields);
	
	void sendInitialData(INetworkFieldProvider prov, CompoundTag nbt);
	
	void handleInitialChange(BlockEntity prov, CompoundTag nbt);
	
	void requestInitialData(INetworkFieldProvider prov);
	
	// TileEvent
	void sendTileEvent(BlockEntity tile, int key, int value, PacketRange target);
	
	void sendTileDataBufferEvent(BlockEntity tile, String id, INetworkDataBuffer buffer, PacketRange target);
	
	void sendClientTileEvent(BlockEntity tile, int key, int value);
	
	void sendClientTileDataBufferEvent(BlockEntity tile, String id, INetworkDataBuffer buffer);
	
	//ItemEvent
	void sendItemEvent(Player player, ItemStack stack, int key, int value);
	
	void sendItemBuffer(Player player, ItemStack stack, String id, INetworkDataBuffer buffer);
	
	void sendClientItemEvent(ItemStack stack, int key, int value);
	
	void sendClientItemBuffer(ItemStack stack, String id, INetworkDataBuffer buffer);
	
}
