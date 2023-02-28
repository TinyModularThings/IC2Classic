package ic2.api.recipes.registries;

import java.util.List;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public interface IFluidFuelRegistry extends IListenableRegistry<IFluidFuelRegistry>
{
	void addFuel(Fluid fluid, int ticksPerBucket, int euPerTick);
	void removeFuel(Fluid fluid);
	List<FuelEntry> getFuels();
	FuelEntry getFuel(Fluid fluid);
	
	public static class FuelEntry
	{
		final Fluid fluid;
		final int ticksPerBucket;
		final int euPerTick;
		
		public FuelEntry(FriendlyByteBuf buffer)
		{
			fluid = buffer.readRegistryIdUnsafe(ForgeRegistries.FLUIDS);
			ticksPerBucket = buffer.readVarInt();
			euPerTick = buffer.readVarInt();
		}
		
		public FuelEntry(Fluid fluid, int ticksPerBucket, int euPerTick)
		{
			this.fluid = fluid;
			this.ticksPerBucket = ticksPerBucket;
			this.euPerTick = euPerTick;
		}
		
		public Fluid getFluid()
		{
			return fluid;
		}
		
		public int getTicksPerBucket()
		{
			return ticksPerBucket;
		}
		
		public int getEuPerTick()
		{
			return euPerTick;
		}
		
		public void write(FriendlyByteBuf buffer)
		{
			buffer.writeRegistryIdUnsafe(ForgeRegistries.FLUIDS, fluid);
			buffer.writeVarInt(ticksPerBucket);
			buffer.writeVarInt(euPerTick);
		}
	}
}