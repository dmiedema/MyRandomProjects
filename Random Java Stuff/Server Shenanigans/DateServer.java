/* Date Server */

/* Returns a random word to the client*/
/* Listens on Port 65534 */

import java.net.*; 
import java.io.*; 
import java.util.*;
import java.util.Collections;



public class DateServer { 	

	public static final boolean DEBUG = false;

	public static String randomWord() {
		Random generator = new Random();
		String string = "";
		//123 - 97 is a - z lower case
		int randomLength = generator.nextInt(10) + 5;
		for ( int i = 0; i < randomLength; i ++ ) {
			int randomChar = generator.nextInt(26) + 97;
			char c = (char) randomChar;
			string = string + c;
		}
		return string;
	}

	/* totally stole this shuffle code from online */
	/* and not using it
	public static String shuffleString( String shuffleMe ) {
		if (shuffleMe.length()<=1)
            return shuffleMe;
     
        int split=shuffleMe.length()/2;
     
        String temp1=shuffle(shuffleMe.substring(0,split));
        String temp2=shuffle(shuffleMe.substring(split));
     
        if (Math.random() > 0.5) 
            return temp1 + temp2;
        else
            return temp2 + temp1;
	}
	*/

	/* totally stole THIS code too, yay StackOverflow *
	public static String shuffleString ( String input ) {
		// public void shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for( char c:input.toCharArray() ){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        //System.out.println(output.toString());
        return output.toString();
    // }
	}
	More theft from the internet... */
	public static String shuffleString ( String s ) {
	 String shuffledString = ""; 

        while (s.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * s.length());
            char c = s.charAt(index);
            s = s.substring(0,index)+s.substring(index+1);
            shuffledString += c;
        }

        return shuffledString;

    }
	
	public static void main(String[] args) { 
		try { 
			ServerSocket sock = new ServerSocket(65534); // now listen for connections 
			while (true) { Socket client = sock.accept(); 
				InputStream in = client.getInputStream();
				BufferedReader bin = new BufferedReader(new InputStreamReader(in));	
				DataInputStream dis = new DataInputStream( in );
				OutputStream out = client.getOutputStream();
				DataOutputStream dos = new DataOutputStream( out );
				// New Code to shuffle string
				while ( client.isConnected() ) {
					String outputLine = dis.readUTF();
					if (DEBUG) System.out.println("OutputLine : " + outputLine);
					if ( outputLine.equals("close") ) {
						client.close();
						System.out.println("Told to close");
					}
					if ( outputLine == null ) {
						System.out.println("No input received");
						break;
					}
					outputLine = shuffleString( outputLine );
					dos.writeUTF( outputLine );
					System.out.println( outputLine );		
				}
			} 
		} 
		catch (IOException ioe) { System.err.println(ioe); } 
	}
}