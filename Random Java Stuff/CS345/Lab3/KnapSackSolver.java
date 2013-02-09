// Daniel Miedema
// CS345 Lab 3
// 
// le knapsack

/* I'm a pro at recylcing GUIs */

/*
	It seems to work, but memoized doesn't seem to *always* print out all it's elements.
	Sometimes it will, sometimes it won't.  Not sure what I did wrong but...
	Everything else works and it works half the time-ish (it seems).	
*/

// GUI imports
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

// Other imports
import java.lang.Math;

public class KnapSackSolver implements ActionListener {
	
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

	public int object1Weight;
	public int object1Profit;
	public int object2Weight;
	public int object2Profit;
	public int object3Weight;
	public int object3Profit;
	public int object4Weight;
	public int object4Profit;

	private static final boolean DEBUG = true;
	

	public KnapSackSolver() {
		makeFrame();
	}

	public void actionPerformed( ActionEvent event ) {
		Object source = event.getSource();
		// read text fields and assign values
		maxWeight     = Integer.parseInt( maxWeightTextField.getText() );
		object1Profit = Integer.parseInt( objectOneValueTextField.getText() );
		object1Weight = Integer.parseInt( objectOneWeightTextField.getText() );
		object2Profit = Integer.parseInt( objectTwoValueTextField.getText() );
		object2Weight = Integer.parseInt( objectTwoWeightTextField.getText() );
		object3Profit = Integer.parseInt( objectThreeValueTextField.getText() );
		object3Weight = Integer.parseInt( objectThreeWeightTextField.getText() );
		object4Profit = Integer.parseInt( objectFourValueTextField.getText() );
		object4Weight = Integer.parseInt( objectFourWeightTextField.getText() );

		if ( DEBUG ) { 
			System.out.println("maxWeight: " + maxWeight);
			System.out.println("object1Value: "  + object1Profit);
			System.out.println("object1Weight: " + object1Weight);
			System.out.println("object2Value: "  + object2Profit);
			System.out.println("object2Weight: " + object2Weight);
			System.out.println("object3Value: "  + object3Profit);
			System.out.println("object3Weight: " + object3Weight);
			System.out.println("object4Value: "  + object4Profit);
			System.out.println("object4Weight: " + object4Weight);
		}

		// set up variables I need in my methods.
		// maxProfit = 0;
		// profits = initializeArray( maxWeight );
		// weights = initializeArray( maxWeight );
		// profitMemoized = initializeProfitArrayForMemoized( maxWeight );
		

		
		if( source == recursiveSolverButton ) {
			maxProfit = 0;
			output.append("Working on solving Recursively...\n");
			output.append("\tR E C U R S I V E\n");

			int printProfits = recursiveSolution( maxWeight );

			System.out.println("Max profit recusive : " + printProfits );
			output.append("Max Profit from recursion is: " + printProfits + "\n");
		}		
		else if( source == dynamicProgrammingSolverButton ) {
			maxProfit = 0;
			profits = initializeArray( maxWeight );
			weights = initializeArray( maxWeight );
			
			output.append("Working on solving Dynamically...\n");
			output.append("\tD Y N A M I C A L L Y\n");

			if ( DEBUG ) { System.out.println("maxWeight = " + maxWeight ); }
			dynamicProgrammingSolution( maxWeight );
			
			output.append("Maximum Profit is: " + profits[ maxWeight - 1] + "\n");
		}
		else if( source == memoizedSolutionButton ) {
			maxProfit = 0;
			weights = initializeArray( maxWeight );
			profitMemoized = initializeProfitArrayForMemoized( maxWeight );
			
			output.append("Working on solving Memoized...ly? \n");

			int printProfits = memoizedSolution( maxWeight );
			output.append("\tM E M O I Z E D\n");
			System.out.println("Maximum Profit is: " + printProfits);
			int i = maxWeight - 1;
			while ( i > 0 ) {
				System.out.println("An object of weight " + weights[ i ] + " was used.");
				output.append("An object of weight " + weights[ i ] + " was used." + "\n");
				// if ( weights[i] == 0 ) i--;
				i -= weights[i];
			}
			output.append("Maximum Profit is: " + printProfits + "\n");
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
            int a = object1Profit + recursiveSolution( weight - object1Weight );
            int b = object2Profit + recursiveSolution( weight - object2Weight );
            int c = object3Profit + recursiveSolution( weight - object3Weight );
            int d = object4Profit + recursiveSolution( weight - object4Weight );
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
            if( currentWeight - object1Weight >= 0 ) { a = object1Profit + profits[ currentWeight - object1Weight ]; }
            b = 0;
            if( currentWeight - object2Weight >= 0 ) { b = object2Profit + profits[ currentWeight - object2Weight ]; }
            c = 0;
            if( currentWeight - object3Weight >= 0 ) { c = object3Profit + profits[ currentWeight - object3Weight ]; }
            d = 0;
            if( currentWeight - object4Weight >= 0 ) { d = object4Profit + profits[ currentWeight - object4Weight ]; }

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
            
            if ( p == a && p != 0 ) { w = object1Weight; }
            else if ( p == b && p != 0 ) { w = object2Weight; }
            else if ( p == c && p != 0 ) { w = object3Weight; }
            else if ( p == d && p != 0 ) { w = object4Weight; }
            else { w = object4Weight; }
            

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
                if (i - weights[i] < 0 ) break;
                i -= weights[i];
                // i--;
            }
    } 

