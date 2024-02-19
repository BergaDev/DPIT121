
package LectureLambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Hooman
 */
public class B_IntStreamOperations 
{
    
    public static void main(String[] args) 
    {
        int[] vals = {3,4,5,6,7,2,3,4,5,10,9,8,2,1};
        System.out.println("Original values: ");
        IntStream.of(vals)
            .forEach(v->System.out.printf("%d ,", v));

        System.out.printf("%nCount: %d %n",IntStream.of(vals).count());
        System.out.printf("%nMin: %d %n",IntStream.of(vals).min().getAsInt());
        System.out.printf("%nMax: %d %n",IntStream.of(vals).max().getAsInt());
        System.out.printf("%nSum: %d %n",IntStream.of(vals).sum());
        System.out.printf("%nAverage: %f %n",IntStream.of(vals).average().getAsDouble() );


        // sum via reduce  
        System.out.printf("%n sum: %d %n",
            IntStream.of(vals)
            .reduce(0,(x,y)-> x + y));
        
        // sum via  reduction
        System.out.printf("%n sumv2: %d %n",
            IntStream.of(vals)
            .reduce((x,y)->x+y).getAsInt());

        // sum of y^2 
        System.out.printf("%n sum: %d %n",
            IntStream.of(vals)
            .reduce(0,(x,y)->x+y*y));
 
        // product of x
        System.out.printf("%n sum: %d %n",
            IntStream.of(vals)
            .reduce(1,(x,y)->x*y));     

        System.out.print(" Filter: ");
            IntStream.of(vals)
            .filter(v->v%2==0).sorted()
            .forEach(v->System.out.printf("%d ,", v));
        System.out.println();               

        System.out.print(" Odd value multiplied by 10: ");
            IntStream.of(vals)
            .filter(v->v%2!=0)
            .map(v->v*10)
            .sorted()
            .forEach(v->System.out.printf("%d ,", v));          
        System.out.println();    

        System.out.printf("%n sum from 1 to 9: %d %n",
            IntStream.range(1, 10).sum());


        System.out.printf("%n sum from 1 to 10: %d %n",
            IntStream.rangeClosed(1, 10).sum()); 
        
        int[] values ={3,1,4,2,6,5,7,8,9,10};
        System.out.println(IntStream.of(values).reduce(0, (x,y) -> x+y));

        System.out.println(IntStream.of(values).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList()));

        List<Integer> a = IntStream.of(values).boxed().collect(Collectors.toList());
        ArrayList<Integer> b= new ArrayList<Integer>(a);
        System.out.println(IntStream.of(values).boxed().filter((x)->x%2==0).findAny().get());
    }     
}
