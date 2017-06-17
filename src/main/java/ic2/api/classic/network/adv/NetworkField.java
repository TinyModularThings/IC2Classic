package ic2.api.classic.network.adv;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(CLASS)
@Target(FIELD)
public @interface NetworkField
{
	public int index();
	
	public BitLevel compression() default BitLevel.Bit0;
	
	public boolean override() default false;
	
	public static enum BitLevel
	{
		Bit0(0), //IgnoreCase
		Bit8(1), //Byte
		Bit16(2), //Short
		Bit24(3), //Medium
		Bit32(4), //Integer
		Bit40(5), //Integer + Byte
		Bit48(6), //Integer + Short
		Bit56(7), //Integer + Medium
		Bit64(8); //long
		
		final int index;
		final long maxNumber;
		public static BitLevel[] levels;
		
		private BitLevel(int lvl)
		{
			index = lvl;
			maxNumber = (1L << (lvl * 8));
		}
		
		public int getIndex()
		{
			return index;
		}
		
		public boolean isSkip()
		{
			return index == 0;
		}
		
		public boolean isValid(BitLevel limit)
		{
			if(index == 0) return false;
			else return index < limit.index;
		}
		
		public long limitNumber(long input)
		{
			if(index == 0) return 0;
			if(input >= maxNumber) return maxNumber - 1;
			return input;
		}
		
		public static BitLevel getLevel(int index)
		{
			if(index < 0 || index > 8)
			{
				return BitLevel.Bit32;
			}
			return levels[index];
		}
		static
		{
			levels = new BitLevel[values().length];
			for(BitLevel level : values())
			{
				levels[level.getIndex()] = level;
			}
		}
	}
}
