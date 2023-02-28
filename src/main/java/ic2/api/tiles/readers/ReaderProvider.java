package ic2.api.tiles.readers;

import java.util.Map;

import com.google.common.base.Function;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ReaderProvider
{
	static final Map<Class<?>, Function<BlockEntity, IFuelStorage>> FUEL_PROVIDER = new Object2ObjectOpenHashMap<>();
	static final Map<Class<?>, Function<BlockEntity, IActivityProvider>> ACTIVE_PROVIDER = new Object2ObjectOpenHashMap<>();
	static final Map<Class<?>, Function<BlockEntity, IProgressMachine>> PROGRESS_PROVIDER = new Object2ObjectOpenHashMap<>();

	public static void registerActiveProvider(Class<?> clz, Function<BlockEntity, IActivityProvider> provider)
	{
		ACTIVE_PROVIDER.put(clz, provider);
	}
	
	public static void registerFuelProvider(Class<?> clz, Function<BlockEntity, IFuelStorage> provider)
	{
		FUEL_PROVIDER.put(clz, provider);
	}
	
	public static void registerProgressProvider(Class<?> clz, Function<BlockEntity, IProgressMachine> provider)
	{
		PROGRESS_PROVIDER.put(clz, provider);
	}
	
	public static IFuelStorage getFuelProvider(BlockEntity tile)
	{
		if(tile == null) return null;
		if(tile instanceof IFuelStorage) return (IFuelStorage)tile;
		Class<?> clz = tile.getClass();
		while(clz != BlockEntity.class)
		{
			Function<BlockEntity, IFuelStorage> storage = FUEL_PROVIDER.get(clz);
			if(storage != null) {
				if(clz != tile.getClass()) FUEL_PROVIDER.put(tile.getClass(), storage);
				return storage.apply(tile);
			}
			clz = clz.getSuperclass();
		}
		return null;
	}
	
	public static IProgressMachine getProgressProvider(BlockEntity tile)
	{
		if(tile == null) return null;
		if(tile instanceof IProgressMachine) return (IProgressMachine)tile;
		Class<?> clz = tile.getClass();
		while(clz != BlockEntity.class)
		{
			Function<BlockEntity, IProgressMachine> storage = PROGRESS_PROVIDER.get(clz);
			if(storage != null) {
				if(clz != tile.getClass()) PROGRESS_PROVIDER.put(tile.getClass(), storage);
				return storage.apply(tile);
			}
			clz = clz.getSuperclass();
		}
		return null;
	}
	
	public static IActivityProvider getActivityProvider(BlockEntity tile)
	{
		if(tile == null) return null;
		if(tile instanceof IActivityProvider) return (IActivityProvider)tile;
		Class<?> clz = tile.getClass();
		while(clz != BlockEntity.class)
		{
			Function<BlockEntity, IActivityProvider> storage = ACTIVE_PROVIDER.get(clz);
			if(storage != null) {
				if(clz != tile.getClass()) ACTIVE_PROVIDER.put(tile.getClass(), storage);
				return storage.apply(tile);
			}
			clz = clz.getSuperclass();
		}
		return null;
	}
	
}
