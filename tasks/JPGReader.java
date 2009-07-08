package DataCompression.tasks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class JPGReader {

	private BufferedImage image;
	private Raster raster;
	private int width;
	private int height;

	public JPGReader(String inFile) throws IOException {
	  image = ImageIO.read(new File(inFile));
		raster = image.getData();
		width=raster.getWidth();
		height=raster.getHeight();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public void test() throws IOException {
		int[] colors;
		int[] colors2=null;
		//for (int i=0; i<width; i++) {
		//	for (int j=0; j<height; j++) {
		//		colors=raster.getPixel(i,j,colors2);
		//		System.out.print(colors[0]+","+
		//										 colors[1]+","+
		//										 colors[2]+" ");
		//	}
		//	System.out.println("");
		//}
		BufferedImage out = new BufferedImage(width, height, image.getType());
		WritableRaster wr=out.getRaster();
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				colors=raster.getPixel(i,j,colors2);
				System.out.println(colors[0]+","+
												 colors[1]+","+
												 colors[2]+" ");
				colors[0]=colors[0]& 0xFFFFFFFC;
				colors[1]=colors[1]& 0xFFFFFFFC;
				colors[2]=colors[2]& 0xFFFFFFFC;
				wr.setPixel(i,j,colors);
				System.out.println(colors[0]+","+
												 colors[1]+","+
												 colors[2]+" ");
			}
		}
		ImageIO.write(out,"PNG",new File("/tmp/test.png"));
	}

	public static void main(String[] argv) {
		try {
		JPGReader a=new JPGReader(argv[0]);
		a.test();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

}
