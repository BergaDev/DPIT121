/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hooman
 */

import java.util.Arrays;

class SumArray
{
    public static int SumArrayIt(int[] items)        
    {
        int sum=0;
        for( int i=0;i<items.length;i++)
        {
            sum+=items[i];
        }
        return sum;

    }
    public static int SumArrayRec(int[] items){
        int sum=0;
        // base case
        if(items.length == 1)
        {
            sum=items[0];
        }
        else if(items.length == 0) // exceptional case when input is null
        {
            sum=0;  
        }
        else   // general case
        {
            sum =items[items.length-1]+SumArrayRec(Arrays.copyOfRange(items, 0, items.length-1));
        }       
        return sum;
    }

    public static void main(String[] args) 
    {
    // sum of array
       int[] i= {1,2,3,4,5,6,7,8,9,10,11,22,33,44,55,66,77,88,99,66};
       int sum1 =SumArrayIt(i);
       int sum2 =SumArrayRec(i);
       int[] a={};
       //sum =Example0.SumArray(a, a.length);
       System.out.println("Iterative: "+sum1);
       System.out.println("Recursive: "+sum2);
    }
    
}
