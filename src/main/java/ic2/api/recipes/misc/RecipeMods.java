package ic2.api.recipes.misc;

import net.minecraft.nbt.CompoundTag;

public enum RecipeMods
{
	ENERGY_USAGE("energy_mod", "energy_add"),
	RECIPE_TIME("time_mod", "time_add");
	
	String mod_tag;
	String add_tag;
	
	RecipeMods(String mod_tag, String add_tag)
	{
		this.mod_tag = mod_tag;
		this.add_tag = add_tag;
	}
	
	public int apply(CompoundTag compound, int base)
	{
		double mod = compound.contains(mod_tag) ? compound.getDouble(mod_tag) : 1D;
		long ret = Math.round((base + compound.getInt(add_tag)) * mod);
		return Math.max(1, (int)(ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret));
	}
	
	public CompoundTag create(CompoundTag compound, double mod, int add)
	{
		compound.putInt(add_tag, add);
		compound.putDouble(mod_tag, mod);
		return compound;
	}
	
	public CompoundTag create(double mod, int add)
	{
		return create(new CompoundTag(), mod, add);
	}
	
	public CompoundTag create(CompoundTag compound, double mod)
	{
		compound.putDouble(mod_tag, mod);
		return compound;
	}
	
	public CompoundTag create(double mod)
	{
		return create(new CompoundTag(), mod);
	}
	
	public CompoundTag create(CompoundTag compound, int add)
	{
		compound.putInt(add_tag, add);
		return compound;
	}
	
	public CompoundTag create(int add)
	{
		return create(new CompoundTag(), add);
	}
	
	public static int apply(int min, int base, int extra, double multiplier)
	{
		long ret = Math.round((base + extra) * multiplier);
		return Math.max(min, (int)(ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret));
	}
	
	public static int apply(int base, int extra, double multiplier)
	{
		long ret = Math.round((base + extra) * multiplier);
		return Math.max(1, (int)(ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret));
	}
	
	public static float apply(float min, float base, float extra, double multiplier)
	{
		double ret = (base + extra) * multiplier;
		return Math.max(min, (float)(ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret));
	}
	
	public static float apply(float base, float extra, double multiplier)
	{
		double ret = (base + extra) * multiplier;
		return Math.max(1F, (float)(ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret));
	}
	
}
