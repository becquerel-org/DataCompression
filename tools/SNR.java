package DataCompression.tools;

import java.io.DataInput;
import java.io.IOException;
import java.io.EOFException;

public class SNR {
	private DataInput origin;
	private DataInput duplicate;

	public SNR(DataInput x, DataInput y) {
		origin=x;
		duplicate=y;
	}

	double computeSNR() throws IOException {
		double sigmasquare = 0;
		double diffsquare = 0;	
		double x;
		double y;
		int n=0;
		try {
				while(true) {
					x=origin.readDouble();
					y=duplicate.readDouble();
					n=n++;
					sigmasquare+=(x*x);
					diffsquare+=((x-y)*(x-y));
				}
		} catch(EOFException e) {};
		double ret=0;
		ret = (1/((double)n)*sigmasquare)/(1/((double)n)*diffsquare);
		return ret;
	}

}
