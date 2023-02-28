package ic2.api.ticks;

import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;

public interface ITickScheduler
{
	void addWorldCallback(Level world, ToIntFunction<Level> ticket);
	void addWorldCallback(Level world, ToIntFunction<Level> ticket, int delay);
	void addServerCallback(ToIntFunction<MinecraftServer> ticket);
	void addServerCallback(ToIntFunction<MinecraftServer> ticket, int delay);
	void addClientCallback(IntSupplier ticket);
	void addClientCallback(IntSupplier ticket, int delay);
}
