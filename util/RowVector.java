package DataCompression.util;

import DataCompression.util.MathVector;

public class RowVector extends MathVector {

	public RowVector(int n) {
		super(n);
	}

	public RowVector(double[] d) {
		super(d);
	}

	public boolean equals (RowVector oth) {
		return data.equals(oth.data);
	}
}
