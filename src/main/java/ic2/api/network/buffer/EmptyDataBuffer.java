package ic2.api.network.buffer;

public class EmptyDataBuffer implements INetworkDataBuffer
{
	public static final EmptyDataBuffer INSTANCE = new EmptyDataBuffer();
	@Override
	public void write(IOutputBuffer buffer) {}
	@Override
	public void read(IInputBuffer buffer) {}
}
