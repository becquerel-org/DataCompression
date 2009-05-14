package DataCompression.tools;

/* Move-to-Front Transformation */
public class MTF
{
	int order[];
	
	public MTF()
	{
		order = new int [256];
		for (int i = 0; i < 256; i++)
		{
			order[i] = i;
		}
	}
	
	public int encode (int in)
	{
		int out;
		
		for (out = 0; (out < 256) && (order[out] != in); out++);

		for (int i = out; i > 0; i--)
		{
			order[i] = order[i - 1];
		}

		order[0] = in;

		return out;
	}

	public int decode (int in)
	{
		int out = order[in];

		for (int i = in; i > 0; i--)
		{
			order[i] = order[i - 1];
		}

		order[0] = out;

		return out;
	}
}
