package ic2.api.network.buffer;

import java.util.UUID;

import ic2.api.network.buffer.NetworkInfo.BitLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistry;

public interface IOutputBuffer
{
	void writeBoolean(boolean value);
	void writeByte(byte value);
	void writeShort(short value);
	void writeMedium(int value);
	void writeInt(int value);
	void writeVarInt(int value);
	void writeFloat(float value);
	void writeDouble(double value);
	void writeLong(long value);
	void writeData(long value, BitLevel type);
	void writeChar(char value);
	void writeEnum(Enum<?> value);
	
	void writeString(String value);
	void writeBytes(byte[] value);
	
	void writeNBTData(CompoundTag value);
	
	<T> void writeForgeEntry(T value, IForgeRegistry<T> registry);
	void writeItemStack(ItemStack stack);
	void writeFluidStack(FluidStack stack);
	void writeUUID(UUID value);
	void writeRegistryKey(ResourceKey<?> key);
	static void writeItemStack(ItemStack stack, IOutputBuffer buffer) {
		buffer.writeItemStack(stack);
	}
	static void writeEnum(Enum<?> value, IOutputBuffer buffer) {
		buffer.writeEnum(value);
	}
}
