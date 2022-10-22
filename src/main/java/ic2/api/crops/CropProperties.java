package ic2.api.crops;

/**
 * Describe the plant through a set of properties, influencing breeding.
 * Plants sharing properties and attributes will tend to cross-breed more often.
 *
 * Properties:
 * - Tier (Tier of the plant. Ranges from 1 to 16, 0 is Weed. Valuable and powerful crops have higher tiers, useless and
 * 				weak ones have lower tiers.)
 * - Chemistry (Industrial uses based on chemical plant components)
 * - Consumable (Food, potion ingredients, stuff meant to be eaten or similarly used)
 * - Defensive (Plants with defense capabilities (damaging, explosive, chemical) or special abilities in general)
 * - Colorful (How colorful/aesthetically/beautiful is the plant, like dye-plants or plants without actual effects)
 * - Weed (Is this plant weed-like and rather unwanted/quick-spreading? Rare super-breed plants should have low values here)
 *
 * @author IC2 Team
 */
public class CropProperties
{
	private final int tier;
	private final int chemistry;
	private final int consumable;
	private final int defensive;
	private final int colorful;
	private final int weed;
	
	/**
	 * Create a CropProperties object.
	 *
	 * @param tier Tier property value
	 * @param chemistry	Chemistry property value
	 * @param consumable Consumable property value
	 * @param defensive Defensive property value
	 * @param colorful Colorful property value
	 * @param weed Weed property value
	 */
	public CropProperties(int tier, int chemistry, int consumable, int defensive, int colorful, int weed)
	{
		this.tier = tier;
		this.chemistry = chemistry;
		this.consumable = consumable;
		this.defensive = defensive;
		this.colorful = colorful;
		this.weed = weed;
	}
	
	public int getTier()
	{
		return tier;
	}
	
	public int getChemistry()
	{
		return chemistry;
	}
	
	public int getConsumable()
	{
		return consumable;
	}
	
	public int getDefensive()
	{
		return defensive;
	}
	
	public int getColorful()
	{
		return colorful;
	}
	
	public int getWeed()
	{
		return weed;
	}
	
	public int[] getAllProperties()
	{
		return new int[] {getChemistry(), getConsumable(), getDefensive(), getColorful(), getWeed() };
	}
}
