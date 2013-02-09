
/** A simple Elem that stores a single integer key. */

public class Data implements Elem
{
	private int key;
	
	public Data( int key )
	{
		this.key = key;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public String toString()
	{
		return "Data: key = " + key;
	}
	
}
