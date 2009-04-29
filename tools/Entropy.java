package DataCompression.tools;

import java.lang.Byte;
import java.lang.Long;
import java.io.FileInputStream;
import java.io.File;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
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
	private Hashtable<Byte, Double> probs;
	/**
	 * @param inFile supposed to exist and to be readable.
	 */
	public Entropy(File inFile) throws IOException {
		analysis = new SimpleAnalysis(inFile);
		entropy=0;
		shanon=0;
		probs = new Hashtable<Byte,Double>();
		perform();
	}

	public Entropy(SimpleAnalysis s) {
		analysis = s;
	}

	/**
	 * Performs the analysis.
	 */
	protected void perform() {
		long noBytes = analysis.getByteCount();
		Hashtable<Byte, Long> freqs = analysis.getByteFrequencies();
		probs = new Hashtable<Byte,Double>();
		Byte b;
                for (Iterator<Byte> it = analysis.getBytes().iterator();
                                it.hasNext();) {
                        b=it.next();
                        probs.put(b,((double)freqs.get(b)) / noBytes);
                }
		entropy=0;
		for (Iterator<Byte> it = analysis.getBytes().iterator();
                                it.hasNext();) {
			b=it.next();
			double prob=probs.get(b);
			entropy+= prob * Math.log(1/prob)/Math.log(2);
		}
	}

	public double getEntropy() {
		return entropy;
	}

	public String toString() {
		long expected=(long)Math.ceil(entropy*analysis.getByteCount());
		String ret = "Entropy: "+ entropy + "\n";
		ret += "Minimal code length " + expected + "\n";
                ret +="Byte probabilities: {";
                Byte b;
                for (Iterator<Byte> it = analysis.getBytes().iterator();
                                it.hasNext();) {
                        b=it.next();
                        ret += b.toString()+"="+probs.get(b).toString()+", ";
                }

                return ret.substring(0,ret.length()-2)+"}\n";

	}
}
