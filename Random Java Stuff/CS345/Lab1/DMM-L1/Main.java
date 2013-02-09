// Daniel Miedema on 2012-01-27.
// CS345 Lab 1

// GUI doesn't work.  But then again neither does my solver.
// Also, I stole the GUI from the Soundex Demo GUI.  
// I hate writing GUIs... 

/*
	<s>When it tries to solve it attemps Right first, if its valid it does the swap
	then when it goes to test up it tests up. However for some reason when
	it goes to do up, it uses the inital space index but the values in the array
	are as if a right swap happened.</s>
	
	IT WORKS. 
*/

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements ActionListener
{
	// GUI crap
	private JFrame frame;
	
	private JLabel fileNameLabel;
	private JLabel actionLabel;
	
	private JTextField fileNameField;
	private JTextField wordField;
	private JTextArea output;
	
	private JPanel filePanel;
	private JPanel wordPanel;
	public JScrollPane outputPane;
	
	private JButton solveButton;
	
	private Dimension buttonSize;	
	private Container content;
	
	
	// public StateQueue queue;
	public State initialState;
	public String solution;
	private Scanner scanner;
    private Scanner userInput;
    private boolean quit;
	private boolean solutionFound;
	public int[] MainArray;
	private static final boolean DEBUG = false;
	
	
	public Main () {
		scanner = new Scanner(System.in);
		solutionFound = false; 
		solution = "";
		
		//////////////
		
		frame = new JFrame( "16 Puzzle 'Solver'" );
		frame.setBounds( 0, 0, 550, 400 );
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		content = frame.getContentPane();
		content.setLayout( new BorderLayout() );
		buttonSize = new Dimension( 100, 20);
		
		filePanel = new JPanel();
		
		wordPanel = new JPanel();
		actionLabel = new JLabel( "Numbers" );
		
		solveButton = new JButton( "Solve" );
		solveButton.setPreferredSize( buttonSize );
		solveButton.addActionListener( this );
		
		wordField = new JTextField("4 1 2 3 0 5 6 7 8 9 10 11 12 13 14 15", 20 );
		wordPanel.add( actionLabel );
		wordPanel.add( wordField );
		wordPanel.add( solveButton );
		
		
		output = new JTextArea(15, 40);
		outputPane = new JScrollPane( output );
		
		content.add( filePanel, BorderLayout.NORTH );
		content.add( wordPanel, BorderLayout.SOUTH );
		content.add( outputPane, BorderLayout.CENTER );
		frame.pack();
		frame.setVisible( true );
	}
	public void actionPerformed( ActionEvent e ) {
		String word;
		String fileName;
		
		Object source = e.getSource();
		
			
		
		if( source == solveButton ) {
			word = wordField.getText();
			output.append( "Working on solving...");
			// place code to insert a word here
			MainArray = parseInput( word );
			output.append("\nAnd the solution is...\n");
			output.append( solver() + "\n\n\n");
			// done.
		}
		else
			output.append( "Unknown Event" );
	
	}
	public void printWelcome()
    {
        System.out.println("\n\n\nWelcome to the 16-Puzzle Solver");
        System.out.println();
        System.out.println("type \"help\" to get help");
        System.out.println();
        System.out.println("Enter \"q\" to quit");
    }

    public void printHelp()
    {
        System.out.println("\t\tP U Z Z L E   S O L V E R");
        System.out.println("\tEnter in the numbers in order");
		System.out.println("\tPlease enter just the number an a space after it.");
		System.out.println("\tex:'2 3 1 0 4 5 7 8 9 10 12 13 15 16 11 6");
		System.out.println("\tWould represent a puzzle that looked like");
		System.out.println("\t  2   3   1   _");
		System.out.println("\t  4   5   7   8");
		System.out.println("\t  9   10  12  13");
		System.out.println("\t  15  16  11  6");
		printMenu();
    }

	public void printMenu() {
		System.out.println();
		System.out.println("\tEnter the List of Values:");
	
	}
	
	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	private int[] parseInput( String input ) {
		Scanner sc = new Scanner(input);
		int[] returnArray = new int[16];
		int index = 0;
		while ( sc.hasNext() ) {
			if ( sc.hasNextInt() ) {
				returnArray[index] = (int) sc.nextInt();
			}
			index++;
		}
		return returnArray;
	}
	
	private void printPuzzle( int[] inarray ) {
		System.out.println("\t  " + "  " + inarray[0]  + "  " +  inarray[1]  + "  " +  inarray[2]  + "  " +  inarray[3]);
		System.out.println("\t  " + "  " + inarray[4]  + "  " +  inarray[5]  + "  " +  inarray[6]  + "  " +  inarray[7]);
		System.out.println("\t  " + "  " + inarray[8]  + "  " +  inarray[9]  + "  " +  inarray[10] + "  " +  inarray[11]);
		System.out.println("\t  " + "  " + inarray[12] + "  " +  inarray[13] + "  " +  inarray[14] + "  " +  inarray[15]);
	}                                                                                  
	
	private void debugQueue( State state ) {
		int[] arr = state.getArray();
		for ( int i = 0; i < arr.length; i++ )
			System.out.print(arr[i]+", ");
	}
	
	public String solver() {
		// this will solve shit eventually.
		solution = "";
		initialState = new State( MainArray );
		LinkedList<State> queue = new LinkedList<State>();
		queue.offer( initialState );
		int count = 0; 
		if ( DEBUG )  { System.out.print("InitState, "); }
		
		while ( queue.size() > 0 || solutionFound == false ) {
			State currentState = new State();
			currentState = queue.poll();
			if ( DEBUG) printPuzzle( currentState.getArray() );
			if ( currentState.goalState( currentState.getArray() ) ) {
				if ( DEBUG ) System.out.print("GoalFound");
				solutionFound = true;
				solution = currentState.getMoves();
				break;
			}
			
			if ( DEBUG ) {
				count++;
				System.out.println("Moves thus far: " + count);
				System.out.println("not goal state");
				System.out.println( currentState.getMoves() );
				if ( currentState.moveRight( currentState.getSpaceIndex() ) ) System.out.print("R possible  ");
				if ( currentState.moveUp( currentState.getSpaceIndex() ) )    System.out.print("U possible  ");
				if ( currentState.moveLeft( currentState.getSpaceIndex() ) )  System.out.print("L possible  ");
				if ( currentState.moveDown( currentState.getSpaceIndex() ) )  System.out.print("D possible  ");
				System.out.print("SpaceIndex: " + currentState.getSpaceIndex() );
			}
			if ( currentState.moveRight( currentState.getSpaceIndex() ) ) {
				if ( DEBUG ) System.out.print("R added, ");
				State newStateRight = new State( currentState.getArray(), currentState.getSpaceIndex(), currentState.getMoves() );
				newStateRight = newStateRight.swap( newStateRight.getArray(), newStateRight.getSpaceIndex(), 1 );
				if ( newStateRight.goalState( newStateRight.getArray() ) ) {
					newStateRight.addMove("R"); 
					solutionFound = true; 
					solution = newStateRight.getMoves(); 
					return solution; 
				}
				else { newStateRight.addMove("R");  queue.offer( newStateRight ); if ( DEBUG ) debugQueue( newStateRight ); }
			}
			if ( currentState.moveUp( currentState.getSpaceIndex() ) ) {
				if ( DEBUG ) System.out.print("U added, ");
				State newStateUp = new State( currentState.getArray(), currentState.getSpaceIndex(), currentState.getMoves() );
				newStateUp = newStateUp.swap( newStateUp.getArray(), newStateUp.getSpaceIndex(), -4 );
				if ( newStateUp.goalState( newStateUp.getArray() ) ) { 
					newStateUp.addMove("U");
					solutionFound = true; 
					solution = newStateUp.getMoves(); 
					return solution; 
				}
				else { newStateUp.addMove("U"); queue.offer( newStateUp ); if ( DEBUG ) debugQueue( newStateUp ); }
			}
			if ( currentState.moveLeft( currentState.getSpaceIndex() ) ) {
				if ( DEBUG ) System.out.print("L added, ");
				State newStateLeft = new State( currentState.getArray(), currentState.getSpaceIndex(), currentState.getMoves() );
				newStateLeft = newStateLeft.swap( newStateLeft.getArray(), newStateLeft.getSpaceIndex(), -1 );
				if ( newStateLeft.goalState( newStateLeft.getArray() ) ) { 
					newStateLeft.addMove("L");
					solutionFound = true; 
					solution = newStateLeft.getMoves(); 
					return solution; 
				}
				else { newStateLeft.addMove("L"); queue.offer( newStateLeft ); if ( DEBUG ) debugQueue( newStateLeft ); }
			}
			if ( currentState.moveDown( currentState.getSpaceIndex() ) ) {
				if ( DEBUG ) System.out.print("D added, ");
				State newStateDown = new State( currentState.getArray(), currentState.getSpaceIndex(), currentState.getMoves() );
				newStateDown = newStateDown.swap( newStateDown.getArray(), newStateDown.getSpaceIndex(), 4 );
				if ( newStateDown.goalState( newStateDown.getArray() ) ) { 
					newStateDown.addMove("D");
					solutionFound = true; 
					solution = newStateDown.getMoves();  
					return solution; 
				}
				else { newStateDown.addMove("D"); queue.offer( newStateDown ); if ( DEBUG ) debugQueue( newStateDown ); }
			}
		}
		
		if ( solutionFound == true ) {
			return solution;
		} 
		else {
			System.out.println("No solution found.");
			return "No Solution found";
		}
	}
	
	public void runSolver()
    {
        quit = false;

        while (!quit) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput = null;

            try {
                userInput = bufferedReader.readLine();
            } catch (IOException ioe) {
                System.out.println("Something went wrong, the trolls revolt.");
                System.exit( 1 );
            }
            if ( userInput.equals( "q" )) quit = true;
			else if ( userInput.equals( "quit" ) ) quit = true;
			else if ( userInput.equals( "h" ) ) {
				printHelp();
			}
            else if ( userInput.equals( "help" ) ) {
                printHelp();
            }
			
            else { 
              	MainArray = parseInput( userInput );
				// System.out.println(solver());
            }
        }
        System.out.println("Solved it? Good.\nLater.");
    }
	public static void main(String[] args) {
		Main main = new Main();
        try {
            main.runSolver();
        }
        catch (IllegalArgumentException error) {
            System.out.println("\tSeriously? Come on.");
            System.out.println("\trestarting the Soundex... Read the directions this time.");
            System.out.println("\n\tPS - here are the directions...");
            main.printHelp();
            main.runSolver();
        }
	}
}