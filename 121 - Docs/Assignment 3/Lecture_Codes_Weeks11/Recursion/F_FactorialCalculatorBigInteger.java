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

class FactorialBigInteger 
{
    
     public static BigInteger factorial2(BigInteger number)
    {

        if (number.compareTo(BigInteger.ONE) <=0)
        {
            return BigInteger.ONE;  
        }
        else
        {
            return number.multiply( factorial2(number.subtract(BigInteger.ONE)));
        }        
    
    }
      
    
    public static void main(String[] args) 
    {
        for (int counter=0;counter <=50; counter++)
        {
           System.out.printf("%d! = %d%n", counter, factorial2(BigInteger.valueOf(counter))); 
        }
       
    }
    
}
