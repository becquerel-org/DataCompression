package DataCompression.tools;

import java.lang.Byte;
import java.lang.Long;
import java.io.FileInputStream;
import java.io.File;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Enumeration;
import java.io.IOException;

import DataCompression.tools.SimpleAnalysis;


/**
 * Computes the entropy of a file.
 * Checks which bytes occur in a file and their frequency.
 */
public class Entropy {

	/** The file to be analysed. */
	private SimpleAnalysis analysis;
	private double entropy;
	private int shanon;
	private Hashtable<Long, Double> probs;

	/**
	 * Takes a SimpleAnalysis. Contains sufficient information to
	 * compute the entropy.
	 */
	public Entropy(SimpleAnalysis s) {
		entropy=0;
		shanon=0;
		probs = new Hashtable<Long,Double>();
		analysis = s;
		perform();
	}

	/**
	 * Performs the analysis.
	 */
	protected void perform() {
		long noBytes = analysis.getByteCount();
		Hashtable<Long, Long> freqs = analysis.getByteFrequencies();
		probs = new Hashtable<Long,Double>();
		Long b;
		for (Iterator<Long> it = analysis.getBytes().iterator();
				it.hasNext();) {
			b=it.next();
			probs.put(b,((double)freqs.get(b)) / noBytes);
				}
		entropy=computeEntropy(probs);
	}

	/**
	 * Returns the entropy of the file.
	 */
	public double getEntropy() {
		return entropy;
	}

	/**
	 * Returns the minimal code length.
	 */
	public long minimalCodeLength() {
		return (long)Math.ceil(entropy*analysis.getByteCount());
	}

	public String toString() {
		long expected = this.minimalCodeLength();
		String ret = "Entropy: "+ entropy + "\n";
		ret += "Minimal code length " + expected + " bits\n";
		ret +="Symbol probabilities: {";
		Long b;
		for (Iterator<Long> it = analysis.getBytes().iterator();
				it.hasNext();) {
			b=it.next();
			ret += b.toString()+"="+probs.get(b).toString()+", ";
				}

		return ret.substring(0,ret.length()-2)+"}\n";

	}


	public static double computeEntropy(Hashtable<Long, Double> distribution) {
		double ret=0;
		double prob=0;
		for (Enumeration<Double> e = distribution.elements();
				e.hasMoreElements();) {
			prob=e.nextElement();
			ret+= prob * Math.log(1/prob)/Math.log(2);
				}
		return ret;
	}
}
