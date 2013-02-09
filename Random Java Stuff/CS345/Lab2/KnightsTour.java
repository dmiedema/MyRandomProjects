// Daniel Miedema 
// CS345 Lab 2
// 
// created 2012-02-17

/*
	Again, my GUI is ripped from Lab1, which that ones GUI was ripped from the Soundex Demo provided.
*/

// utility imports
import java.util.Stack;
import java.util.Scanner;
import java.util.StringTokenizer;

// GUI imports
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class KnightsTour implements ActionListener {
	
	/********************************
	Start GUI Shit
	*********************************/
		// Frame
		private JFrame frame;
		// Text Fields
		private JTextField xCoordinateField;
		private JTextField yCoordinateField;
		private JTextField dimensionField;
		// Labels
		private JLabel xCoordinateLabel;
		private JLabel yCoordinateLabel;
		private JLabel dimensionLabel;
		// Panels
		private JPanel upperPanel;
		private JPanel lowerPanel;
		// Text Area
		private JTextArea output;
		public JScrollPane outputPane;
		// Buttons
		private JButton initButton;
		// Other
		private Dimension buttonSize;
		private Container content;
	/********************************
	End GUI Shit
	*********************************/
	
	// Other Class Variables
		// Stack for Moves
		private Stack movesStack;
		// Integers I need
		private int startX;
		private int startY;
		private int dimensions;
		private int numberOfMovesNecessaryToWinTheTour;
		// Booleans, end of tour and boolean array
		private boolean tourFound;
		private boolean[][] visited;
	
	// DEBUG variable
	private static final boolean DEBUG = true;
	
	public KnightsTour() {
		frame = new JFrame( "Knights Tour" );
		frame.setBounds( 75, 0, 750, 900 );
		frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		content = frame.getContentPane();
		content.setLayout( new BorderLayout() );
		buttonSize = new Dimension( 100, 20 );
		
		// set up panels
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		
		// Set up my Labels
		xCoordinateLabel = new JLabel( "X: ");
		yCoordinateLabel = new JLabel( "Y: ");
		dimensionLabel   = new JLabel( "M x M : " );
		
		// create my one button
		initButton = new JButton( "Run It" );
		initButton.setPreferredSize( buttonSize );
		initButton.addActionListener( this );
		
		// set up Text Fields
		xCoordinateField = new JTextField( "0", 2 );
		yCoordinateField = new JTextField( "0", 2 );
		dimensionField   = new JTextField( "5", 4 );
		
		// add X and Y coordinate fields and Labels to upper panel
		upperPanel.add( xCoordinateLabel );
		upperPanel.add( xCoordinateField );
		upperPanel.add( yCoordinateLabel );
		upperPanel.add( yCoordinateField );
		upperPanel.add( dimensionLabel );
		upperPanel.add( dimensionField );
		upperPanel.add( initButton );
		// add board dimensions label, text field and my button to lower panel
		// lowerPanel.add( dimensionLabel );
		// lowerPanel.add( dimensionField );
		// lowerPanel.add( initButton );
		
		// set up output pane and scrolling text field of amazingness
		output     = new JTextArea( 40, 40 );
		outputPane = new JScrollPane( output );
		
		// append instructions to output so they load in the window
		output.append("\tWelcome to the Knights Tour Solver\n");
		output.append("Insert the starting X coordinate and Y coordinate in the appropriate boxes.\n");
		output.append("Also, put in the size length ex. input 5 would be a 5 x 5 board.\n\n");
		
		
		content.add( upperPanel, BorderLayout.NORTH );
		// content.add( lowerPanel, BorderLayout.SOUTH );
		content.add( outputPane, BorderLayout.CENTER );
		frame.pack();
		frame.setVisible( true );
	}
	
	public void actionPerformed( ActionEvent event ) {
		Object source = event.getSource();
		
		if( source == initButton ) {
			// do the shit this needs to
			output.append("Working on solving...\n");
			
			movesStack = new Stack();
			
			startX     = Integer.parseInt( xCoordinateField.getText() );
			startY     = Integer.parseInt( yCoordinateField.getText() );
			dimensions = Integer.parseInt( dimensionField.getText() );
			
			visited = new boolean[dimensions][dimensions];
			initializeBooleanArray();
			
			runTheTour( startX, startY, dimensions );
			
			if( tourFound ) printMovesBecauseATourWasFound();
		}
		else
			output.append( "Unknown Event" );
	}
	
	
	/*****************************************************************
	End GUI Code and onto Logic Code
		This could be seperated from this class so my Model and my View were seperate
		but... That'd take more effort and I've already got everything in here.
	*****************************************************************/
	
	
	private void initializeBooleanArray() {
		for ( int i = 0; i < dimensions; i++ ) {
			for ( int j = 0; j < dimensions; j++ ) {
				visited[i][j] = false;
			}
		}
	}
	private void printMovesBecauseATourWasFound() {
		// pop off stack and print them to the output in the GUI
		while ( !movesStack.empty() ) {
			String string = (String) movesStack.pop();
			output.append( string + "\n");
		}
		output.append("SOLVED! Because I'm Awesome!\n\n\n");
	}
	
	public void runTheTour( int startingX, int startingY, int dimensions ) {
		// initialize things and begin movin
		numberOfMovesNecessaryToWinTheTour = (dimensions * dimensions) - 1;
		int movesSoFar = 0;
		tourFound      = false;
		// movin( startingX, startingY, movesSoFar );
		if ( !movin( startingX, startingY, movesSoFar ) ) {
			output.append("No Solution.  Mulligan.\n");
		}
	}
	
	private boolean movin( int xCoordinate, int yCoordinate, int numberOfMovesSoFar) {
		if( ( xCoordinate < 0 ) || ( xCoordinate >= dimensions ) ||
			( yCoordinate < 0 ) || ( yCoordinate >= dimensions ) ) {
				return false;
			}
		if( visited[xCoordinate][yCoordinate] ) return false;
		
		if( numberOfMovesSoFar == numberOfMovesNecessaryToWinTheTour ) {
			// done, do shit.
			tourFound = true;
			String moveString = "( " + xCoordinate + ", " + yCoordinate + " )\n";
			movesStack.push( moveString );
			visited[xCoordinate][yCoordinate] = true;
			return true;
		}
		else {
			visited[xCoordinate][yCoordinate] = true;
			
			boolean localResult = false;
			
			localResult = localResult || movin( xCoordinate + 2, yCoordinate + 1, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate + 2, yCoordinate - 1, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate - 2, yCoordinate + 1, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate - 2, yCoordinate - 1, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate + 1, yCoordinate + 2, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate + 1, yCoordinate - 2, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate - 1, yCoordinate + 2, numberOfMovesSoFar + 1 );
			localResult = localResult || movin( xCoordinate - 1, yCoordinate - 2, numberOfMovesSoFar + 1 );
			
			if( localResult ) {
				String moveString = "( " + xCoordinate + ", " + yCoordinate + " )";
				movesStack.push(moveString);
				return true;
			}
			else {
				visited[xCoordinate][yCoordinate] = false;
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		KnightsTour main = new KnightsTour();
	}
	
}