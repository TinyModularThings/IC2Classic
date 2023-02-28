package ic2.api.recipes.registries;

import java.util.List;

import it.unimi.dsi.fastutil.chars.Char2IntMap;
import it.unimi.dsi.fastutil.chars.Char2IntOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public interface IUUMatterRegistry extends IListenableRegistry<IUUMatterRegistry>
{
	void registerUUShape(UUMatterBuilder builder);
	
	void registerUUEntry(ItemStack output, int milliUU);
	
	List<UUMatterEntry> getEntries();
	
	void removeUUEntry(UUMatterEntry entry);
		
	class UUMatterEntry
	{
		final ItemStack stack;
		final int uuNeeded;
		
		public UUMatterEntry(ItemStack stack, int uuNeeded)
		{
			this.stack = stack.copy();
			this.stack.setCount(1);
			this.uuNeeded = uuNeeded;
		}
		
		public ItemStack getStack()
		{
			return stack.copy();
		}
		
		public int getUUNeeded()
		{
			return uuNeeded;
		}
	}
	
	class UUMatterBuilder
	{
		ItemStack output;
		ResourceLocation id;
		String[] shape;
		boolean special = false;
		boolean hidden = true;
		Char2IntMap letters = new Char2IntOpenHashMap();
		
		public UUMatterBuilder(String name)
		{
			this("ic2", name);
		}
		
		public UUMatterBuilder(String mod, String name)
		{
			this(new ResourceLocation(mod, name));
		}
		
		public UUMatterBuilder(ResourceLocation id)
		{
			this.id = id;
			letters.put('M', 1);
		}
		
		public UUMatterBuilder setSpecial()
		{
			special = true;
			return this;
		}
		
		public UUMatterBuilder setVisible()
		{
			hidden = false;
			return this;
		}
		
		public UUMatterBuilder addLetters(char letter, int uuMatterItems)
		{
			letters.put(letter, uuMatterItems);
			return this;
		}
		
		public UUMatterBuilder setOutput(ItemLike provider, int amount)
		{
			output = new ItemStack(provider, amount);
			return this;
		}
		
		public UUMatterBuilder setOutput(ItemStack output)
		{
			this.output = output.copy();
			return this;
		}
		
		public UUMatterBuilder setShape(String...shapes)
		{
			int length = shapes[0].length();
			for(int i = 1;i<shapes.length;i++)
			{
				if(shapes[i].length() != length)
				{
					throw new IllegalStateException("Each row needs to be the same length");
				}
			}
			shape = shapes;
			return this;
		}
		
		public UUMatterEntry createEntry()
		{
			int totalUUMatter = 0;
			for(int i = 0;i<shape.length;i++)
			{
				String s = shape[i];
				for(int j = 0,m=s.length();j<m;j++)
				{
					totalUUMatter += letters.get(s.charAt(j)) * 1000;
				}
			}
			totalUUMatter /= output.getCount();
			return new UUMatterEntry(output, totalUUMatter);
		}

		public ItemStack getOutput()
		{
			return output.copy();
		}

		public ResourceLocation getId()
		{
			return id;
		}

		public String[] getShape()
		{
			return shape;
		}

		public boolean isSpecial()
		{
			return special;
		}
		
		public boolean isHidden()
		{
			return hidden;
		}

		public Char2IntMap getLetters()
		{
			return letters;
		}
	}
}
