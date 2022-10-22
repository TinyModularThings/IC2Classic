package ic2.api.recipes.registries;

import java.util.List;
import java.util.Map;

import ic2.api.items.IRepairable;
import ic2.api.recipes.ingridients.inputs.IInput;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface ICannerRecipeRegistry extends IListenableRegistry<ICannerRecipeRegistry>
{	
	void registerRepairable(IRepairable repair, IInput input, int repairAmount);
	void removeRepairable(IRepairable repair);
	Tuple<IInput, Integer> getRepairableItem(ItemStack input, ItemStack repair, boolean compareStack);
	Map<Item, List<Tuple<IInput, Integer>>> getRepairItems();
	
	void registerFuelValue(ItemStack stack, int amount);
	void registerFuelMultiplier(ItemStack stack, float multiplier);
	void removeValue(ItemStack stack);
	FuelValue getValueForStack(ItemStack stack);
	List<FuelValue> getFuels();
	
	void registerFillable(ItemStack container, IInput fillable, ItemStack output);
	boolean hasContainer(ItemStack container);
	void removeFillable(ItemStack container);
	void removeFillable(ItemStack container, ItemStack fillable);
	Tuple<IInput, ItemStack> getFillOutput(ItemStack container, ItemStack toFill, boolean compareStack);
	Map<ItemStack, List<Tuple<IInput, ItemStack>>> getFillables();
	
	
	class FuelValue
	{
		ItemStack owner;
		int fuelValue;
		float fuelMultiplier;
		boolean multiplier;
		
		public FuelValue(FriendlyByteBuf buffer)
		{
			owner = buffer.readItem();
			multiplier = buffer.readBoolean();
			fuelValue = buffer.readInt();
			fuelMultiplier = buffer.readFloat();
		}
		
		public FuelValue(ItemStack owner, int fuelValue)
		{
			this(owner, fuelValue, 0F, false);
		}
		
		public FuelValue(ItemStack owner, float fuelMultiplier)
		{
			this(owner, 0, fuelMultiplier, true);
		}
		
		public FuelValue(ItemStack owner, int fuelValue, float fuelMultiplier, boolean multiplier)
		{
			this.owner = owner;
			this.fuelValue = fuelValue;
			this.fuelMultiplier = fuelMultiplier;
			this.multiplier = multiplier;
		}
		
		public int test(int base)
		{
			return multiplier ? (int)(base * fuelMultiplier) : fuelValue;
		}
		
		public int apply(int base)
		{
			return multiplier ? base + (int)(base * fuelMultiplier) : base + fuelValue;
		}
		
		public boolean isMultiplier()
		{
			return multiplier;
		}
		
		public float getFuelMultiplier()
		{
			return fuelMultiplier;
		}
		
		public int getFuelValue()
		{
			return fuelValue;
		}
		
		public ItemStack getStack()
		{
			return owner.copy();
		}
		
		public void write(FriendlyByteBuf buffer)
		{
			buffer.writeItem(owner);
			buffer.writeBoolean(multiplier);
			buffer.writeInt(fuelValue);
			buffer.writeFloat(fuelMultiplier);
		}
	}
}
