/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigInteger;

/**
 *
 * @author Hooman
 */
class FibonaciBigInteger 
{
    
    private static BigInteger TWO = BigInteger.valueOf(2);
    
    public static BigInteger fibonacci(BigInteger number)
    {

        if (number.equals(BigInteger.ONE) || number.equals(BigInteger.ZERO))
        {
            return number;  
        }
        else
        {
           // f(n-1)+f(n-2)
            return fibonacci(number.subtract(BigInteger.ONE)).add(fibonacci(number.subtract(TWO)));
        }        
    
    }
     
    public static void main(String[] args) 
    {   
        for (int counter=0;counter <=40; counter++)
        {
            System.out.printf("Fibonacci of %d! = %d%n", counter,fibonacci(BigInteger.valueOf(counter))); 
        }  
    }
    
}
