// Daniel Miedema
// CS345 Lab 3
// 
// le knapsack

/* I'm a pro at recylcing GUIs */

/*
	Memoized Doesn't work yet	
*/

// GUI imports
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

// Other imports
import java.lang.Math;

public class KnapSackSolverDuplicate implements ActionListener {
	
	public static final int EMPTY = -42;
	
	/********************************
	Start GUI Variables
	*********************************/
		// Frame
		private JFrame frame;
		// Text Fields
		private JTextField maxWeightTextField;
		private JTextField objectOneValueTextField;
		private JTextField objectOneWeightTextField;
		private JTextField objectTwoValueTextField;
		private JTextField objectTwoWeightTextField;
		private JTextField objectThreeValueTextField;
		private JTextField objectThreeWeightTextField;
		private JTextField objectFourValueTextField;
		private JTextField objectFourWeightTextField;
		// Labels
		private JLabel maxWeightLabel;
		private JLabel objectValueLabel;
		private JLabel objectWeightLabel;
		private JLabel objectOneLabel;
		private JLabel objectTwoLabel;
		private JLabel objectThreeLabel;
		private JLabel objectFourLabel;
		private JLabel blankLabel;
		// Panels
		private JPanel subView1;
		private JPanel subView2;
		private JPanel subView3;
		private JPanel subView4;
		// Text Area
		public JTextArea output;
		public JScrollPane outputPane;
		// Buttons
		private JButton recursiveSolverButton;
		private JButton dynamicProgrammingSolverButton;
		private JButton memoizedSolutionButton;
		// Other
		private Dimension buttonSize;
		private Container content;
	/********************************
	End GUI Variables
	*********************************/

	private int maxWeight;
	private int maxProfit;
	private int[] profits;
	private int[] weights;
	private int[] profitMemoized;

	private int object1Weight;
	private int object1Profit;
	private int object2Weight;
	private int object2Profit;
	private int object3Weight;
	private int object3Profit;
	private int object4Weight;
	private int object4Profit;

	private static final boolean DEBUG = true;
	

	public KnapSackSolverDuplicate() {
		makeFrame();
	}

	public void actionPerformed( ActionEvent event ) {
		Object source = event.getSource();
		// read text fields and assign values
		maxWeight = Integer.parseInt( maxWeightTextField.getText() );
		if ( DEBUG ) System.out.println("maxWeight: " + maxWeight);

		// set up variables I need in my methods.
		maxProfit = 0;
		profits = initializeArray( maxWeight );
		weights = initializeArray( maxWeight );
		profitMemoized = initializeProfitArrayForMemoized( maxWeight );
		

		
		if( source == recursiveSolverButton ) {
			output.append("Working on solving Recursively...\n");
			

			output.append("\tR E C U R S I V E\n");
			int printProfits = recursiveSolution( maxWeight );
			System.out.println("Max profit recusive : " + printProfits );
			output.append("Max Profit from recursion is: " + printProfits );
		}		
		else if( source == dynamicProgrammingSolverButton ) {
			output.append("Working on solving Dynamically...\n");
			output.append("\tD Y N A M I C A L L Y\n");

			if ( DEBUG ) { System.out.println("maxWeight = " + maxWeight ); }
			dynamicKnapSackSolver main = new dynamicKnapSackSolver( maxWeight );
			dynamicProgrammingSolution( maxWeight );
			// dynamicProgrammingSolution( maxWeight );

			
			// System.out.println("Maximum Profit is: " + profits[ maxWeight ]);
			output.append("Maximum Profit is: " + profits[ maxWeight ] + "\n");

			// int i = maxWeight - 1;
			// while ( i > 0 ) {
			// 	System.out.println("An object of weight " + weights[ i ] + " was used.");
			// 	output.append("An object of weight " + weights[ i ] + " was used." + "\n");
			// 	i -= weights[i];
			// }

		}
		else if( source == memoizedSolutionButton ) {
			output.append("Working on solving Memoized...ly? \n");

			int printProfits = memoizedSolution( maxWeight );
			output.append("\tM E M O I Z E D\n");
			System.out.println("Maximum Profit is: " + printProfits);
			output.append("Maximum Profit is: " + printProfits + "\n");
			int i = maxWeight;
			while ( i > 0 ) {
				System.out.println("An object of weight " + weights[ i ] + " was used.");
				output.append("An object of weight " + weights[ i ] + " was used." + "\n");
				i -= weights[i];
			}
		}
		else
			output.append( "Unknown Event" );
		
	}

