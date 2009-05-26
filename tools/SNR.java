package DataCompression.tools;

import DataCompression.util.ObjectReader;

import java.io.DataCompression;
import java.io.IOException;

class SimpleSNR {
	private DataCompression origin;
	private DataCompression duplicate;

	public SimpleSNR(DataInputStream x, DataInputStream y) {
		origin=x;
		duplicate=y;
	}

	float computeSNR() throw IOException {
		double sigmasquare = 0;
		double diffsquare = 0;	
		double x;
		double y;
		int n=0;
		while(true) {
			x=origin.readDouble();
			y=duplicate.readDouble();
			n=n++;
			sigmasquare+=(x*x);
			diffsquare+=((x-y)*(x-y));
		}
		
	}

}

/** Transform objects of type T to an int */
interface Quantify<T> {
	public int meassure(T obj);
}

class SNR<T> {
	
	private ObjectReader<T> reader;

	public SNR(ObjectReader<T> x, ObjectReader) {
		reader=r;
	}
}
