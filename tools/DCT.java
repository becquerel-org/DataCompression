package DataCompression.tools;

import java.lang.Math;
import DataCompression.util.DoubleMatrix;

/**
 *	Discrete Cosinus Transformation
 * */
public abstract class DCT {
	
	static private double c(int n) {
		if (n==0) {
			return Math.sqrt(2);
		} else {
			return 1;
		}
	}

	/**
	 * Computes transformation matrix for dimension n.
	 */
	static private DoubleMatrix computeTransformationMatrix(int n) {
		DoubleMatrix ret = new DoubleMatrix(n);
		for (int i=0; i<n; ++i) {
			for (int j=0; j<n; ++j) {
				double val;
				if (i==0)
					val=Math.sqrt(1.0/n);
				else
					val=Math.sqrt(2.0/n)*Math.cos((2*j+1)*i*Math.PI/(2*n));
				ret.set(i,j,val);
			}
		}
		return ret;
	}

	// Why the f*** doesn't this work?
	/**
	 * Should compute the DCT but it does not, why?
	 */
	public DoubleMatrix computeF(DoubleMatrix m) {
		int dimension=m.rows();
		DoubleMatrix ret = new DoubleMatrix(dimension);
		for (int x =0; x<dimension; ++x) {
			for (int y=0; y<dimension; ++y) {
				double val=2*(double)(c(x)*c(y))/(double)dimension;
				System.err.print(x + " " + y + ": " + val + " ");
				double sum=0;
				for (int i =0; i<dimension; ++i) {
					for (int j=0; j<dimension; ++j) {
						sum+=m.at(i,j)*Math.cos((2*i+1)*x*Math.PI/(2*dimension))*Math.cos((2*j+1)*y*Math.PI/(2*dimension));
					}
				}
				val*=sum;
				ret.set(x,y,val);
				System.err.println(val);
			}
		}

		return ret;
		
	}

	/**
	 * Computes DCT via matrix multiplication.
	 */
	static public DoubleMatrix computeDCT(DoubleMatrix m) {
		DoubleMatrix a= computeTransformationMatrix(m.rows());
		DoubleMatrix b=a.transpose();
		System.err.println(a);
		System.err.println(b);

		return a.multiply(m.multiply(b));
	}

	/**
	 * Computes inverse DCT via matrix multiplication.
	 */
	static public DoubleMatrix computeDCTinverse(DoubleMatrix m) {
		DoubleMatrix a= computeTransformationMatrix(m.rows());
		DoubleMatrix b=a.transpose();
		System.err.println(a);
		System.err.println(b);

		return b.multiply(m.multiply(a));
	}
}
