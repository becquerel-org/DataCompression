package DataCompression.tasks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.Raster;

import java.io.IOException;



public class BWImageTester {
	
	private BWImageReader original;
	private BWImageReader compressed;

	public BWImageTester(String ori, BWImageReader comp) throws IOException {
		original=new PNGReader(ori);
		compressed=comp;
	}

	

	protected boolean comparePixel(int i, int j) {
		if ((original.getPixel(i,j)==0 && compressed.getPixel(i,j)==0) ||
				(original.getPixel(i,j)>0 && compressed.getPixel(i,j)>0)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean compare() {
		if (original.getHeight()!=compressed.getHeight() ||
  			original.getWidth()!=compressed.getWidth()) {
				System.err.println("Size or Width do not match!");
				return false;
		}
		int width=original.getWidth();
		int height=original.getHeight();
		boolean ret=true;
		for (int i=0; i<width;++i) {
			for (int j=0; j<height;++j) {
				if (!comparePixel(i,j)) {
					System.err.println("Difference at (" + i + ", " + j+")");
					ret=false;
  			}
			}
		}
		return ret;
	}

}
