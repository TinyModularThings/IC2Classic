package ic2.api.network.buffer;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface NetworkInfo
{
	BitLevel value() default BitLevel.BIT_0;
	
	enum BitLevel
	{
		BIT_0(0), // IgnoreCase
		BIT_8(1), // Byte
		BIT_16(2), // Short
		BIT_24(3), // Medium
		BIT_32(4), // Integer
		BIT_40(5), // Integer + Byte
		BIT_48(6), // Integer + Short
		BIT_56(7), // Integer + Medium
		BIT_64(8); // long
		
		final int index;
		final long maxNumber;
		public static final BitLevel[] LEVELS;
		
		BitLevel(int lvl)
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
			if(index == 0)
				return false;
			else
				return index < limit.index;
		}
		
		public long limitNumber(long input)
		{
			if(index == 0)
				return 0;
			if(input >= maxNumber)
				return maxNumber - 1;
			return input;
		}
		
		public static BitLevel getLevel(int index)
		{
			if(index < 0 || index > 8)
			{
				return BitLevel.BIT_32;
			}
			return LEVELS[index];
		}
		
		static
		{
			LEVELS = new BitLevel[values().length];
			for(BitLevel level : values())
			{
				LEVELS[level.getIndex()] = level;
			}
		}
	}
}
