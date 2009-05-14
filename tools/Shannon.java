package DataCompression.tools;

import DataCompression.tools.Huffman;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.HashSet;
import java.lang.Math;

/* Shannon encoding */
public class Shannon extends Huffman
{
	public Shannon()
	{
		super();
		initialise();
	}

	public Shannon(Hashtable<Long, Long> frequencies, Long max)
	{
		super();
		initialise(frequencies, max);
	}

	/*! construct the basic tree with equal probabilities */
	public void initialise()
	{
		double symbols = 256;
		/* <symbols> potential codes, so each has a probability of 1/<symbols>,
		   with the Shannon P_i = i/<symbols>. */
		for (int i = 0; i < symbols; i++)
		{
			double l_i = -1 * (Math.log(1/symbols) / Math.log(2));
			double P_i = i/symbols;
			int il_i = (int)Math.ceil(l_i) + 1;
			
			boolean c[] = new boolean[il_i];
			
			double cpi = P_i;

			for (int j = 1; j < il_i; j++)
			{
				double e = Math.pow (2, -j);
				if ((cpi - e) < 0)
				{
					c[j] = false;
				}
				else
				{
					c[j] = true;
					cpi -= e;
				}
			}

			addCode (c, i);
		}
	}
	
	public void initialise (Hashtable<Long, Long> frequencies, Long max)
	{
		double probabilities[] = new double[256];
		max += 256;

		for (int i = 0; i < 256; i++)
		{
			probabilities[i] = 1 / (double)256;
		}

		HashSet<Long> ret = new HashSet<Long>();
		for (Enumeration<Long> e = frequencies.keys(); e.hasMoreElements();)
		{
			Long v = e.nextElement();
			ret.add(v);
		}

		for (Iterator<Long> it = ret.iterator(); it.hasNext();)
		{
			Long b=it.next();

			probabilities[b.intValue()] = (frequencies.get(b) + 1)/(double)max;
		}
		
		int order[] = new int [256];
		
		for (int i = 0; i < 256; i++)
		{
			double highest = 0.0;
			int id = 0;

			for (int j = 0; j < 256; j++)
			{
				if (probabilities[j] > highest)
				{
					boolean add = true;

					for (int k = 0; k < i; k++)
					{
						if (j == order[k]) add = false;
					}

					if (add)
					{
						highest = probabilities[j];
						id = j;
					}
				}
			}
			
			order[i] = id;
		}

		double symbols = 256;
		for (int i = 0; i < symbols; i++)
		{
			double l_i = -1 * (Math.log(probabilities[order[i]]) / Math.log(2));
			double P_i = 0;
			for (int j = 0; j < i; j++)
			{
				P_i += probabilities[order[j]];
			}
			
			int il_i = (int)Math.ceil(l_i) + 1;

			boolean c[] = new boolean[il_i];
			
			double cpi = P_i;

			for (int j = 1; j < il_i; j++)
			{
				double e = Math.pow (2, -j);
				if ((cpi - e) < 0)
				{
					c[j] = false;
				}
				else
				{
					c[j] = true;
					cpi -= e;
				}
			}

			addCode (c, i);
		}
	}
}
