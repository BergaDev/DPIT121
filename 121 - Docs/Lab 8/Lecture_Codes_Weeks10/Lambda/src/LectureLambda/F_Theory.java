/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectureLambda;

/**
 *
 * @author hooman
 */

import java.util.*;

class Program 
{
    public static void main(String[] args) 
    {
        
        IntFunction f = x -> x + 3;
        int r1 = f.apply(10);
        System.out.println ("result 1: " + r1);
        
        IntFunction2 m = (x,y) -> x + y;
        int r2 = m.apply (10,5);
        System.out.println ("result 2: " + r2);
        
        int z = 7;
        IntFunction h = x -> x + z;
        int r3 = h.apply(10);
        System.out.println ("result 1: " + r3);    
        
        for (int i = 1; i < 10; i++) {
            int offset = i;
            IntFunction k = x -> x + offset;
            int rLoop = k.apply (10);
            System.out.println ("Loop result: " + rLoop);
            }
        
        ApplyIntFunction f1 = (a,x) -> a.apply (x);
        System.out.println ("Result f1 = " + f1.apply (x->2*x,10));
        
        ApplyIntFunction f2 = (b,x) -> b.apply (b.apply (x));
        System.out.println ("Result f2 = " + f2.apply (x->2*x,10));
       
        int[] vals = { 1, 2, 3, 4, 5 };
        IntFunction f3 = x -> x*2;
        map (f3, vals);
        for (int val : vals)
            System.out.println (val + " ");
        
        map (twice (f3), vals);
        for (int val : vals)
            System.out.println (val + " ");
        
        IntFunctionTransform twiceF = g -> (x -> g.apply (g.apply (x)));
        map (twiceF.apply (f), vals);
    }
    
    public static IntFunction twice (IntFunction f)
    {
        return x -> f.apply (f.apply (x));
    }
    
    public static void map (IntFunction f, int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
        {
            nums[i] = f.apply (nums[i]);
        }
    }
}

interface IntFunctionTransform
{
    IntFunction apply (IntFunction f);
}

interface ApplyIntFunction
{
    int apply (IntFunction a, int x);
}

interface IntFunction
{
    int apply (int a);
}

interface IntFunction2
{
    int apply (int a, int b);
}