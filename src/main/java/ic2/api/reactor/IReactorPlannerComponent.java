package ic2.api.reactor;

import java.text.DecimalFormat;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import ic2.api.reactor.planner.SimulatedStack;
import ic2.core.utils.collection.CollectionUtils;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public interface IReactorPlannerComponent extends IReactorComponent
{
	DecimalFormat FORMAT = new DecimalFormat("###,###.##");
	NumericTag NULL_VALUE = IntTag.valueOf(0);
	
	default ItemStack applyStackSize(ItemStack newStack, int size) {
		newStack.setCount(Mth.clamp(size, 0, newStack.getMaxStackSize()));
		return newStack;
	}
	
	default int getStackSize(ItemStack input) {
		return input.getCount();
	}
	
	void provideComponents(NonNullList<ItemStack> list);
	short getComponentID(ItemStack stack);
	SimulatedStack createSimulationComponent(ItemStack self);
	
	default void addToolTip(ItemStack stack, Consumer<Component> tooltips) {}
	void addAffectedSlots(int x, int y, BiPredicate<Integer, Integer> slots);
	ReactorType getSupportedReactor(ItemStack stack);
	ComponentType getType(ItemStack stack);
	List<ReactorStat> getStats(ItemStack stack);
	NumericTag getReactorStat(ReactorStat stat, ItemStack stack);
	default NumericTag getReactorStat(ReactorStat stat, ItemStack stack, IReactor planner, int x, int y) { return getReactorStat(stat, stack); }
	
	public static enum ReactorType
	{
		ELECTRIC,
		STEAM,
		UNIVERSAL
	}
	
	public static class ComponentType
	{
		static final List<ComponentType> TYPES = ObjectLists.synchronize(CollectionUtils.createList());
		public static final ComponentType FUEL_ROD = new ComponentType("gui.ic2.reactor_planner.component.fuel_rod");
		public static final ComponentType COOLANT_CELL = new ComponentType("gui.ic2.reactor_planner.component.coolant_cell");
		public static final ComponentType CONDENSATOR = new ComponentType("gui.ic2.reactor_planner.component.condensator");
		public static final ComponentType HEAT_PACK = new ComponentType("gui.ic2.reactor_planner.component.heat_pack");
		public static final ComponentType HEAT_VENT = new ComponentType("gui.ic2.reactor_planner.component.heat_vent");
		public static final ComponentType HEAT_SPREAD = new ComponentType("gui.ic2.reactor_planner.component.heat_spread");
		public static final ComponentType HEAT_EXCHANGER = new ComponentType("gui.ic2.reactor_planner.component.heat_exchanger");
		public static final ComponentType HEAT_PUMP = new ComponentType("gui.ic2.reactor_planner.component.heat_pump");
		public static final ComponentType PLATING = new ComponentType("gui.ic2.reactor_planner.component.plating");
		public static final ComponentType REFLECTORS = new ComponentType("gui.ic2.reactor_planner.component.reflectors");
		public static final ComponentType ISOTOPE_CELL = new ComponentType("gui.ic2.reactor_planner.component.isotope_cell");
		public static final ComponentType UNDEFINED = new ComponentType("gui.ic2.reactor_planner.component.undefined");
		
		int index;
		Component name;
		
		public ComponentType(String name)
		{
			this.name = Component.translatable(name);
			this.index = TYPES.size();
			TYPES.add(this);
		}
		
		public Component getName()
		{
			return name;
		}
		
		public int getIndex()
		{
			return index;
		}
		
		public static int size()
		{
			return TYPES.size();
		}
		
		public static ComponentType byID(int index)
		{
			return TYPES.get(index % TYPES.size());
		}
	}
		
	public static enum ReactorStat
	{
		//Rods
		HEAT_PRODUCTION(false, "gui.ic2.reactor_planner.stat.heat_production"),
		ENERGY_PRODUCTION(true, "gui.ic2.reactor_planner.stat.energy_production"),
		STEAM_PRODUCTION(true, "gui.ic2.reactor_planner.stat.steam_production"),
		ROD_COUNT(false, "gui.ic2.reactor_planner.stat.rod_count"),
		PULSE_COUNT(false, "gui.ic2.reactor_planner.stat.pulse_count"),
		
		//Vents
		SELF_COOLING(false, "gui.ic2.reactor_planner.stat.self_cooling"),
		REACTOR_COOLING(false, "gui.ic2.reactor_planner.stat.reactor_cooling"),
		PART_COOLING(false, "gui.ic2.reactor_planner.stat.part_cooling"),
		
		//Exchangers
		PART_BALANCING(false, "gui.ic2.reactor_planner.stat.part_balance"),
		REACTOR_BALANCING(false, "gui.ic2.reactor_planner.stat.reactor_balance"),
		
		//Storage
		HEAT_STORAGE(false, "gui.ic2.reactor_planner.stat.heat_storage"),
		MAX_HEAT_STORAGE(false, "gui.ic2.reactor_planner.stat.max_heat_storage"),
		
		//Misc
		REACTOR_HEAT_EFFECT_MODIFIER(true, "gui.ic2.reactor_planner.stat.reactor_hem"),
		REACTOR_HEAT_EFFECT_MULTIPLIER(true, "gui.ic2.reactor_planner.stat.reactor_hem_mul"),
		MAX_COMPONENT_DURABILITY(false, "gui.ic2.reactor_planner.stat.max_durability"),
		RECHARGEABLE(false, "gui.ic2.reactor_planner.stat.rechargeable"),
		
		//SpecialStuff
		ENERGY_USAGE(true, "gui.ic2.reactor_planner.stat.energy_usage"),
		WATER_CONSUMPTION(true, "gui.ic2.reactor_planner.stat.water_consumption"),
		WATER_STORAGE(true, "gui.ic2.reactor_planner.stat.water_storage");
		
		boolean isFloat;
		String name;

		ReactorStat(boolean isFloat, String name)
		{
			this.isFloat = isFloat;
			this.name = name;
		}
		
		public NumericTag createStat(Number nr)
		{
			if(isFloat)
			{
				return FloatTag.valueOf(nr.floatValue());
			}
			return IntTag.valueOf(nr.intValue());
		}
		
		public boolean isFloat()
		{
			return isFloat;
		}
		
		public Component getName(NumericTag nbt)
		{
			return Component.translatable(name, (isFloat ? FORMAT.format(nbt.getAsDouble()) : FORMAT.format(nbt.getAsLong())));
		}
		
	}
}
