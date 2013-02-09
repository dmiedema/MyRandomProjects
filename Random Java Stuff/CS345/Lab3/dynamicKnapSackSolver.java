class dynamicKnapSackSolver {
    public int maxProfit;
    public boolean DEBUG;
    public int[] profits;
    public int[] weights;
    
    public dynamicKnapSackSolver(){
        int maxProfit = -99;
        DEBUG=false;
    }
    public dynamicKnapSackSolver( int size ){
            int maxProfit = -99;
            DEBUG=true;
            profits = new int[size];
            weights = new int[size];
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
                // output.append("An object of weight " + weights[ i ] + " was used." + "\n");
                i -= weights[i];
                // i--;
            }
    } 
        
    public static void main(String[] args) {
        dynamicKnapSackSolver main = new dynamicKnapSackSolver(17);
        //int max = 
        main.dynamicProgrammingSolution(17);
        //System.out.println(max);
    }
}