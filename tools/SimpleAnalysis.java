package DataCompression.tools;

import java.lang.Byte;
import java.lang.Long;
import java.io.FileInputStream;
import java.io.File;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;


public class SimpleAnalysis {

	/** Array that holds for each byte the number of its occurrences */
	private long[] bytecount;
	private File inputFile;
	
	public SimpleAnalysis(File inFile) throws IOException {
		inputFile = inFile;
		bytecount = new long[Byte.MAX_VALUE];
		perform();
	}

	protected void perform() throws IOException {
		Byte b;
		int c;
		FileInputStream inputStream=new FileInputStream(inputFile);

		bytecount = new long[Byte.MAX_VALUE];

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
	public long getByteCount() {
		long ret=0;
		for (byte i=0; i < Byte.MAX_VALUE; ++i)
			ret+=bytecount[i];

		return ret;

	}

	public 	HashSet<Byte> getBytes() {
		HashSet<Byte> ret = new HashSet<Byte>();
		for (byte i=0; i < Byte.MAX_VALUE; ++i) {
			if (bytecount[i]>0) {
				ret.add(i);
			}
		}
		return ret;
	}

	public Hashtable<Byte, Long> getByteFrequencies() {
		Hashtable<Byte,Long> ret = new Hashtable<Byte,Long>();
		for (byte i=0; i < Byte.MAX_VALUE; ++i) {
			ret.put(i,bytecount[i]);
		}
		return ret;
	}

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
