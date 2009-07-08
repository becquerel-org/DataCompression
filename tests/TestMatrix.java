package DataCompression.tests;

import DataCompression.util.Matrix;
import DataCompression.util.ColumnVector;
import DataCompression.util.RowVector;


public class TestMatrix {
	public static void main(String[] argv) {
		Matrix a = new Matrix(3);
		a.set(0,1,1);
		a.set(1,2,1);
		a.set(2,0,1);

		Matrix b=a.transpose();

		Matrix c = a.multiply(b);

		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(c.toString());

		double[] d = {3,2,1};
		ColumnVector v = new ColumnVector(d);

		RowVector u = a.multiply(v);

		for (int i=0; i<u.size()-1; ++i) {
			System.out.print(u.get(i));
			System.out.print(", ");
		}
		System.out.println(u.get(u.size()-1));
	}
}
