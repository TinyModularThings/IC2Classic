package ic2.api.recipes.ingridients.inputs;

import java.util.List;

import com.google.gson.JsonObject;

import ic2.api.core.IC2Classic;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInput implements IInput
{	
	Fluid fluid;
	int fluidAmount;
	int size;
	
	public FluidInput(JsonObject obj)
	{
		fluid = ForgeRegistries.FLUIDS.getValue(ResourceLocation.tryParse(obj.get("fluid").getAsString()));
		fluidAmount = obj.get("amount").getAsInt();
		size = obj.get("size").getAsInt();
	}
	
	public FluidInput(FriendlyByteBuf buffer)
	{
		fluid = buffer.readRegistryIdUnsafe(ForgeRegistries.FLUIDS);
		fluidAmount = buffer.readInt();
		size = buffer.readByte();
	}
	
	public FluidInput(FluidStack stack)
	{
		this(stack.getFluid(), stack.getAmount(), 1);
	}
	
	public FluidInput(Fluid fluid)
	{
		this(fluid, 1);
	}

	public FluidInput(Fluid fluid, int size)
	{
		this(fluid, 1000, size);
	}
	
	public FluidInput(Fluid fluid, int fluidAmount, int size)
	{
		this.fluid = fluid;
		this.fluidAmount = fluidAmount;
		this.size = size;
	}

	@Override
	public List<ItemStack> getComponents()
	{
		ObjectList<ItemStack> stacks = new ObjectArrayList<>();
		for(ItemStack stack : IC2Classic.getHelper().getFluidContainers(fluid))
		{
			stacks.add(stack.copy());
		}
		return ObjectLists.unmodifiable(stacks);
	}
	
	@Override
	public int getInputSize()
	{
		return size;
	}
	
	@Override
	public boolean matches(ItemStack stack)
	{
		if(stack.isEmpty())
		{
			return false;
		}
		FluidStack fluidStack = FluidUtil.getFluidContained(stack).orElse(FluidStack.EMPTY);
		return !fluidStack.isEmpty() && (fluidStack.getFluid() == fluid && fluidStack.getAmount() >= fluidAmount);
	}
	
	@Override
	public void serialize(FriendlyByteBuf buffer)
	{
		buffer.writeRegistryIdUnsafe(ForgeRegistries.FLUIDS, fluid);
		buffer.writeInt(fluidAmount);
		buffer.writeByte(size);
	}
	
	@Override
	public JsonObject serialize()
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(fluid).toString());
		obj.addProperty("amount", fluidAmount);
		obj.addProperty("size", size);
		return obj;
	}
}
