package DataCompression.tests;

import java.io.File;
import DataCompression.tools.Entropy;
import DataCompression.tools.SimpleAnalysis;
import DataCompression.util.FileReader;

public class TestEntropyCSV {
	public static void main(String[] args) {
		try {
			File f = FileReader.getFile(args);
			Entropy countSingular=new Entropy(new SimpleAnalysis(f));
			Entropy countDual=new Entropy(new SimpleAnalysis(f, 2));
			Entropy countTriple=new Entropy(new SimpleAnalysis(f, 3));

			System.out.println
					("\"" + countSingular.getEntropy() + "\",\""
					      + countDual.getEntropy() + "\",\""
					      + countTriple.getEntropy() + "\"");
		} catch (Exception e) {
			System.out.println(e.toString());
			StackTraceElement trace=e.getStackTrace()[0];
			System.out.println(trace.getFileName()+": "+trace.getLineNumber());
		}
	}
}
