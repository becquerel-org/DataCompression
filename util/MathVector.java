package DataCompression.util;


public class MathVector {
	protected double[] data;

	public MathVector(int n) {
		data = new double[n];
	}

	public MathVector(double[] d) {
		data = new double[d.length];
		for (int i=0; i<d.length; i++)
			data[i]=d[i];
	}

	public int size() {
		return data.length;
	}

	public double get(int i) throws IndexOutOfBoundsException {
		return data[i];
	}

	public void set(int i, double value) throws IndexOutOfBoundsException {
		data[i]=value;
	}
}
