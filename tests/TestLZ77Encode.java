package DataCompression.tests;

import DataCompression.tools.LZ77;

public class TestLZ77Encode {
	public static void main(String[] args)
	{
		try
		{
			LZ77 t = new LZ77(100, 100);
			
			t.encode (args[0], args[1]);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
