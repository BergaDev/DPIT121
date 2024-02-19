
package LectureLambda;

import java.util.*; 
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
//import java.util.Stream;
  
import java.util.stream.Stream;

class D_StringStreams 
{ 
    public static void main(String args[]) 
    { 
        // create a list of strings 
        List<String> names = Arrays.asList("Geek","GeeksQuiz","g111","QA11","Geek2"); 
        // declare the predicate type as string and use lambda expression to create object 
        Predicate<String> p = (s)-> s.startsWith("G");
        Predicate<String> p1 = (s)-> s.contains("a");
        
        // Traditional coding - Iterate through the list 
        for (String st: names) 
        { 
            // call the test method 
            if (p.test(st)) 
                System.out.println("p: "+st); 
        }
        //stream 
        names.stream().filter(p).forEach(System.out::println);
        
        Function<String,String> fun1 =  (ss)->{return ss.substring(0, 3);};
        names.stream().map(fun1).forEach(System.out::println);
        
        names.stream().map(x-> x.substring(2,3)).forEach(System.out::println);
        
        List<Integer> lens=names.stream().map(x-> x.length()).collect(Collectors.toList());
        lens.forEach(System.out::println);
        
        
        String[] data={"as","ba","ca","ds","ee"};
        String key="a";
        Stream<String> stream1 = Stream.of(data);
        stream1.filter(v->v.contains(key)).map(m->m.toUpperCase()).forEach(v->System.out.println("Stream: "+v));
        List<String> list1= Stream.of(data).collect(Collectors.toList());
       
        //Predicate
        Predicate<String> p2= (ss) ->
        {
            ss.substring(1,1);
            return (ss.toLowerCase().charAt(1)=='a')? true:false;
        };
        
        for (String st: data) 
        { 
            // call the test method 
            if (p2.test(st)) 
                System.out.println("p2: "+ st); 
        } 
        Arrays.stream(data).filter(p2).forEach(System.out::println);
        
        //Function
        Function<String,Boolean> p3 = (ss)->
        {
            ss=ss.substring(1,2);
            return (ss.toLowerCase().charAt(0)=='a')? true:false;
        }; 
        
        
        for (String st: data)
        { 
            // call the apply method 
            if (p3.apply(st)) 
                System.out.println("p3: "+ st); 
        }
        Arrays.stream(data).map(p3).forEach(System.out::println);   
    } 
}