	/************************************************
	
	E N D   G U I   C O D E 
	
	*************************************************/
		

	private int getCapacity() {
		return maxWeight;
	}
	private void setCapacity( int newCapacity ) {
		this.maxWeight = newCapacity;
	}
	private int recursiveSolution( int weight ) {
        if ( weight < 0 ) { return -9; }
        else if ( weight < 2 ) { return 0; }
        else {
            int a = 9 + recursiveSolution( weight - 8 );
            int b = 7 + recursiveSolution( weight - 7 );
            int c = 5 + recursiveSolution( weight - 6 );
            int d = 1 + recursiveSolution( weight - 2 );
            int maxOfAandB = Math.max( a, b );
            int maxOfCandD = Math.max( c, d );
            int maxProfit  = Math.max( maxOfAandB, maxOfCandD );
            return maxProfit;
        }
    }
	public void dynamicProgrammingSolution( int weight ) {
        profits = new int[weight];
        weights = new int[weight];
        int a, b, c, d, p, w = 0;

        int currentWeight = 0;
        while (currentWeight < weight ) {
            if ( DEBUG ) System.out.println("currentWeight = " + currentWeight );
            a = 0;
            if( currentWeight - 8 >= 0 ) { a = 9 + profits[ currentWeight - 8 ]; }
            b = 0;
            if( currentWeight - 7 >= 0 ) { b = 7 + profits[ currentWeight - 7 ]; }
            c = 0;
            if( currentWeight - 6 >= 0 ) { c = 5 + profits[ currentWeight - 6 ]; }
            d = 0;
            if( currentWeight - 2 >= 0 ) { d = 1 + profits[ currentWeight - 2 ]; }

            if ( DEBUG ) {
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("c = " + c);
                System.out.println("d = " + d);
            }

            int maxOfAandB = Math.max( a, b );
            int maxOfCandD = Math.max( c, d );
            p = Math.max( maxOfAandB, maxOfCandD );

            if ( DEBUG ) {
                System.out.println("p = " + p);
            }
            
            if ( p == a && p != 0 ) { w = 8; }
            else if ( p == b && p != 0 ) { w = 7; }
            else if ( p == c && p != 0 ) { w = 6; }
            else if ( p == d && p != 0 ) { w = 2; }
            else { w = 2; }
            

            if ( DEBUG ) { System.out.println("w = " + w );}

            profits[ currentWeight ] = p;
            weights[ currentWeight ] = w;
            if ( DEBUG ) { 
                System.out.println("w = " + w); 
                System.out.println("profits[w] = " + profits[currentWeight] );
                System.out.println("weights[w] = " + weights[currentWeight] );
            }
            currentWeight++;
        }
        
        System.out.println("Maximum Profit is: " + profits[ weight -1 ]);
        int i = weight - 1;
        System.out.println(i);
            while ( i >= 0 ) {
                System.out.println("An object of weight " + weights[ i ] + " was used.");
                output.append("An object of weight " + weights[ i ] + " was used." + "\n");
                i -= weights[i];
                // i--;
            }
    } 

