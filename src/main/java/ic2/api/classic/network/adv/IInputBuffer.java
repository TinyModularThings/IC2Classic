package ic2.api.classic.network.adv;

import java.util.UUID;

import ic2.api.classic.network.adv.NetworkField.BitLevel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.registries.IForgeRegistryEntry;


public interface IInputBuffer
{

	public boolean readBoolean();
	
	public byte readByte();
	
	public short readShort();
	
	public int readMedium();
	
	public int readInt();
	
	public float readFloat();
	
	public double readDouble();
	
	public long readLong();
	
	public long readData(BitLevel length);
	
	public char readChar();
	
	public byte[] readBytes();
	
	public byte[] readBytes(BitLevel length);
	
	public String readString();
	
	public String readString(BitLevel length);
	
	public NBTTagCompound readNBTData();
	
	public <T extends IForgeRegistryEntry> T readForgeRegistryEntry(RegistryNamespaced<ResourceLocation, T> registry);
	
	public UUID readUUID();

	
}
