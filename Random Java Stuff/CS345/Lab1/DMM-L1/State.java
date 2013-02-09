// Daniel Miedema
// CS345 Lab 1

public class State {
	// variables
	private String solutionString;
	public int[] array;
	public int spaceIndex;
	public String moves;
	private char prevMove;
	private static final boolean DEBUG = false;
		
	public State(  ) {
		// initialize
	}
	public State( int[] inarray ) {
		this.array = inarray;
		spaceIndex = findSpaceIndex( inarray );
		resetMoves();
	}
	public State( int[] inarray, int inspaceIndex, String inmoves ) {
		this.array = new int[16];
		for ( int i = 0; i < 16; i++ ) {
			this.array[i] = inarray[i];
		}
		this.spaceIndex = findSpaceIndex(inarray);
		this.moves = inmoves;
	}
	public int findSpaceIndex ( int[] inarray ) {
		int returnint = -1;
		for ( int i = 0; i < inarray.length; i++ ) {
			if ( inarray[i] == 0 ) returnint = i;
		}
		return returnint;
	}
	public int[] getArray() {
		return array;
	}
	public int getSpaceIndex() {
		return spaceIndex;
	}
	public void setArray( int[] intarray ) {
		array = intarray;
	}
	public void setSpaceIndex( int newIndex ) {
		spaceIndex = newIndex;
	}
	public char getPrevMove( String moves ) {
		if ( moves == null ) { return 'z'; }
		if ( moves.length() == 0 ) return '-';
		moves = moves.toUpperCase();
		char[] c = moves.toCharArray();
		return c[c.length - 1]; 
	}
	public String getMoves() {
		return moves;
	}
	public void addMove( String newMove ) {
		moves = moves + newMove;
	}
	public void resetMoves() {
		moves = "";
	}
	public State swap ( int[] swapArray, int spindex, int swapAmount ) {
		if ( goalState( swapArray ) ) {
			done();
			State state = new State( swapArray, spindex, moves );
			return state;
		}
		// store values
		if ( DEBUG ) { 
			System.out.print("\n\t\t\tspace index: " + spaceIndex + " passedInIndex: " + spindex + " swapamount: "+ swapAmount + "\n");
			System.out.print("\n\t   array beforeSwap: ");
			for ( int i = 0; i < swapArray.length; i++ )
				System.out.print(swapArray[i]+" ");
			}
		int[] copyArray = new int[16];
		System.arraycopy( swapArray, 0, copyArray, 0, 16 );
		int tmp = copyArray[spindex + swapAmount];
		int org = copyArray[spindex];
		if ( DEBUG ) System.out.print("\n\tswapping: "+ tmp +" with: " + org +"\n");
		// swap em
		copyArray[spindex + swapAmount ] = org;
		copyArray[spindex]               = tmp;
		
		spindex = spindex + swapAmount;
		// setSpaceIndex( spindex );
		if ( DEBUG ) { 
			System.out.print("\n\t   array afterSwap: ");
			for ( int i = 0; i < copyArray.length; i++ )
				System.out.print(copyArray[i]+" ");
			System.out.print("spaceindexNow: "+ spaceIndex );
		}
		// make new state
		// public static void arraycopy(Object source, int sourcePosition, 
		// Object destination, int destinationPosition, int numberOfElements)
		// int[] copyArray = new int[16];
		// System.arraycopy( swapArray, 0, copyArray, 0, 16 );
		if ( DEBUG ) {
			System.out.print("    ");
			for ( int i = 0; i < copyArray.length; i++ )
				System.out.print(copyArray[i]+" ");
		}
		State state = new State( copyArray, spindex, moves);
		// state.setSpaceIndex( state.findSpaceIndex( state.getArray() ) );
		
		if ( DEBUG ) {
			System.out.print("Moves of previous state " + getMoves() );
			System.out.print("moves of NEW state: "+ state.getMoves() );
		}
		return state;
	}
	public boolean goalState( int[] arr ) {
		boolean soFarSoGood = false;
		for ( int i = 0; i < array.length; i++ ) {
			if ( arr[i] == i ) soFarSoGood = true;
			else { soFarSoGood = false; break; }
		}
		return soFarSoGood;
	}
	
	public boolean moveUp( int spindex ) {
		if ( getPrevMove( moves ) == 'D' ) return false;
		if ( spindex < 4 ) return false;
		else {
			return true;
		}
	}
	public boolean moveDown( int spindex ) {
		if ( getPrevMove( moves ) == 'U' ) return false;
		if ( spindex > 11 ) return false;
		else {
			return true; 	 	
		}
	}
	public boolean moveLeft( int spindex ) {
		if ( getPrevMove( moves ) == 'R' )  return false;
		if ( ( spindex % 4) == 0) return false;
		else {
			return true;
		}
	}
	public boolean moveRight( int spindex ) {
		if ( getPrevMove( moves ) == 'L' ) return false;
		if ( ( spindex % 4) == 3 ) return false;
		else {
			return true;
		}
	}
	public void done () {
		System.out.println("Solution Found!");
		System.out.println( moves );
	}
	/*
		Right       Space % 4 != 3 	Swap( A[Space], A[Space + 1] ), Space += 1
		Left        Space % 4 != 0  Swap( A[Space], A[Space - 1] ), Space -= 1
		Up          Space > 3       Swap( A[Space], A[Space - 4] ),	Space -= 4
		Down        Space < 12      Swap( A[Space], A[Space + 4] ), Space += 4
	*/
}