package DataCompression.tasks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.Raster;


public class BWImageCompressor {

	BufferedImage image;
	Raster raster;
	int width;
	int height;
	

	public BWImageCompressor(String inFile, String outFile) throws IOException {
	  image = ImageIO.read(new File(inFile));
		raster = image.getData();
		width=raster.getWidth();
		height=raster.getHeight();
	}

	protected int readPixel(int i, int j) {
		int[] tmp=new int[2];
		return raster.getPixel(i,j,tmp)[0];
	}


	public void compress() {
	}


	public void write() {
	}

	public static void main(String[] argv) {
		try {
		BWImageCompressor a = new BWImageCompressor(argv[0], argv[0]+".bw c");
		a.compress();
		} catch (Exception e) {
			System.out.println(e.toString());
      StackTraceElement trace=e.getStackTrace()[0];
      System.out.println(trace.getFileName()+": "+trace.getLineNumber());
	  }
	}
}
