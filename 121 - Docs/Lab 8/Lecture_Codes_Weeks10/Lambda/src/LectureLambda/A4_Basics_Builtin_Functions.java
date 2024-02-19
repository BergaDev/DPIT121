
package LectureLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author hooman
 */
public class A4_Basics_Builtin_Functions 
{
    
    public static void main(String[] args) 
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        list.add(4);
        list.add(6);
        list.add(14);
        list.add(60);
        list.add(41);
        list.add(3);
        list.add(1);
        list.add(5);

        
        System.out.println("original list");
        list.stream().forEach(System.out::println);
        //Predicate and test
        Predicate<Integer> p1=x-> x>4;
        boolean pp=p1.test(6);
        System.out.println("Filtered list>4");
        list.stream().filter(p1).forEach(System.out::println);
        List<Integer> list2=list.stream().filter(p1).collect(Collectors.toList());
        System.out.println("Filtered list>4");
        list2.forEach(System.out::println);
        System.out.println("Filtered list even numbers");
        list.stream().filter(x->x%2==0).forEach(System.out::println);
    
        Predicate<Integer> p2=new Predicate<Integer>()
        {
            @Override
            public boolean test(Integer x)
            {
                if (x>20)
                   return true;
                return false;    
            }
        };
        System.out.println("Filtered list>20");
        list.stream().filter(p2).forEach(System.out::println);
    
        
        
        //Function and apply
        Function<Integer,Integer> f1=x->x*2;
        int d1=f1.apply(2);
        System.out.println("Mapped list *2");
        list.stream().map(f1).forEach(System.out::println);
        List<Integer> list3=list.stream().map(f1).collect(Collectors.toList());
        System.out.println("Mapped list +2");
        list.stream().map(x->x+2).forEach(System.out::println);
        
        Function<Integer,Integer> f2=new Function<Integer,Integer>()
        {
            @Override
            public Integer apply (Integer i )
            {
                return i*3-2;
            }
        };
        System.out.println("Mapped list *3-2");
        list.stream().map(f2).forEach(System.out::println);
        
        
        
        
        //BinaryOperator and apply
        BinaryOperator<Integer> b1=(x,y)->x+y;
        int d2=b1.apply(1,3);
        System.out.println("Sum of the list");
        System.out.println(list.stream().reduce(0,b1));
        int sum=list.stream().reduce(0,b1);
        System.out.println("Product of the list");
        System.out.println(list.stream().reduce(1,(x,y)->x*y));
        
        BinaryOperator<Integer> b2=new BinaryOperator<Integer>()
        {
            @Override
            public Integer apply(Integer x,Integer y)
            {
                Integer out=0;
                out=x*y;
                return out;
            } 
        };
        
        // Consumer and accept
        
        Consumer<Integer> c1= x->System.out.println("out: "+x.toString());
        c1.accept(5);
        System.out.println("Print the list");
        list.stream().forEach(c1);
        
        Consumer<Integer> c2= new Consumer<Integer>()
        {
            @Override
            public void accept(Integer x)
            {
                System.out.println("Consumer: "+x.toString());  
            }
        };
        System.out.println("Print the list");
        list.stream().forEach(c2);
        System.out.println("filtered list mapped *2 sorted");
        list.stream().filter(x->x%2!=0).map(x->x*2).sorted().forEach(System.out::println);

    }
}
