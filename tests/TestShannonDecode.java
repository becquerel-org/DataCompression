package DataCompression.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import DataCompression.tools.Shannon;

public class TestShannonDecode {
	public static void main(String[] args)
	{
		try 
		{
			Shannon t = new Shannon();

			FileInputStream in = new FileInputStream(args[0]);
			FileOutputStream out = new FileOutputStream(args[1]);

			for (int c = in.read(); c != -1; c = in.read())
			{
				int d = t.decode(c);
				if (d != -1)
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
