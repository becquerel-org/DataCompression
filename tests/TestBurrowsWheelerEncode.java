package DataCompression.tests;

import java.io.File;
import java.lang.Integer;
import DataCompression.tools.BurrowsWheeler;

public class TestBurrowsWheelerEncode {
	public static void main(String[] args)
	{
		try
		{
			BurrowsWheeler t = new BurrowsWheeler();
			
			t.encode (args[0], args[1]);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
