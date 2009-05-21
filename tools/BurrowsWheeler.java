package DataCompression.tools;

import java.util.Comparator;
import java.util.Arrays;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class intArrayComparator implements Comparator<int[]>
{
	public int compare(int[] o1, int[] o2)
	{
		int j = (o1.length < o2.length) ? o1.length : o2.length;

		for (int i = 0; i < j; i++)
		{
			if (o1[i] < o2[i]) return -1;
			if (o2[i] < o1[i]) return 1;
		}
		
		if (o1.length < o2.length) return -1;
		if (o2.length < o1.length) return 1;
		return 0;
	}
}

/* Burrows-Wheeler Transformation */
public class BurrowsWheeler
{
	public static int EndOfLine = 257;
	
	public int[] encode (int in[])
	{
		int t1[] = new int[in.length + 1];

		for (int i = 0; i < in.length; i++)
		{
			t1[i] = in[i];
		}
		t1[in.length] = BurrowsWheeler.EndOfLine;

		int transformations[][] = new int[t1.length][t1.length];

		for (int i = 0; i < t1.length; i++)
		{
			for (int j = 0; j < t1.length; j++)
			{
				int index = ((j - i) < 0) ? (t1.length + (j - i)) : (j - i);

				transformations[i][j] = t1[index];
			}
		}

		int t2[] = new int[t1.length];

		Arrays.sort(transformations, new intArrayComparator());

		for (int i = 0; i < t1.length; i++)
		{
			t2[i] = transformations[i][in.length];
		}

		return t2;
	}
	
	public void encode (int in[], FileOutputStream out) throws java.io.IOException
	{
		in = encode (in);

		for (int i = 0; i < in.length; i++)
		{
			if (in[i] == BurrowsWheeler.EndOfLine)
			{
				out.write ((i >> 24) & 0xff);
				out.write ((i >> 16) & 0xff);
				out.write ((i >> 8)  & 0xff);
				out.write ((i)       & 0xff);
				break;
			}
		}
		for (int i = 0; i < in.length; i++)
		{
			if (in[i] != BurrowsWheeler.EndOfLine)
			{
				out.write (in[i]);
			}
		}
	}

	public void encode (FileInputStream in, FileOutputStream out) throws java.io.IOException
	{
		Vector<Integer> d = new Vector<Integer>();
		int count = 0;

		for (int c = in.read(); c != -1; c = in.read())
		{
			d.add (new Integer(c));
			count++;
		}

		int tr[] = new int[count];

		for (int i = 0; i < count; i++)
		{
			tr[i] = d.elementAt(i).intValue();
		}

		encode (tr, out);
	}

	public void encode (int in[], String out) throws java.io.IOException
	{
		encode (in, new FileOutputStream(out));
	}

	public void encode (String in, String out) throws java.io.IOException
	{
		encode (new FileInputStream(in), new FileOutputStream(out));
	}

/*	protected void printArray(int in[][])
	{
		for (int a = 0; a < in.length; a++)
		{
			for (int b = 0; b < in[a].length; b++)
			{
				System.out.print ((char)in[a][b]);
			}
			System.out.println ("");
		}
		System.out.println ("--");
	}*/

	protected void printArray(int in[])
	{
		for (int a = 0; a < in.length; a++)
		{
			System.out.print ((char)in[a]);
		}
		System.out.println ("");
	}

	public int[] decode (int in[])
	{
		int t1[][] = new int[in.length][1];

		for (int i = 0; i < in.length; i++)
		{
			t1[i][0] = in[i];
		}

/*		printArray (in);
		printArray (t1);*/

		Arrays.sort(t1, new intArrayComparator());

		for (int i = 0; i < (in.length - 1); i++)
		{
			int t2[][] = new int[in.length][i+2];

/*			printArray (t1);*/

			for (int j = 0; j < in.length; j++)
			{
				t2[j][0] = in[j];

				for (int k = 0; k < t1[j].length; k++)
				{
					t2[j][k + 1] = t1[j][k];
				}
			}

			Arrays.sort(t2, new intArrayComparator());

			t1 = t2;
		}

/*		printArray (t1);*/

		for (int i = 0; i < in.length; i++)
		{
			if (t1[i][(in.length - 1)] == EndOfLine)
			{
				int r[] = new int[in.length - 1];

				for (int j = 0; j < r.length; j++)
				{
					r[j] = t1[i][j];
				}

/*				printArray (t1[i]);
				printArray (r);*/

				return r;
			}
		}

		int t[] = new int[0];

 		return t;
	}

	public void decode (int in[], FileOutputStream out) throws java.io.IOException
	{
		in = decode (in);

		for (int i = 0; i < in.length; i++)
		{
			out.write (in[i]);
		}
	}

	public void decode (FileInputStream in, FileOutputStream out) throws java.io.IOException
	{
		Vector<Integer> d = new Vector<Integer>();
		int count = 0, eofat = 0;

		try
		{
			eofat = (in.read() << 24) | (in.read() << 16) |
			        (in.read() << 8)  | in.read();
		}
		catch (IOException e)
		{
			throw e;
		}

		for (int c = in.read(); c != -1; c = in.read())
		{
			d.add (new Integer(c));
			count++;
		}

		if (count == 0)
		{
			return;
		}

		int tr[] = new int[count + 1];

		for (int i = 0, j = 0; i < count; i++, j++)
		{
			if (j == eofat)
			{
				tr[j] = BurrowsWheeler.EndOfLine;
				j++;
			}

			tr[j] = d.elementAt(i).intValue();
		}

		decode (tr, out);
	}

	public void decode (int in[], String out) throws java.io.IOException
	{
		decode (in, new FileOutputStream(out));
	}

	public void decode (String in, String out) throws java.io.IOException
	{
		decode (new FileInputStream(in), new FileOutputStream(out));
	}
}
