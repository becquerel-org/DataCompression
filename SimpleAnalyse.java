import java.lang.Byte;
import java.lang.Long;
import java.io.InputStream;
import java.util.HashMap;



class SimpleAnalyse {
	private boolean[] bytes;
	private HashMap<Byte,Long> frequencies;
	private InpustStream inputStream;
	SimpleAnalyse(InputStream in){
		bytes = new boolean[Byte.MAX_VALUE];
		frequencies = new HashMap<Byte,Long>();
		inputStream = in;
		perform();
	}

	private void perform() {
		byte b;
		
	}
}
