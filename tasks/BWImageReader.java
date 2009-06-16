package DataCompression.tasks;

public interface BWImageReader {
	public byte getPixel(int i, int j);
	public int getWidth();
	public int getHeight();
}
