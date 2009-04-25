import java.lang.Byte;
import java.lang.Long;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.HashSet;
import java.io.IOException;


class SimpleAnalysis {
	private long[] bytecount;
	private InputStream inputStream;
	SimpleAnalysis(InputStream in) throws IOException {
		inputStream = in;
		bytecount = new long[Byte.MAX_VALUE];
		perform();
	}

	private void perform() throws IOException {
		Byte b;
		int c;

		bytecount = new long[Byte.MAX_VALUE];

		try {
			c=inputStream.read();
			while (c!=-1) {
				bytecount[c]=bytecount[c]+1;
				c=inputStream.read();
			}
		} finally {
			inputStream.reset();
		}
	}

	HashSet<Byte> getBytes() {
		HashSet<Byte> ret = new HashSet<Byte>();
		for (byte i=0; i < Byte.MAX_VALUE; ++i) {
			if (bytecount[i]>0) {
				ret.add(i);
			}
		}
		return ret;
	}

	Hashtable<Byte, Long> getByteCount() {
		Hashtable<Byte,Long> ret = new Hashtable<Byte,Long>();
		for (byte i=0; i < Byte.MAX_VALUE; ++i) {
			ret.put(i,bytecount[i]);
		}
		return ret;
	}

}
