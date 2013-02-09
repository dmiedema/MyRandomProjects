/*

	This GUI may be used for the Soundex Lab.  There are places to place code for 
	each of the operations necessary.  Alternately, you may add methods to this GUI and just make 
	method calls where it says to place code.  In addition, you can create a separate class for
	your code, add an object of that class, and call methods of that class.

*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class GUI implements ActionListener {
	private JFrame frame;
	
	private JLabel fileNameLabel;
	private JLabel actionLabel;
	
	private JTextField fileNameField;
	private JTextField wordField;
	private JTextArea output;
	
	private JPanel filePanel;
	private JPanel wordPanel;
	public JScrollPane outputPane;
	
	private JButton newButton;
	private JButton openButton;
	private JButton closeButton;
	private JButton insertButton;
	private JButton searchButton;
	
	private Dimension buttonSize;	
	private Container content;

	
	public GUI() {
		frame = new JFrame( "GUI" );
		frame.setBounds( 0, 0, 550, 400 );
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		content = frame.getContentPane();
		content.setLayout( new BorderLayout() );
		buttonSize = new Dimension( 100, 20);
		
		filePanel = new JPanel();
		// fileNameLabel = new JLabel( "File" );
		
		// newButton = new JButton( "create" );
		// newButton.setPreferredSize( buttonSize );
		// newButton.addActionListener( this );
		
		// openButton = new JButton( "open" );
		// openButton.setPreferredSize( buttonSize );
		// openButton.addActionListener( this );
		
		// closeButton = new JButton( "close" );
		// closeButton.setPreferredSize( buttonSize );
		// closeButton.addActionListener( this );

		
		// fileNameField = new JTextField("test.bin", 15 );
		// filePanel.add( fileNameLabel );
		// filePanel.add( fileNameField );
		// filePanel.add( newButton );
		// filePanel.add( openButton );
		// filePanel.add( closeButton );
		
		wordPanel = new JPanel();
		actionLabel = new JLabel( "Numbers" );
		
		insertButton = new JButton( "Solve" );
		insertButton.setPreferredSize( buttonSize );
		insertButton.addActionListener( this );
		
		// searchButton = new JButton( "search" );
		// searchButton.setPreferredSize( buttonSize );
		// searchButton.addActionListener( this );
		
		wordField = new JTextField("4 1 2 3 5 6 10 7 8 0 9 11 12 13 14 15", 20 );
		wordPanel.add( actionLabel );
		wordPanel.add( wordField );
		wordPanel.add( insertButton );
		wordPanel.add( searchButton );
		
		
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
		
		if( source == newButton ) {
			fileName = fileNameField.getText();
			output.append( "Create Button Clicked: file = " + fileName + "\n" );
			//Place code to create a file here
			
		}
		else if( source == openButton ) {
			fileName = fileNameField.getText();
			output.append( "Open Button Clicked: file = " + fileName + "\n" );
			//place code to open a file here
			

		}
		else if( source == closeButton ) {
			output.append( "Close Button Clicked\n" );
			//place code to close a file here
			
		}
		else if( source == insertButton ) {
			word = wordField.getText();
			output.append( "Insert Button Clicked: Word = " + word + "\n" );
			//place code to insert a word here
			
		}
		else if( source == searchButton ) {
			word = wordField.getText();
			output.append( "Search Button Clicked: Word = " + word + "\n" );
			//place code to search for a word here
			
		}
		else
			output.append( "Unknown Event" );
	
	}
	
	public static void main( String[] args ) {
		SoundexGUI gui = new SoundexGUI();
		// System.setOut(gui.output);
		Main main = new Main();
		
	}

}