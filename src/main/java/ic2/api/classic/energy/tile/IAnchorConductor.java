package ic2.api.classic.energy.tile;

import ic2.api.energy.tile.IEnergyConductor;
import net.minecraft.util.EnumFacing;

/**
 * 
 * @author Speiger
 * API for cables which allows to place anchors
 * to a cable which blocks connectivity on the side
 */
public interface IAnchorConductor extends IEnergyConductor
{
	/**
	 * Function to add a Anchor to a cable
	 * @param side the side you want it to add
	 * @return if it was added
	 */
	public boolean addAnchor(EnumFacing side);
	
	/**
	 * Function to remove an anchor to a cable
	 * @param side the side you want it to remove
	 * @return if it was removed
	 */
	public boolean removeAnchor(EnumFacing side);
	
	/**
	 * function to check if a anchor is on the cable
	 * @param side the side you want to know
	 * @return if it has a anchor
	 */
	public boolean hasAnchor(EnumFacing side);
}
