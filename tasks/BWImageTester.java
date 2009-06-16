package DataCompression.tasks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.Raster;

import java.io.IOException;



public class BWImageTester {
	
	private PNGReader original;
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

}
