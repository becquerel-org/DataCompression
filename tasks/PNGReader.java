package DataCompression.tasks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.Raster;


public class PNGReader implements BWImageReader {

	private BufferedImage image;
	private Raster raster;
	private int width;
	private int height;

	public PNGReader(String inFile) throws IOException {
	  image = ImageIO.read(new File(inFile));
		raster = image.getData();
		width=raster.getWidth();
		height=raster.getHeight();
	}

	public byte getPixel(int i, int j) {
		int[] tmp=new int[2];
		return (byte)raster.getPixel(i,j,tmp)[0];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static void main(String[] argv) {
		try {
		PNGReader a = new PNGReader(argv[0]);
		} catch (Exception e) {
			System.out.println(e.toString());
      StackTraceElement trace=e.getStackTrace()[0];
      System.out.println(trace.getFileName()+": "+trace.getLineNumber());
	  }
	}
}
