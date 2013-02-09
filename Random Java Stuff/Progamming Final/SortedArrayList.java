import java.util.ArrayList;
import java.lang.StringBuilder;

/**
	A dynamic array-based list whose items are stored in sorted order, low to high.
*/
public class SortedArrayList
{
	private int size;
	private Data min;
	private Data max;
	private Data root; // should be min, i might not need this but I'm adding it anyway for now.
	private int biggestItCanBeNow;
	public Data[] array;
	private static final int DEFAULT_SIZE = 1000;
	private static final int GROW_AMOUNT  = 250;
	
	// public ArrayList<Data> arraylist;
	
	
	public SortedArrayList()
	{
		root = null;
		size = 0;
		array = new Data[ DEFAULT_SIZE ];
		biggestItCanBeNow = DEFAULT_SIZE;
	}
	
	public SortedArrayList( int initialArraySize )
	{
		root = null;
		size = 0;
		array = new Data[ initialArraySize ];
		biggestItCanBeNow = initialArraySize;
	}
	
	public Elem add( Elem e )
	{
		if ( e == null ) return null;
		
		int key = e.getKey();
		
		if ( size == 0 ) {
			Data newData = new Data( key );
			root = newData;
			size++;
			array[size] = newData;
			return newData;
		}
		
		boolean placed = false;
		if ( size + 1 >= biggestItCanBeNow ) {
			grow();
		}
		size++;
		Data newData = new Data( key );
		array[size] = newData;
		
		// int index = 1;
		while ( !placed ) {
			if ( array[size].getKey() < array[size / 2].getKey() ) {
				Data smaller = array[size];
				array[ size ] = array[ size / 2 ];
				array[ size / 2 ] = smaller;
			}
			else placed = true;
		}
		return newData;	
	}
	
	/** The remove method is extra credit.  Only try this if you're sure everything
	 	else is finished correctly. */
	public Elem remove( Elem e )
	{
		if ( e == null ) return null;
		if ( isEmpty() || binarySearch(e) == null ) return null;
		
		int key = e.getKey();
		int index = 0;
		Data retdata = array[key];
		// array[key] = null;
		
		
		int left  = 1;
		int right = size;
		boolean found = false;
		while ( !found ) {
			if ( right < left ) return null;
			int middle  = ( left + right ) / 2;
			if ( key == array[middle].getKey() ) {
				array[middle] = null;
				index = middle;
				found = true;
			}
			else if ( key < array[middle].getKey() ) right = middle - 1;
			else left = middle + 1;
		}
		
		// size--;
		
		while ( index < size ) {
			array[index] = array[index + 1];
			index++;
		}
		
		
		boolean placed = false;
		while ( !placed ) {
			if ( array[size].getKey() < array[size / 2].getKey() ) {
				Data smaller = array[size];
				array[ size ] = array[ size / 2 ];
				array[ size / 2 ] = smaller;
			}
			else placed = true;
		}
		array[size] = null;
		size --;
		return retdata;
	}
	
	public void grow() 
	{
		Data[] newArray = new Data[ biggestItCanBeNow + GROW_AMOUNT ];
		biggestItCanBeNow += GROW_AMOUNT;
		System.arraycopy( array, 1, newArray, 1, size );
		array = newArray;
	}
	
	
	public Elem binarySearch( Elem e )
	{
		if ( e == null ) return null;
		int key = e.getKey();
		
		if ( isEmpty() ) return null;
		
		int left  = 1;
		int right = size;
		boolean found = false;
		while ( !found ) {
			if ( right < left ) return null;
			int middle  = ( left + right ) / 2;
			if ( key == array[middle].getKey() ) return array[middle];
			else if ( key < array[middle].getKey() ) right = middle - 1;
			else left = middle + 1;
		}
		// it won't get here...
		return null;
		
		// but shhhh, don't tell.
	}
	
	public Elem minValue()
	{
		if ( isEmpty() ) return null;
		return array[1];
	}
	
	public Elem maxValue()
	{
		if ( isEmpty() ) return null;
		return array[size];
	}
	
	public int size()
	{
		return size;
	}
	
	public void clear()
	{
		size = 0;
		root = null;
		// min  = null;
		// max  = null;
	}
	
	public boolean isEmpty()
	{
		return ( size == 0 );
	}	
	
	public String toString()
	{
		StringBuilder builder  = new StringBuilder();
		for ( int i = 1; i < size + 1; i++ ) {
			// System.out.println( array[i].toString() + "\n" );
			builder.append( array[i].toString() + "\n" );
		}
		return builder.toString();
	}
}
