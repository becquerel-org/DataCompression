package DataCompression.tools;

import java.io.DataInput;
import java.io.IOException;
import java.io.EOFException;


public class Distortion {
	
	private DataInput origin;
	private DataInput duplicate;

	Distortion(DataInput origin, DataInput duplicate) throws IOException {
		origin=origin;
		duplicate=duplicate;
		perform();
	}

	private double sqrErr;
	private double mediumSqrErr;
	private double snr;
	private double sigmaSqr;
	private double mediumSigmaSqr;

	private void perform() throws IOException {
		sqrErr=0;
		mediumSqrErr=0;
		sigmaSqr=0;
		mediumSigmaSqr=0;
		snr=0;
		int n=0;
		double x;
		double y;
		try {
		while(true) {
			x=origin.readDouble();
			y=origin.readDouble();
			n++;
			sqrErr+=(x-y)*(x-y);
			sigmaSqr+=x*x;
		}
		} catch (EOFException e) {};
		mediumSqrErr=sqrErr/n;
		mediumSigmaSqr=sigmaSqr/n;
		snr=mediumSqrErr/mediumSigmaSqr;
	}

	/** Returns the medium square error */
	public double getMSE() {
		return mediumSqrErr;
	}

	public double getSNR() {
		return snr;
	}

	public double getLogSNR() {
		return 10*Math.log10(snr);
	}
}
