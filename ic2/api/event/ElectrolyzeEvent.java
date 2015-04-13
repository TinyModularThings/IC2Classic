package ic2.api.event;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

public class ElectrolyzeEvent extends Event
{
	
	/**
	 * 
	 * @author Speiger
	 * This event allows you to add Recipes to the Electrolyzer.
	 * Which some mods before required. See AdvGenerators which had to override IC2 Classes for it.
	 * To explain it: 
	 * @Input: is your Item that you put in/out (depends on the working way)
	 * @Output: is the Output that you have.
	 * @Energy: the energy that is required to create the item into the item which you want to create.
	 * @Charge: Means that you can charge that item. false mean that direction is dissabled.
	 * @Discharge: Means that you can discharge it. false mean this direction is dissabled.
	 * 
	 * A little note here. The Discharge energy is: 60% + 10% * MFE LvL (MFE = 2, MFSU = 3) of the charge energy.
	 * So keep that in mind when you create a recipe.
	 * Also NBTData will be ignored. Only at the Item which you put in. So you can CreateNBTBased Items but not use them in the
	 * Electrolyzer!
	 */
	@Cancelable
	public static class Recipe extends ElectrolyzeEvent
	{
		public final ItemStack input;
		public final ItemStack output;
		public final int energy;
		public final boolean charge;
		public final boolean discharge;
		
		public Recipe(ItemStack par1, ItemStack par2, int par3, boolean par4, boolean par5)
		{
			input = par1;
			output = par2;
			energy = par3;
			charge = par4;
			discharge = par5;
		}
	}
	
}
