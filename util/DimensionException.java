package DataCompression.util;

public class DimensionException extends IndexOutOfBoundsException {
	public DimensionException(String s) {
		super(s);
	}

	public static String multiplicationError(int i,int j,int k,int l) {
		return "Cannot multiply " + i + " times " + j +
			" by a " + k + " times " + l + " matrix!"; 
	}
}
