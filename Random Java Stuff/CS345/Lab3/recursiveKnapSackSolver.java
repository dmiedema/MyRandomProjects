class recursiveKnapSackSolver {
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
    public static void main(String[] args) {
        recursiveKnapSackSolver main =  new recursiveKnapSackSolver();
        int print = main.recursiveSolution( 17 );
        System.out.println(print);
    }
}