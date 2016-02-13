package ic2.api.energy.tile;

import net.minecraft.item.ItemDye;

public interface IEnergyConductorColored extends IEnergyConductor
{
	/**
	 * @Return the color of the Conductor.
	 * 0 means none Color.
	 * That helps with Addon Compatiblity.
	 * IC2 Cables uses that also!
	 */
	public WireColor getConductorColor();
	
	public enum WireColor
	{
		Blank,
		Black,
		Red,
		Green,
		Brown,
		Blue,
		Purple,
		Cyan,
		Silver,
		Gray,
		Pink,
		Lime,
		Yellow,
		LightBlue,
		Magenta,
		Orange,
		White;
	}
}
