package ic2.api.network.buffer;

public interface INetworkDataBuffer
{
	void write(IOutputBuffer buffer);
	
	void read(IInputBuffer buffer);
}
