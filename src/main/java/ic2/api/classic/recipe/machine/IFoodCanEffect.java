package ic2.api.classic.recipe.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFoodCanEffect
{
	/**
	 * When the Food can is eaten you can apply your effect.
	 * Changing the Item does not work its returning always the same item
	 * @param stack Item that is eating it. (meta-data == effect) (also copy)
	 * @param world World
	 * @param player Player who is eating it or get feed
	 */
	public void onEaten(ItemStack stack, World world, EntityPlayer player);
	
	/**
	 * If it is a bad Effect Can. (Applies bad Effects)
	 * @param stack stack that is using it.
	 * @return if it is a bad Effect
	 */
	public boolean isBadEffect(ItemStack stack);
}
