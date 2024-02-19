package LectureLambda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hooman
 */

import java.util.*;

class Program1 
{
    public static void main(String[] args) 
    {
        List<Integer> numsA = Arrays.asList (1, 2, 3, 4, 5);
        int result1 = reduceByAdd (numsA);
        System.out.println (result1);
        
        int result2 = reduceBy (numsA, (x,y)->x*y);
        System.out.println (result2);
        
        Function2<Integer> reduceB = (x,y)->x*y;
        int result3 = reduceB.reduce (1, numsA);
        System.out.println (result3);
    }
    
    public static int reduceBy (List<Integer> vals, IntFunction21 f)
    {
        if (vals.isEmpty ())
            return 0;
        
        if (vals.size () == 1)
            return vals.get (0);
        
        int subTotal = vals.get (0);
        for (int i = 1; i < vals.size (); i++)
            subTotal = f.apply (subTotal, vals.get (i));
        
        return subTotal;
    }
    
   public static int reduceByAdd (List<Integer> vals)
    {
        if (vals.isEmpty ())
            return 0;
        
        if (vals.size () == 1)
            return vals.get (0);
        
        int subTotal = vals.get (0);
        for (int i = 1; i < vals.size (); i++)
            subTotal += vals.get (i);
        
        return subTotal;
    }
    
    
    public static void stage2 ()
    {
        List<Integer> numsA = Arrays.asList (1, 2, 3, 4, 5);
        
        List<Integer> result1 = filterBy (numsA, x->x > 2);
        System.out.println (result1);
        
        List<String> names = Arrays.asList ("joe", "jill", "sue", "jack", "kim");
        Predicate<String> jNames = x->x.startsWith ("j");
        List<String> result2 = jNames.filterBy (names);
        System.out.println (result2);
    }
    
    public static void stage1 ()
    {
        List<Integer> numsA = Arrays.asList (1, 2, 3, 4, 5);
        ArrayList<Integer> numsB = new ArrayList<>(numsA);
        
        numsB.replaceAll (x->x*2);
        numsB.removeIf (x->x < 5);
        numsB.forEach (System.out::println);
    } 
    
    public static List<Integer> filterBy (List<Integer> nums, IntPredicate p)
    {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer num : nums) 
        {
            if (p.predicate(num))
                result.add (num);
        }
        return result;
    }
 }

interface IntPredicate
{
    boolean predicate (int val);
}

interface Predicate<T>
{
    boolean predicate (T val);
   
    public default List<T> filterBy (List<T> nums)
    {
        List<T> result = new ArrayList<T>();
        for (T num : nums) {
            if (predicate(num))
                result.add (num);
            }
        return result;
    }
}


interface IntFunction21
{
    Integer apply (Integer a, Integer b);
}

interface Function2<T>
{
    T apply (T a, T b);
    
    // The 'reduceBy' method has been moved into this interface and made 
    // generic in the same way as was done for filterBy. Again all the
    // 'int' types have been changed to 'T'.
    //
    // A default value parameter was needed as '0' is an integer, and this
    // interface could be for any type 'T'.
        
    default T reduce (T emptyValue, List<T> vals)
    {
        if (vals.isEmpty ())
            return emptyValue;
        
        if (vals.size () == 1)
            return vals.get (0);
        
        T subTotal = vals.get (0);
        for (int i = 1; i < vals.size (); i++)
            subTotal = apply (subTotal, vals.get (i));
        
        return subTotal;
    }
}