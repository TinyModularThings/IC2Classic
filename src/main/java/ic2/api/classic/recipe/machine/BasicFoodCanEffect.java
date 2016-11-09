package ic2.api.classic.recipe.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BasicFoodCanEffect implements IFoodCanEffect
{
	boolean isBadEffect;
	PotionEffect[] effects;
	boolean isRandom;
	float chance;
	
	public BasicFoodCanEffect(boolean bad, PotionEffect... effectArray)
	{
		isBadEffect = bad;
		effects = effectArray;
	}
	
	public BasicFoodCanEffect setRandom(float chance)
	{
		this.chance = chance;
		isRandom = true;
		return this;
	}
	
	@Override
	public void onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if(player.worldObj.isRemote)
		{
			return;
		}
		if((isRandom && world.rand.nextFloat() >= chance) || effects == null || effects.length == 0)
		{
			return;
		}
		for(int i = 0;i<effects.length;i++)
		{
			if(effects[i] == null)
			{
				continue;
			}
			player.addPotionEffect(new PotionEffect(effects[i]));
		}
	}
	
	@Override
	public boolean isBadEffect(ItemStack stack)
	{
		return isBadEffect;
	}
	
}
