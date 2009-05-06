package DataCompression.tests;

import java.io.File;
import DataCompression.tools.Entropy;
import DataCompression.tools.SimpleAnalysis;
import DataCompression.util.FileReader;

public class TestEntropy {
	public static void main(String[] args) {
		try {
			File f = FileReader.getFile(args);
			Entropy countSingular=new Entropy(new SimpleAnalysis(f));
			System.out.println(countSingular.toString());
			Entropy countDual=new Entropy(new SimpleAnalysis(f, 2));
			System.out.println(countDual.toString());
			Entropy countTriple=new Entropy(new SimpleAnalysis(f, 3));
			System.out.println(countTriple.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
			StackTraceElement trace=e.getStackTrace()[0];
			System.out.println(trace.getFileName()+": "+trace.getLineNumber());
		}
	}
}
