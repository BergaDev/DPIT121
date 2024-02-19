/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectureLambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 *
 * @author Hooman
 */
public class C_ArrayStream 
{
    public static void main(String[] args) 
    {
        int[] vals = {3,4,5,6,7,2,3,4,5,10,9,8,2,1};
        Integer[] valsA = {3,4,5,6,7,2,3,4,5,10,9,8,2,1};
        Integer[] valsB = Arrays.stream(vals).boxed().toArray(Integer[]::new);


        System.out.printf("%n Original: %s %n", Arrays.asList(valsA)); 
        System.out.printf("%n Original: %s %n", Arrays.asList(valsB)); 
        System.out.printf("%n Sorted: %s %n", 
            Arrays.asList(valsA).stream()
            .sorted()
            .collect(Collectors.toList())); 

        System.out.printf("%n Sorted: %s %n",
            Arrays.stream(vals) 
            .sorted()
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new)));  

        System.out.printf("%n Sorted: %s %n",
            Arrays.stream(vals) 
            .sorted()
            .boxed()
            .collect(Collectors.toList())); 

        List<Integer> g =  Arrays.stream(valsA) 
            .filter(v-> v>4)
            .collect(Collectors.toList());  
        System.out.printf("%n Value > 4: %s %n",g);    

        List<Integer> g1=  Arrays.stream(valsA) 
            .filter(v-> v>4)
            .sorted()
            .collect(Collectors.toList());  
        System.out.printf("%n Value > 4: %s %n",g1); 

        List<Integer> g2=  Arrays.stream(valsA) 
            .filter(v-> v>4)
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());  
        System.out.printf("%n Value > 4: %s %n",g2); 
        
        System.out.printf("%n Value > 4: %s %n",
                g2.stream()
                .sorted()
                .collect(Collectors.toList()));
        
        


        //------------------------------
        String[] colors= {"Black","Blue","Green","Yellow","Black","Green", "White"};
        System.out.printf("%n Original String: %s %n",  Arrays.asList(colors)); 
        String string="";

        // to uper case
        System.out.printf("%n String in uppercase: %s %n",
            Arrays.stream(colors)
            .map(String::toUpperCase)
            .sorted()       
            .collect(Collectors.toCollection(ArrayList::new)));  

        System.out.printf("%n String > n: %s %n",
            Arrays.stream(colors)
            .filter(s->s.compareToIgnoreCase("n")>0)
            .sorted(String.CASE_INSENSITIVE_ORDER)       
             //.collect(Collectors.toCollection(ArrayList::new)) 
            .collect(Collectors.toList()));
        
        System.out.printf("%n String > a sorter descending order: %s %n",
            Arrays.stream(colors)
            .filter(s->s.compareToIgnoreCase("a")>0)
            .sorted(String.CASE_INSENSITIVE_ORDER.reversed())       
             //.collect(Collectors.toCollection(ArrayList::new)) 
            .collect(Collectors.toList())); 
        
        System.out.printf("%n String contain 'ee' : %s %n",
              Arrays.stream(colors)
             .filter(s->s.contains("ee"))
             .sorted(String.CASE_INSENSITIVE_ORDER.reversed())       
             //.collect(Collectors.toCollection(ArrayList::new)) 
             .collect(Collectors.toList())); 

        System.out.printf("%n String contain 'A' : %s %n",
            Arrays.stream(colors)
            .filter(s->s.toUpperCase().contains("A"))
            .sorted(String.CASE_INSENSITIVE_ORDER.reversed())       
             //.collect(Collectors.toCollection(ArrayList::new)) 
            .collect(Collectors.toList())); 

    }    
}
