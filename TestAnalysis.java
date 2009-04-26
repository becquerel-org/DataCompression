package DataCompression;

import java.io.File;
import DataCompression.SimpleAnalysis;
import DataCompression.util.FileReader;

public class TestAnalysis {
	public static void main(String[] args) {
		try {
			File f = FileReader.getFile(args);
			SimpleAnalysis count=new SimpleAnalysis(f);
			System.out.println(count.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
