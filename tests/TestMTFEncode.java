package DataCompression.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import DataCompression.tools.MTF;

public class TestMTFEncode {
	public static void main(String[] args)
	{
		try 
		{
			MTF t = new MTF();
			FileInputStream in = new FileInputStream(args[0]);
			FileOutputStream out = new FileOutputStream(args[1]);

			for (int c = in.read(); c != -1; c = in.read())
			{
				out.write (t.encode(c));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
