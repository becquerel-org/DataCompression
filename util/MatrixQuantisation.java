package DataCompression.util;

import DataCompression.util.DoubleMatrix;

public class MatrixQuantisation {

	static private int doubleToInt(double x) {
		return (int)((x*100));
	}

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
