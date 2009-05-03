package DataCompression.tools;

import java.lang.Byte;
import java.lang.Long;
import java.io.FileInputStream;
import java.io.File;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;


/**
 * Simple analysis of a file.
 * Checks which bytes occur in a file and their frequency.
 */
public class SimpleAnalysis {

	/** Array that holds for each byte the number of its occurrences */
	private long[] bytecount;
	/** The file to be analysed. */
	private File inputFile;
	
	/**
	 * @param inFile supposed to exist and to be readable.
	 */
	public SimpleAnalysis(File inFile) throws IOException {
		inputFile = inFile;
		bytecount = new long[Byte.MAX_VALUE*2+2];
		perform();
	}

	/**
	 * Performs the analysis.
	 */
	protected void perform() throws IOException {
		Byte b;
		int c;
		FileInputStream inputStream=new FileInputStream(inputFile);

		bytecount = new long[Byte.MAX_VALUE*2+2];

		try {
			c=inputStream.read();
			while (c!=-1) {
				bytecount[c]=bytecount[c]+1;
				c=inputStream.read();
			}
		} finally {
			inputStream.close();
		}
	}

	/**
	 * Returns the number of bytes read in the file.
	 */
	public long getByteCount() {
		long ret=0;
		for (byte i=0; i < Byte.MAX_VALUE; ++i)
			ret+=bytecount[i];

		return ret;

	}

	/**
	 * Returns the set of bytes that the file contains.
	 */
	public 	HashSet<Byte> getBytes() {
		HashSet<Byte> ret = new HashSet<Byte>();
		for (byte i=0; i < Byte.MAX_VALUE; ++i) {
			if (bytecount[i]>0) {
				ret.add(i);
			}
		}
		return ret;
	}

	/**
	 * Returns a Hashtable containing for each byte how often it occurres in the file.
	 */
	public Hashtable<Byte, Long> getByteFrequencies() {
		Hashtable<Byte,Long> ret = new Hashtable<Byte,Long>();
		for (byte i=0; i < Byte.MAX_VALUE; ++i) {
			ret.put(i,bytecount[i]);
		}
		return ret;
	}

	/**
	 * String representation of the results.
	 */
	public String toString() {
		String ret =  "Read "+this.getByteCount()+" Bytes\n";
		ret +="Occuring bytes: "+this.getBytes().toString()+"\n";
		ret +="Characters: [";
		for (Iterator<Byte> it = this.getBytes().iterator(); it.hasNext();) {
			ret+=(char)it.next().byteValue()+", ";
		}
		ret = ret.substring(0,ret.length()-2)+"]\n";
		ret +="Byte distribution: {";
		Hashtable<Byte,Long> freq=this.getByteFrequencies();
		Byte b;
		for (Iterator<Byte> it = this.getBytes().iterator();
				it.hasNext();) {
			 b=it.next();
			ret += b.toString()+"="+freq.get(b).toString()+", ";
		}
		
		return ret.substring(0,ret.length()-2)+"}\n";
	}

}
