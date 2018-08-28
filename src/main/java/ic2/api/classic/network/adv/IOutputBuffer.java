package ic2.api.classic.network.adv;

import java.util.UUID;

import ic2.api.classic.network.adv.NetworkField.BitLevel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IOutputBuffer
{
	public void writeBoolean(boolean value);
	
	public void writeByte(byte value);
	
	public void writeShort(short value);
	
	public void writeMedium(int value);
	
	public void writeInt(int value);
	
	public void writeFloat(float value);
	
	public void writeDouble(double value);
	
	public void writeLong(long value);
	
	public void writeData(long value, BitLevel type);
	
	public void writeChar(char value);
	
	public void writeString(String value);
	
	public void writeString(String value, BitLevel length);
	
	public void writeBytes(byte[] value);
	
	public void writeBytes(byte[] value, BitLevel length);
	
	public void writeNBTData(NBTTagCompound value);
	
	public void writeForgeEntry(IForgeRegistryEntry value);
	
	public void writeUUID(UUID value);
	
}
