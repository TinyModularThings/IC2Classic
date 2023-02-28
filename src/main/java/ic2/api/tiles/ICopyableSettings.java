package ic2.api.tiles;

import net.minecraft.nbt.CompoundTag;

public interface ICopyableSettings
{
	public void saveSettings(CompoundTag compound);
	public void loadSettings(CompoundTag compound);
}
