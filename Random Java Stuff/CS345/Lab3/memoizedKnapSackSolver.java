class memoizedKnapSackSolver {
    public int maxProfit;
    public boolean DEBUG;
    public int[] profitMemoized;
    public int[] weights;
    public static final int EMPTY = -42;
    
    public memoizedKnapSackSolver(){
        int maxProfit = -99;
        DEBUG=false;
    }
    public memoizedKnapSackSolver( int size ){
            int maxProfit = -99;
            DEBUG=true;
            profitMemoized = new int[size];
            for( int i = 0; i < size; i++ )
                profitMemoized[i] = EMPTY;
            weights = new int[size];
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
   private int memoizedSolution( int weight ) {		
        int d = 0;

        if( weight < 0 ) return -999;
        else if ( weight < 2 ) return 0;
        else {
            int a, b, c, w = 0;

            if( weight - 8 < 0 ) a = -999;
            else { 
                if( profitMemoized[weight - 8] == EMPTY ) profitMemoized[weight - 8] = recursiveSolution( weight - 8 );
                if ( DEBUG ) System.out.println(recursiveSolution( weight - 8 ));
                a = 9 + profitMemoized[weight - 8]; 
            }
            if( weight - 7 < 0 ) b = -999;
            else { 
                if( profitMemoized[weight - 7] == EMPTY ) profitMemoized[weight - 7] = recursiveSolution( weight - 7 );
                if ( DEBUG ) System.out.println(recursiveSolution( weight - 7 ));
                b = 7 + profitMemoized[weight - 7]; 
            }
            if( weight - 6 < 0 ) c = -999;
            else {
                if( profitMemoized[weight - 6] == EMPTY ) profitMemoized[weight - 6] = recursiveSolution( weight - 6 );
                if ( DEBUG ) System.out.println(recursiveSolution( weight - 6 ));
                c = 5 + profitMemoized[weight - 6];
            }
            if( weight - 2 < 0 ) b = -999;
            else {
                if( profitMemoized[weight - 2] == EMPTY ) profitMemoized[weight - 2] = recursiveSolution( weight - 2 );
                if ( DEBUG ) System.out.println(recursiveSolution( weight - 2 ));
                d = 1 + profitMemoized[weight - 2];
            }

            int maxOfAandB = Math.max( a, b );
            int maxOfCandD = Math.max( c, d );
            int maxProfit = Math.max( maxOfAandB, maxOfCandD );
            if ( DEBUG ) {
                System.out.println("maxProfit = " + maxProfit);
            }


            if ( maxProfit == a && maxProfit != 0 ) { w = 8; }
            else if ( maxProfit == b && maxProfit != 0 ) { w = 7; }
            else if ( maxProfit == c && maxProfit != 0 ) { w = 6; }
            else if ( maxProfit == d && maxProfit != 0 ) { w = 2; }
            else { w = 2; }
            
            if ( DEBUG ) { System.out.println("w = " + w );}

            weights[ weight - 1 ] = w;
        }
        return maxProfit;
    }
        
        
    public static void main(String[] args) {
        memoizedKnapSackSolver main = new memoizedKnapSackSolver(17);
        int max = main.memoizedSolution(17);
        System.out.println("maxprofit outOfLoop= " + max);
    }
}