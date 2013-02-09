public class StateQueue {
	//instance variables
	// private String solutionString;
	public State state;
	public State next;
	public State prev;
	
	public StateQueue () {
		this(null);
	}
	public StateQueue ( State newState ) {
		this( newState, null, null );
	}
	public StateQueue ( State newState, State nextState, State previousState ) {
		state = newState;
		next = nextState;
		prev = previousState;
	}
}