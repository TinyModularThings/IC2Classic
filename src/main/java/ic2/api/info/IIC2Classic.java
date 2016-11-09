package ic2.api.info;

import ic2.api.classic.audio.IAudioManager;
import ic2.api.classic.trading.ITradeManager;
import ic2.api.classic.util.ITickCallbackProvider;
import ic2.api.classic.wind.IWindManager;
import net.minecraftforge.common.config.Configuration;

public interface IIC2Classic
{
	public IAudioManager getAudioManager();
	
	public Configuration getConfig();

	public IWindManager getWindManager();
	
	public ITradeManager getTradeManager();
	
	public ITickCallbackProvider getTickProvider();
}
