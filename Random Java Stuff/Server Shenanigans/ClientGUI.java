/*
	Daniel Miedema
	CS 372 Lab 2

*/

// Utility imports I need
import java.net.*;
import java.io.*;

// GUI imports
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

// ripped my GUI from previous labs
public class ClientGUI implements ActionListener {

	/*********************************
			G U I   S T U F F
	*********************************/
		// Frame
		private JFrame frame;
		// Text Fields
		private JTextField ipAddressField;
		private JTextField portField;
		private JTextField clientSubmissionField;
		// Labels
		private JLabel ipAddressLabel;
		private JLabel portLabel;
		private JLabel clientSubmissionLabel;
		// Panel
		private JPanel upperPanel;
		private JPanel lowerPanel;
		// Text Area
		private JTextArea output;
		public JScrollPane outputPane;
		// Buttons
		private JButton connectButton;
		private JButton submitTextButton;
		private JButton clearServerOutputPaneButton;
		// Other
		private Dimension buttonSize;
		private Container content;
	/*********************************
			E N D   G U I
	*********************************/

	// Other Class Variables
	private String textToSubmit;
	private String ipAddress;
	private int portNumber;

	public Socket sock;


	// DEBUG variable
	private static final boolean DEBUG = true;

	public ClientGUI() {
		// set up frame and container
		frame = new JFrame( "Client" );
		frame.setBounds( 75, 0, 750, 900 );
		frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		content = frame.getContentPane();
		content.setLayout( new BorderLayout() );
		buttonSize = new Dimension( 100, 20 );

		// set up panels
		upperPanel = new JPanel();
		lowerPanel = new JPanel();

		// set up Labels
		ipAddressLabel        = new JLabel( "IP: " );
		portLabel             = new JLabel( "Port: " );
		clientSubmissionLabel = new JLabel( "Enter Text: " );

		// Create Buttons
		connectButton               = new JButton( "Connect" );
		connectButton.setPreferredSize( buttonSize );
		connectButton.addActionListener( this );
		submitTextButton            = new JButton( "Submit" );
		submitTextButton.setPreferredSize( buttonSize );
		submitTextButton.addActionListener( this );
		clearServerOutputPaneButton = new JButton( "Clear Output" );
		clearServerOutputPaneButton.setPreferredSize( buttonSize );
		clearServerOutputPaneButton.addActionListener( this );

		// set up Text Fields
		ipAddressField        = new JTextField ( "199.193.232.20", 14 );
		portField             = new JTextField ( "65534", 6 );
		clientSubmissionField = new JTextField ( "some Text", 24 );

		// Add instructions, IP & Port label and fields to upper panel
		upperPanel.add( ipAddressLabel );
		upperPanel.add( ipAddressField );
		upperPanel.add( portLabel );
		upperPanel.add( portField );
		upperPanel.add( connectButton );
		// add text submission field and label to lower panel
		lowerPanel.add( clientSubmissionLabel );
		lowerPanel.add( clientSubmissionField );
		lowerPanel.add( submitTextButton );
		lowerPanel.add( clearServerOutputPaneButton );

		// set up output pane and scrolling text field
		output     = new JTextArea( 40, 40 );
		outputPane = new JScrollPane( output );

		// append anything necessary to the output pane here.
		output.append("\tClient GUI!\n");
		output.append("Enter the IP and The Port\n");
		output.append("of the server into the correct fields.\n");
		output.append("Submit text in the submit\n");
		output.append("text field.\n\n");

		// add Panels and TextArea to BorderLayout
		content.add( upperPanel, BorderLayout.NORTH );
		content.add( lowerPanel, BorderLayout.SOUTH );
		content.add( outputPane, BorderLayout.CENTER );

		frame.pack();
		frame.setVisible( true );
	}

	public void actionPerformed( ActionEvent event ) {
		// This means it'll do shit when a button gets pressed.
		Object source = event.getSource();

		if( source == connectButton ) {
			// connect and things
			// /*  Commented out multiline comments for lab demonstration if this doens't work
			ipAddress = ipAddressField.getText();
			portNumber = Integer.parseInt( portField.getText() );
			try { sock = new Socket( ipAddress, portNumber ); }
			catch ( IOException e) { System.err.println(e); }
			

			if ( sock.isConnected() ) {
				output.append("Connection Established\n");
				output.append("IP: " + sock.getInetAddress() + "\n");
				output.append("Port: " + sock.getPort() + "\n");
			}
			// */
		}
		else if( source == submitTextButton ) {
			// submit text and stuff
			/*************************************************************
				L A B 2  C O D E --  U N C O M M E N T   T O   S H O W
			*************************************************************/
			// output.append( clientSubmissionField.getText() + "\n");

			// Comment out for Demo
			submitToServer( sock, clientSubmissionField.getText() );
		}
		else if( source == clearServerOutputPaneButton ) {
			// clear that screen and shit
			output.setText("");
		}
		else
			output.append( "Unknown Event" );
	}

	/*******************************************
		End GUI Code and onto Logical Code 
	*******************************************/

	private void connectToServer( ) {

	}
	private void submitToServer( Socket socket, String string ) {
		try {
			InputStream inStream   = socket.getInputStream();
			DataInputStream in     = new DataInputStream( inStream );
			OutputStream outStream = socket.getOutputStream(); 
			DataOutputStream out   = new DataOutputStream( outStream );

			out.writeUTF( string );
			String line;

			if ( (line = in.readUTF()) != null )
				output.append(line + "\n");
		} catch ( IOException e ) { System.err.println(e); }
	}

	public static void main(String[] args) {
		ClientGUI main = new ClientGUI();
	}
}