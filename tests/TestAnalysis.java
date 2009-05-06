package DataCompression.tests;

import java.io.File;
import DataCompression.tools.SimpleAnalysis;
import DataCompression.util.FileReader;

public class TestAnalysis {
	public static void main(String[] args) {
		try {
			File f = FileReader.getFile(args);
			SimpleAnalysis countSingular=new SimpleAnalysis(f);
			System.out.println(countSingular.toString());
			SimpleAnalysis countDual=new SimpleAnalysis(f, 2);
			System.out.println(countDual.toString());
			SimpleAnalysis countTriple=new SimpleAnalysis(f, 3);
			System.out.println(countTriple.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
