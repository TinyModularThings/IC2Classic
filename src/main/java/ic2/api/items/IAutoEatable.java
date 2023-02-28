package ic2.api.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IAutoEatable
{
	public boolean canAutoEat(ItemStack stack);
	public int getFoodValue(ItemStack stack);
	public ItemStack onEaten(ItemStack stack, Level level, Player player);
}