	private int memoizedSolution( int weight ) {		
		int d = 0;

		if( weight < 0 ) maxProfit = -999;
		else if ( weight < 2 ) maxProfit = 0;
		else {
			int a, b, c, w = 0;

			if( weight - 8 < 0 ) a = -999;
			else { 
				if( profits[weight - 8] == EMPTY ) profitMemoized[weight - 8] = memoizedSolution( weight - 8 );
				a = 9 + profitMemoized[weight - 8]; 
			}
			if( weight - 7 < 0 ) b = -999;
			else { 
				if( profits[weight - 7] == EMPTY ) profitMemoized[weight - 7] = memoizedSolution( weight - 7 );
				b = 7 + profitMemoized[weight - 7]; 
			}
			if( weight - 6 < 0 ) c = -999;
			else {
				if( profits[weight - 6] == EMPTY ) profitMemoized[weight - 6] = memoizedSolution( weight - 6 );
				c = 5 + profitMemoized[weight - 6];
			}
			if( weight - 2 < 0 ) b = -999;
			else {
				if( profits[weight - 2] == EMPTY ) profitMemoized[weight - 2] = memoizedSolution( weight - 2 );
				d = 1 + profitMemoized[weight - 2];
			}

			int maxOfAandB = Math.max( a, b );
			int maxOfCandD = Math.max( c, d );
			int maxProfit = Math.max( maxOfAandB, maxOfCandD );

			if( maxProfit == a ) w = 8;
			else if( maxProfit == b ) w = 7;
			else if( maxProfit == c ) w = 6;
			else w = 2;

			weights[ weight - 1 ] = w;
		}
		return maxProfit;
	}


