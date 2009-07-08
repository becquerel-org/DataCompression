package DataCompression.util;

import DataCompression.util.MathVector;

public class ColumnVector extends MathVector {

	public ColumnVector(int n) {
		super(n);
	}

	public ColumnVector(double[] d) {
		super(d);
	}

	public boolean equals (ColumnVector oth) {
		return data.equals(oth.data);
	}
}
