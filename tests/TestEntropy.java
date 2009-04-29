package DataCompression.tests;

import java.io.File;
import DataCompression.tools.Entropy;
import DataCompression.util.FileReader;

public class TestEntropy {
	public static void main(String[] args) {
		try {
			File f = FileReader.getFile(args);
			Entropy count=new Entropy(f);
			System.out.println(count.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
