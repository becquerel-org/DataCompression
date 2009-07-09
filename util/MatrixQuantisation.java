package DataCompression.util;

import DataCompression.util.DoubleMatrix;

public class MatrixQuantisation {

	private int doubleToInt(double x) {
		return (int)Math.round((x*100));
	}

	static public int[][] pack(DoubleMatrix data, DoubleMatrix quant) {
		return new int[data.rows()][data.columns()];
	}
}
