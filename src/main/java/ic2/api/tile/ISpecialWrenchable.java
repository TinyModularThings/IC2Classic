package ic2.api.tile;

import net.minecraft.entity.player.EntityPlayer;

public interface ISpecialWrenchable extends IWrenchable
{
	public boolean canDoSpecial(EntityPlayer player, int side);
	
	public boolean doSpecial(EntityPlayer player, int side);
}
