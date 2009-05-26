package DataCompression.tools;

import DataCompression.util.ObjectInputStream;
import DataCompression.util.Pair;
import java.io.IOException;
import java.io.EOFException;
import java.util.Hashtable;
import java.lang.Long;

// TODO Unfinished.

/**
Take two streams of objects and compute mutual information and entropy.
*/
public class PairAnalysis<T> {

	private ObjectInputStream<T> xInput;
	private ObjectInputStream<T> yInput;

	private Hashtable<Pair<T>, Long> counts;
	private int count;

	public PairAnalysis(ObjectInputStream<T> x, ObjectInputStream<T> y) throws IOException {
		xInput=x;
		yInput=y;
		count=0;
	}

	void perform() throws  IOException {
		counts=new Hashtable<Pair<T>, Long>();
		T x;
		T y;
		int n=0;
		try {
		  while(xInput.hasNext() && yInput.hasNext()) {
				x=xInput.readNext();
				y=yInput.readNext();
				n++;
				Pair<T> p = new Pair<T>(x,y);
				Long v=counts.get(p);
				counts.put(p, new Long(v+1));
      }
	  } catch(EOFException e) {}
	}	
}
