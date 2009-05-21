package DataCompression.tests;

import java.io.File;
import java.lang.Integer;
import DataCompression.tools.BurrowsWheeler;

public class TestBurrowsWheelerDecode {
	public static void main(String[] args)
	{
		try
		{
			BurrowsWheeler t = new BurrowsWheeler();
			
			t.decode (args[0], args[1]);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
