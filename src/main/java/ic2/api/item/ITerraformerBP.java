package ic2.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * @author Speiger
 * Meta/NBT Sensitive Version of the ITerraformingBP
 * Just look at their description. It is the same
 * IC2 Classic will prefer this class but ITerraformingBP will be still supported
 */
public interface ITerraformerBP
{
	public abstract int getConsume(ItemStack item);
	
	public abstract int getRange(ItemStack item);
	
	public abstract boolean terraform(ItemStack item, World world, int x, int z, int yCoord);

}