	private int[] initializeArray( int size ) {
		int[] array = new int[size];
		for( int i = 0; i < size; i++ ) {
			array[i] = 0;
		}
		return array;
	}
	private int[] initializeProfitArrayForMemoized( int size ) {
		int[] array = new int[size];
		for( int i = 0; i < size; i++ ) { 
			array[i] = EMPTY;
		}
		return array;
	}


	
	private void makeFrame() {
		frame = new JFrame( "KnapSack Solver" );
		frame.setBounds( 75, 0, 750, 900 );
		frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		content = frame.getContentPane();
		content.setLayout( new BorderLayout() );
		buttonSize = new Dimension( 100, 20 );
		
		// set up panels for subviews
		subView1 = new JPanel();
		subView2 = new JPanel();
		subView3 = new JPanel();
		subView4 = new JPanel();
		// set panel layouts 
		subView1.setLayout( new BorderLayout(6, 6) );
		subView2.setLayout( new BorderLayout(6, 6) );
		subView3.setLayout( new BorderLayout(6, 6) );
		subView4.setLayout( new BorderLayout(6, 6) );
		// Set up flow layouts to be added into subview panels
        JPanel flowN1 = new JPanel();
        JPanel flowN2 = new JPanel();
        JPanel flowN3 = new JPanel();
        JPanel flowN4 = new JPanel();
		
		
		// Set up my Labels
		maxWeightLabel    = new JLabel( "Max Weight: " );
		objectValueLabel  = new JLabel( "Value:  " );
		objectWeightLabel = new JLabel( "Weight: " );
		objectOneLabel    = new JLabel( "Object 1        " );
		objectTwoLabel    = new JLabel( "Object 2       " );
		objectThreeLabel  = new JLabel( "Object 3       " );
		objectFourLabel   = new JLabel( "Object 4" );
		blankLabel        = new JLabel( "           " );
		
		// set up Text Fields
		maxWeightTextField          = new JTextField( "17", 8 ); // max weight
		objectOneValueTextField     = new JTextField( "9", 5 );  // object one value
		objectOneWeightTextField    = new JTextField( "8", 5 );  // object one weight
		objectTwoValueTextField     = new JTextField( "7", 5 );  // object two value
		objectTwoWeightTextField    = new JTextField( "7", 5 );  // object two weight
		objectThreeValueTextField   = new JTextField( "5", 5 );  // object three value
		objectThreeWeightTextField  = new JTextField( "6", 5 );  // object three weight
		objectFourValueTextField    = new JTextField( "1", 5 );  // object four value
		objectFourWeightTextField   = new JTextField( "2", 5 );  // object four weight
				                                           
		// create my buttons
		recursiveSolverButton = new JButton( "Recursively Solve" );
		recursiveSolverButton.setPreferredSize( buttonSize );
		recursiveSolverButton.addActionListener( this );
		
		dynamicProgrammingSolverButton = new JButton( "Dynamically Solve" );
		dynamicProgrammingSolverButton.setPreferredSize( buttonSize );
		dynamicProgrammingSolverButton.addActionListener( this );
		
		memoizedSolutionButton = new JButton( "Memoized Solution" );
		memoizedSolutionButton.setPreferredSize( buttonSize );
        memoizedSolutionButton.addActionListener( this );
		
		// Add text fields, labels and buttons to the various views
		// flowNX is adding the items to a panel with a flow layout
		flowN1.add( blankLabel, BorderLayout.NORTH );
		flowN1.add( objectOneLabel, BorderLayout.NORTH );
		flowN1.add( objectTwoLabel, BorderLayout.NORTH );
		flowN1.add( objectThreeLabel, BorderLayout.NORTH );
		flowN1.add( objectFourLabel, BorderLayout.NORTH );
		// added that flow layout panel to my subview (with matching number)
		// to whatever part I want it in.
		subView1.add( flowN1, BorderLayout.NORTH );
		
		flowN2.add( objectValueLabel, BorderLayout.NORTH );
		flowN2.add( objectOneValueTextField, BorderLayout.NORTH );	
		flowN2.add( objectTwoValueTextField, BorderLayout.NORTH );
		flowN2.add( objectThreeValueTextField, BorderLayout.NORTH );
		flowN2.add( objectFourValueTextField, BorderLayout.NORTH );
		subView2.add( flowN2, BorderLayout.NORTH );
		
		flowN3.add( objectWeightLabel, BorderLayout.NORTH );
		flowN3.add( objectOneWeightTextField, BorderLayout.NORTH );
		flowN3.add( objectTwoWeightTextField, BorderLayout.NORTH );
		flowN3.add( objectThreeWeightTextField, BorderLayout.NORTH );
		flowN3.add( objectFourWeightTextField, BorderLayout.NORTH );
		subView3.add( flowN3, BorderLayout.NORTH );
		
		flowN4.add( maxWeightLabel, BorderLayout.NORTH );
		flowN4.add( maxWeightTextField, BorderLayout.NORTH );
		flowN4.add( recursiveSolverButton, BorderLayout.NORTH );
		flowN4.add( dynamicProgrammingSolverButton, BorderLayout.NORTH );
		flowN4.add( memoizedSolutionButton, BorderLayout.NORTH );
		subView4.add( flowN4, BorderLayout.NORTH );
		
		// set up output pane and scrolling text field of amazingness
		output     = new JTextArea( 40, 40 );
		outputPane = new JScrollPane( output );
		
		// append instructions to output so they load in the window
		output.append("\tW e l c o m e   t o   t h e   K n a p   S a c k  S o l v e r \n\n");
		output.append("          Put in the weights and values of the different objects\n");
		output.append("          As well as the maximum weight of the knap sack.\n");
		output.append("          Then choose which method you'd like to use to solve it and...\n");
		output.append("          then tada, it'll solve it.\n\n\n");
		
		
		content.add( subView1, BorderLayout.NORTH );
		
		subView1.add( subView2, BorderLayout.CENTER );
		subView2.add( subView3, BorderLayout.CENTER );
		subView3.add( subView4, BorderLayout.CENTER );
		subView4.add( outputPane, BorderLayout.CENTER );
		frame.pack();
		frame.setVisible( true );
	}
	
	
	
	public static void main(String[] args) {
		KnapSackSolverDuplicate main = new KnapSackSolverDuplicate();
	}
}