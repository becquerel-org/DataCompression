/** Allows pixelwise reading of a black-white image. */
public interface BWImageReader {
	/** Returns the value of the pixel at position width,height.
			Returns either zero or a non-zero value.
	*/
	public int getPixel(int width, int height);
	/** Return the width of the image.*/
	public int getWidth();
	/** Return the height of the image.*/
	public int getHeight();
}
