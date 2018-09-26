package ic2.api.classic.addon;

import net.minecraftforge.fml.common.ModMetadata;

public interface IModModul extends IModul
{
	/**
	 * Return either {@code null} if you want to use a mcmod.info file or a
	 * default metadata. Otherwise return a proper ModMetadata object here.
	 * 
	 * @return your module metadata
	 */
	public ModMetadata createModMetadata();
}
