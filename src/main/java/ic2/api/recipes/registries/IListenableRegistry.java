package ic2.api.recipes.registries;

import java.util.function.Consumer;

public interface IListenableRegistry<T extends IListenableRegistry<T>>
{
	void registerListener(Consumer<T> listener);
}
