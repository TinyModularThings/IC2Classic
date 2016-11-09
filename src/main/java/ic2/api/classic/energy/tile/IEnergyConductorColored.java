package ic2.api.classic.energy.tile;

import ic2.api.energy.tile.IEnergyConductor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;


public interface IEnergyConductorColored extends IEnergyConductor
{
	/**
	 * @Return the color of the conductor.
	 * 0 means none Color.
	 * That helps with addon Compatibility.
	 * IC2 Cables uses that also!
	 */
	public WireColor getConductorColor();
	
	public enum WireColor implements IStringSerializable
	{
		Blank(null, "blank"),
		Black(EnumDyeColor.BLACK, "black"),
		Red(EnumDyeColor.RED, "red"),
		Green(EnumDyeColor.GREEN, "green"),
		Brown(EnumDyeColor.BROWN, "brown"),
		Blue(EnumDyeColor.BLUE, "blue"),
		Purple(EnumDyeColor.PURPLE, "purple"),
		Cyan(EnumDyeColor.CYAN, "cyan"),
		Silver(EnumDyeColor.SILVER, "silver"),
		Gray(EnumDyeColor.GRAY, "gray"),
		Pink(EnumDyeColor.PINK, "pink"),
		Lime(EnumDyeColor.LIME, "lime"),
		Yellow(EnumDyeColor.YELLOW, "yellow"),
		LightBlue(EnumDyeColor.LIGHT_BLUE, "lightblue"),
		Magenta(EnumDyeColor.MAGENTA, "magenta"),
		Orange(EnumDyeColor.ORANGE, "orange"),
		White(EnumDyeColor.WHITE, "white");
		
		EnumDyeColor color;
		String name; //For IProperty
		
		public static WireColor[] valueLookup;
		
		private WireColor(EnumDyeColor color, String name)
		{
			this.color = color;
			this.name = name;
		}
		
		public EnumDyeColor toColor()
		{
			return color;
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		public static WireColor fromColor(EnumDyeColor color)
		{
			if(color == null)
			{
				return WireColor.Blank;
			}
			return valueLookup[color.getDyeDamage()+1];
		}

		static
		{
			valueLookup = values();
		}

	}
}
