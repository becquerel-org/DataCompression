package DataCompression.util;

import DataCompression.util.DoubleMatrix;

public class MatrixQuantisation {

	/**
	 * Round double to int.
	 */
	static private int doubleToInt(double x) {
		return (int)((x*100));
	}

	/**
	 * Inverse to doubleToInt
	 */
	static private double intToDouble(int i) {
		return ((double)i)/100;
	}

	/**
	 * Transform each entry of data to an int and divide by
	 * corresponding entry in quant.
	 */
	static public int[][] pack(DoubleMatrix data, int[][] quant) {
		int[][] ret =  new int[data.rows()][data.columns()];
		
		for (int i=0; i<data.rows(); ++i) {
			for (int j=0; j<data.columns(); ++j) {
				ret[i][j]= MatrixQuantisation.doubleToInt(
						(int)(data.at(i,j)/quant[i][j]+0.5)
						);
			}
		}
		return ret;
	}

	/**
	 * Compute inverse to pack.
	 */
	static public DoubleMatrix unpack(int[][] data, int[][] quant) {
		DoubleMatrix ret = new DoubleMatrix(data.length,data[0].length);

		for (int i=0; i<data.length; ++i) {
			for (int j=0; j<data[0].length; ++j) {
				ret.set(i,j,data[i][j]*quant[i][j]);
			}
		}
		return ret;
	}	
}
