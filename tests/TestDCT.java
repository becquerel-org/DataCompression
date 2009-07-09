package DataCompression.tests;

import DataCompression.util.DoubleMatrix;
import DataCompression.tools.DCT;

import java.text.DecimalFormat;


public class TestDCT {
	public static void main(String[] argv) {
		DoubleMatrix a = new DoubleMatrix(8);
		for (int i=0; i<8;++i) {
		for (int j=0; j<8;++j) {
			if (j<4) {
				a.set(i,j,10);
			} else {
				a.set(i,j,128);
			}
		}
		}

		DecimalFormat format = new DecimalFormat("###.##");

		System.out.println(a.toString(format));
		DoubleMatrix b = DCT.computeDCT(a);
		System.out.println(b.toString(format));

	}
}
