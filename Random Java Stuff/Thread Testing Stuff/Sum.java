class Sum
{
        private long  sum;

Sum()
{
   sum =0;
}

Sum(long sum)
{
   this.sum = sum;
}


public synchronized long  getSum()
        {
           return sum;
        }

public synchronized void add2Sum(long addAmount)
{
sum += addAmount;

}

public synchronized void setSum(long sum)
{
this.sum = sum;

}



public static void main(String [] args)
{
   long test = 5;
   Sum mySum =  new Sum();
   mySum.setSum(8);
   System.out.println("Sum is "+mySum.getSum());
}
}