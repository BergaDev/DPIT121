/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hooman
 */

class Basics {
    
    public static int sumIterative(int n)
    {
        int total=0;
        for (int i=1;i<=n;i++)
        {
            total+=i;
        }
        return total;
    }
    public static int sumRecursive(int n)
    {
        if (n==0)
            return 0;
        return sumRecursive(n-1)+n;
    }
    public static int fib(int n)
    {
        if (n==0 ||n==1)
            return 1;
        return fib(n-1)+fib(n-2);
    }
    public static int factorialRec(int n)
    {
        if (n==0 ||n==1)
           return 1;
        return n*factorialRec(n-1);
    }
    public static int factorialIterative(int number)//iterative
    {
        int result =1;
        for(int i=number; i>=1; i--)
        {
            result *=i;
        }
        
        return result;
    }
    public static void main ( String [] args)
    {
        System.out.println("Iterative Sum:" + sumIterative(5));
        System.out.println("Recursive Sum:"  + sumRecursive(5));
        System.out.println("fib(10) ="+fib(10));
        System.out.println("fib(20) ="+fib(20));
        System.out.println("fib(40) ="+fib(40));
        //System.out.println("fib(1000) ="+fib(1000)); //O(2^n) NP Hard not feasible 
        System.out.println("Recursive: 10! = "+factorialRec(10));
        System.out.println("Iterative: 10! = "+factorialIterative(10));
        System.out.println("15! = "+factorialRec(15));
        System.out.println("20! = "+factorialRec(20)); // too big for int and it show a negative number.
        System.out.println("30! = "+factorialRec(30) ); // to big for int (32 bits) and wrong output
    }
    
}
