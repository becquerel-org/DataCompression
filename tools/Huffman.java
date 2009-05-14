package DataCompression.tools;

class HuffmanBSTNode
{
	public HuffmanBSTNode left;
	public HuffmanBSTNode right;
	public int value;
	
	public HuffmanBSTNode()
	{
		left  = null;
		right = null;
		value = -1;
	}
	
	public boolean contains (int b)
	{
		if (value == b)
		{
			return true;
		}
		else if ((left != null) && left.contains(b))
		{
			return true;
		}
		else if ((right != null) && right.contains(b))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}

/* Shannon encoding */
abstract public class Huffman
{
	protected HuffmanBSTNode tTree = null;
	protected HuffmanBSTNode tCursor = null;

	protected Huffman()
	{
		tTree   = new HuffmanBSTNode();
		tCursor = tTree;
	}
	
	protected void addCode (boolean c[], int v)
	{
		for (int i = 0; i < c.length; i++)
		{
			if (c[i])
			{
				if (tCursor.right == null)
				{
					tCursor.right = new HuffmanBSTNode();
				}

				tCursor = tCursor.right;
			}
			else
			{
				if (tCursor.left == null)
				{
					tCursor.left = new HuffmanBSTNode();
				}

				tCursor = tCursor.left;
			}
		}

		tCursor.value = v;
		tCursor = tTree;
	}
	
	public int encode (int in)
	{
		if (tCursor.value == in)
		{
			tCursor = tTree;
			return -1;
		}
		else if ((tCursor.right != null) && tCursor.right.contains (in))
		{
			tCursor = tCursor.right;
			return '1';
		}
		
		tCursor = tCursor.left;
		return '0';
	}

	public int decode (int in)
	{
		int rv = -1;

		if (in == '1')
		{
			tCursor = tCursor.right;
		}
		else
		{
			tCursor = tCursor.left;
		}
		
		if (tCursor.value != -1)
		{
			rv = tCursor.value;
			tCursor = tTree;
		}
		
		return rv;
	}
}
