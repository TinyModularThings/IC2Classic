package ic2.api.classic.reactor;

import ic2.api.reactor.IBaseReactorComponent;
import net.minecraft.item.ItemStack;

public interface IReactorProduct extends IBaseReactorComponent
{
	/**
	 * Sub Class which allow you to have Reactor Products (results)
	 * in the reactor. (Like a UsedUp ReactorCell)
	 * @param item Item that needs a check
	 * @return if it is a Product
	 */
	public boolean isProduct(ItemStack item);
}
