package DataCompression.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import DataCompression.tools.Shannon;
import DataCompression.tools.SimpleAnalysis;

public class TestShannonEncode {
	public static void main(String[] args)
	{
		try 
		{
			Shannon t = new Shannon();

			FileInputStream in = new FileInputStream(args[0]);
			FileOutputStream out = new FileOutputStream(args[1]);

			for (int c = in.read(); c != -1; c = in.read())
			{
				for (int d = t.encode (c); d != -1; d = t.encode(c))
				{
					out.write (d);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