	private int memoizedSolution( int weight ) {
		System.out.println("memoized");	
		int d = 0;
		int a, b, c, w, p = 0;

		if( weight < 0 ) maxProfit = -999;
		else if ( weight < 2 ) maxProfit = 0;
		else {			
			if( weight - object1Weight < 0 ) a = -999;
			else { 
				if( profitMemoized[weight - object1Weight] == EMPTY ) profitMemoized[weight - object1Weight] = memoizedSolution( weight - object1Weight );
				a = object1Profit + profitMemoized[weight - object1Weight]; 
			}
			if( weight - object2Weight < 0 ) b = -999;
			else { 
				if( profitMemoized[weight - object2Weight] == EMPTY ) profitMemoized[weight - object2Weight] = memoizedSolution( weight - object2Weight );
				b = object2Profit + profitMemoized[weight - object2Weight]; 
			}
			if( weight - object3Weight < 0 ) c = -999;
			else {
			 	if( profitMemoized[weight - object3Weight] == EMPTY ) profitMemoized[weight - object3Weight] = memoizedSolution( weight - object3Weight );
				c = object3Profit + profitMemoized[weight - object3Weight];
			}
			if( weight - object4Weight < 0 ) b = -999;
			else {
				if( profitMemoized[weight - object4Weight] == EMPTY ) profitMemoized[weight - object4Weight] = memoizedSolution( weight - object4Weight );
				d = object4Profit + profitMemoized[weight - object4Weight];
			}

			int maxOfAandB = Math.max( a, b );
			int maxOfCandD = Math.max( c, d );
			maxProfit = Math.max( maxOfAandB, maxOfCandD );

			if ( DEBUG ) {
                System.out.println("p = " + maxProfit );
            }

			if( maxProfit == a && maxProfit != 0 ) w = object1Weight;
			else if( maxProfit == b && maxProfit != 0 ) w = object2Weight;
			else if( maxProfit == c && maxProfit != 0 ) w = object3Weight;
			else w = object4Weight;

			if ( DEBUG ) { 
                System.out.println("w = " + w); 
                System.out.println("profits[w] = " + profitMemoized[weight - 1] );
                System.out.println("weights[w] = " + weights[weight - 1] );
            }

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
		KnapSackSolver main = new KnapSackSolver();
	}
}