package ic2.api.network.buffer;

import java.util.UUID;

import ic2.api.network.buffer.NetworkInfo.BitLevel;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistry;

public interface IInputBuffer
{
	boolean readBoolean();
	byte readByte();
	short readShort();
	int readMedium();
	int readInt();
	int readVarInt();
	float readFloat();
	double readDouble();
	long readLong();
	long readData(BitLevel length);
	char readChar();
	<T extends Enum<T>> T readEnum(Class<T> clz);
	
	byte[] readBytes();
	String readString();
	
	CompoundTag readNBTData();
	
	<T> T readForgeRegistryEntry(IForgeRegistry<T> registry);
	ItemStack readItemStack();
	FluidStack readFluidStack();
	UUID readUUID();
	<T> ResourceKey<T> readRegistryKey(ResourceKey<Registry<T>> key);
}
