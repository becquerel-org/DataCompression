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


/**
 * Simple analysis of a file.
 * Checks which bytes occur in a file and their frequency.
 */
public class SimpleAnalysis {
	/** contains the counts for each symbol */
	protected Hashtable<Long, Long> bytecount;
	protected int bps;

	/** The file to be analysed. */
	private File inputFile;
	
	/**
	 * @param inFile    supposed to exist and to be readable.
	 * @param byteCount the number of bytes per symbol
	 */
	public SimpleAnalysis(File inFile, int byteCount) throws IOException {
		inputFile = inFile;
		bps = byteCount;
		perform();
	}

	/**
	 * @param inFile supposed to exist and to be readable.
	 */
	public SimpleAnalysis(File inFile) throws IOException {
		inputFile = inFile;
		bps = 1;
		perform();
	}

	protected void increaseCount (Long cl)
	{
		Long v = bytecount.get(cl);
		if (v == null)
		{
			bytecount.put    (cl, new Long (1));
		}
		else
		{
			bytecount.remove (cl);
			bytecount.put    (cl, new Long (v + 1));
		}
	}

	/**
	 * Performs the analysis.
	 */
	protected void perform() throws IOException {
		Byte b;
		FileInputStream inputStream=new FileInputStream(inputFile);
		
		bytecount = new Hashtable<Long, Long>();

		try {
			long t = 0, tc = 1;
			for (int c = inputStream.read(); c != -1; c = inputStream.read())
			{
				t = ((t << 8) + c);
				if (tc == bps)
				{
					increaseCount (new Long(t));
					t  = 0;
					tc = 1;
				}
				else
				{
					tc++;
				}
			}
			
			if (tc != 1)
			{
				t = t << (8 * (bps - tc));
				increaseCount (new Long(t));
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
		
		for (Enumeration<Long> e = bytecount.elements(); e.hasMoreElements();)
		{
			Long v = e.nextElement();
			ret += v;
		}
		
		return ret;

	}

	/**
	 * Returns the set of bytes that the file contains.
	 */
	public 	HashSet<Long> getBytes() {
		HashSet<Long> ret = new HashSet<Long>();
		
		for (Enumeration<Long> e = bytecount.keys(); e.hasMoreElements();)
		{
			Long v = e.nextElement();
			ret.add(v);
		}

		return ret;
	}

	/**
	 * Returns a Hashtable containing for each byte how often it occurres in the file.
	 */
	public Hashtable<Long, Long> getByteFrequencies() {
		return bytecount;
	}

	/**
	 * String representation of the results.
	 */
	public String toString() {
		String ret =  "Read "+this.getByteCount()+" Symbols\n";
		ret +="Occuring symbols: "+this.getBytes().toString()+"\n";
		ret +="Characters: [";
		for (Iterator<Long> it = this.getBytes().iterator(); it.hasNext();)
		{
			ret+=(char)it.next().byteValue()+", ";
		}
		ret = ret.substring(0,ret.length()-2)+"]\n";
		ret +="Symbol distribution: {";
		Hashtable<Long,Long> freq=this.getByteFrequencies();
		Long b;
		for (Iterator<Long> it = this.getBytes().iterator(); it.hasNext();)
		{
			b=it.next();
			ret += b.toString()+"="+freq.get(b).toString()+", ";
		}
		
		return ret.substring(0,ret.length()-2)+"}\n";
	}

}
