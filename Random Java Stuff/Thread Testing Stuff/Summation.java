class Summation implements Runnable{
private int index;
private long upper;
private long lower;
private long maxValue2Add;
private Sum sumValue;
private Sum totalTime;

public Summation()
{
  totalTime = new Sum();
  sumValue = new Sum();
}

public Summation(int index, long lower, long upper,long maxValue2Add,Sum sumValue,Sum totalTime)
{ this.index = index;
  this.upper = upper;
  this.lower = lower;
  this.sumValue = sumValue;
  this.totalTime  =totalTime;
  this.maxValue2Add = maxValue2Add;
}



public long addIt(long lower, long upper)
{
long sum =0;
long startTime;
long endTime;
System.out.println("lower "+lower+" upper "+ upper);
startTime = System.nanoTime();


for (long i = lower; i<=upper; i++)
{
     //startTime=System.nanotime();
     //System.out.println(System.nanoTime());
    if (i<=maxValue2Add)
           sum+=i;
     //System.out.println("sum is "+sum);
 }
sumValue.add2Sum(sum);
endTime=System.nanoTime();
totalTime.add2Sum(endTime-startTime);
try
   {
//      Thread.sleep((long)(100L-index));

}//try
catch(Exception e)
{
System.out.println(e);
}
System.out.println("addIt index "+index);
System.out.println(sumValue.getSum());
System.out.println("Elapsed time "+(endTime-startTime));
return 0;
}


public void run()
{
addIt(lower,upper);
}


public static void main(String [] args)
{
    //The number of threads you want to use to solve the problem.
    int magicNumber = 3;
    // if ( args.length == 0 ) magicNumber = 12;
    // else magicNumber = Integer.parseInt(args[0]);

    Sum sumObject = new Sum();
    Sum timeObject = new Sum();
    long  LOWER = 1; //Beginning of sequence
    // long  UPPER =10000000; // End of sequence
    long UPPER;
    if ( args.length == 0 ) UPPER = 10000000;
    else UPPER = Integer.parseInt(args[0]);
    //   1+2+3+4+5+6+...+9999  LOWER is 1 UPPER is 9999
    long maxValue2Add = UPPER;
      

  long INCREMENT = (UPPER - LOWER) / magicNumber;
  // Thread [] threadArray = new Thread[magicNumber+1];
  Thread [] threadArray = new Thread[magicNumber+10];
//    for(int i =0; i <  magicNumber; i ++)
int i = (int)LOWER;
int threadCount=0;
while(i <= UPPER)
   {
          threadArray[threadCount] = new Thread(new Summation(i,i,i+INCREMENT-1,maxValue2Add,sumObject,timeObject));
          threadArray[threadCount].start();
           i+=INCREMENT;
          ++threadCount;

    }

System.out.println("threadCount is "+threadCount);
      try{
       for(int j =0;j<threadCount;j++)
           {
             System.out.println("Joining thread "+j);
             threadArray[j].join();
            }//for
             }//try
             catch(Exception e)
             {
                System.out.println("Exception is "+e);
             }//catch
              System.out.println(" Sum is "+sumObject.getSum());
              System.out.println(" Total Time is "+timeObject.getSum());






}//main
}//Summation class