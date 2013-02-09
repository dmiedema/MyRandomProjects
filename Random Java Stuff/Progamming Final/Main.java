import java.util.Random;


public class Main
{
	public static final int RANGE = 100000;
	
	public static void main(String[] args) {
		SortedArrayList sal = new SortedArrayList();
		System.out.println("#####################################");
		System.out.println("----- NUMBERS I KNOW ( 10-100 ) -----");
		System.out.println("#####################################");
		for ( int i = 10; i < 101; i += 10 ) { 
			Data elem = new Data(i);
			sal.add(elem);
		}
		System.out.println("min:  " + sal.minValue() );
		System.out.println("max:  " + sal.maxValue() );
		System.out.println("size: " + sal.size() );
		Data findit = new Data( 30 );
		System.out.println( "found 30: " + sal.binarySearch(findit) );
		findit = new Data( 35 );
		System.out.println("find 35 (failure): " + sal.binarySearch(findit) );
		System.out.println("to String: \n" + sal.toString() );
		System.out.println("||||||||| trying to remove |||||||||||");
		findit = new Data( 30 );
		System.out.println("removing: " + findit.getKey() );
		sal.remove(findit);
		System.out.println("to String: \n" + sal.toString() );
		findit = new Data( 20 );
		System.out.println("removing: " + findit.getKey() );
		sal.remove(findit);
		System.out.println("to String: \n" + sal.toString() );
		
		
		sal.clear();
		/*
		System.out.println("#####################################");
		System.out.println("----------  RANDOM NUMBERS  ---------");
		System.out.println("#####################################");
		Random randint = new Random();
		// FILLERUP
		for ( int i = 1; i < 1101; i++ ) {
			int addme = randint.nextInt(RANGE);
			Data addage = new Data( addme );
			sal.add(addage);
		}
		
		System.out.println("min: " + sal.minValue() );
		System.out.println("max: " + sal.maxValue() );
		System.out.println("size: " + sal.size() );
		System.out.println("to String: \n" + sal.toString() );
		*/
		
		findit = new Data( 20 );
		sal.add(findit);
		findit = new Data( 30 );
		sal.add(findit);
		findit = new Data( 5 );
		sal.add(findit);
		findit = new Data( 100 );
		sal.add(findit);
		findit = new Data( 15 );
		sal.add(findit);
		System.out.println("to String: \n" + sal.toString() );
		findit = new Data( 100 );
		sal.remove(findit);
		System.out.println("to String: \n" + sal.toString() );
	}
}
