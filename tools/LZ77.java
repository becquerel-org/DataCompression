package DataCompression.tools;

import java.util.Vector;
import java.util.Iterator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* LZ77 Algorithm */
/* Search buffer indices are from right to left, code buffer indices are from
   left to right.
   This implementation is rather simple/primitive, the maximum window size is
   255 bytes. */
public class LZ77
{
	int searchBufferSize;
	int previewBufferSize;

	public LZ77(int s, int p)
	{
		searchBufferSize  = s;
		previewBufferSize = p;
	}

	public int[] encode (int in[])
	{
		Vector<int[]> r = new Vector<int[]>();

		for (int i = 0; i < in.length; i++)
		{
			boolean didInsert = false;

			for (int j = ((i - searchBufferSize) <= 0 ? 0 : (i - searchBufferSize));
			     j < i; j++)
			{
				if (in[i] == in[j])
				{
					int k = 0;

					while (((i + k) < (in.length-1)) && (in[(i + k)] == in[(j + k)]))
					{
						k++;
					}

					int n[] = new int[3];

					n[0] = i - j;
					n[1] = k;
					i += k;
					n[2] = in[i];

					r.add (n);

					didInsert = true;
					break;
				}
			}
			
			if (!didInsert)
			{
				int n[] = new int[3];

				n[0] = 0;
				n[1] = 0;
				n[2] = in[i];

				r.add (n);
			}
		}

		int ri[] = new int[(r.size() * 3)];
		
		Iterator<int[]> i = r.iterator();
		for (int j = 0; i.hasNext(); j += 3)
		{
			int[] n = i.next();
			ri[j]   = n[0];
			ri[j+1] = n[1];
			ri[j+2] = n[2];
		}

		return ri;
	}

	public void encode (int in[], FileOutputStream out) throws java.io.IOException
	{
		in = encode (in);

		for (int i = 0; i < in.length; i++)
		{
			out.write (in[i]);
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

	public int[] decode (int in[])
	{
		Vector<Integer> out = new Vector<Integer>();

		for (int i = 0; i < in.length; i += 3)
		{
			int l = in[i];
			int k = in[i+1];

			for (int j = 0; j < k; j++)
			{
				out.add (out.elementAt(out.size() - l));
			}
			
			out.add (new Integer(in[i+2]));
		}

		int ri[] = new int[out.size()];
		
		Iterator<Integer> i = out.iterator();
		for (int j = 0; i.hasNext(); j++)
		{
			int n = i.next();
			ri[j] = n;
		}

		return ri;
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

		for (int c = in.read(); c != -1; c = in.read())
		{
			d.add (new Integer(c));
			count++;
		}

		if (count == 0)
		{
			return;
		}

		int tr[] = new int[count];

		for (int i = 0; i < count; i++)
		{
			tr[i] = d.elementAt(i).intValue();
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
