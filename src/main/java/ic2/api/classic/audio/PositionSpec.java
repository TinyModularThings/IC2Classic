package ic2.api.classic.audio;

public enum PositionSpec
{
	Center(0), //Mostly TileEntities or Blocks
	Backpack(1), //Back of a Player (Armor Slots)
	Hand(2); //Equipped Item
	
	int type;
	
	private PositionSpec(int theType)
	{
		type = theType;
	}
	
	public int getType()
	{
		return type;
	}
}
